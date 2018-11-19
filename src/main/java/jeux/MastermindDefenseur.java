package jeux;

import launcher.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MastermindDefenseur {

    public static void mastermindDefenseur() throws FileNotFoundException {

        Scanner sc = new Scanner(System.in);
        Random r = new Random();

        int coups = 0;
        int coupsMax;   // NOMBRE DE COUPS   (CONFIGURABLE)
        int fourchette; // UTILISER DES CHIFFRES ENTRE 1 ET ... (CONFIGURABLE)
        int max;        // TAILLE DU TABLEAU (CONFIGURABLE)

        System.out.println("Avant de commencer, combien de chiffres souhaitez-vous pour le code secret ?");
        max = sc.nextInt();
        System.out.println("Le code secret sera composé de " + max + " chiffres.");
        System.out.println("---------------------------------------------------");
        System.out.println("Ces chiffres sont compris entre 1 et ... (Entrez le chiffre de votre choix)");
        fourchette = sc.nextInt();
        System.out.println("Les chiffres seront compris entre 1 et " + fourchette + ".");
        System.out.println("----------------------------------------");
        System.out.println("Combien d'essais souhaitez-vous pour trouver le code secret ?");
        coupsMax = sc.nextInt();
        System.out.println("Vous avez " + coupsMax + " essais pour trouver le code secret, à vous de jouer !");
        System.out.println("------------------------------------------------------------------------");
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
            System.out.println(saisieBot);

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
