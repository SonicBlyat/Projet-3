import java.util.Scanner;

public class Menu {

    public int mainMenu() {

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

            if (selection2 == 1) {
                Main.rechercheChallenger();
            }

            if (selection2 == 2) {
                Main.rechercheDefenseur();
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

            selection2 = choix.nextInt();

            if (selection2 == 1) {
                Main.mastermindChallenger();
            }

            if (selection2 == 2) {
                Main.mastermindDefenseur();
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

    public static void endMenuMastermindChallenger() {
        Scanner sc = new Scanner(System.in);
        int selection3 = 0;
        System.out.printf("%n");
        System.out.println("1 - Rejouer");
        System.out.println("2 - Retour au menu principal");
        System.out.println("3 - Quitter l'application");
        selection3 = sc.nextInt();
        if (selection3 == 1) {
            Main.mastermindChallenger();
        }
        if (selection3 == 2) {
            Menu menu = new Menu();
            menu.mainMenu();
        }
        if (selection3 == 3) {
            System.exit(0);
        }
    }

    public static void endMenuMastermindDefenseur() {
        Scanner sc = new Scanner(System.in);
        int selection3 = 0;
        System.out.printf("%n");
        System.out.println("1 - Rejouer");
        System.out.println("2 - Retour au menu principal");
        System.out.println("3 - Quitter l'application");
        selection3 = sc.nextInt(); // SAISIE POUR REJOUER / RETOUR AU MENU PRINCIPAL / QUITTER
        if (selection3 == 1) {
            Main.mastermindDefenseur();
        }
        if (selection3 == 2) {
            Menu menu = new Menu();
            menu.mainMenu();
        }
        if (selection3 == 3) {
            System.exit(0);
        }
    }

    public static void endMenuRechercheChallenger() {
        Scanner sc = new Scanner(System.in);
        int selection3 = 0;
        System.out.printf("%n");
        System.out.println("1 - Rejouer");
        System.out.println("2 - Retour au menu principal");
        System.out.println("3 - Quitter l'application");
        selection3 = sc.nextInt(); // SAISIE POUR REJOUER / RETOUR AU MENU PRINCIPAL / QUITTER
        if (selection3 == 1) {
            Main.rechercheChallenger();
        }
        if (selection3 == 2) {
            Menu menu = new Menu();
            menu.mainMenu();
        }
        if (selection3 == 3) {
            System.exit(0);
        }
    }

    public static void endMenuRechercheDefenseur() {
        Scanner sc = new Scanner(System.in);
        int selection3 = 0;
        System.out.printf("%n");
        System.out.println("1 - Rejouer");
        System.out.println("2 - Retour au menu principal");
        System.out.println("3 - Quitter l'application");
        selection3 = sc.nextInt(); // SAISIE POUR REJOUER / RETOUR AU MENU PRINCIPAL / QUITTER
        if (selection3 == 1) {
            Main.rechercheDefenseur();
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
