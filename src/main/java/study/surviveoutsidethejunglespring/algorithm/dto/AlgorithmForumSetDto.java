package study.surviveoutsidethejunglespring.algorithm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import study.surviveoutsidethejunglespring.algorithm.entity.Algorithm;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AlgorithmForumSetDto {
	private String guildId;
	private String forumId;

	public static Algorithm toEntity(AlgorithmForumSetDto dto) {
		return Algorithm.builder()
				.guildId(dto.getGuildId())
				.forumId(dto.getForumId())
				.build();
	}


}
