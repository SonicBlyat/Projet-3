package jeux;

import launcher.*;
import org.apache.commons.lang3.StringUtils;

import java.io.FileNotFoundException;
import java.util.*;

public class MastermindChallenger {

    public static void mastermindChallenger() throws FileNotFoundException {

        Scanner sc = new Scanner(System.in);
        Random r = new Random();

        ResourceBundle bundle = ResourceBundle.getBundle("config");

        int coups = 0;
        int coupsMax = Integer.parseInt(bundle.getString("coupsMaxMastermindChallenger")); // NOMBRE DE COUPS (CONFIGURABLE)
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
            System.out.println("SOLUTION : " + code);
            System.out.printf("%n");
        }

        while (coups < coupsMax) {

            try {
                // SAISIE UTILISATEUR
                int[] saisie = new int[max];
                int inputSaisie = sc.nextInt();
                for (int i = 0; i < max; i++) {
                    saisie[i] = (int) (inputSaisie / (Math.pow(10, (max - i - 1)))) % 10;
                    if (saisie[i] < 1) {
                        System.out.printf("%n");
                        System.out.println("Saisie incorrect : " + max + " chiffres maximum composés de chiffres entre 1 et " + fourchette);
                        System.out.println("Veuillez entrer une nouvelle saisie ci-dessous :");
                        inputSaisie = sc.nextInt();
                    }
                    if (saisie[i] > fourchette) {
                        System.out.printf("%n");
                        System.out.println("Saisie incorrect : " + max + " chiffres maximum composés de chiffres entre 1 et " + fourchette);
                        System.out.println("Veuillez entrer une nouvelle saisie ci-dessous :");
                        inputSaisie = sc.nextInt();
                    }
                }

                // RESULTAT DE LA SAISIE UTILISATEUR
                boolean[] codeUsed = new boolean[code.size()];
                boolean[] saisieUsed = new boolean[saisie.length];
                int numberOfCorrect = 0;
                int numberOfPresent = 0;

                for (int i = 0; i < code.size(); i++) {
                    if (code.get(i) == saisie[i]) {
                        numberOfCorrect++;
                        codeUsed[i] = saisieUsed[i] = true;
                    }
                }

                for (int i = 0; i < code.size(); i++) {
                    for (int j = 0; j < saisie.length; j++) {
                        if (!codeUsed[i] && !saisieUsed[j] && code.get(i) == saisie[j]) {
                            numberOfPresent++;
                            codeUsed[i] = saisieUsed[j] = true;
                            break;
                        }
                    }
                }

                // INDICES
                System.out.println(numberOfCorrect + " Bien placé(s)");
                System.out.println(numberOfPresent + " Présent(s) mais mal placé(s)");
                System.out.printf("%n");
                coups++;

                if (coups == coupsMax) {
                    System.out.println("Le code secret était " + StringUtils.join(code, ""));
                    System.out.println("Défaite, vous avez atteint les 10 coups autorisés");
                    Menu.endMenuMastermindChallenger();
                }
                if (numberOfCorrect == max) {
                    System.out.println("Victoire en seulement " + coups + " coups !");
                    Menu.endMenuMastermindChallenger();
                }
            } catch (InputMismatchException e) {
                System.out.printf("%n");
                System.out.println("Saisie incorrecte, les lettres et les chiffres inférieurs à 1 sont interdits !");
                System.out.println("Un nouveau code a été généré..");
                MastermindChallenger.mastermindChallenger();
            }
        }
    }
}
