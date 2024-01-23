package dev.archimedes.controller;

import dev.archimedes.dtos.MatchDto;
import dev.archimedes.dtos.PlayerDto;
import dev.archimedes.repositories.MatchRepository;
import dev.archimedes.repositories.PlayerRepository;
import dev.archimedes.services.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/player")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerRepository playerRepository;
    private final PlayerService playerService;
    private final MatchRepository matchRepository;

    @GetMapping("/all")
    public ResponseEntity<?> getAllPlayers(){
        return new ResponseEntity<>(playerRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/match")
    public ResponseEntity<?> getMatchByPlayer(@RequestParam String playerName){
        return new ResponseEntity<>(matchRepository.getMatchByPlayer_Name(playerName), HttpStatus.OK);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<?> createPlayer(@RequestBody PlayerDto playerDto){
        return playerService.createPlayer(playerDto);
    }

    @PostMapping("/add-match")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<?> addMatchToPlayer(@RequestParam("playerId") int playerId, @RequestBody MatchDto matchDto){
        return playerService.addMatchToPlayer(matchDto, playerId);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> runtimeException(RuntimeException runtimeException){
        return new ResponseEntity<>(runtimeException.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception exception){
        return new ResponseEntity<>(exception.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
