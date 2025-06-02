package com.example.CIPFinal.contoller;

import com.example.CIPFinal.model.Score;
import com.example.CIPFinal.service.ScoreService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/scores")
public class ScoreController {
    private final ScoreService scoreService;

    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @PatchMapping("/{id}")
    public Score updateScore(@PathVariable Long id, @RequestBody Score score) {
        return scoreService.updateScore(id, score);
    }
}
