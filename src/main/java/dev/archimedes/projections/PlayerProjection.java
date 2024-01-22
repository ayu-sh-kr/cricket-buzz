package dev.archimedes.projections;

import java.time.LocalDate;

public interface PlayerProjection {

//    @Value("#{T(dev.archimedes.utils.KeyEncoderUtil).encodeKey('#{target.id}')}")
//    String getEncodedId();
    String getName();
    int getScore();
    String getStadium();
    LocalDate getMatchDate();
    String getPlayingTeam();
    String getPlayedAgainst();
}
