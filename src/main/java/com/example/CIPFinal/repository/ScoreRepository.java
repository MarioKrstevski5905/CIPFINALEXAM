package com.example.CIPFinal.repository;

import com.example.CIPFinal.model.Score;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {

    List<Score> findBySubmissionId(Long submissionId);

    List<Score> findByJudgeId(Long judgeId);

    boolean existsBySubmissionIdAndJudgeId(Long submissionId, Long judgeId);

    @Query("SELECT s.submission.id as submissionId, AVG(s.scorePoints) as avgScore FROM Score s GROUP BY s.submission.id ORDER BY avgScore DESC") // Changed s.value to s.scorePoints
    List<Map<String, Object>> findTopSubmissions(Pageable pageable);

    @Query("SELECT COUNT(s) as scoresGiven, AVG(s.scorePoints) as avgDeviation FROM Score s WHERE s.judge.id = :judgeId") // Changed s.value to s.scorePoints
    Map<String, Object> findJudgeStats(@Param("judgeId") Long judgeId);
}