package com.example.CIPFinal.contoller;


import com.example.CIPFinal.model.Judge;
import com.example.CIPFinal.model.Score;
import com.example.CIPFinal.service.JudgeService;
import com.example.CIPFinal.service.ScoreService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/judges")
public class JudgeController {

    private final JudgeService judgeService;
    private final ScoreService scoreService;

    public JudgeController(JudgeService judgeService, ScoreService scoreService) {
        this.judgeService = judgeService;
        this.scoreService = scoreService;
    }

    @PostMapping
    public Judge createJudge(@RequestBody Judge judge) {
        return judgeService.createJudge(judge);
    }

    @GetMapping
    public List<Judge> getAllJudges() {
        return judgeService.getAllJudges();
    }

    @GetMapping("/{id}")
    public Judge getJudgeById(@PathVariable Long id) {
        return judgeService.getJudgeById(id);
    }

    @GetMapping("/{id}/scores")
    public List<Score> getScoresByJudge(@PathVariable(value = "id") Long judgeId) {
        return scoreService.getScoresByJudge(judgeId);
    }

    @GetMapping("/{id}/stats")
    public Map<String, Object> getJudgeStats(@PathVariable(value = "id") Long judgeId) {
        return scoreService.getJudgeStats(judgeId);
    }
}
