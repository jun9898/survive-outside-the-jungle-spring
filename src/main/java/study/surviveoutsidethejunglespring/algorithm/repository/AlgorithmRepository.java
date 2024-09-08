package study.surviveoutsidethejunglespring.algorithm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import study.surviveoutsidethejunglespring.algorithm.entity.Algorithm;

public interface AlgorithmRepository extends JpaRepository<Algorithm, Long> {

	Optional<Algorithm> findByGuildId(String guildId);

}
