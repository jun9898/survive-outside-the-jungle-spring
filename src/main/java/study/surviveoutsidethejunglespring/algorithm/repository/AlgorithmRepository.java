package study.surviveoutsidethejunglespring.algorithm.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import study.surviveoutsidethejunglespring.algorithm.entity.Algorithm;
import study.surviveoutsidethejunglespring.guild.entity.Guild;

public interface AlgorithmRepository extends JpaRepository<Algorithm, Long> {

	boolean existsByRegistrationAtAndGuild(LocalDateTime registrationAt, Guild guild);
	List<Algorithm> findByRegistrationAtBetweenAndGuild(LocalDateTime startDate, LocalDateTime endDate, Guild guild);
	Optional<Algorithm> findByRegistrationAtAndGuild(LocalDateTime today, Guild guild);

}
