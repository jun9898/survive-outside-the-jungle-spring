package study.surviveoutsidethejunglespring.guild.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import study.surviveoutsidethejunglespring.guild.entity.Guild;

public interface GuildRepository extends JpaRepository<Guild, Long> {
}
