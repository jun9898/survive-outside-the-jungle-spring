package study.surviveoutsidethejunglespring.guild.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import study.surviveoutsidethejunglespring.guild.dto.GuildSetDto;
import study.surviveoutsidethejunglespring.guild.service.GuildService;

@RestController
@RequiredArgsConstructor
public class GuildController {

	private final GuildService guildService;

	@PostMapping("/set-guild")
	public String setGuild(@RequestBody GuildSetDto guildSetDto) {
		guildService.setGuild(guildSetDto);
		return "It has been set up successfully.";
	}
}

