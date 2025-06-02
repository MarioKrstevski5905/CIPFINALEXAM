package com.example.CIPFinal;


import com.example.CIPFinal.model.Category;
import com.example.CIPFinal.model.Judge;
import com.example.CIPFinal.model.Score;
import com.example.CIPFinal.model.Submission;
import com.example.CIPFinal.repository.JudgeRepository;
import com.example.CIPFinal.repository.ScoreRepository;
import com.example.CIPFinal.repository.SubmissionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    private final SubmissionRepository submissionRepository;
    private final JudgeRepository judgeRepository;
    private final ScoreRepository scoreRepository;

    public DataSeeder(SubmissionRepository submissionRepository, JudgeRepository judgeRepository, ScoreRepository scoreRepository) {
        this.submissionRepository = submissionRepository;
        this.judgeRepository = judgeRepository;
        this.scoreRepository = scoreRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadInitialData();
    }

    private void loadInitialData() {
        if (submissionRepository.count() == 0) {
            Judge judge1 = new Judge();
            judge1.setFirstName("Jovan");
            judge1.setLastName("Petrovski");
            judge1.setExpertise("Mountaion");
            judgeRepository.save(judge1);

            Judge judge2 = new Judge();
            judge2.setFirstName("Darko");
            judge2.setLastName("Petrovski");
            judge2.setExpertise("River");
            judgeRepository.save(judge2);

            Judge judge3 = new Judge();
            judge3.setFirstName("Peter");
            judge3.setLastName("Jones");
            judge3.setExpertise("Street");
            judgeRepository.save(judge3);

            Submission sub1 = new Submission();
            sub1.setTitle("Mountain");
            sub1.setCategory(Category.LANDSCAPE);
            sub1.setPhotographerName("Petar");
            sub1.setUploadedAt(LocalDateTime.now());
            submissionRepository.save(sub1);

            Submission sub2 = new Submission();
            sub2.setTitle("City");
            sub2.setCategory(Category.STREET);
            sub2.setPhotographerName("Bojan");
            sub2.setUploadedAt(LocalDateTime.now());
            submissionRepository.save(sub2);

            Submission sub3 = new Submission();
            sub3.setTitle("Portrait");
            sub3.setCategory(Category.PORTRAIT);
            sub3.setPhotographerName("David");
            sub3.setUploadedAt(LocalDateTime.now());
            submissionRepository.save(sub3);

            Submission sub4 = new Submission();
            sub4.setTitle("Forest Path");
            sub4.setCategory(Category.NATURE);
            sub4.setPhotographerName("David");
            sub4.setUploadedAt(LocalDateTime.now());
            submissionRepository.save(sub4);

            Submission sub5 = new Submission();
            sub5.setTitle("Abstract Forms");
            sub5.setCategory(Category.ABSTRACT);
            sub5.setPhotographerName("Eva");
            sub5.setUploadedAt(LocalDateTime.now());
            submissionRepository.save(sub5);

            // Seed scores
            scoreRepository.saveAll(List.of(
                    createScore(8, "", judge1, sub1),
                    createScore(9, "", judge2, sub1),
                    createScore(7, "", judge1, sub2),
                    createScore(8, "", judge3, sub2),
                    createScore(9, "", judge2, sub3),
                    createScore(10, "", judge3, sub3),
                    createScore(6, "", judge1, sub4),
                    createScore(7, "", judge2, sub4),
                    createScore(8, "", judge3, sub5),
                    createScore(9, "", judge1, sub5)
            ));
        }
    }

    private Score createScore(int value, String comment, Judge judge, Submission submission) {
        Score score = new Score();
        score.setComment(comment);
        score.setJudge(judge);
        score.setSubmission(submission);
        return score;
    }
}

