package com.kim.miniportfolioapi.controller;

import com.kim.miniportfolioapi.entity.Problem;
import com.kim.miniportfolioapi.service.ProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/problems")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class ProblemController {

    private final ProblemService problemService;

    @GetMapping
    public List<Problem> getAll() {
        return problemService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Problem> getById(@PathVariable Long id) {
        return problemService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Problem create(@RequestBody Problem problem) {
        return problemService.create(problem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Problem> update(@PathVariable Long id, @RequestBody Problem problem) {
        return problemService.update(id, problem)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (problemService.delete(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}