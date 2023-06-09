package com.GrooveSpring.CodePostal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CodePostalService {

    private final CodePostalRepository codePostalRepository;

    @Autowired
    public CodePostalService(CodePostalRepository codePostalRepository) {
        this.codePostalRepository = codePostalRepository;
    }

    public List<CodePostal> getAllCodePostaux() {
        return codePostalRepository.findAll();
    }

    public Optional<CodePostal> getCodePostalById(Long id) {
        return codePostalRepository.findById(id);
    }

    public CodePostal createCodePostal(CodePostal codePostal) {
        return codePostalRepository.save(codePostal);
    }

    public Optional<CodePostal> updateCodePostal(Long id, CodePostal codePostal) {
        Optional<CodePostal> existingCodePostal = codePostalRepository.findById(id);
        if (existingCodePostal.isPresent()) {
            codePostal.setId(id);
            return Optional.of(codePostalRepository.save(codePostal));
        } else {
            return Optional.empty();
        }
    }

    public boolean deleteCodePostal(Long id) {
        if (codePostalRepository.existsById(id)) {
            codePostalRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
