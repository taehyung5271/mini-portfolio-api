package com.kim.miniportfolioapi.service;

import com.kim.miniportfolioapi.entity.Problem;
import com.kim.miniportfolioapi.repository.ProblemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProblemService {

    private final ProblemRepository problemRepository;

    public List<Problem> findAll() {
        return problemRepository.findAllByOrderByCreatedAtDesc();
    }

    public Optional<Problem> findById(Long id) {
        return problemRepository.findById(id);
    }

    public Problem create(Problem problem) {
        return problemRepository.save(problem);
    }

    public Optional<Problem> update(Long id, Problem problem) {
        return problemRepository.findById(id)
                .map(existing -> {
                    existing.setTitle(problem.getTitle());
                    existing.setPlatform(problem.getPlatform());
                    existing.setDifficulty(problem.getDifficulty());
                    existing.setStatus(problem.getStatus());
                    existing.setLink(problem.getLink());
                    existing.setMemo(problem.getMemo());
                    return problemRepository.save(existing);
                });
    }

    public boolean delete(Long id) {
        if (problemRepository.existsById(id)) {
            problemRepository.deleteById(id);
            return true;
        }
        return false;
    }
}