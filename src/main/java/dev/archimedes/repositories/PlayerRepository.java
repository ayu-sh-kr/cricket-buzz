package dev.archimedes.repositories;

import dev.archimedes.dtos.PlayerResponseDto;
import dev.archimedes.entities.Player;
import dev.archimedes.projections.PlayerProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
    boolean existsByNameAndDateOfBirth(String name, LocalDate dateOfBirth);

    @Query("select m.player from MATCH m where m.matchDate = ?1 and m.stadium = ?2 order by m.player.country")
    List<Player> findPlayerByDateAndStadium(LocalDate matchDate, String stadium);


    @Query("SELECT p.name as name, m.score as score, m.stadium as stadium, m.matchDate as matchDate, m.playingTeam as playingTeam, m.playedAgainst as playedAgainst " +
            "FROM PLAYER p JOIN p.matches m " +
            "WHERE m.matchDate = :date AND m.stadium = :stadium order by p.country")
    List<PlayerProjection> findPlayerProjections(@Param("date") LocalDate date, @Param("stadium") String stadium);

    @Query("select new dev.archimedes.dtos.PlayerResponseDto(p.id, p.name, m.score, m.stadium, m.matchDate, p.country)" +
            "FROM PLAYER p JOIN p.matches m " +
            "WHERE m.matchDate = :date AND m.stadium = :stadium order by p.country")
    List<PlayerResponseDto> findPlayerResponseDto(@Param("date") LocalDate date, @Param("stadium") String stadium);
}
