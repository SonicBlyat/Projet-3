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

            int numberOfCorrect = 0;
            int numberOfPresent = 0;

            // VERIFICATION
            for (int i = 0; i < code.size(); i++) {
                boolean Correct = saisieBot.get(i) == code.get(i);
                boolean Present = code.contains(saisieBot.get(i));
                if (Correct) {
                    numberOfCorrect += 1;
                    saisieBot.get(i);      // SI CORRECT ON GARDE LA VALEUR
                } else if (Present) {
                    numberOfPresent += 1;
                    saisieBot.set(i, r.nextInt(fourchette) + 1);  // SI PRESENT ON REMPLACE LA VALEUR PAR UNE NOUVELLE ALEATOIRE
                } else if (!Present) {
                    saisieBot.set(i, i + 1);  // SI NON PRESENT ON AJOUTE +1 A LA VALEUR
                    if (saisieBot.get(i) > max) {
                        saisieBot.set(i, r.nextInt(fourchette) + 1); // SI LA VALEUR ATTEINT 4, LA PROCHAINE SERA ALEATOIRE
                    }
                } else { saisieBot.set(i, r.nextInt(fourchette) + 1); } // SI NON CORRECT OU NON PRESENT, REMPLACE PAR NOUVELLE ALEATOIRE
            }

            System.out.println(saisieBot);

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
