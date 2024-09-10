package study.surviveoutsidethejunglespring.algorithm.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import study.surviveoutsidethejunglespring.algorithm.entity.Algorithm;
import study.surviveoutsidethejunglespring.guild.entity.Guild;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDto {

	private Long guildInfoId;
	private String mon;
	private String tue;
	private String wed;
	private String thu;
	private String fri;
	private String sat;
	private String sun;


	public static Algorithm toEntity(LocalDateTime registrationAt, String algorithmType, Guild guild) {
		Algorithm build = Algorithm.builder()
			.registrationAt(registrationAt)
			.algorithmType(algorithmType)
			.build();
		guild.addAlgorithm(build);
		return build;
	}
}
