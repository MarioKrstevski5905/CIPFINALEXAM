package com.example.CIPFinal.contoller;

import com.example.CIPFinal.model.Score;
import com.example.CIPFinal.model.Submission;
import com.example.CIPFinal.service.ScoreService;
import com.example.CIPFinal.service.SubmissionService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/submissions")
public class SubmissionController {

    private final SubmissionService submissionService;
    private final ScoreService scoreService;

    public SubmissionController(SubmissionService submissionService, ScoreService scoreService) {
        this.submissionService = submissionService;
        this.scoreService = scoreService;
    }

    @PostMapping
    public Submission createSubmission(@RequestBody Submission submission) {
        return submissionService.createSubmission(submission);
    }

    @GetMapping
    public List<Submission> getAllSubmissions() {
        return submissionService.getAllSubmissions();
    }

    @GetMapping("/{id}")
    public Submission getSubmissionById(@PathVariable Long id) {
        return submissionService.getSubmissionById(id);
    }

    @GetMapping("/leaderboard")
    public List<Map<String, Object>> getLeaderboard(@RequestParam(defaultValue = "10") int top) {
        return scoreService.getLeaderboard(top);
    }

    @PostMapping("/{subId}/judges/{judgeId}/scores")
    public Score addScore(@PathVariable Long subId, @PathVariable Long judgeId, @RequestBody Score score) {
        Submission submission = submissionService.getSubmissionById(subId);
        score.setSubmission(submission);
        return scoreService.addScore(score);
    }

    @GetMapping("/{subId}/scores")
    public List<Score> getScoresForSubmission(@PathVariable Long subId) {
        return scoreService.getScoresForSubmission(subId);
    }
}
