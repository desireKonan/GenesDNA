package org.dna.features;

public class Part3 {

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

    public String findGene(String dna, int startIndex) {
        int taaIndex = 0, tagIndex = 0, tgaIndex = 0, minIndex = 0;
        String gene = "";
        startIndex = dna.indexOf("ATG", startIndex + 3);
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


    public int countGenes(String dna) {
        int position = 0, count = 0;
        String gene = "";

        if(dna.isEmpty())
            return -1;

        while(true) {
            gene = findGene(dna, position);
            if(gene.isEmpty()) {
                break;
            } else {
                count++;
            }
            position += (gene.length() + 1);
        }

        return count;
    }


    public void testCountGenes() {
        String[] samplesDNA = {
                "CCCCATGAAAAATTTAGGGCCCACTTAATAA",
                "ATGAAAAATTTTGGGGAGGGG",
                "",
                "AGATGAAAGGCATGTAAGGAT",
                "ATGCCTA",
                "ATATGAAAAAAAAAAATTTGCAATGATA"
        };

        for (int i = 0; i < samplesDNA.length; i++) {
            var sample = countGenes(samplesDNA[i]);
            System.out.println("|------------------------------- Test Count Genes Numbers ---------------------------------|");
            System.out.println("Nombres de Genes : " + sample);
            System.out.println("|------------------------------- End Count Genes Numbers ---------------------------------|");
        }
    }

}
