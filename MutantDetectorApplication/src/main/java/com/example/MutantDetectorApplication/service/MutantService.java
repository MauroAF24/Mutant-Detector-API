package com.example.MutantDetectorApplication.service;

import com.example.MutantDetectorApplication.entity.DnaRecord;
import com.example.MutantDetectorApplication.repository.DnaRecordRepository;
import com.example.MutantDetectorApplication.exception.DnaHashCalculationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MutantService {
    private final DnaRecordRepository repository;
    private final MutantDetector detector;

    public boolean analyzeDna(String[] dna) {
        String hash = calculateDnaHash(dna);
        Optional<DnaRecord> existing = repository.findByDnaHash(hash);
        if (existing.isPresent()) return existing.get().isMutant();

        boolean isMutant = detector.isMutant(dna);
        DnaRecord r = new DnaRecord();
        r.setDnaHash(hash);
        r.setIsMutant(isMutant);
        r.setCreatedAt(LocalDateTime.now());
        repository.save(r);
        return isMutant;
    }

    private String calculateDnaHash(String[] dna) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            String joined = String.join("", dna);
            byte[] hash = digest.digest(joined.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) sb.append('0');
                sb.append(hex);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new DnaHashCalculationException("Error calculando hash", e);
        }
    }
}