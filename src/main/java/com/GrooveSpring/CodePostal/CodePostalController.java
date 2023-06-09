package com.GrooveSpring.CodePostal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/code-postal")
public class CodePostalController {

    private final CodePostalRepository codePostalRepository;

    public CodePostalController(CodePostalRepository codePostalRepository) {
        this.codePostalRepository = codePostalRepository;
    }

    @GetMapping("")
    public ResponseEntity<List<CodePostal>> getAllCodePostals() {
        List<CodePostal> codePostaux = codePostalRepository.findAll();
        return ResponseEntity.ok(codePostaux);
    }

    @GetMapping("/{id}")
    public Optional<CodePostal> getCodePostalById(@PathVariable Long id) {
        return codePostalRepository.findById(id);
    }

    @PostMapping
    public ResponseEntity<CodePostal> createCodePostal(@RequestBody CodePostal codePostal) {
        CodePostal savedCodePostal = codePostalRepository.save(codePostal);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCodePostal);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CodePostal> updateCodePostal(@PathVariable Long id, @RequestBody CodePostal codePostal) {
        if (codePostalRepository.existsById(id)) {
            codePostal.setId(id);
            CodePostal updatedCodePostal = codePostalRepository.save(codePostal);
            return ResponseEntity.ok(updatedCodePostal);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCodePostal(@PathVariable Long id) {
        if (codePostalRepository.existsById(id)) {
            codePostalRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
