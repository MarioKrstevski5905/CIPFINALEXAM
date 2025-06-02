package com.example.CIPFinal.service;


import com.example.CIPFinal.model.Judge;
import com.example.CIPFinal.repository.JudgeRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class JudgeService {

    private final JudgeRepository judgeRepository;

    public JudgeService(JudgeRepository judgeRepository) {
        this.judgeRepository = judgeRepository;
    }

    public List<Judge> getAllJudges() {
        return judgeRepository.findAll();
    }

    public Judge getJudgeById(Long id) {
        return judgeRepository.findById(id).orElse(null);
    }

    public Judge createJudge(Judge judge) {
        return judgeRepository.save(judge);
    }
}
