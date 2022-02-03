package com.dna;

import java.util.Locale;

public class Gene {

    private String piece;

    public Gene(String piece) {
        this.piece = piece;
    }

    public String getPiece() {
        return piece;
    }

    public void setPiece(String piece) {
        this.piece = piece;
    }

    public boolean isGene() {
        return this.piece.length() % 3 == 0;
    }

    public String isUpperCase() {
        return this.piece.toUpperCase(Locale.ROOT);
    }

    public String isLowerCase() {
        return this.piece.toLowerCase(Locale.ROOT);
    }
}