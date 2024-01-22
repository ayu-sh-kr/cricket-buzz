package dev.archimedes.dtos;

import dev.archimedes.utils.KeyEncoderUtil;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PlayerResponseDto {
    String id;
    String name;
    int score;
    String stadium;
    LocalDate matchDate;
    String country;

    public PlayerResponseDto(int id, String name, int score, String stadium, LocalDate matchDate, String country) {
        this.id = KeyEncoderUtil.encodeKey(id);
        this.name = name;
        this.score = score;
        this.stadium = stadium;
        this.matchDate = matchDate;
        this.country = country;
    }
}
