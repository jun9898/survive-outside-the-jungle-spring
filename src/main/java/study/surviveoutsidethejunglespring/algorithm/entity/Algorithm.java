package study.surviveoutsidethejunglespring.algorithm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.surviveoutsidethejunglespring.algorithm.dto.RegistrationDto;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Algorithm {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(unique = true)
	private String guildId;

	private String forumId;
	private String mon;
	private String tue;
	private String wed;
	private String thu;
	private String fri;
	private String sat;
	private String sun;

	public void updateForumId(String forumId) {
		this.forumId = forumId;
	}

	public void updateRegistration(RegistrationDto registrationDto) {
		this.mon = registrationDto.getMon();
		this.tue = registrationDto.getTue();
		this.wed = registrationDto.getWed();
		this.thu = registrationDto.getThu();
		this.fri = registrationDto.getFri();
		this.sat = registrationDto.getSat();
		this.sun = registrationDto.getSun();
	}
}
