package com.example.CIPFinal.service;

import com.example.CIPFinal.model.Submission;
import com.example.CIPFinal.repository.SubmissionRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SubmissionService {

    private final SubmissionRepository submissionRepository;

    public SubmissionService(SubmissionRepository submissionRepository) {
        this.submissionRepository = submissionRepository;
    }

    public List<Submission> getAllSubmissions() {
        return submissionRepository.findAll();
    }

    public Submission getSubmissionById(Long id) {
        return submissionRepository.findById(id).orElse(null);
    }

    public Submission createSubmission(Submission submission) {
        return submissionRepository.save(submission);
    }
}
