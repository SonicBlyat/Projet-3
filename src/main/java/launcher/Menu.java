package launcher;

import jeux.*;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Menu {

    public int mainMenu() throws FileNotFoundException {

        Logger logger = LogManager.getLogger();

        Scanner choix = new Scanner(System.in);
        int selection = 0;
        int selection2 = 0;

        logger.trace("Affichage du menu principal");
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
            logger.trace("Sélection du jeu : RECHERCHE +/-");
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
                logger.trace("Sélection du mode : CHALLENGER");
                RechercheChallenger.rechercheChallenger();
            }

            if (selection2 == 2) {
                logger.trace("Sélection du mode : DEFENSEUR");
                RechercheDefenseur.rechercheDefenseur();
            }

            if (selection2 == 3) {
                logger.trace("Sélection du mode : DUEL");
                RechercheDuel.rechercheDuel();
            }

            if (selection2 == 4) {
                logger.trace("Retour au menu principal");
                mainMenu();
            }
        }

        if (selection == 2) {
            logger.trace("Sélection du jeu : MASTERMIND");
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
                logger.trace("Sélection du mode : CHALLENGER");
                MastermindChallenger.mastermindChallenger();
            }

            if (selection2 == 2) {
                logger.trace("Sélection du mode : DEFENSEUR");
                MastermindDefenseur.mastermindDefenseur();
            }

            if (selection2 == 3) {
                logger.trace("Sélection du mode : DUEL");
                MastermindDuel.mastermindDuel();
            }

            if (selection2 == 4) {
                logger.trace("Retour au menu principal");
                mainMenu();
            }
        }

        if (selection == 3) {
            logger.trace("ARRÊT DE L'APPLICATION");
            System.exit(0);
        }

        return selection;
    }

    public static void endMenuMastermindChallenger() throws FileNotFoundException {
        Logger logger = LogManager.getLogger();
        logger.trace("Affichage du menu de fin");
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
            logger.trace("L'utilisateur souhaite rejouer");
            MastermindChallenger.mastermindChallenger();
        }
        if (selection3 == 2) {
            logger.trace("L'utilisateur souhaite retourner au menu principal");
            Menu menu = new Menu();
            menu.mainMenu();
        }
        if (selection3 == 3) {
            logger.trace("ARRÊT DE L'APPLICATION");
            System.exit(0);
        }
    }

    public static void endMenuMastermindDefenseur() throws FileNotFoundException {
        Logger logger = LogManager.getLogger();
        logger.trace("Affichage du menu de fin");
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
            logger.trace("L'utilisateur souhaite rejouer");
            MastermindDefenseur.mastermindDefenseur();
        }
        if (selection3 == 2) {
            logger.trace("L'utilisateur souhaite retourner au menu principal");
            Menu menu = new Menu();
            menu.mainMenu();
        }
        if (selection3 == 3) {
            logger.trace("ARRÊT DE L'APPLICATION");
            System.exit(0);
        }
    }

    public static void endMenuRechercheChallenger() throws FileNotFoundException {
        Logger logger = LogManager.getLogger();
        logger.trace("Affichage du menu de fin");
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
            logger.trace("L'utilisateur souhaite rejouer");
            RechercheChallenger.rechercheChallenger();
        }
        if (selection3 == 2) {
            logger.trace("L'utilisateur souhaite retourner au menu principal");
            Menu menu = new Menu();
            menu.mainMenu();
        }
        if (selection3 == 3) {
            logger.trace("ARRÊT DE L'APPLICATION");
            System.exit(0);
        }
    }

    public static void endMenuRechercheDefenseur() throws FileNotFoundException {
        Logger logger = LogManager.getLogger();
        logger.trace("Affichage du menu de fin");
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
            logger.trace("L'utilisateur souhaite rejouer");
            RechercheDefenseur.rechercheDefenseur();
        }
        if (selection3 == 2) {
            logger.trace("L'utilisateur souhaite retourner au menu principal");
            Menu menu = new Menu();
            menu.mainMenu();
        }
        if (selection3 == 3) {
            logger.trace("ARRÊT DE L'APPLICATION");
            System.exit(0);
        }

    }

    public static void endMenuRechercheDuel() throws FileNotFoundException {
        Logger logger = LogManager.getLogger();
        logger.trace("Affichage du menu de fin");
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
            logger.trace("L'utilisateur souhaite rejouer");
            RechercheDuel.rechercheDuel();
        }
        if (selection3 == 2) {
            logger.trace("L'utilisateur souhaite retourner au menu principal");
            Menu menu = new Menu();
            menu.mainMenu();
        }
        if (selection3 == 3) {
            logger.trace("ARRÊT DE L'APPLICATION");
            System.exit(0);
        }
    }

    public static void endMenuMastermindDuel() throws FileNotFoundException {
        Logger logger = LogManager.getLogger();
        logger.trace("Affichage du menu de fin");
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
            logger.trace("L'utilisateur souhaite rejouer");
            MastermindDuel.mastermindDuel();
        }
        if (selection3 == 2) {
            logger.trace("L'utilisateur souhaite retourner au menu principal");
            Menu menu = new Menu();
            menu.mainMenu();
        }
        if (selection3 == 3) {
            logger.trace("ARRÊT DE L'APPLICATION");
            System.exit(0);
        }
    }
}
