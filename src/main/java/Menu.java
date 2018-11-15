import java.io.FileNotFoundException;
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
                System.out.println("Ce mode n'est pas encore disponible");
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
                System.out.println("Ce mode n'est pas encore disponible");
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
}
