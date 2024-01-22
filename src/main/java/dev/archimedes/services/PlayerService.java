package dev.archimedes.services;

import dev.archimedes.dtos.MatchDto;
import dev.archimedes.dtos.PlayerDto;
import dev.archimedes.entities.Match;
import dev.archimedes.entities.Player;
import dev.archimedes.enums.PlayerType;
import dev.archimedes.repositories.MatchRepository;
import dev.archimedes.repositories.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final MatchRepository matchRepository;

    private boolean isValidPlayerType(String playerType){
        try {
            PlayerType.valueOf(playerType);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public ResponseEntity<?> createPlayer(PlayerDto playerDto){
        try{
            if(isValidPlayerType(playerDto.getPlayerType())){
                Player player = PlayerDto.playerTypeToPlayer(playerDto);
                if(playerRepository.existsByNameAndDateOfBirth(player.getName(), player.getDateOfBirth())){
                    return new ResponseEntity<>("Player Already Exist", HttpStatus.BAD_REQUEST);
                }
                playerRepository.save(player);
                return new ResponseEntity<>("Player created", HttpStatus.CREATED);
            }
            throw new RuntimeException("Invalid Player Type");
        }catch (Exception e){
            throw new RuntimeException(e.getLocalizedMessage());
        }
    }

    public ResponseEntity<?> addMatchToPlayer(MatchDto matchDto, int playerId){
        try {
            Player player = playerRepository.getReferenceById(playerId);
            if(matchRepository.existsByStadiumAndMatchDateAndPlayer_Id(matchDto.getStadium(), getDate(matchDto.getMatchDate()), playerId)){
                return new ResponseEntity<>("Match already added to player data. Try updating it", HttpStatus.BAD_REQUEST);
            }
            Match match = MatchDto.parse(matchDto, player);
            player.addMatch(match);
            playerRepository.save(player);
            matchRepository.save(match);
            return new ResponseEntity<>("Match Added Successfully", HttpStatus.CREATED);
        }catch (Exception e){
            throw new RuntimeException(e.getLocalizedMessage());
        }
    }

    public ResponseEntity<?> findMatch(String date, int id) {
        try {
            LocalDate date1 = getDate(date);
            return new ResponseEntity<>(matchRepository.findMatchByDate(date1, id), HttpStatus.OK);
        }catch (Exception e){
            throw new RuntimeException(e.getLocalizedMessage());
        }
    }

    public ResponseEntity<?> findMatch(String date, String stadium){
        try {
            LocalDate date1 = getDate(date);
            return new ResponseEntity<>(playerRepository.findPlayerResponseDto(date1, stadium), HttpStatus.OK);
        }catch (Exception e){
            throw new RuntimeException(e.getLocalizedMessage());
        }
    }

    private LocalDate getDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(date, formatter);
    }
}
