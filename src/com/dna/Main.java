package com.dna;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import edu.duke.StorageResource;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String dna = "";
        DNAStrand strand = null;
        int choix = 0;
        //Menu.
        do {
            System.out.println("[-------------- DNA Apps --------------]\n");

            System.out.println("|----- Bonjour. Bienvenu sur DNA Apps -----|\n|----- Application de gestion des ADN et des GENES -----|");
            System.out.println("|----- 1. Vérification de l'ADN (voir si l'ADN est correcte) -----|");
            System.out.println("|----- 2. Voir si un motif existe -----|");
            System.out.println("|----- 3. Compter le nombre de gènes -----|");
            System.out.println("|----- 4. Lire tout les gènes dans un ADN -----|");
            System.out.println("|----- 5. Afficher tout les gènes dans un ADN -----|");
            System.out.println("|----- 6. Afficher le CG Ratio dans un ADN -----|");
            System.out.println("|----- 7. Afficher le compte CTG dans un ADN -----|");
            System.out.println("|----- 8. Processus d'analyse des gènes -----|");
            System.out.println("|----- 0. Quittez l'application -----|");
            System.out.println("|----- Que voulez-vous faire ?  -----|\n");
            System.out.print("|----- Faites un choix (1 - 2 - 3 - 4 - 5 - 6 - 7 - 8 - 0) : ");
            choix = scanner.nextInt();

            if (choix != 0 && choix != 8) {
                System.out.print("\n|----- Donnez un ADN à analyser : ");
                dna = scanner.next();
            }

            switch (choix) {
                case 1: {
                    strand = new DNAStrand(dna);
                    System.out.println("\n|----- " + dna + " est un un ADN ? " + strand.isDNAStrand() + " -----|\n");
                    break;
                }

                case 2: {
                    System.out.println("\n|----- Donnez un motif à rechercher dans l'ADN : -----|\n");
                    String motif = scanner.next();
                    strand = new DNAStrand(dna);
                    System.out.println("|----- " + motif + " existe dans l'ADN ? " + strand.contains(motif) + " -----|");
                    break;
                }

                case 3: {
                    strand = new DNAStrand(dna);
                    System.out.println("\n|----- Le nombre de gènes dans l'ADN ? " + strand.countGenes() + " -----|\n");
                    break;
                }

                case 4: {
                    strand = new DNAStrand(dna);
                    strand.printAllGenes();
                    break;
                }

                case 5: {
                    strand = new DNAStrand(dna);
                    System.out.println("\n|----- Tout les gènes de l'ADN  -----|\n");
                    for (String gene : strand.getAllGenes().data()) {
                        System.out.println("|----- Gène de l'ADN : " + gene + " -----|");
                    }
                    break;
                }

                case 6: {
                    System.out.println("\n|----- Le CG ratio de " + dna + " est de : " + DNAApplication.cgRadio(dna)  + "-----|\n");
                    break;
                }

                case 7: {
                    System.out.println("\n|----- Le nombre de 'C','T' et 'G' " + dna + " est de : " + DNAApplication.countCTG(dna)  + "-----|\n");
                    break;
                }

                case 8: {
                    FileResource fr = new FileResource("resources/GRch38dnapart.fa");
                    var strandDNA = fr.asString();
                    DNAStrand dnaStrand = new DNAStrand(strandDNA);
                    StorageResource rs = dnaStrand.getAllGenes();
                    DNAStrand.processGenes(rs);
                    break;
                }

                default:
                    break;
            }
            System.out.println("\n[-------------- End DNA Apps --------------]\n");
        } while (choix != 0);
    }
}