package launcher;

import jeux.*;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Menu {

    public void mainMenu() throws Exception {

        Logger logger = LogManager.getLogger();
        Scanner choix = new Scanner(System.in);
        int selection;
        int selection2;

        try {
            logger.trace("Affichage du menu principal");
            System.out.printf("%n");
            System.out.println("------------------------------------------");
            System.out.println("MAIN MENU");
            System.out.println("Enter a number to choose a game");
            System.out.println("------------------------------------------");
            System.out.printf("%n");
            System.out.println("1 - Research +/-");
            System.out.printf("%n");
            System.out.println("2 - Mastermind");
            System.out.printf("%n");
            System.out.println("3 - Quit the application");
            System.out.printf("%n");
            System.out.printf("%n");
            selection = choix.nextInt();
            if (selection < 1 || selection > 3) {
                System.out.println("Invalid input : Select an existing menu");
                selection = choix.nextInt();
            }
            System.out.printf("%n");

            if (selection == 1) {
                logger.trace("Sélection du jeu : RECHERCHE +/-");
                System.out.println("WELCOME TO RESEARCH +/-");
                System.out.println("Please select a game mode");
                System.out.println("------------------------------------");
                System.out.printf("%n");
                System.out.println("1 - Challenger mode");
                System.out.printf("%n");
                System.out.println("2 - Defender mode");
                System.out.printf("%n");
                System.out.println("3 - Duel mode");
                System.out.printf("%n");
                System.out.println("4 - Back to main menu");
                System.out.printf("%n");
                System.out.printf("%n");
                selection2 = choix.nextInt();
                if (selection2 < 1 || selection2 > 4) {
                    System.out.println("Invalid input : Select an existing menu");
                    selection2 = choix.nextInt();
                }
                System.out.printf("%n");

                if (selection2 == 1) {
                    logger.trace("Sélection du mode : CHALLENGER");
                    ResearchChallenger researchChallenger = new ResearchChallenger();
                    researchChallenger.main();
                }

                if (selection2 == 2) {
                    logger.trace("Sélection du mode : DEFENSEUR");
                    ResearchDefender researchDefender = new ResearchDefender();
                    researchDefender.main();
                }

                if (selection2 == 3) {
                    logger.trace("Sélection du mode : DUEL");
                    ResearchDuel researchDuel = new ResearchDuel();
                    researchDuel.main();
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
                System.out.printf("%n");
                System.out.println("1 - Challenger mode");
                System.out.printf("%n");
                System.out.println("2 - Defender mode");
                System.out.printf("%n");
                System.out.println("3 - Duel mode");
                System.out.printf("%n");
                System.out.println("4 - Back to main menu");
                System.out.printf("%n");
                System.out.printf("%n");
                selection2 = choix.nextInt();
                if (selection2 < 1 || selection2 > 4) {
                    System.out.println("Invalid input : Select an existing menu");
                    selection2 = choix.nextInt();
                }
                System.out.printf("%n");

                if (selection2 == 1) {
                    logger.trace("Sélection du mode : CHALLENGER");
                    MastermindChallenger mastermindChallenger = new MastermindChallenger();
                    mastermindChallenger.main();
                }

                if (selection2 == 2) {
                    logger.trace("Sélection du mode : DEFENSEUR");
                    MastermindDefender mastermindDefender = new MastermindDefender();
                    mastermindDefender.main();
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

        } catch (InputMismatchException e) {
            System.out.println("Invalid input : Select an existing menu");
            mainMenu();
        }
    }

    public void endMenuMastermindChallenger() throws Exception {
        Logger logger = LogManager.getLogger();
        logger.trace("Affichage du menu de fin");
        Scanner sc = new Scanner(System.in);
        int selection3;

        try {
            System.out.printf("%n");
            System.out.println("1 - Play again");
            System.out.printf("%n");
            System.out.println("2 - Back to main menu");
            System.out.printf("%n");
            System.out.println("3 - Quit the application");
            System.out.printf("%n");
            System.out.printf("%n");
            selection3 = sc.nextInt();
            if (selection3 < 1 || selection3 > 3) {
                System.out.println("Invalid input : Select an existing menu");
                selection3 = sc.nextInt();
            }
            System.out.printf("%n");
            if (selection3 == 1) {
                logger.trace("L'utilisateur souhaite rejouer");
                MastermindChallenger mastermindChallenger = new MastermindChallenger();
                mastermindChallenger.main();
            }
            if (selection3 == 2) {
                logger.trace("L'utilisateur souhaite retourner au menu principal");
                mainMenu();
            }
            if (selection3 == 3) {
                logger.trace("ARRÊT DE L'APPLICATION");
                System.exit(0);
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input : Select an existing menu");
            endMenuMastermindChallenger();
        }
    }

    public void endMenuMastermindDefender() throws Exception {
        Logger logger = LogManager.getLogger();
        logger.trace("Affichage du menu de fin");
        Scanner sc = new Scanner(System.in);
        int selection3;

        try {
            System.out.printf("%n");
            System.out.println("1 - Play again");
            System.out.printf("%n");
            System.out.println("2 - Back to main menu");
            System.out.printf("%n");
            System.out.println("3 - Quit the application");
            System.out.printf("%n");
            System.out.printf("%n");
            selection3 = sc.nextInt();
            if (selection3 < 1 || selection3 > 3) {
                System.out.println("Invalid input : Select an existing menu");
                selection3 = sc.nextInt();
            }
            System.out.printf("%n");
            if (selection3 == 1) {
                logger.trace("L'utilisateur souhaite rejouer");
                MastermindDefender mastermindDefender = new MastermindDefender();
                mastermindDefender.main();
            }
            if (selection3 == 2) {
                logger.trace("L'utilisateur souhaite retourner au menu principal");
                mainMenu();
            }
            if (selection3 == 3) {
                logger.trace("ARRÊT DE L'APPLICATION");
                System.exit(0);
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input : Select an existing menu");
            endMenuMastermindDefender();
        }
    }

    public void endMenuResearchChallenger() throws Exception {
        Logger logger = LogManager.getLogger();
        logger.trace("Affichage du menu de fin");
        Scanner sc = new Scanner(System.in);
        int selection3;

        try {
            System.out.printf("%n");
            System.out.println("1 - Play again");
            System.out.printf("%n");
            System.out.println("2 - Back to main menu");
            System.out.printf("%n");
            System.out.println("3 - Quit the application");
            System.out.printf("%n");
            System.out.printf("%n");
            selection3 = sc.nextInt();
            if (selection3 < 1 || selection3 > 3) {
                System.out.println("Invalid input : Select an existing menu");
                selection3 = sc.nextInt();
            }
            System.out.printf("%n");
            if (selection3 == 1) {
                logger.trace("L'utilisateur souhaite rejouer");
                ResearchChallenger researchChallenger = new ResearchChallenger();
                researchChallenger.main();
            }
            if (selection3 == 2) {
                logger.trace("L'utilisateur souhaite retourner au menu principal");
                mainMenu();
            }
            if (selection3 == 3) {
                logger.trace("ARRÊT DE L'APPLICATION");
                System.exit(0);
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input : Select an existing menu");
            endMenuResearchChallenger();
        }
    }

    public void endMenuResearchDefender() throws Exception {
        Logger logger = LogManager.getLogger();
        logger.trace("Affichage du menu de fin");
        Scanner sc = new Scanner(System.in);
        int selection3;

        try {
            System.out.printf("%n");
            System.out.println("1 - Play again");
            System.out.printf("%n");
            System.out.println("2 - Back to main menu");
            System.out.printf("%n");
            System.out.println("3 - Quit the application");
            System.out.printf("%n");
            System.out.printf("%n");
            selection3 = sc.nextInt();
            if (selection3 < 1 || selection3 > 3) {
                System.out.println("Invalid input : Select an existing menu");
                selection3 = sc.nextInt();
            }
            System.out.printf("%n");
            if (selection3 == 1) {
                logger.trace("L'utilisateur souhaite rejouer");
                ResearchDefender researchDefender = new ResearchDefender();
                researchDefender.main();
            }
            if (selection3 == 2) {
                logger.trace("L'utilisateur souhaite retourner au menu principal");
                mainMenu();
            }
            if (selection3 == 3) {
                logger.trace("ARRÊT DE L'APPLICATION");
                System.exit(0);
            }

        } catch (InputMismatchException e) {
            System.out.println("Invalid input : Select an existing menu");
            endMenuResearchDefender();
        }
    }

    public void endMenuResearchDuel() throws Exception {
        Logger logger = LogManager.getLogger();
        logger.trace("Affichage du menu de fin");
        Scanner sc = new Scanner(System.in);
        int selection3;

        try {
            System.out.printf("%n");
            System.out.println("1 - Play again");
            System.out.printf("%n");
            System.out.println("2 - Back to main menu");
            System.out.printf("%n");
            System.out.println("3 - Quit the application");
            System.out.printf("%n");
            System.out.printf("%n");
            selection3 = sc.nextInt();
            if (selection3 < 1 || selection3 > 3) {
                System.out.println("Invalid input : Select an existing menu");
                selection3 = sc.nextInt();
            }
            System.out.printf("%n");
            if (selection3 == 1) {
                logger.trace("L'utilisateur souhaite rejouer");
                ResearchDuel researchDuel = new ResearchDuel();
                researchDuel.main();
            }
            if (selection3 == 2) {
                logger.trace("L'utilisateur souhaite retourner au menu principal");
                mainMenu();
            }
            if (selection3 == 3) {
                logger.trace("ARRÊT DE L'APPLICATION");
                System.exit(0);
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input : Select an existing menu");
            endMenuResearchDuel();
        }
    }

    public void endMenuMastermindDuel() throws Exception {
        Logger logger = LogManager.getLogger();
        logger.trace("Affichage du menu de fin");
        Scanner sc = new Scanner(System.in);
        int selection3;

        try {
            System.out.printf("%n");
            System.out.println("1 - Play again");
            System.out.printf("%n");
            System.out.println("2 - Back to main menu");
            System.out.printf("%n");
            System.out.println("3 - Quit the application");
            System.out.printf("%n");
            System.out.printf("%n");
            selection3 = sc.nextInt();
            if (selection3 < 1 || selection3 > 3) {
                System.out.println("Invalid input : Select an existing menu");
                selection3 = sc.nextInt();
            }
            System.out.printf("%n");
            if (selection3 == 1) {
                logger.trace("L'utilisateur souhaite rejouer");
                MastermindDuel.mastermindDuel();
            }
            if (selection3 == 2) {
                logger.trace("L'utilisateur souhaite retourner au menu principal");
                mainMenu();
            }
            if (selection3 == 3) {
                logger.trace("ARRÊT DE L'APPLICATION");
                System.exit(0);
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input : Select an existing menu");
            endMenuMastermindDuel();
        }
    }
}
