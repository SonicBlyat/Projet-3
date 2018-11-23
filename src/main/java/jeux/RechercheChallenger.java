package jeux;

import launcher.Menu;
import org.apache.commons.lang3.StringUtils;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;

public class RechercheChallenger {

    public static void rechercheChallenger() throws FileNotFoundException {

        Scanner sc = new Scanner(System.in);
        Random r = new Random();

        ResourceBundle bundle = ResourceBundle.getBundle("config");

        int coups = 0;
        int coupsMax = Integer.parseInt(bundle.getString("coupsMaxChallenger")); // NOMBRE DE COUPS (CONFIGURABLE)
        int fourchette = Integer.parseInt(bundle.getString("chiffreMax"));       // UTILISER DES CHIFFRES ENTRE 1 ET ... (CONFIGURABLE)
        int max = Integer.parseInt(bundle.getString("tailleCode"));              // TAILLE DU CODE (CONFIGURABLE)
        boolean modeDev = Boolean.parseBoolean(bundle.getString("modeDev"));

        System.out.printf("%n");
        System.out.println("RECHERCHE +/- : CHALLENGER");
        System.out.println("Trouvez le code secret en 10 coups maximum !");
        System.out.printf("%n");

        // GENERATION DU CODE SECRET
        int[] code = new int[max];
        for (int i = 0; i < max; i++) {
            code[i] = r.nextInt(fourchette) + 1;
        }

        if (modeDev == true) {
            System.out.println("SOLUTION : " + Arrays.toString(code));
        }

        while (coups < coupsMax) {

            int[] saisie = new int[max];
            System.out.printf("%n");
            int inputSaisie = sc.nextInt();
            for (int i = 0; i < max; i++) {
                saisie[i] = (int) (inputSaisie / (Math.pow(10, (max - i - 1)))) % 10;
            }

            String resultat = "";
            for (int i = 0; i < max; i++) {
                boolean bonChiffre = saisie[i] == code[i];
                boolean inferieurChiffre = saisie[i] < code[i];
                boolean superieurChiffre = saisie[i] > code[i];
                if (bonChiffre) {
                    resultat = resultat + "=";
                }
                if (inferieurChiffre) {
                    resultat += ">";
                }
                if (superieurChiffre) {
                    resultat = resultat + "<";
                }
            }
            System.out.println(resultat);
            int numberOfCorrectUser = StringUtils.countMatches(resultat, "="); // COMPTE LE NOMBRE DE "="
            coups++;

            if (coups == coupsMax) {
                System.out.printf("%n");
                System.out.println("Le code secret était " + Arrays.toString(code));
                System.out.println("Défaite, vous avez atteint les 10 coups autorisés");
                Menu.endMenuRechercheChallenger();
            }
            if (numberOfCorrectUser == max) {
                System.out.printf("%n");
                System.out.println("Victoire en seulement " + coups + " coups !");
                Menu.endMenuRechercheChallenger();
            }
        }
    }
}