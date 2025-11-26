package com.example.MutantDetectorApplication.controller;

import com.example.MutantDetectorApplication.dto.DnaRequest;
import com.example.MutantDetectorApplication.dto.StatsResponse;
import com.example.MutantDetectorApplication.service.MutantService;
import com.example.MutantDetectorApplication.service.StatsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mutant")
@Tag(name = "Mutant API", description = "API para detectar mutantes y obtener estadísticas")
public class MutantController {

    private final MutantService mutantService;
    private final StatsService statsService;

    @PostMapping
    @Operation(
            summary = "Verificar si un ADN pertenece a un mutante",
            description = "Recibe una secuencia de ADN y devuelve si es mutante (HTTP 200) o no mutante (HTTP 403)."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Es mutante - Se encontraron más de una secuencia de 4 letras iguales"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos - ADN nulo, vacío, matriz no cuadrada o caracteres inválidos"),
            @ApiResponse(responseCode = "403", description = "No es mutante - Se encontró una o ninguna secuencia de 4 letras iguales")
    })
    public ResponseEntity<Void> checkMutant(@Valid @RequestBody DnaRequest request) {
        boolean isMutant = mutantService.analyzeDna(request.getDna());
        return isMutant ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @GetMapping("/stats")
    @Operation(
            summary = "Obtener estadísticas de verificaciones de ADN",
            description = "Retorna estadísticas de todas las verificaciones de ADN realizadas: cantidad de ADN mutantes detectados, cantidad de ADN humanos detectados, y el ratio entre mutantes y humanos (count_mutant_dna / count_human_dna)."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Estadísticas obtenidas exitosamente",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = StatsResponse.class)
            )
    )
    public ResponseEntity<StatsResponse> getStats() {
        return ResponseEntity.ok(statsService.getStats());
    }
}
