package com.example.MutantDetectorApplication;

import com.example.MutantDetectorApplication.service.MutantDetector;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


class MutantDetectorTest {

	// 1. CAMBIO: Usar la clase que tiene la lógica
	private MutantDetector detector;

	@BeforeEach
	void setUp() {
		// 2. CAMBIO: Instanciar la clase correcta
		detector = new MutantDetector();
	}

	@Test
	void mutantExample() {
		String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
		// Esta llamada ahora será válida
		assertTrue(detector.isMutant(dna));
	}

	@Test
	void humanExample() {
		String[] dna = {"ATGCGA","CAGTGC","TTATTT","AGACGG","GCGTCA","TCACTG"};
		assertFalse(detector.isMutant(dna));
	}
}