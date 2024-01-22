package dev.archimedes.dtos;

import dev.archimedes.entities.Match;
import dev.archimedes.entities.Player;
import dev.archimedes.enums.MatchType;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
public class MatchDto {

    private int score;
    private int wicket;
    private String stadium;
    private String matchType;
    private String matchDate;
    private String playingTeam;
    private String playedAgainst;


    public static Match parse(MatchDto matchDto, Player player){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return Match.builder()
                .matchType(MatchType.valueOf(matchDto.getMatchType()))
                .matchDate(LocalDate.parse(matchDto.getMatchDate(), formatter))
                .playingTeam(matchDto.getPlayingTeam())
                .playedAgainst(matchDto.getPlayedAgainst())
                .player(player)
                .wicket(matchDto.getWicket())
                .score(matchDto.getScore())
                .stadium(matchDto.getStadium())
                .build();
    }

}
