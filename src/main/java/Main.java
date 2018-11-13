import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Menu menu = new Menu();
        menu.mainMenu();
    }

    public static void rechercheChallenger() throws FileNotFoundException {

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
        System.out.println("RECHERCHE +/- : CHALLENGER");
        System.out.println("Trouvez le code secret en 10 coups maximum !");
        System.out.printf("%n");

        // GENERATION DU CODE SECRET
        int[] code = new int[max];
        for (int i = 0; i < max; i++) {
            code[i] = r.nextInt(fourchette) + 1;
        }

        while (coups < coupsMax) {

            int[] saisie = new int[max];
            System.out.printf("%n");
            int inputSaisie = sc.nextInt();
            for (int i = 0; i < max; i++) {
                saisie[i] = (int) (inputSaisie / (Math.pow(10, (max - i - 1)))) % 10;
            }

            String resultat = "";
            for (int i = 0; i < max; i++) {
                boolean bonChiffre = saisie[i] == code[i];
                boolean inferieurChiffre = saisie[i] < code[i];
                boolean superieurChiffre = saisie[i] > code[i];
                if (bonChiffre) {
                    resultat = resultat + "=";
                }
                if (inferieurChiffre) {
                    resultat += ">";
                }
                if (superieurChiffre) {
                    resultat = resultat + "<";
                }
            }
            System.out.println(resultat);

            coups++;
            if (coups == coupsMax) {
                System.out.println("Le code secret était " + code[0] + code[1] + code[2] + code[3]);
                System.out.println("Défaite, vous avez atteint les 10 coups autorisés");
                Menu.endMenuRechercheChallenger();
            }
            if (saisie[0] == code[0] && saisie[1] == code[1] && saisie[2] == code[2] && saisie[3] == code[3]) {
                System.out.printf("%n");
                System.out.printf("%n");
                System.out.println("Victoire en seulement " + coups + " coups !");
                Menu.endMenuRechercheChallenger();
            }
        }
    }

    public static void rechercheDefenseur() {
        System.out.println("RECHERCHE +/- : DÉFENSEUR");
    }

    public static void rechercheDuel() {
        System.out.println("RECHERCHE +/- : DUEL");
    }

    public static void mastermindChallenger() throws FileNotFoundException {

        Properties p = new Properties();
        OutputStream os = new FileOutputStream("config.properties");

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
        System.out.println("MASTERMIND : CHALLENGER");
        System.out.println("Trouvez le code secret en 10 coups maximum !");
        System.out.printf("%n");

        // GENERATION DU CODE SECRET
        ArrayList<Integer> code = new ArrayList<Integer>();
        for (int i = 0; i < max; i++) {
            code.add(r.nextInt(fourchette) + 1);
        }
        System.out.println(code);

        while (coups < coupsMax) {

            ArrayList saisie = new ArrayList();
            String inputSaisie = sc.next();
            for (int i = 0; i < max; i++) {
                saisie.add(Integer.parseInt(inputSaisie.charAt(i) + ""));
            }

                int numberOfCorrect = 0;
                int numberOfPresent = 0;

                for (int i = 0; i < code.size(); i++) {
                    boolean Correct = saisie.get(i) == code.get(i);
                    boolean Present = code.contains(saisie.get(i)+"");
                    if (Correct) {
                        numberOfCorrect += 1;
                    } else if (Present) {
                        numberOfPresent += 1;
                    }
                }

                System.out.println(numberOfCorrect + " Bien placé(s)");
                System.out.println(numberOfPresent + " Présent(s) mais mal placé(s)");

                coups++;
                if (coups == coupsMax) {
                    System.out.printf("%n");
                    System.out.println("Le code secret était " + code);
                    System.out.println("Défaite, vous avez atteint les 10 coups autorisés");
                    Menu.endMenuMastermindChallenger();
                }
                if (numberOfCorrect == max) {
                    System.out.printf("%n");
                    System.out.println("Victoire en seulement " + coups + " coups !");
                    Menu.endMenuMastermindChallenger();
                }
            }
        }

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