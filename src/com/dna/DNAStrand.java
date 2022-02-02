package com.dna;

import edu.duke.StorageResource;

import java.util.ArrayList;
import java.util.Locale;

/**
 *
 ** This Class represent a chunck of DNA Structures.
 */
public class DNAStrand {

    private String strand;


    public DNAStrand(String strand) {
        this.strand = strand.toUpperCase(Locale.ROOT);
    }


    public void setStrand(String strand) {
        if(this.isDNAStrand()) {
            this.strand = strand;
        }
    }


    public DNAStrand newStrand(String dna) {
        return new DNAStrand(dna);
    }


    /**
     * Vérifie si la chaîne de caractère est un brin d'ADN (chaîne contenant seulement les caractères "A", "T", "C" et "G" en majuscule et en minuscule).
     * @return boolean
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


    /***
     * <p> Vérifie si un motif existe dans un brin d'ADN </p>
     * @param motif
     * @return boolean
     */
    public boolean contains(String motif) {
        if(motif.length() > this.strand.length()) {
            //Si l'élement a comparé à une plus petite taille que notre motif, on retourne -1 (qui signifie une erreur dans la recherche).
            return false;
        }
        //On retourne Vrai si la première occurence de l'élément est retrouvé sinon si -1 on ne retourne faux.
        return this.strand.indexOf(motif) != -1;
    }


    /***
     * <p> Retourne tout les gènes du brin d'ADN </p>
     * @param pattern
     * @return int
     */
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


    /***
     * <p> Compte le nombre de gènes du brin d'ADN </p>
     * @return int
     */
    public int countGenes() {
        int position = 0, count = 0;
        Gene gene = null;

        if(this.strand.isEmpty())
            return -1;

        while(true) {
            gene = findGene(position);
            if(gene.getPiece().isEmpty()) {
                break;
            }
            count++;
            position += gene.getPiece().length();
        }

        return count;
    }


    /***
     * <p> Affiche tout les gènes du brin d'ADN </p>
     * @return void
     */
    public void printAllGenes() {
        Gene gene = null; int pos = 0;
        while(true) {
            gene = findGene(pos);
            if(gene.getPiece().isEmpty()) {
                break;
            }
            System.out.println("Gene : " + gene.getPiece());
            pos += gene.getPiece().length();
        }
    }


    /***
     * <p> Retourne tout les gènes du brin d'ADN </p>
     * @return Gene
     */
    public ArrayList<Gene> getGenes() {
        ArrayList<Gene> arrayGenes = new ArrayList<>();
        Gene gene = null; int pos = 0;
        while(true) {
            gene = findGene(pos);
            if(gene.getPiece().isEmpty()) {
                break;
            }
            arrayGenes.add(gene);
            pos += gene.getPiece().length();
        }
        return arrayGenes;
    }


    /***
     * <p> Retourne tout les gènes du brin d'ADN </p>
     * @return StorageResource
     */
    public StorageResource getAllGenes() {
        StorageResource resource = new StorageResource();
        Gene gene = null; int pos = 0;
        while(true) {
            gene = findGene(pos);
            if(gene.getPiece().isEmpty()) {
                break;
            }
            resource.add(gene.getPiece());
            pos += gene.getPiece().length();
        }
        return resource;
    }


    /***
     * <p> Retourne un gène du brin d'ADN à partir d'une position précise </p>
     * @param pos
     * @return Gene
     */
    private Gene findGene(int pos) {
        int taaIndex = 0, tagIndex = 0, tgaIndex = 0, minIndex = 0;
        Gene gene = new Gene("");
        String chain = "";
        pos = this.strand.indexOf("ATG", pos);

        if(pos == -1) {
            return new Gene("");
        }

        //On réccupère les différents index de nos motifs de recherche (codon d'arrêt).
        taaIndex = findStopCodonIndex(pos, Codon.TAA.getCodon());
        tagIndex = findStopCodonIndex(pos, Codon.TAG.getCodon());
        tgaIndex = findStopCodonIndex(pos, Codon.TGA.getCodon());

        //On réccupère l'index du motif le plus court (les motifs non existants ne seront pas sélectionné).
        minIndex = Math.min(taaIndex, Math.min(tagIndex, tgaIndex));

        //Si le codon trouvé est un fin de la chaîne et la chaîne est un multiple de 3, alors on retourne la chaîne sous lignée.
        if(minIndex == this.strand.length()) {
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


    /***
     * <p> Retourne l'index du codon d'arrêt pour un Codon donné </p>
     * @param pos
     * @param codon
     * @return int
     */
    private int findStopCodonIndex(int pos, String codon) {
        int currentIndex = 0;
        pos = this.strand.indexOf("ATG", pos);
        if(pos == -1) {
            return this.strand.length();
        }
        currentIndex = this.strand.indexOf(codon, pos + 3);
        while (currentIndex != -1) {
            if( ((currentIndex - pos) % 3) == 0) {
                return currentIndex;
            } else {
                currentIndex = strand.indexOf(codon, currentIndex + 3);
            }
        }
        return this.strand.length();
    }


    /***
     * <p> Affiche des informations sur les gènes </p>
     * @param resource
     * @return
     */
    public static void processGenes(StorageResource resource) {
        int longestGene = 0, countGene = 0, countHigherThan60Gene = 0, cgRatioCount = 0, cgtCount = 0;
        for (String gene : resource.data()) {
            System.out.println("   - : " + gene + "\n");
            countGene++;
            if(gene.length() > 60)
                countHigherThan60Gene++;
            if(longestGene < gene.length())
                longestGene = gene.length();
            if(DNAApplication.cgRadio(gene) > 0.35)
                cgRatioCount++;

            cgtCount += DNAApplication.countCTG(gene);
        }

        System.out.println("|----- Longueur plus le gène : " + longestGene);
        System.out.println("|----- Nombre de gènes : " + countGene);
        System.out.println("|----- Nombre de gènes avec cgRatio > 0.35 : " + cgRatioCount);
        System.out.println("|----- Nombre de gènes avec CTG : " + cgtCount);
        System.out.println("|----- Longueur > 60 : " + countHigherThan60Gene);

    }
}
