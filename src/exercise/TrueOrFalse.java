package exercise;

import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class TrueOrFalse {

    // Récupération de notre logger.
    private static final Logger LOGGER = Logger.getLogger(TrueOrFalse.class.getPackage().getName());

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String val1 = "", val2 = "", operator = "";

        System.out.println("[----- Projet 1 : TrueOrFalse -----]");
        System.out.print("Veuillez entrer une 1ière valeur : ");
        val1 = scanner.nextLine();

        System.out.print("\n");
        System.out.print("Veuillez entrer une 2ième valeur : ");
        val2 = scanner.nextLine();

        System.out.print("\n");

        System.out.print("Veuillez entrer un opérateur (opérateur Javascript accepté) : ");
        operator = scanner.nextLine();

        System.out.println(val1 + " " + operator + " " + val2 + " ? " + TrueOrFalse(val1, val2, operator));
    }


    public static boolean TrueOrFalse(String val1, String val2, String operator) {
        //On vérifie si les 2 valeurs ne contiennent que des élement non numériques.
        if (val1.matches("\\D+") && val2.matches("\\D+")) {
            if (operator.equals("=")) {
                return val1.equals(val2);
            } else {
                LOGGER.log(Level.WARNING, "Seul l'opérateur ('=') est attendu. Votre opérateur n'est pas valide !");
                return false;
            }
        } else if(val1.matches("^[+-]?(\\d+([.,][\\d]*)?|[.][0-9]+)$") && val2.matches("^[+-]?(\\d+([.,][\\d]*)?|[.][0-9]+)$")) {
            //Partie pour les nombres.
            double num1 = Double.parseDouble(Arrays.stream(val1.split("[,.]")).collect(Collectors.joining("."))),
                  num2 = Double.parseDouble(Arrays.stream(val2.split("[,.]")).collect(Collectors.joining(".")));
            boolean valid = false;

            switch (operator) {
                case ">": {
                    valid = num1 > num2 ? true : false;
                    break;
                }
                case "<" : {
                    valid = num1 < num2 ? true :false;
                    break;
                }
                case "=" : {
                    valid = num1 == num1 ? true : false;
                    break;
                }
                default : {
                    LOGGER.log(Level.WARNING, "Seul les opérateurs ('>', '<' et '=') sont attendus. Votre opérateur n'est pas valide !");
                }
            };
            return valid;
        } else {
            LOGGER.log(Level.WARNING, "Comparaison entre un entier ou réel avec une chaîne de caractères (imposssible) ! ");
            return false;
        }
    }
}
