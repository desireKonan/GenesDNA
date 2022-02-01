package org.dna.features;

public class Part2 {

    public int howMany(String a, String b) {
        int position = 0, count = 0;

        if(a.length() > b.length()) {
            //Si l'élement a comparé à une plus petite taille que notre motif, on retourne -1 (qui signifie une erreur dans la recherche).
            return -1;
        }

        //Initialisation de la position 0.
        position = b.indexOf(a, position);
        while (true) {
            if(position == -1) {
                //Si plus aucune position trouvée, on fait un break (sortie de la boucle).
                break;
            } else {
                count++;
            }
            //On itère va à la position suivante en fonction de notre motif (sa longueur) et de la position actuelle.
            position = b.indexOf(a, (position + a.length()));
        }

        return count;
    }


    public void testHowMany() {
        String[] samplesDNA = {
                "CCCCATGAAAAATTTAGGGCCCACTTAATAA",
                "ATGAAAAATTTTGGGGAGGGG",
                "",
                "AGATGAAAGGCATGTAAGGAT",
                "ATGCCTA",
                "ATATGAAAAAAAAAAATTTGCAATGATA"
        };

        for (int i = 0; i < samplesDNA.length; i++) {
            var sample = howMany("AA", samplesDNA[i]);
            System.out.println("|------------------------------- Test Find Gene ---------------------------------|");
            System.out.println("Nombre d'occurence : " + sample);
            System.out.println("|------------------------------- End Test Find Gene ---------------------------------|");
        }
    }
}
