package study.surviveoutsidethejunglespring.algorithm.service;

import java.util.NoSuchElementException;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import study.surviveoutsidethejunglespring.algorithm.dto.AlgorithmForumSetDto;
import study.surviveoutsidethejunglespring.algorithm.dto.RegistrationDto;
import study.surviveoutsidethejunglespring.algorithm.repository.AlgorithmRepository;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AlgorithmService {

	private final AlgorithmRepository algorithmRepository;

	// 나중에 시간날때 Predicate를 사용해서 리팩토링 해보기
	@Transactional
	public void setAlgorithmForum(AlgorithmForumSetDto algorithmForumSetDto) {
		algorithmRepository.findByGuildId(algorithmForumSetDto.getGuildId())
			.ifPresentOrElse(
				algorithm -> algorithm.updateForumId(algorithmForumSetDto.getForumId()),
				() -> algorithmRepository.save(AlgorithmForumSetDto.toEntity(algorithmForumSetDto))
			);
	}

	@Transactional
	public void algorithmRegistration(RegistrationDto registrationDto) {
		algorithmRepository.findByGuildId(registrationDto.getGuildId())
			.ifPresentOrElse(
				algorithm -> algorithm.updateRegistration(registrationDto),
				() -> {
					throw new NoSuchElementException("There is no guildId in the database.");
				}
			);
	}
}
