package jeux;

import launcher.Menu;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.util.*;

public class ResearchChallenger {

    public static void researchChallenger() throws FileNotFoundException {

        Scanner sc = new Scanner(System.in);
        Random r = new Random();
        ResourceBundle bundle = ResourceBundle.getBundle("config");
        Logger logger = LogManager.getLogger();

        int userTry = 0;                                                                  // CURRENT TRY
        int maxTry = Integer.parseInt(bundle.getString("maxTryResearchChallenger"));  // NUMBER OF TRY ALLOWED
        int maxNumber = Integer.parseInt(bundle.getString("maxNumber"));              // USE DIGITS BETWEEN 1 AND ...
        int codeSize = Integer.parseInt(bundle.getString("codeSize"));                // CODE SIZE
        boolean devMode = Boolean.parseBoolean(bundle.getString("devMode"));          // DEVELOPER MODE

        logger.info("LANCEMENT DU JEU : RECHERCHE CHALLENGER");
        logger.trace(maxTry + " coups maximum");
        logger.trace("Chiffres entre 1 et " + maxNumber);
        logger.trace("Taille du code : " + codeSize + " chiffres");
        logger.trace("Mode développeur : " + devMode);

        System.out.printf("%n");
        System.out.println("RESEARCH +/- : CHALLENGER MODE");
        System.out.println("Find the secret code, you have " + maxTry  + " try !");

        // RANDOM SECRET CODE
        int[] code = new int[codeSize];
        for (int i = 0; i < codeSize; i++) {
            code[i] = r.nextInt(maxNumber) + 1;
        }
        logger.info("Code secret généré par l'ordinateur");

        if (devMode) {
            System.out.printf("%n");
            System.out.println("[DEV MODE] CODE : " + Arrays.toString(code));
        }

        while (userTry < maxTry) {

            // USER INPUT
            try {
                int[] input = new int[codeSize];
                System.out.printf("%n");
                int inputScanner = sc.nextInt();
                for (int i = 0; i < codeSize; i++) {
                    input[i] = (int) (inputScanner / (Math.pow(10, (codeSize - i - 1)))) % 10;
                    if (input[i] < 1) {
                        System.out.printf("%n");
                        System.out.println("Invalid input : " + codeSize + " digits maximum between 1 and " + maxNumber);
                        System.out.println("Please enter a valid input below :");
                        logger.error("Saisie incorrect");
                        inputScanner = sc.nextInt();
                    }
                    if (input[i] > maxNumber) {
                        System.out.printf("%n");
                        System.out.println("Invalid input : " + codeSize + " digits maximum between 1 and " + maxNumber);
                        System.out.println("Please enter a valid input below :");
                        logger.error("Saisie incorrect");
                        inputScanner = sc.nextInt();
                    }
                }
                logger.info("L'utilisateur vient d'entrer sa saisie");

                // RESULT FOR USER INPUT
                String result = "";
                for (int i = 0; i < codeSize; i++) {
                    boolean correctDigit = input[i] == code[i];
                    boolean inferiorDigit = input[i] < code[i];
                    boolean superiorDigit = input[i] > code[i];
                    if (correctDigit) {
                        result = result + "=";
                    }
                    if (inferiorDigit) {
                        result += "+";
                    }
                    if (superiorDigit) {
                        result = result + "-";
                    }
                }
                logger.info("Traitement de la saisie utilisateur..");

                // CLUES
                System.out.println(result);
                int numberOfCorrectUser = StringUtils.countMatches(result, "="); // COMPTE LE NOMBRE DE "="
                userTry++;
                logger.info("Les indices ont été envoyés à l'utilisateur");
                logger.trace("Coups : " + userTry);

                if (userTry == maxTry) {
                    System.out.printf("%n");
                    logger.info("La partie est terminée (Défaite, coups maximum atteint)");
                    System.out.println("The secret code was " + Arrays.toString(code));
                    System.out.println("Defeat, you have reached the " + maxTry + " allowed try");
                    Menu.endMenuResearchChallenger();
                }
                if (numberOfCorrectUser == codeSize) {
                    System.out.printf("%n");
                    logger.info("La partie est terminée (Victoire, code trouvé)");
                    System.out.println("Victory in only " + userTry + " try !");
                    Menu.endMenuResearchChallenger();
                }
            } catch (InputMismatchException e) {
                System.out.printf("%n");
                logger.fatal("InputMismatchException catchée : Saisie incorrect, redémarrage du jeu");
                System.out.println("Invalid input, letters and digits less than 1 are forbidden !");
                System.out.println("A new secret code has been generated..");
                ResearchChallenger.researchChallenger();
            }
        }
    }
}