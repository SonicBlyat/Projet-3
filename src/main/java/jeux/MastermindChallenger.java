import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

public class MastermindChallenger {

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
        ArrayList<Integer> code = new ArrayList<Integer>();
        for (int i = 0; i < max; i++) {
            code.add(r.nextInt(fourchette) + 1);
        }
        System.out.println(code);

        while (coups < coupsMax) {

            ArrayList<Integer> saisie = new ArrayList<Integer>();
            String inputSaisie = sc.next();
            for (int i = 0; i < max; i++) {
                saisie.add(Integer.parseInt(inputSaisie.charAt(i) + ""));
            }

            // boolean combi = true;
            int mal = 0, bien = 0;
            // Première boucle parcourant la combinaison à deviner
            for (int i = 0; i < max; i++) {
                boolean bienPlace = false;
                boolean malPlace = false;
                // Deuxième boucle parcourant la proposition
                for (int j = 0; j < max; j++) {

                    int cmb = saisie.get(i);
                    // Comparaison de la proposition par rapport à la combinaison
                    if (cmb == code.get(i)) {
                        if (i == j) {
                            bienPlace = true;
                            if (code.contains(saisie.get(i))){
                                malPlace = true;
                            }
                        }
                    }

                    if (bienPlace == true)
                        bien++;
                    else if (malPlace == true)
                        mal++;
                }}
            System.out.println(mal + " chiffres présents et " + bien + " bien placés");

            coups++;
            if (coups == coupsMax) {
                System.out.printf("%n");
                System.out.println("Le code secret était " + code);
                System.out.println("Défaite, vous avez atteint les 10 coups autorisés");
                Menu.endMenuMastermindChallenger();
            }
            if (bien == max) {
                System.out.printf("%n");
                System.out.println("Victoire en seulement " + coups + " coups !");
                Menu.endMenuMastermindChallenger();
            }
        }
    }
}