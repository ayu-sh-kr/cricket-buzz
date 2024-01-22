package dev.archimedes.controller;

import dev.archimedes.services.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/match")
@RequiredArgsConstructor
public class MatchController {

    private final PlayerService playerService;

    @GetMapping("/find-by-date")
    public ResponseEntity<?> findMatch(@RequestParam("date") String date, @RequestParam("id") int id){
        return playerService.findMatch(date, id);
    }

    @GetMapping("/players-by-date-stadium")
    public ResponseEntity<?> findPlayersByMatchAndDate(@RequestParam("date") String date, @RequestParam("stadium") String stadium){
        return playerService.findMatch(date, stadium);
    }
}
