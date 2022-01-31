package org.dna.features;

import java.util.Arrays;

public class Part1 {

    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int currentIndex = 0;
        startIndex = dna.indexOf("ATG", startIndex);
        if(startIndex == -1) {
            return dna.length();
        }
        currentIndex = dna.indexOf(stopCodon, startIndex + 3);
        while (currentIndex != -1) {
            if( ((currentIndex - startIndex) % 3) == 0 ) {
                return currentIndex;
            } else {
                currentIndex = dna.indexOf(stopCodon, currentIndex + 3);
            }
        }
        return dna.length();
    }


    public String findGene(String dna) {
        int startIndex = 0, taaIndex = 0, tagIndex = 0, tgaIndex = 0, minIndex = 0;
        String gene = "";
        startIndex = dna.indexOf("ATG");
        if(startIndex == -1) {
            return "";
        }
        //On réccupère les différents index de nos motifs de recherche (codon d'arrêt).
        taaIndex = findStopCodon(dna, startIndex, "TAA");
        tagIndex = findStopCodon(dna, startIndex, "TAG");
        tgaIndex = findStopCodon(dna, startIndex, "TGA");
        //On réccupère l'index du motif le plus court (les motifs non existants ne seront pas sélectionné).
        minIndex = Math.min(taaIndex, Math.min(tagIndex, tgaIndex));
        //Si le codon trouvé est un fin de la chaîne et la chaîne est un multiple de 3, alors on retourne la chaîne sous lignée.
        if(minIndex == dna.length()) {
            gene = dna.substring(startIndex, minIndex);
        } else {
            gene = dna.substring(startIndex, minIndex + 3);
        }
        if(gene.endsWith("TAA") || gene.endsWith("TAG") || gene.endsWith("TGA")) {
            if((minIndex - startIndex) % 3 == 0) {
                return gene;
            }
        }
        return "";
    }


    public String findGene(String dna, int startIndex) {
        int taaIndex = 0, tagIndex = 0, tgaIndex = 0, minIndex = 0;
        String gene = "";
        startIndex = dna.indexOf("ATG", startIndex);
        if(startIndex == -1) {
            return "";
        }
        //On réccupère les différents index de nos motifs de recherche (codon d'arrêt).
        taaIndex = findStopCodon(dna, startIndex, "TAA");
        tagIndex = findStopCodon(dna, startIndex, "TAG");
        tgaIndex = findStopCodon(dna, startIndex, "TGA");
        //On réccupère l'index du motif le plus court (les motifs non existants ne seront pas sélectionné).
        minIndex = Math.min(taaIndex, Math.min(tagIndex, tgaIndex));
        //Si le codon trouvé est un fin de la chaîne et la chaîne est un multiple de 3, alors on retourne la chaîne sous lignée.
        if(minIndex == dna.length()) {
            gene = dna.substring(startIndex, minIndex);
        } else {
            gene = dna.substring(startIndex, minIndex + 3);
        }
        if(gene.endsWith("TAA") || gene.endsWith("TAG") || gene.endsWith("TGA")) {
            if((minIndex - startIndex) % 3 == 0) {
                return gene;
            }
        }
        return "";
    }

    public void printAllGenes(String dna) {
        String gene = "";
        int startIndex = 0, i = 0;
        while(true) {
            gene = findGene(dna, startIndex);
            if(gene.isEmpty()) {
                break;
            } else {
                System.out.println("Gene : " + gene);
            }
            startIndex += gene.length() + 1;
            i++;
        }
    }



    public void testFindStopCodon() {
        String[] samplesDNA = {
                "CCCCATGAAAAATTTAGGGCCCACTTAATAA",
                "ATGAAAAATTTTGGGGAGGGG",
                "AATGAAAAAAGGGATAA",
                "AGATGAAAGGCATGTAAGGAT",
                "ATGAAATAA",
                "ATATGAAAAAAAAAAATTTGCAATGATA"
        };

        for (int i = 0; i < samplesDNA.length; i++) {
            var sample = findStopCodon(samplesDNA[i], 0, "TAA");
            System.out.println("Codon d'arrêt du " + (i + 1) + "ième échantillon d'ADN : " + sample);
        }
    }

    public void testFindGene() {
        String[] samplesDNA = {
                "CCCCATGAAAAATTTAGGGCCCACTTAATAA",
                "ATGAAAAATTTTGGGGAGGGG",
                "AATGAAAAAAGGGATAA",
                "AGATGAAAGGCATGTAAGGAT",
                "ATGAAATAA",
                "ATATGAAAAAAAAAAATTTGCAATGATA"
        };

        for (int i = 0; i < samplesDNA.length; i++) {
            var sample = findGene(samplesDNA[i]);
            System.out.println("|------------------------------- Test Find Gene ---------------------------------|");
            System.out.println("L'ADN " + (i + 1) + " : " + samplesDNA[i]);
            System.out.println("Codon d'arrêt du " + (i + 1) + "ième échantillon d'ADN : " + sample);
            System.out.println("|------------------------------- End Test Find Gene ---------------------------------|");
        }
    }


    public void testPrintAllGenes() {
        String[] samplesDNA = {
                "CCCCATGAAAAATTTAGGGCCCACTTAATAA",
                "ATGAAAAATTTTGGGGAGGGG",
                "AATGAAAAAAGGGATAA",
                "AGATGAAAGGCATGTAAGGAT",
                "ATGAAATAA",
                "ATATGAAAAAAAAAAATTTGCAATGATA"
        };

        for (int i = 0; i < samplesDNA.length; i++) {
            printAllGenes(samplesDNA[i]);
        }
    }
}
