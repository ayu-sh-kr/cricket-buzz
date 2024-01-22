package dev.archimedes;

import dev.archimedes.entities.Match;
import dev.archimedes.entities.Player;
import dev.archimedes.enums.PlayerType;
import dev.archimedes.repositories.MatchRepository;
import dev.archimedes.repositories.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static dev.archimedes.enums.MatchType.*;

@SpringBootApplication
@RequiredArgsConstructor
public class CricketBuzzApplication implements ApplicationRunner {

    private final PlayerRepository playerRepository;

    private final MatchRepository matchRepository;


    public static void main(String[] args) {
        SpringApplication.run(CricketBuzzApplication.class, args);
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {

        Player viratKohli = new Player(
                "Virat Kohli",
                LocalDate.of(1988, Month.NOVEMBER, 5),
                "India", PlayerType.BATTER);

        Player rohitSharma = new Player(
                "Rohit Sharma",
                LocalDate.of(1987, Month.APRIL, 30),
                "India", PlayerType.BATTER);

        Match match = Match.builder()
                .score(209)
                .matchDate(LocalDate.of(2013, Month.NOVEMBER, 2))
                .stadium("Chinnaswami, Bengaluru")
                .wicket(0)
                .player(rohitSharma)
                .playingTeam("INDIA")
                .playedAgainst("AUSTRALIA")
                .matchType(ODI)
                .build();

        playerRepository.saveAll(
                List.of(
                        viratKohli,
                        rohitSharma
                )
        );

        matchRepository.save(match);

    }
}
