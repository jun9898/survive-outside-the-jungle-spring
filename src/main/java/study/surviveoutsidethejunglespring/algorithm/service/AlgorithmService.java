package study.surviveoutsidethejunglespring.algorithm.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import study.surviveoutsidethejunglespring.algorithm.dto.AlgorithmForumSetDto;
import study.surviveoutsidethejunglespring.algorithm.dto.AlgorithmTypeDto;
import study.surviveoutsidethejunglespring.algorithm.dto.RegistrationDto;
import study.surviveoutsidethejunglespring.algorithm.entity.Algorithm;
import study.surviveoutsidethejunglespring.algorithm.repository.AlgorithmRepository;
import study.surviveoutsidethejunglespring.guild.entity.Guild;
import study.surviveoutsidethejunglespring.guild.repository.GuildRepository;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AlgorithmService {

	private final AlgorithmRepository algorithmRepository;
	private final GuildRepository guildRepository;

	// 나중에 시간날때 Predicate를 사용해서 리팩토링 해보기
	@Transactional
	public void setAlgorithmForum(AlgorithmForumSetDto algorithmForumSetDto) {
		Guild invalidGuildId = guildRepository.findByGuildInfoId(algorithmForumSetDto.getGuildId())
			.orElseThrow(() -> new IllegalArgumentException("Invalid guild ID"));
		invalidGuildId.setAlgorithmForumId(algorithmForumSetDto.getForumId());
	}

	@Transactional
	public void saveWeeklyAlgorithms(RegistrationDto registrationDto) {
		Guild guild = guildRepository.findByGuildInfoId(registrationDto.getGuildInfoId())
			.orElseThrow(() -> new IllegalArgumentException("Invalid guild ID"));

		List<String> algorithmTypes = Arrays.asList(
			registrationDto.getMon(), registrationDto.getTue(), registrationDto.getWed(),
			registrationDto.getThu(), registrationDto.getFri(), registrationDto.getSat(),
			registrationDto.getSun()
		);

		LocalDate monday = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));

		LocalDate weekStartDate = monday;
		boolean thisWeekDataExists = IntStream.range(0, algorithmTypes.size())
			.anyMatch(i -> algorithmRepository.existsByRegistrationAtAndGuild(weekStartDate.plusDays(i).atStartOfDay(),
				guild));

		if (thisWeekDataExists) {
			monday = monday.plusWeeks(1);
		}

		LocalDate finalMonday = monday;
		IntStream.range(0, algorithmTypes.size())
			.mapToObj(
				i -> RegistrationDto.toEntity(finalMonday.plusDays(i).atStartOfDay(), algorithmTypes.get(i), guild))
			.forEach(algorithmRepository::save);
	}

	public List<AlgorithmTypeDto> getWeeklyAlgorithms(Long guildInfoId) {
		Guild guild = guildRepository.findByGuildInfoId(guildInfoId)
			.orElseThrow(() -> new IllegalArgumentException("Invalid guild ID"));

		LocalDate monday = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
		LocalDate sunday = monday.plusDays(6); // 현재 주의 마지막 날

		// 현재 주의 알고리즘 데이터를 한 번의 쿼리로 가져옴
		List<Algorithm> algorithms = algorithmRepository.findByRegistrationAtBetweenAndGuild(
			monday.atStartOfDay(), sunday.atStartOfDay(), guild);

		// 가져온 데이터를 DTO로 변환
		return algorithms.stream()
			.map((Algorithm algorithm) -> AlgorithmTypeDto.of(algorithm, guild))
			.toList();
	}

	public AlgorithmTypeDto getTodayAlgorithm(Long guildInfoId) {
		Guild guild = guildRepository.findByGuildInfoId(guildInfoId)
			.orElseThrow(() -> new IllegalArgumentException("Invalid guild ID"));
		return algorithmRepository.findByRegistrationAtAndGuild(LocalDate.now().atStartOfDay(), guild)
			.map((Algorithm algorithm) -> AlgorithmTypeDto.of(algorithm, guild))
			.orElseThrow(() -> new IllegalArgumentException("There is no algorithm data for today"));
	}
}
