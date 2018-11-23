package jeux;

import launcher.*;
import org.apache.commons.lang3.StringUtils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;

public class MastermindChallenger {

    public static void mastermindChallenger() throws FileNotFoundException {

        Scanner sc = new Scanner(System.in);
        Random r = new Random();

        ResourceBundle bundle = ResourceBundle.getBundle("config");

        int coups = 0;
        int coupsMax = Integer.parseInt(bundle.getString("coupsMaxChallenger")); // NOMBRE DE COUPS (CONFIGURABLE)
        int fourchette = Integer.parseInt(bundle.getString("chiffreMax"));       // UTILISER DES CHIFFRES ENTRE 1 ET ... (CONFIGURABLE)
        int max = Integer.parseInt(bundle.getString("tailleCode"));              // TAILLE DU CODE (CONFIGURABLE)
        boolean modeDev = Boolean.parseBoolean(bundle.getString("modeDev"));

        System.out.printf("%n");
        System.out.println("MASTERMIND : CHALLENGER");
        System.out.println("Trouvez le code secret en 10 coups maximum !");
        System.out.printf("%n");

        // GENERATION DU CODE SECRET
        ArrayList<Integer> code = new ArrayList<Integer>();
        for (int i = 0; i < max; i++) {
            code.add(r.nextInt(fourchette) + 1);
        }

        if (modeDev == true) {
            System.out.println("SOLUTION : " + StringUtils.join(code, ""));
        }

        while (coups < coupsMax) {

            ArrayList saisie = new ArrayList();
            String inputSaisie = sc.next();
            for (int i = 0; i < max; i++) {
                saisie.add(Integer.parseInt(inputSaisie.charAt(i) + ""));
            }

            if (inputSaisie == "" || inputSaisie.length() > max) {
                System.out.println("Veuillez entrer une proposition à " + max + " chiffres");
            }

            boolean[] codeUsed = new boolean[code.size()];
            boolean[] saisieUsed = new boolean[saisie.size()];
            int numberOfCorrect = 0;
            int numberOfPresent = 0;

            for (int i = 0; i < code.size(); i++) {
                if (code.get(i) == saisie.get(i)) {
                    numberOfCorrect++;
                    codeUsed[i] = saisieUsed[i] = true;
                }
            }

            for (int i = 0; i < code.size(); i++) {
                for (int j = 0; j < saisie.size(); j++) {
                    if (!codeUsed[i] && !saisieUsed[j] && code.get(i) == saisie.get(j)) {
                        numberOfPresent++;
                        codeUsed[i] = saisieUsed[j] = true;
                        break;
                    }
                }
            }

            System.out.println(numberOfCorrect + " Bien placé(s)");
            System.out.println(numberOfPresent + " Présent(s) mais mal placé(s)");
            System.out.printf("%n");
            coups++;
            if (coups == coupsMax) {
                System.out.printf("%n");
                System.out.println("Le code secret était " + StringUtils.join(code, ""));
                System.out.println("Défaite, vous avez atteint les 10 coups autorisés");
                Menu.endMenuMastermindChallenger();
            }
            if (numberOfCorrect == max) {
                System.out.printf("%n");
                System.out.println("Victoire en seulement " + coups + " coups !");
                Menu.endMenuMastermindChallenger();
            }
        }
    }
}
