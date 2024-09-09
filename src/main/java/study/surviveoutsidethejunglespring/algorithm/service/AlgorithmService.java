package study.surviveoutsidethejunglespring.algorithm.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import study.surviveoutsidethejunglespring.algorithm.dto.AlgorithmForumSetDto;
import study.surviveoutsidethejunglespring.algorithm.dto.RegistrationDto;
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
		Guild invalidGuildId = guildRepository.findById(algorithmForumSetDto.getGuildId())
			.orElseThrow(() -> new IllegalArgumentException("Invalid guild ID"));
		invalidGuildId.setAlgorithmForumId(algorithmForumSetDto.getForumId());
	}

	@Transactional
	public void saveWeeklyAlgorithms(RegistrationDto registrationDto) {
		Guild guild = guildRepository.findById(Long.parseLong(registrationDto.getGuildId()))
			.orElseThrow(() -> new IllegalArgumentException("Invalid guild ID"));

		List<String> algorithmTypes = Arrays.asList(
			registrationDto.getMon(), registrationDto.getTue(), registrationDto.getWed(),
			registrationDto.getThu(), registrationDto.getFri(), registrationDto.getSat(),
			registrationDto.getSun()
		);

		LocalDate monday = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));

		IntStream.range(0, algorithmTypes.size())
			.mapToObj(i -> RegistrationDto.toEntity(monday.plusDays(i).atStartOfDay(), algorithmTypes.get(i), guild))
			.forEach(algorithmRepository::save);
	}
}
