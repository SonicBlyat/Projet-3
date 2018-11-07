import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

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
        int[] code = new int[max];
        for (int i = 0; i < max; i++) {
            code[i] = r.nextInt(fourchette) + 1;
        }

        while (coups < coupsMax) {

            int[] saisie = new int[max];
            System.out.printf("%n");
            String inputSaisie = sc.next();
            for (int i = 0; i < max; i++) {
                saisie[i] = Integer.parseInt(inputSaisie.charAt(i) + "");
            }

                int numberOfPresent = 0;

                for (int i = 0; i < code.length; i++) {
                    if (code[i] == (saisie[i])) {
                        numberOfPresent += 1;
                    }
                }

                int numberOfCorrect = 0;

                for (int i = 0; i < code.length; i++) {
                    if (Arrays.asList(code[i]).contains(saisie[i])) {
                        numberOfCorrect += 1;
                    }
                }

                System.out.println(numberOfPresent + " Présent(s)");
                System.out.println(numberOfCorrect + " Bien placé(s)");


                coups++;
                if (coups == coupsMax) {
                    System.out.println("Le code secret était " + code[0] + code[1] + code[2] + code[3]);
                    System.out.println("Défaite, vous avez atteint les 10 coups autorisés");
                    Menu.endMenuMastermindChallenger();
                }
                if (saisie[0] == code[0] && saisie[1] == code[1] && saisie[2] == code[2] && saisie[3] == code[3]) {
                    System.out.printf("%n");
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
        System.out.println("Trouvez le code secret en 10 coups maximum !");
        System.out.printf("%n");

        // CHOIX DU CODE SECRET PAR L'UTILISATEUR
        int[] code = new int[max];
        System.out.printf("%n");
        int inputCode = sc.nextInt();
        for (int i = 0; i < max; i++) {
            code[i] = (int) (inputCode / (Math.pow(10, (max - i - 1)))) % 10;
        }

        while (coups < coupsMax) {

            int[] saisie = new int[max];
            System.out.printf("%n");
            int botSaisie = r.nextInt(fourchette) + 1;
            for (int i = 0; i < max; i++) {
                saisie[i] = (int) (botSaisie / (Math.pow(10, (max - i - 1)))) % 10;
            }

            for (int i = 0; i < max; i++) {
                boolean bonChiffre = saisie[i] == code[i];
                System.out.print((bonChiffre ? saisie[i] : "*") + " "); // BON CHIFFRE ? SI OUI AFFICHE SAISIE[i]
            }                                                           // SINON AFFICHE "*"

            coups++;
            if (coups == coupsMax) {
                System.out.println("Le code secret était " + code[0] + code[1] + code[2] + code[3]);
                System.out.println("Défaite, vous avez atteint les 10 coups autorisés");
                Menu.endMenuMastermindDefenseur();
            }
            if (saisie[0] == code[0] && saisie[1] == code[1] && saisie[2] == code[2] && saisie[3] == code[3]) {
                System.out.printf("%n");
                System.out.printf("%n");
                System.out.println("Victoire en seulement " + coups + " coups !");
                Menu.endMenuMastermindDefenseur();
            }
        }
    }
}