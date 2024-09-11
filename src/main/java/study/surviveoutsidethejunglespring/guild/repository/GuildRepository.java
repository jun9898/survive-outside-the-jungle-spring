package study.surviveoutsidethejunglespring.guild.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import study.surviveoutsidethejunglespring.guild.entity.Guild;

public interface GuildRepository extends JpaRepository<Guild, Long> {
	Optional<Guild> findByGuildInfoId(Long guildInfoId);
}
