package launcher;

import jeux.*;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class Menu {

    public int mainMenu() throws FileNotFoundException {

        Scanner choix = new Scanner(System.in);
        int selection = 0;
        int selection2 = 0;

        System.out.println("MENU PRINCIPAL");
        System.out.println("Entrez le numéro correspondant à votre jeu");
        System.out.println("------------------------------------------");
        System.out.println("1 - Recherche +/-");
        System.out.println("2 - Mastermind");
        System.out.println("3 - Quitter l'application");
        System.out.printf("%n");
        selection = choix.nextInt();
        System.out.printf("%n");

        if (selection == 1) {
            System.out.println("BIENVENUE DANS RECHERCHE +/-");
            System.out.println("Veuillez sélectionner un mode de jeu");
            System.out.println("------------------------------------");
            System.out.println("1 - Mode challenger");
            System.out.println("2 - Mode défenseur");
            System.out.println("3 - Mode duel");
            System.out.println("4 - Retour au menu principal");
            System.out.printf("%n");
            selection2 = choix.nextInt();
            System.out.printf("%n");

            if (selection2 == 1) {
                RechercheChallenger.rechercheChallenger();
            }

            if (selection2 == 2) {
                RechercheDefenseur.rechercheDefenseur();
            }

            if (selection2 == 3) {
                RechercheDuel.rechercheDuel();
            }

            if (selection2 == 4) {
                mainMenu();
            }
        }

        if (selection == 2) {
            System.out.println("BIENVENUE DANS MASTERMIND");
            System.out.println("Veuillez sélectionner un mode de jeu");
            System.out.println("------------------------------------");
            System.out.println("1 - Mode challenger");
            System.out.println("2 - Mode défenseur");
            System.out.println("3 - Mode duel");
            System.out.println("4 - Retour au menu principal");
            System.out.printf("%n");
            selection2 = choix.nextInt();
            System.out.printf("%n");

            if (selection2 == 1) {
                MastermindChallenger.mastermindChallenger();
            }

            if (selection2 == 2) {
                MastermindDefenseur.mastermindDefenseur();
            }

            if (selection2 == 3) {
                MastermindDuel.mastermindDuel();
            }

            if (selection2 == 4) {
                mainMenu();
            }
        }

        if (selection == 3) {
            System.exit(0);
        }

        return selection;
    }

    public static void endMenuMastermindChallenger() throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        int selection3 = 0;
        System.out.printf("%n");
        System.out.println("1 - Rejouer");
        System.out.println("2 - Retour au menu principal");
        System.out.println("3 - Quitter l'application");
        System.out.printf("%n");
        selection3 = sc.nextInt();
        System.out.printf("%n");
        if (selection3 == 1) {
            MastermindChallenger.mastermindChallenger();
        }
        if (selection3 == 2) {
            Menu menu = new Menu();
            menu.mainMenu();
        }
        if (selection3 == 3) {
            System.exit(0);
        }
    }

    public static void endMenuMastermindDefenseur() throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        int selection3 = 0;
        System.out.printf("%n");
        System.out.println("1 - Rejouer");
        System.out.println("2 - Retour au menu principal");
        System.out.println("3 - Quitter l'application");
        System.out.printf("%n");
        selection3 = sc.nextInt(); // SAISIE POUR REJOUER / RETOUR AU MENU PRINCIPAL / QUITTER
        System.out.printf("%n");
        if (selection3 == 1) {
            MastermindDefenseur.mastermindDefenseur();
        }
        if (selection3 == 2) {
            Menu menu = new Menu();
            menu.mainMenu();
        }
        if (selection3 == 3) {
            System.exit(0);
        }
    }

    public static void endMenuRechercheChallenger() throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        int selection3 = 0;
        System.out.printf("%n");
        System.out.println("1 - Rejouer");
        System.out.println("2 - Retour au menu principal");
        System.out.println("3 - Quitter l'application");
        System.out.printf("%n");
        selection3 = sc.nextInt(); // SAISIE POUR REJOUER / RETOUR AU MENU PRINCIPAL / QUITTER
        System.out.printf("%n");
        if (selection3 == 1) {
            RechercheChallenger.rechercheChallenger();
        }
        if (selection3 == 2) {
            Menu menu = new Menu();
            menu.mainMenu();
        }
        if (selection3 == 3) {
            System.exit(0);
        }
    }

    public static void endMenuRechercheDefenseur() throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        int selection3 = 0;
        System.out.printf("%n");
        System.out.println("1 - Rejouer");
        System.out.println("2 - Retour au menu principal");
        System.out.println("3 - Quitter l'application");
        System.out.printf("%n");
        selection3 = sc.nextInt(); // SAISIE POUR REJOUER / RETOUR AU MENU PRINCIPAL / QUITTER
        System.out.printf("%n");
        if (selection3 == 1) {
            RechercheDefenseur.rechercheDefenseur();
        }
        if (selection3 == 2) {
            Menu menu = new Menu();
            menu.mainMenu();
        }
        if (selection3 == 3) {
            System.exit(0);
        }

    }

    public static void endMenuRechercheDuel() throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        int selection3 = 0;
        System.out.printf("%n");
        System.out.println("1 - Rejouer");
        System.out.println("2 - Retour au menu principal");
        System.out.println("3 - Quitter l'application");
        System.out.printf("%n");
        selection3 = sc.nextInt(); // SAISIE POUR REJOUER / RETOUR AU MENU PRINCIPAL / QUITTER
        System.out.printf("%n");
        if (selection3 == 1) {
            RechercheDuel.rechercheDuel();
        }
        if (selection3 == 2) {
            Menu menu = new Menu();
            menu.mainMenu();
        }
        if (selection3 == 3) {
            System.exit(0);
        }
    }

    public static void endMenuMastermindDuel() throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        int selection3 = 0;
        System.out.printf("%n");
        System.out.println("1 - Rejouer");
        System.out.println("2 - Retour au menu principal");
        System.out.println("3 - Quitter l'application");
        System.out.printf("%n");
        selection3 = sc.nextInt(); // SAISIE POUR REJOUER / RETOUR AU MENU PRINCIPAL / QUITTER
        System.out.printf("%n");
        if (selection3 == 1) {
            MastermindDuel.mastermindDuel();
        }
        if (selection3 == 2) {
            Menu menu = new Menu();
            menu.mainMenu();
        }
        if (selection3 == 3) {
            System.exit(0);
        }
    }

    public static void choixParametres() {
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
    }
}
