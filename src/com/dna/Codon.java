package com.dna;

public enum Codon {

    TAA("TAA"), TGA("TGA"), TAG("TAG");

    private String codon;

    Codon(String codon) {
        this.codon = codon;
    }

    public String getCodon() {
        return this.codon;
    }
}
