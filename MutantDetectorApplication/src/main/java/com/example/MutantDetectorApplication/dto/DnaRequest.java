package com.example.MutantDetectorApplication.dto;

import com.example.MutantDetectorApplication.validation.ValidDnaSequence;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request para verificar si un ADN es mutante")
public class DnaRequest {
    @NotNull(message = "DNA no puede ser null")
    @NotEmpty(message = "DNA no puede estar vacío")
    @ValidDnaSequence
    @Schema(
            description = "Secuencia de ADN representada como array de strings (matriz NxN). Cada string representa una fila de la matriz y debe contener solo los caracteres A, T, C, G. La matriz debe ser cuadrada (NxN) con un tamaño mínimo de 4x4.",
            example = "[\"ATGCGA\", \"CAGTGC\", \"TTATGT\", \"AGAAGG\", \"CCCCTA\", \"TCACTG\"]"
    )
    private String[] dna;
}
