package study.surviveoutsidethejunglespring.guild.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.classic.Logger;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import study.surviveoutsidethejunglespring.guild.dto.GuildSetDto;
import study.surviveoutsidethejunglespring.guild.service.GuildService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class GuildController {

	private final GuildService guildService;

	@PostMapping("/join-server")
	public String setGuild(@RequestBody GuildSetDto guildSetDto) {
		log.info("guildSetDto: {}", guildSetDto.toString());
		guildService.setGuild(guildSetDto);
		return "It has been set up successfully.";
	}
}

