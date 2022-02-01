package com.dna;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 *
 ** This Class represent a chunck of DNA Structures.
 */
public class DNAStrand {

    private String strand;


    public DNAStrand(String strand) {
        this.strand = strand;
    }

    public void setStrand(String strand) {
        if(this.isDNAStrand()) {
            this.strand = strand;
        }
    }

    /**
     * Vérifie si la chaîne de caractère est un brin d'ADN
     * (chaîne contenant seulement les caractères "A", "T", "C" et "G" en majuscule et en minuscule).
     * @return
     */
    public boolean isDNAStrand() {
        if(this.strand.matches("[ACGTacgt]+")) {
            return true;
        }
        return false;
    }

    public String isLowerCase() {
        return this.strand.toLowerCase(Locale.ROOT);
    }

    public String isUpperCase() {
        return this.strand.toUpperCase(Locale.ROOT);
    }

    public boolean contains(String motif) {
        if(motif.length() > this.strand.length()) {
            //Si l'élement a comparé à une plus petite taille que notre motif, on retourne -1 (qui signifie une erreur dans la recherche).
            return false;
        }
        //On retourne Vrai si la première occurence de l'élément est retrouvé sinon si -1 on ne retourne faux.
        return this.strand.indexOf(motif) != -1;
    }

    public int countPattern(String pattern) {
        int position = 0, count = 0;

        if(pattern.length() > this.strand.length()) {
            //Si l'élement a comparé à une plus petite taille que notre motif, on retourne -1 (qui signifie une erreur dans la recherche).
            return -1;
        }

        //Initialisation de la position 0.
        position = this.strand.indexOf(pattern, position);
        while (true) {
            if(position == -1) {
                //Si plus aucune position trouvée, on fait un break (sortie de la boucle).
                break;
            } else {
                count++;
            }
            //On itère va à la position suivante en fonction de notre motif (sa longueur) et de la position actuelle.
            position = this.strand.indexOf(pattern, (position + pattern.length()));
        }

        return count;
    }

    public int countGenes() {
        int position = 0, count = 0;
        Gene gene = null;

        if(this.strand.isEmpty())
            return -1;

        while(true) {
            gene = findGene(this.strand, position);
            if(gene.getPiece().isEmpty()) {
                break;
            }
            count++;
            position += gene.getPiece().length();
        }

        return count;
    }

    public void printAllGenes() {
        Gene gene = null; int pos = 0;
        while(true) {
            gene = findGene(this.strand, pos);
            if(gene.getPiece().isEmpty()) {
                break;
            }
            System.out.println("Gene : " + gene.getPiece());
            pos += gene.getPiece().length();
        }
    }

    public ArrayList<Gene> getGenes() {
        ArrayList<Gene> arrayGenes = new ArrayList<>();
        Gene gene = null; int pos = 0;
        while(true) {
            gene = findGene(this.strand, pos);
            if(gene.getPiece().isEmpty()) {
                break;
            }
            arrayGenes.add(gene);
            pos += gene.getPiece().length();
        }
        return arrayGenes;
    }

    private Gene findGene(String dna, int pos) {
        int taaIndex = 0, tagIndex = 0, tgaIndex = 0, minIndex = 0;
        Gene gene = new Gene("");
        String chain = "";
        pos = dna.indexOf("ATG", pos);

        if(pos == -1) {
            return new Gene("");
        }

        //On réccupère les différents index de nos motifs de recherche (codon d'arrêt).
        taaIndex = findStopCodonIndex(this.strand, pos, Codon.TAA.getCodon());
        tagIndex = findStopCodonIndex(this.strand, pos, Codon.TAG.getCodon());
        tgaIndex = findStopCodonIndex(this.strand, pos, Codon.TGA.getCodon());

        //On réccupère l'index du motif le plus court (les motifs non existants ne seront pas sélectionné).
        minIndex = Math.min(taaIndex, Math.min(tagIndex, tgaIndex));

        //Si le codon trouvé est un fin de la chaîne et la chaîne est un multiple de 3, alors on retourne la chaîne sous lignée.
        if(minIndex == dna.length()) {
            chain = this.strand.substring(pos, minIndex);
        } else {
            chain = this.strand.substring(pos, minIndex + 3);
        }
        //On modifie la propriété du Gène.
        gene.setPiece(chain);

        if(gene.getPiece().endsWith("TAA") || gene.getPiece().endsWith("TAG") || gene.getPiece().endsWith("TGA")) {
            if((minIndex - pos) % 3 == 0) {
                return gene;
            }
        }

        return new Gene("");
    }

    private int findStopCodonIndex(String strand, int pos, String codon) {
        int currentIndex = 0;
        pos = strand.indexOf("ATG", pos);
        if(pos == -1) {
            return strand.length();
        }
        currentIndex = strand.indexOf(codon, pos + 3);
        while (currentIndex != -1) {
            if( ((currentIndex - pos) % 3) == 0) {
                return currentIndex;
            } else {
                currentIndex = strand.indexOf(codon, currentIndex + 3);
            }
        }
        return strand.length();
    }
}
