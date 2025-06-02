package com.example.CIPFinal.service;


import com.example.CIPFinal.model.Score;
import com.example.CIPFinal.repository.ScoreRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class ScoreService {

    private final ScoreRepository scoreRepository;

    public ScoreService(ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }

    public Score addScore(Score score) {
        if (score.getScorePoints() < 1 || score.getScorePoints() > 10) { // Changed from getValue
            throw new IllegalArgumentException("Score value must be between 1 and 10.");
        }
        if (scoreRepository.existsBySubmissionIdAndJudgeId(score.getSubmission().getId(), score.getJudge().getId())) {
            throw new IllegalStateException("This judge has already scored this submission.");
        }
        return scoreRepository.save(score);
    }

    public Score updateScore(Long id, Score updatedScore) {
        Score existingScore = scoreRepository.findById(id).orElseThrow(() -> new RuntimeException("Score not found"));
        if (updatedScore.getScorePoints() < 1 || updatedScore.getScorePoints() > 10) { // Changed from getValue
            throw new IllegalArgumentException("Score value must be between 1 and 10.");
        }
        existingScore.setScorePoints(updatedScore.getScorePoints()); // Changed from setValue
        existingScore.setComment(updatedScore.getComment());
        return scoreRepository.save(existingScore);
    }

    public List<Score> getScoresForSubmission(Long submissionId) {
        return scoreRepository.findBySubmissionId(submissionId);
    }

    public List<Score> getScoresByJudge(Long judgeId) {
        return scoreRepository.findByJudgeId(judgeId);
    }

    public List<Map<String, Object>> getLeaderboard(int top) {
        // The JPQL query in ScoreRepository uses "s.value", this needs to be "s.scorePoints"
        // This change is in ScoreRepository.java
        return scoreRepository.findTopSubmissions(PageRequest.of(0, top));
    }

    public Map<String, Object> getJudgeStats(Long judgeId) {
        // The JPQL query in ScoreRepository uses "s.value", this needs to be "s.scorePoints"
        // This change is in ScoreRepository.java
        return scoreRepository.findJudgeStats(judgeId);
    }
}
