import java.util.Scanner;

public class Menu {

    public int mainMenu() {

        Scanner choix = new Scanner(System.in);
        int selection = 0;

        System.out.println("Vous êtes sur le menu principal");
        System.out.println("Entrez le numéro correspondant à votre choix");
        System.out.println("-------------------------------");
        System.out.println("1 - Mode challenger");
        System.out.println("2 - Mode défenseur");
        System.out.println("3 - Mode duel");
        System.out.println("4 - Quitter l'application");

        selection = choix.nextInt();

        if (selection == 1) {
            Main.challenger();
        }

        if (selection == 2) {
            System.out.println("------------------------------------------");
            System.out.println("CE MODE DE JEU N'EST PAS ENCORE DISPONIBLE");
            System.out.println("------------------------------------------");
            mainMenu();
        }

        if (selection == 3) {
            System.out.println("------------------------------------------");
            System.out.println("CE MODE DE JEU N'EST PAS ENCORE DISPONIBLE");
            System.out.println("------------------------------------------");
            mainMenu();
        }

        if (selection == 4) {
            System.exit(0);
        }

        if (selection >= 5) {
            System.out.println("------------------------------------------");
            System.out.println("SAISIE INCORRECT, VEUILLEZ SAISIR UN CHOIX EXISTANT");
            System.out.println("------------------------------------------");
            mainMenu();
        }

        return selection;
    }
}
