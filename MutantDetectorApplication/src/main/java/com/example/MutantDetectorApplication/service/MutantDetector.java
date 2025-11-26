package com.example.MutantDetectorApplication.service;

import org.springframework.stereotype.Component;

@Component
public class MutantDetector {
    private static final int SEQ_LEN = 4;

    public boolean isMutant(String[] dna) {
        if (!isValidInput(dna)) return false;

        int n = dna.length;
        char[][] m = new char[n][];
        for (int i=0;i<n;i++) m[i] = dna[i].toCharArray();

        int sequences = 0;

        for (int r=0; r<n; r++) {
            for (int c=0; c<n; c++) {
                char base = m[r][c];
                if (c <= n - SEQ_LEN && checkHorizontal(m, r, c, base)) {
                    sequences++; if (sequences > 1) return true;
                }
                if (r <= n - SEQ_LEN && checkVertical(m, r, c, base)) {
                    sequences++; if (sequences > 1) return true;
                }
                if (r <= n - SEQ_LEN && c <= n - SEQ_LEN && checkDiagDown(m, r, c, base)) {
                    sequences++; if (sequences > 1) return true;
                }
                if (r >= SEQ_LEN - 1 && c <= n - SEQ_LEN && checkDiagUp(m, r, c, base)) {
                    sequences++; if (sequences > 1) return true;
                }
            }
        }
        return false;
    }

    private boolean isValidInput(String[] dna) {
        if (dna == null || dna.length == 0) return false;
        int n = dna.length;
        for (String row : dna) {
            if (row == null || row.length() != n) return false;
            for (char ch : row.toCharArray()) {
                if ("ATCG".indexOf(ch) == -1) return false;
            }
        }
        return true;
    }

    private boolean checkHorizontal(char[][] m, int r, int c, char base) {
        return m[r][c+1]==base && m[r][c+2]==base && m[r][c+3]==base;
    }
    private boolean checkVertical(char[][] m, int r, int c, char base) {
        return m[r+1][c]==base && m[r+2][c]==base && m[r+3][c]==base;
    }
    private boolean checkDiagDown(char[][] m, int r, int c, char base) {
        return m[r+1][c+1]==base && m[r+2][c+2]==base && m[r+3][c+3]==base;
    }
    private boolean checkDiagUp(char[][] m, int r, int c, char base) {
        return m[r-1][c+1]==base && m[r-2][c+2]==base && m[r-3][c+3]==base;
    }
}
