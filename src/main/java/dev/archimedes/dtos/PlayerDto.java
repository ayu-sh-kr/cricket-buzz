package dev.archimedes.dtos;

import dev.archimedes.entities.Player;
import dev.archimedes.enums.PlayerType;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
public class PlayerDto {

    private String name;

    private String dateOfBirth;

    private String country;

    private String playerType;


    public static Player playerTypeToPlayer(PlayerDto playerDto){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return Player.builder()
                .name(playerDto.name)
                .country(playerDto.country)
                .dateOfBirth(LocalDate.parse(playerDto.dateOfBirth, dateTimeFormatter))
                .playerType(PlayerType.valueOf(playerDto.playerType))
                .build();
    }

}
