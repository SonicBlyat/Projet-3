import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class RechercheDefenseur {

    public static void rechercheDefenseur() throws FileNotFoundException {

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

        System.out.println("RECHERCHE : DEFENSEUR");
        System.out.println("Choisissez une combinaison !");
        System.out.printf("%n");

        // GENERATION DU CODE PAR L'UTILISATEUR
        int[] code = new int[max];
        String inputCode = sc.next();
        for (int i = 0; i < max; i++) {
            code[i] = (Integer.parseInt(inputCode.charAt(i) + ""));
        }

        // PREMIER ESSAI DU BOT
        ArrayList<Integer> TryBot = new ArrayList<Integer>();
        for (int i = 0; i < max; i++) {
            TryBot.add(r.nextInt(fourchette) + 1);
        }


        while (coups < coupsMax) {
            for (int i = 0; i < max; i++) {
                System.out.println(TryBot);
                System.out.println("Donnez des indices : ");
                String[] reponse = new String[max];
                String inputReponse = sc.next();
                for (i = 0; i < max; i++) {
                    reponse[i] = (inputReponse.charAt(i) + "");
                }

                for (i = 0; i < reponse.length; i++) {
                    if (reponse[i].equals("=")) {
                        TryBot.get(i);
                    } else if (reponse[i].equals("+")) {
                        TryBot.set(i, TryBot.get(i) + 1);
                    } else if (reponse[i].equals("-")) {
                        TryBot.set(i, TryBot.get(i) - 1);
                    }
                }

                coups++;
                if (coups == coupsMax) {
                    System.out.printf("%n");
                    System.out.println("Victoire, l'ordinateur a atteint les " + coupsMax + " coups autorisés");
                    Menu.endMenuRechercheDefenseur();
                }
                if (reponse[0].equals("=") && reponse[1].equals("=") && reponse[2].equals("=") && reponse[3].equals("=")) {
                    System.out.printf("%n");
                    System.out.println("Défaite, l'ordinateur a trouvé le code en seulement " + coups + " coups !");
                    Menu.endMenuRechercheDefenseur();
                }
            }
        }
    }
}
