package jeux;

import launcher.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.util.*;

public class MastermindChallenger {

    public static void mastermindChallenger() throws FileNotFoundException {

        Scanner sc = new Scanner(System.in);
        Random r = new Random();

        ResourceBundle bundle = ResourceBundle.getBundle("config");

        Logger logger = LogManager.getLogger();

        int coups = 0;
        int coupsMax = Integer.parseInt(bundle.getString("coupsMaxMastermindChallenger")); // NOMBRE DE COUPS (CONFIGURABLE)
        int fourchette = Integer.parseInt(bundle.getString("chiffreMax"));       // UTILISER DES CHIFFRES ENTRE 1 ET ... (CONFIGURABLE)
        int max = Integer.parseInt(bundle.getString("tailleCode"));              // TAILLE DU CODE (CONFIGURABLE)
        boolean modeDev = Boolean.parseBoolean(bundle.getString("modeDev"));

        logger.info("LANCEMENT DU JEU : MASTERMIND CHALLENGER");
        logger.trace(coupsMax + " coups maximum");
        logger.trace("Chiffres entre 1 et " + fourchette);
        logger.trace("Taille du code : " + max + " chiffres");
        logger.trace("Mode développeur : " + modeDev);

        System.out.printf("%n");
        System.out.println("MASTERMIND : CHALLENGER");
        System.out.println("Trouvez le code secret en 10 coups maximum !");
        System.out.printf("%n");

        // GENERATION DU CODE SECRET
        ArrayList<Integer> code = new ArrayList<Integer>();
        for (int i = 0; i < max; i++) {
            code.add(r.nextInt(fourchette) + 1);
        }
        logger.info("Code secret généré par l'ordinateur");

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
                        logger.error("Saisie incorrect");
                        inputSaisie = sc.nextInt();
                    }
                    if (saisie[i] > fourchette) {
                        System.out.printf("%n");
                        System.out.println("Saisie incorrect : " + max + " chiffres maximum composés de chiffres entre 1 et " + fourchette);
                        System.out.println("Veuillez entrer une nouvelle saisie ci-dessous :");
                        logger.error("Saisie incorrect");
                        inputSaisie = sc.nextInt();
                    }
                }
                logger.info("L'utilisateur vient d'entrer sa saisie");

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
                logger.info("Traitement de la saisie utilisateur..");

                // INDICES
                System.out.println(numberOfCorrect + " Bien placé(s)");
                System.out.println(numberOfPresent + " Présent(s) mais mal placé(s)");
                System.out.printf("%n");
                coups++;
                logger.info("Les indices ont été envoyés à l'utilisateur");
                logger.trace("Coups : " + coups);

                if (coups == coupsMax) {
                    logger.info("La partie est terminée (Défaite, coups maximum atteint)");
                    System.out.println("Le code secret était " + StringUtils.join(code, ""));
                    System.out.println("Défaite, vous avez atteint les 10 coups autorisés");
                    Menu.endMenuMastermindChallenger();
                }
                if (numberOfCorrect == max) {
                    logger.info("La partie est terminée (Victoire, code trouvé)");
                    System.out.println("Victoire en seulement " + coups + " coups !");
                    Menu.endMenuMastermindChallenger();
                }
            } catch (InputMismatchException e) {
                System.out.printf("%n");
                logger.fatal("InputMismatchException catchée : Saisie incorrect, redémarrage du jeu");
                System.out.println("Saisie incorrecte, les lettres et les chiffres inférieurs à 1 sont interdits !");
                System.out.println("Un nouveau code a été généré..");
                MastermindChallenger.mastermindChallenger();
            }
        }
    }
}
