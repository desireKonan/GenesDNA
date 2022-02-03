package com.dna;

public class DNAApplication {

    /***
     * <p> Retourne le ratio des caractères C et G dans la chaîne d'ADN </p>
     * @return int
     */
    public static float cgRadio(String dna) {
        int count = 0;
        for (int i = 0; i < dna.length(); i++) {
            if (dna.charAt(i) == 'C' || dna.charAt(i) == 'G' || dna.charAt(i) == 'c' || dna.charAt(i) == 'g') {
                count++;
            }
        }
        return  Math.nextUp((float) count / dna.length());
    }


    /***
     * <p> Retourne le nombre des caractères C, G et T dans la chaîne d'ADN </p>
     * @return int
     */
    public static int countCTG(String dna) {
        int count = 0;
        for (int i = 0; i < dna.length(); i++) {
            if (dna.charAt(i) == 'C' || dna.charAt(i) == 'G' || dna.charAt(i) == 'T' || dna.charAt(i) == 'c' || dna.charAt(i) == 'g' || dna.charAt(i) == 't') {
                count++;
            }
        }
        return count;
    }

}