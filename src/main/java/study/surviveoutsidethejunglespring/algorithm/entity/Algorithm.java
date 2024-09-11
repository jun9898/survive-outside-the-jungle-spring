package study.surviveoutsidethejunglespring.algorithm.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import study.surviveoutsidethejunglespring.guild.entity.Guild;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Algorithm {

	@Id
	@Column(name = "algorithm_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private LocalDateTime registrationAt;
	private String algorithmType;

	@Setter
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "guild_id")
	private Guild guild;


}
