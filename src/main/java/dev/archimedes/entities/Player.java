package dev.archimedes.entities;

import dev.archimedes.enums.PlayerType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "PLAYER")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PLAYER_ID")
    private int id;

    @Column(name = "NAME")
    private String name;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_OF_BIRTH")
    private LocalDate dateOfBirth;

    @Column(name = "COUNTRY")
    private String country;

    @Enumerated(EnumType.STRING)
    private PlayerType playerType;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    private Set<Match> matches = new HashSet<>();

    public Player(String name, LocalDate dateOfBirth, String country, PlayerType playerType) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.country = country;
        this.playerType = playerType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (!name.equals(player.name)) return false;
        if (!dateOfBirth.equals(player.dateOfBirth)) return false;
        return country.equals(player.country);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + dateOfBirth.hashCode();
        result = 31 * result + country.hashCode();
        return result;
    }

    public void addMatch(Match match) {
        this.matches.add(match);
    }
}
