package study.surviveoutsidethejunglespring.guild.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import study.surviveoutsidethejunglespring.guild.entity.Guild;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GuildSetDto {

	private Long guildId;
	private String guildName;

	public static Guild toEntity(GuildSetDto guildSetDto) {
		return Guild.builder()
			.guildInfoId(guildSetDto.getGuildId())
			.guildName(guildSetDto.getGuildName())
			.build();
	}
}
