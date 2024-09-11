package study.surviveoutsidethejunglespring.scheduled;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import study.surviveoutsidethejunglespring.algorithm.dto.AlgorithmTypeDto;
import study.surviveoutsidethejunglespring.algorithm.repository.AlgorithmRepository;
import study.surviveoutsidethejunglespring.guild.entity.Guild;
import study.surviveoutsidethejunglespring.guild.repository.GuildRepository;

@Slf4j
@Component
@RequiredArgsConstructor
public class ScheduledTasks {

	private final GuildRepository guildRepository;
	private final AlgorithmRepository algorithmRepository;
	private final RestTemplate restTemplate;
	private static final String DISCORD_ALGORITHM_URL = "http://localhost:8081/today-algorithm";

	@Scheduled(cron = "0 0 8 * * ?")  // 매일 아침 8시에 실행
	// @Scheduled(cron = "*/30 * * * * ?")
	public void sendDailyData() {
		log.info("Sending daily data to Discord...");
		List<Guild> guilds = guildRepository.findAll();  // 모든 Guild 조회

		List<AlgorithmTypeDto> algorithmDtos = guilds.parallelStream()
			.map(guild -> {
				// 각 Guild에 대한 오늘 날짜의 Algorithm 조회 (Optional로 처리)
				return algorithmRepository.findByRegistrationAtAndGuild(LocalDate.now().atStartOfDay(), guild)
					.map(algorithm -> AlgorithmTypeDto.of(algorithm, guild))  // 값이 있으면 DTO로 변환
					.orElse(null);  // 값이 없으면 null 반환
			})
			.filter(Objects::nonNull)  // null인 값들을 필터링
			.toList();  // 결과를 리스트로 수집

		if (!algorithmDtos.isEmpty()) {
			try {
				// POST 요청에 사용할 데이터를 리스트로 준비
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);

				// DTO 리스트 자체를 request body로 전달
				HttpEntity<List<AlgorithmTypeDto>> request = new HttpEntity<>(algorithmDtos, headers);

				String response = restTemplate.postForObject(DISCORD_ALGORITHM_URL, request, String.class);
			} catch (Exception e) {
				System.err.println("Error sending data: " + e.getMessage());
			}
		} else {
			System.out.println("No algorithms to send today.");
		}
	}
}
