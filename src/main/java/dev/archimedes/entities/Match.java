package dev.archimedes.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.archimedes.enums.MatchType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity(name = "MATCH")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MATCH_ID")
    private int id;

    @Column(name = "SCORE")
    private int score;

    @Column(name = "WICKET_TAKEN")
    private int wicket;

    @Column(name = "STADIUM")
    private String stadium;

    @Column(name = "MATCH_DATE")
    @Temporal(TemporalType.DATE)
    private LocalDate matchDate;

    @Column(name = "PLAYER_TEAM")
    private String playingTeam;

    @Column(name = "OPPONENT")
    private String playedAgainst;

    @Enumerated(EnumType.STRING)
    @Column(name = "MATCH_CATEGORY")
    private MatchType matchType;

    @ManyToOne
    @JoinColumn(name = "PLAYER_ID")
    @JsonIgnore
    private Player player;

    public Match(int score, int wicket, String stadium, LocalDate matchDate, Player player) {
        this.score = score;
        this.wicket = wicket;
        this.stadium = stadium;
        this.matchDate = matchDate;
        this.player = player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Match match = (Match) o;

        if (score != match.score) return false;
        return stadium.equals(match.stadium);
    }

}
