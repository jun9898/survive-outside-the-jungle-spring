package study.surviveoutsidethejunglespring.guild.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import study.surviveoutsidethejunglespring.guild.dto.GuildSetDto;
import study.surviveoutsidethejunglespring.guild.repository.GuildRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GuildService {

	private final GuildRepository guildRepository;

	@Transactional
	public void setGuild(GuildSetDto guildSetDto) {
		guildRepository.save(GuildSetDto.toEntity(guildSetDto));
	}
}
