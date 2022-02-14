package exercise;


import java.util.logging.Level;
import java.util.logging.Logger;

public class Assertions {

    // Récupération de notre logger.
    private static final Logger LOGGER = Logger.getLogger(TrueOrFalse.class.getPackage().getName());


    public static boolean trueOrFalse(Value val1, Value val2, String operator) {
        //On vérifie si les 2 valeurs ne contiennent que des élement non numériques.
        if (val1.isNumber() && val2.isNumber()) {
            if (operator.equals("=")) {
                return val1.equals(val2);
            } else {
                LOGGER.log(Level.WARNING, "Seul l'opérateur ('=') est attendu. Votre opérateur n'est pas valide !");
                return false;
            }
        } else if(val1.isString() && val2.isString()) {
            //Partie pour les nombres.
            double num1 = Double.parseDouble(val1.toDouble()),
                    num2 = Double.parseDouble(val2.toDouble());
            return compare(num1, num2, operator);
        } else {
            LOGGER.log(Level.WARNING, "Comparaison entre un entier ou réel avec une chaîne de caractères (imposssible) ! ");
            return false;
        }
    }

    private static boolean compare(double val1, double val2, String operator) {
        return switch (operator) {
            case ">" -> (val1 > val2) ? true : false;

            case "<" -> val1 < val2 ? true :false;
            case "=" -> val1 == val2 ? true : false;
            default -> throw new IllegalArgumentException("Seul les opérateurs ('>', '<' et '=') sont acceptés !");
        };
    }
}
