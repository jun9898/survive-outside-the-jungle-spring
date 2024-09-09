package study.surviveoutsidethejunglespring.guild.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import study.surviveoutsidethejunglespring.algorithm.entity.Algorithm;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Guild {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "guild_id")
	private Long id;

	private Long guildInfoId;

	@OneToMany(mappedBy = "guild")
	@Builder.Default
	private List<Algorithm> algorithms = new ArrayList<>();

	@Setter
	private Long algorithmForumId;

	// Helper method to add algorithm
	public void addAlgorithm(Algorithm algorithm) {
		algorithms.add(algorithm);
		algorithm.setGuild(this);
	}
}
