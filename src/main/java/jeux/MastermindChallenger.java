package jeux;

import launcher.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.util.*;

public class MastermindChallenger {

    public static void mastermindChallenger() throws Exception {

        Scanner sc = new Scanner(System.in);
        Random r = new Random();
        ResourceBundle bundle = ResourceBundle.getBundle("config");
        Logger logger = LogManager.getLogger();

        int userTry = 0;                                                                    // CURRENT TRY
        int maxTry = Integer.parseInt(bundle.getString("maxTryMastermindChallenger"));  // NUMBER OF TRY ALLOWED
        int maxNumber = Integer.parseInt(bundle.getString("maxNumber"));                // USE DIGITS BETWEEN 1 AND ...
        int codeSize = Integer.parseInt(bundle.getString("codeSize"));                  // CODE SIZE
        boolean devMode = Boolean.parseBoolean(bundle.getString("devMode"));            // DEVELOPER MODE

        logger.info("LANCEMENT DU JEU : MASTERMIND CHALLENGER");
        logger.trace(maxTry + " coups maximum");
        logger.trace("Chiffres entre 1 et " + maxNumber);
        logger.trace("Taille du code : " + codeSize + " chiffres");
        logger.trace("Mode développeur : " + devMode);

        System.out.printf("%n");
        System.out.println("MASTERMIND : CHALLENGER MODE");
        System.out.println("Find the secret code, you have " + maxTry  + " try !");
        System.out.printf("%n");

        // RANDOM SECRET CODE
        ArrayList<Integer> code = new ArrayList<Integer>();
        for (int i = 0; i < codeSize; i++) {
            code.add(r.nextInt(maxNumber) + 1);
        }
        logger.info("Code secret généré par l'ordinateur");

        if (devMode) {
            System.out.println("[DEV MODE] CODE : " + code);
            System.out.printf("%n");
        }

        while (userTry < maxTry) {

            try {
                // USER INPUT
                int[] input = new int[codeSize];
                int inputScanner = sc.nextInt();
                for (int i = 0; i < codeSize; i++) {
                    input[i] = (int) (inputScanner / (Math.pow(10, (codeSize - i - 1)))) % 10;
                    if (input[i] < 1) {
                        System.out.printf("%n");
                        System.out.println("Invalid input : Enter " + codeSize + " digits between 1 and " + maxNumber);
                        System.out.println("Please enter a valid input below :");
                        logger.error("Saisie incorrect");
                        inputScanner = sc.nextInt();
                    }
                    if (input[i] > maxNumber) {
                        System.out.printf("%n");
                        System.out.println("Invalid input : Enter " + codeSize + " digits between 1 and " + maxNumber);
                        System.out.println("Please enter a valid input below :");
                        logger.error("Saisie incorrect");
                        inputScanner = sc.nextInt();
                    }
                }
                logger.info("L'utilisateur vient d'entrer sa saisie");

                // RESULT FOR USER INPUT
                boolean[] codeUsed = new boolean[code.size()];
                boolean[] inputUsed = new boolean[input.length];
                int numberOfCorrect = 0;
                int numberOfPresent = 0;

                for (int i = 0; i < code.size(); i++) {
                    if (code.get(i) == input[i]) {
                        numberOfCorrect++;
                        codeUsed[i] = inputUsed[i] = true;
                    }
                }

                for (int i = 0; i < code.size(); i++) {
                    for (int j = 0; j < input.length; j++) {
                        if (!codeUsed[i] && !inputUsed[j] && code.get(i) == input[j]) {
                            numberOfPresent++;
                            codeUsed[i] = inputUsed[j] = true;
                            break;
                        }
                    }
                }
                logger.info("Traitement de la saisie utilisateur..");

                // CLUES
                System.out.println(numberOfCorrect + " Correct");
                System.out.println(numberOfPresent + " Present but wrong position");
                System.out.printf("%n");
                userTry++;
                logger.info("Les indices ont été envoyés à l'utilisateur");
                logger.trace("Coups : " + userTry);

                if (userTry == maxTry) {
                    logger.info("La partie est terminée (Défaite, coups maximum atteint)");
                    System.out.println("The secret code was " + StringUtils.join(code, ""));
                    System.out.println("Defeat, you have reached the " + maxTry + " allowed try");
                    Menu menu = new Menu();
                    menu.endMenuMastermindChallenger();
                }
                if (numberOfCorrect == codeSize) {
                    logger.info("La partie est terminée (Victoire, code trouvé)");
                    System.out.println("Victory in only " + userTry + " try !");
                    Menu menu = new Menu();
                    menu.endMenuMastermindChallenger();
                }
            } catch (InputMismatchException e) {
                System.out.printf("%n");
                logger.fatal("InputMismatchException catchée : Saisie incorrect, redémarrage du jeu");
                System.out.println("Invalid input, letters and digits less than 1 are forbidden !");
                System.out.println("A new secret code has been generated..");
                MastermindChallenger.mastermindChallenger();
            }
        }
    }
}
