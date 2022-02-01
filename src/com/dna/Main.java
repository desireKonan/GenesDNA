package com.dna;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String dna = "";
        int choix = 0;
        //Menu.
        do {
            System.out.println("[-------------- DNA Apps --------------]");

            System.out.println("|----- Bonjour. Bienvenu sur DNA Apps -----|\n|----- Application de gestion des ADN et des GENES -----|");
            System.out.println("|----- 1. Vérification de l'ADN (voir si l'ADN est correcte) -----|");
            System.out.println("|----- 2. Voir si un motif existe -----|");
            System.out.println("|----- 3. Compter le nombre de gènes -----|");
            System.out.println("|----- 4. Lire tout les gènes dans un ADN -----|");
            System.out.println("|----- 0. Quittez l'application -----|");
            System.out.println("|----- Que voulez-vous faire ? Faites un choix (1 - 2 - 3 - 4 - 0) -----|");
            choix = scanner.nextInt();

            if (choix != 0) {
                System.out.println("|----- Donnez un ADN à analyser -----|");
                dna = scanner.next();
            }

            switch (choix) {
                case 1: {
                    DNAStrand strand = new DNAStrand(dna);
                    System.out.println("|----- " + dna + " est un un ADN ? " + strand.isDNAStrand() + " -----|");
                    break;
                }

                case 2: {
                    System.out.println("|----- Donnez un motif à rechercher dans l'ADN : -----|");
                    String motif = scanner.next();
                    DNAStrand strand = new DNAStrand(dna);
                    System.out.println("|----- " + motif + " existe dans l'ADN ? " + strand.contains(motif) + " -----|");
                    break;
                }

                case 3: {
                    DNAStrand strand = new DNAStrand(dna);
                    System.out.println("|----- Le nombre de gènes dans l'ADN ? " + strand.countGenes() + " -----|");
                    break;
                }

                case 4: {
                    DNAStrand strand = new DNAStrand(dna);
                    strand.printAllGenes();
                    break;
                }

                default:
                    break;
            }
            System.out.println("[-------------- End DNA Apps --------------]");
        } while (choix != 0);
    }
}
