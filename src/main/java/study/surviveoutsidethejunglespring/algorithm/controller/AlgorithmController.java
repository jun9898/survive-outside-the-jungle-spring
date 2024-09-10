package study.surviveoutsidethejunglespring.algorithm.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import study.surviveoutsidethejunglespring.algorithm.dto.AlgorithmForumSetDto;
import study.surviveoutsidethejunglespring.algorithm.dto.AlgorithmTypeDto;
import study.surviveoutsidethejunglespring.algorithm.dto.RegistrationDto;
import study.surviveoutsidethejunglespring.algorithm.service.AlgorithmService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AlgorithmController {

	private final AlgorithmService algorithmService;

	@GetMapping("/bot-test")
	public String botTest() {
		return "bot-test";
	}

	@PostMapping("/set-algorithm-forum")
	public String setAlgorithmForum(@RequestBody AlgorithmForumSetDto algorithmForumSetDto) {
		algorithmService.setAlgorithmForum(algorithmForumSetDto);
		return "It has been set up successfully.";
	}

	@PostMapping("/algorithm-registration")
	public String algorithmRegistration(@RequestBody RegistrationDto registrationDto) {
		log.info("registrationDto: {}", registrationDto.toString());
		algorithmService.saveWeeklyAlgorithms(registrationDto);
		return "It has been set up successfully.";
	}

	@GetMapping("/weekly-algorithms")
	public List<AlgorithmTypeDto> getWeeklyAlgorithms (@RequestParam Long guildInfoId) {
		return algorithmService.getWeeklyAlgorithms(guildInfoId);
	}

	@GetMapping("/today-algorithm")
	public AlgorithmTypeDto todayAlgorithm (@RequestParam Long guildInfoId) {
		return algorithmService.getTodayAlgorithm(guildInfoId);
	}

}
