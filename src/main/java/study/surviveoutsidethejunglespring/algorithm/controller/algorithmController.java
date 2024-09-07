package study.surviveoutsidethejunglespring.algorithm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import study.surviveoutsidethejunglespring.algorithm.dto.RegistrationDto;

@Slf4j
@RestController
public class algorithmController {

	@GetMapping("/bot-test")
	public String botTest() {
		return "bot-test";
	}

	@PostMapping("/algorithm-registration")
	public String algorithmRegistration(@RequestBody RegistrationDto dto) {
		log.info(dto.toString());
		return "bot-test";
	}
}
