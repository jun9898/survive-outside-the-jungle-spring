package study.surviveoutsidethejunglespring.algorithm.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import study.surviveoutsidethejunglespring.algorithm.entity.Algorithm;
import study.surviveoutsidethejunglespring.guild.entity.Guild;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlgorithmTypeDto {

	private Long guildInfoId;
	private Long algorithmForumId;
	private LocalDateTime registrationAt;
	private String algorithmType;

	public static AlgorithmTypeDto of(Algorithm algorithm, Guild guild) {
		return AlgorithmTypeDto.builder()
			.guildInfoId(guild.getGuildInfoId())
			.algorithmForumId(guild.getAlgorithmForumId())
			.registrationAt(algorithm.getRegistrationAt())
			.algorithmType(algorithm.getAlgorithmType())
			.build();
	}
}
