package com.example.MutantDetectorApplication.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Respuesta con estadísticas de verificaciones de ADN")
public class StatsResponse {
    @Schema(description = "Cantidad de mutantes detectados", example = "32")
    private long countMutantDna;

    @Schema(description = "Cantidad de humanos detectados", example = "120")
    private long countHumanDna;

    @Schema(description = "Ratio entre mutantes y humanos", example = "0.27")
    private double ratio;
}
