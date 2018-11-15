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

            for (int i = 0; i < code.length; i++) {
                boolean egal = TryBot.get(i) == code[i];
                boolean inferieur = TryBot.get(i) < code[i];
                boolean superieur = TryBot.get(i) > code[i];

                if (superieur) {
                    TryBot.set(i, r.nextInt(fourchette));
                    break;
                }

                if (inferieur) {
                    TryBot.set(i, r.nextInt(fourchette));
                    break;
                }

                if (egal) {
                    TryBot.get(i);
                    break;
                }
            }

            System.out.println(TryBot);


            coups++;
            if (coups == coupsMax) {
                System.out.printf("%n");
                System.out.println("Victoire, l'ordinateur a atteint les " + coupsMax + " coups autorisés");
                Menu.endMenuRechercheDefenseur();
            }
            if (TryBot.get(0) == code[0] && TryBot.get(1) == code[1] && TryBot.get(2) == code[2] && TryBot.get(3) == code[3]) {
                System.out.printf("%n");
                System.out.println("Défaite, l'ordinateur a trouvé le code en seulement " + coups + " coups !");
                Menu.endMenuRechercheDefenseur();
            }
        }
    }
}
