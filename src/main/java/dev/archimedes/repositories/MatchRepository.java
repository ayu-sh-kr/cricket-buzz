package dev.archimedes.repositories;

import dev.archimedes.entities.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Integer> {

    @Query("select m from MATCH m where m.player.name = ?1")
    Match getMatchByPlayer_Name(String name);

    @Query("select m from MATCH m where m.matchDate = ?1 and m.player.id = ?2")
    List<Match> findMatchByDate(LocalDate matchDate, int id);

    boolean existsByStadiumAndMatchDateAndPlayer_Id(String stadium, LocalDate matchDate, int playerId);

}
