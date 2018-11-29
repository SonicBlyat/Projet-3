package launcher;

import jeux.*;
import java.io.FileNotFoundException;
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
        System.out.println("MAIN MENU");
        System.out.println("Enter a number to choose a game");
        System.out.println("------------------------------------------");
        System.out.println("1 - Research +/-");
        System.out.println("2 - Mastermind");
        System.out.println("3 - Quit the application");
        System.out.printf("%n");
        selection = choix.nextInt();
        System.out.printf("%n");

        if (selection == 1) {
            logger.trace("Sélection du jeu : RECHERCHE +/-");
            System.out.println("WELCOME TO RESEARCH +/-");
            System.out.println("Please select a game mode");
            System.out.println("------------------------------------");
            System.out.println("1 - Challenger mode");
            System.out.println("2 - Defender mode");
            System.out.println("3 - Duel mode");
            System.out.println("4 - Back to main menu");
            System.out.printf("%n");
            selection2 = choix.nextInt();
            System.out.printf("%n");

            if (selection2 == 1) {
                logger.trace("Sélection du mode : CHALLENGER");
                ResearchChallenger.researchChallenger();
            }

            if (selection2 == 2) {
                logger.trace("Sélection du mode : DEFENSEUR");
                ResearchDefender.researchDefender();
            }

            if (selection2 == 3) {
                logger.trace("Sélection du mode : DUEL");
                ResearchDuel.researchDuel();
            }

            if (selection2 == 4) {
                logger.trace("Retour au menu principal");
                mainMenu();
            }
        }

        if (selection == 2) {
            logger.trace("Sélection du jeu : MASTERMIND");
            System.out.println("WELCOME TO MASTERMIND");
            System.out.println("Please select a game mode");
            System.out.println("------------------------------------");
            System.out.println("1 - Challenger mode");
            System.out.println("2 - Defender mode");
            System.out.println("3 - Duel mode");
            System.out.println("4 - Back to main menu");
            System.out.printf("%n");
            selection2 = choix.nextInt();
            System.out.printf("%n");

            if (selection2 == 1) {
                logger.trace("Sélection du mode : CHALLENGER");
                MastermindChallenger.mastermindChallenger();
            }

            if (selection2 == 2) {
                logger.trace("Sélection du mode : DEFENSEUR");
                MastermindDefender.mastermindDefender();
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
        System.out.println("1 - Play again");
        System.out.println("2 - Back to main menu");
        System.out.println("3 - Quit the application");
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

    public static void endMenuMastermindDefender() throws FileNotFoundException {
        Logger logger = LogManager.getLogger();
        logger.trace("Affichage du menu de fin");
        Scanner sc = new Scanner(System.in);
        int selection3 = 0;
        System.out.printf("%n");
        System.out.println("1 - Play again");
        System.out.println("2 - Back to main menu");
        System.out.println("3 - Quit the application");
        System.out.printf("%n");
        selection3 = sc.nextInt();
        System.out.printf("%n");
        if (selection3 == 1) {
            logger.trace("L'utilisateur souhaite rejouer");
            MastermindDefender.mastermindDefender();
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

    public static void endMenuResearchChallenger() throws FileNotFoundException {
        Logger logger = LogManager.getLogger();
        logger.trace("Affichage du menu de fin");
        Scanner sc = new Scanner(System.in);
        int selection3 = 0;
        System.out.printf("%n");
        System.out.println("1 - Play again");
        System.out.println("2 - Back to main menu");
        System.out.println("3 - Quit the application");
        System.out.printf("%n");
        selection3 = sc.nextInt();
        System.out.printf("%n");
        if (selection3 == 1) {
            logger.trace("L'utilisateur souhaite rejouer");
            ResearchChallenger.researchChallenger();
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

    public static void endMenuResearchDefender() throws FileNotFoundException {
        Logger logger = LogManager.getLogger();
        logger.trace("Affichage du menu de fin");
        Scanner sc = new Scanner(System.in);
        int selection3 = 0;
        System.out.printf("%n");
        System.out.println("1 - Play again");
        System.out.println("2 - Back to main menu");
        System.out.println("3 - Quit the application");
        System.out.printf("%n");
        selection3 = sc.nextInt();
        System.out.printf("%n");
        if (selection3 == 1) {
            logger.trace("L'utilisateur souhaite rejouer");
            ResearchDefender.researchDefender();
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

    public static void endMenuResearchDuel() throws FileNotFoundException {
        Logger logger = LogManager.getLogger();
        logger.trace("Affichage du menu de fin");
        Scanner sc = new Scanner(System.in);
        int selection3 = 0;
        System.out.printf("%n");
        System.out.println("1 - Play again");
        System.out.println("2 - Back to main menu");
        System.out.println("3 - Quit the application");
        System.out.printf("%n");
        selection3 = sc.nextInt();
        System.out.printf("%n");
        if (selection3 == 1) {
            logger.trace("L'utilisateur souhaite rejouer");
            ResearchDuel.researchDuel();
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
        System.out.println("1 - Play again");
        System.out.println("2 - Back to main menu");
        System.out.println("3 - Quit the application");
        System.out.printf("%n");
        selection3 = sc.nextInt();
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
