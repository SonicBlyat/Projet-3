package jeux;

import launcher.*;
import org.apache.commons.lang3.StringUtils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;

public class MastermindDefenseur {

    public static void mastermindDefenseur() throws FileNotFoundException {

        Scanner sc = new Scanner(System.in);
        Random r = new Random();

        ResourceBundle bundle = ResourceBundle.getBundle("config");

        int coups = 0;
        int coupsMax = Integer.parseInt(bundle.getString("coupsMaxDefenseur")); // NOMBRE DE COUPS (CONFIGURABLE)
        int fourchette = Integer.parseInt(bundle.getString("chiffreMax"));       // UTILISER DES CHIFFRES ENTRE 1 ET ... (CONFIGURABLE)
        int max = Integer.parseInt(bundle.getString("tailleCode"));              // TAILLE DU CODE (CONFIGURABLE)

        System.out.printf("%n");
        System.out.println("MASTERMIND : DEFENSEUR");
        System.out.println("Choisissez une combinaison !");
        System.out.printf("%n");

        // GENERATION DU CODE PAR L'UTILISATEUR
        ArrayList code = new ArrayList();
        String inputCode = sc.next();
        for (int i = 0; i < max; i++) {
            code.add(Integer.parseInt(inputCode.charAt(i) + ""));
        }

        // PREMIERE SAISIE DE L'ORDINATEUR
        ArrayList<Integer> saisieBot = new ArrayList<Integer>();
        for (int i = 0; i < max; i++) {
            saisieBot.add(r.nextInt(fourchette) + 1);
        }

        while (coups < coupsMax) {

            boolean[] codeUsed = new boolean[code.size()];
            boolean[] saisieUsed = new boolean[saisieBot.size()];
            int numberOfCorrect = 0;
            int numberOfPresent = 0;
            System.out.println("Proposition de l'ordinateur : " + StringUtils.join(saisieBot, ""));

            for (int i = 0; i < code.size(); i++) {
                if (code.get(i) == saisieBot.get(i)) {
                    numberOfCorrect++;
                    codeUsed[i] = saisieUsed[i] = true;
                }
            }

            for (int i = 0; i < code.size(); i++) {
                for (int j = 0; j < saisieBot.size(); j++) {
                    if (!codeUsed[i] && !saisieUsed[j] && code.get(i) == saisieBot.get(j)) {
                        numberOfPresent++;
                        codeUsed[i] = saisieUsed[j] = true;
                        break;
                    }
                }
            }

            for (int i = 0; i < code.size(); i++) {
                for (int j = 0; j < saisieBot.size(); j++) {
                    boolean test = code.get(i) != saisieBot.get(i);
                    if (code.get(i) == saisieBot.get(i)) {
                        saisieBot.get(i);
                    } else if (!codeUsed[i] && !saisieUsed[j] && code.get(i) == saisieBot.get(j)) {
                        if (saisieBot.indexOf(test) == 0) { // SI i EST PRESENT ET PAS DE BIEN PLACE SUR INDEX 0
                            saisieBot.set(0, i); // DEPLACE i SUR INDEX 0 POUR TESTER SI IL PASSE EN BIEN PLACE ICI
                        }
                        if (saisieBot.indexOf(test) == 1) {
                            saisieBot.set(1, i);
                        }
                        if (saisieBot.indexOf(test) == 2) {
                            saisieBot.set(2, i);
                        }
                        if (saisieBot.indexOf(test) == 3) {
                            saisieBot.set(3, i);
                        }
                    } else { saisieBot.set(i, r.nextInt(fourchette) + 1); } // SI NI CORRECT NI PRESENT, NOUVELLE VALEUR
                }
            }

            System.out.println(numberOfCorrect + " Bien placé(s)");
            System.out.println(numberOfPresent + " Présent(s) mais mal placé(s)");
            System.out.printf("%n");

            coups++;
            if (coups == coupsMax) {
                System.out.printf("%n");
                System.out.println("Victoire, l'ordinateur a atteint les " + coupsMax + " coups autorisés");
                Menu.endMenuMastermindDefenseur();
            }
            if (numberOfCorrect == max) {
                System.out.printf("%n");
                System.out.println("Défaite, l'ordinateur a trouvé le code en seulement " + coups + " coups !");
                Menu.endMenuMastermindDefenseur();
            }
        }
    }
}
