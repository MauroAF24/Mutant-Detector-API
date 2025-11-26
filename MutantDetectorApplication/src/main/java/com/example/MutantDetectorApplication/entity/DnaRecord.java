package com.example.MutantDetectorApplication.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "dna_records", indexes = {@Index(name="idx_dna_hash", columnList = "dna_hash")})
@Getter
@Setter
@NoArgsConstructor
public class DnaRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dna_hash", unique = true, nullable = false, length = 64)
    private String dnaHash;

    @Column(name = "is_mutant", nullable = false)
    private boolean isMutant;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    //Lo creamos para evitar error en  r.setIsMutant(isMutant);
    public void setIsMutant(boolean isMutant) {
        this.isMutant = isMutant;
    }
}