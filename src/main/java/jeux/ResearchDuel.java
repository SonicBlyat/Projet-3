package jeux;

import launcher.Menu;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.util.*;

public class ResearchDuel {

    public static void researchDuel() throws Exception {

        Scanner sc = new Scanner(System.in);
        Random r = new Random();
        ResourceBundle bundle = ResourceBundle.getBundle("config");
        Logger logger = LogManager.getLogger();

        int userTry = 0;                                                                // CURRENT USER TRY
        int botTry = 0;                                                                 // CURRENT BOT TRY
        int maxUserTry = Integer.parseInt(bundle.getString("maxTryResearchDuel"));  // NUMBER OF TRY ALLOWED (USER)
        int maxBotTry = Integer.parseInt(bundle.getString("maxTryResearchDuel"));   // NUMBER OF TRY ALLOWED (BOT)
        int maxNumber = Integer.parseInt(bundle.getString("maxNumber"));            // USE DIGITS BETWEEN 1 AND ...
        int codeSize = Integer.parseInt(bundle.getString("codeSize"));              // CODE SIZE
        boolean devMode = Boolean.parseBoolean(bundle.getString("devMode"));        // DEVELOPER MODE

        logger.info("LANCEMENT DU JEU : RECHERCHE DUEL");
        logger.trace(maxUserTry + " coups maximum");
        logger.trace("Chiffres entre 1 et " + maxNumber);
        logger.trace("Taille du code : " + codeSize + " chiffres");
        logger.trace("Mode développeur : " + devMode);

        System.out.printf("%n");
        System.out.println("RESEARCH +/- : DUEL MODE");
        System.out.println("Enter a secret code so the bot can choose he's own !");
        System.out.printf("%n");

        // USER CREATE THE SECRET CODE
        try {
            int[] userCode = new int[codeSize];
            int inputUserCode = sc.nextInt();
            for (int i = 0; i < codeSize; i++) {
                userCode[i] = (int) (inputUserCode / (Math.pow(10, (codeSize - i - 1)))) % 10;
                if (userCode[i] < 1) {
                    System.out.printf("%n");
                    System.out.println("Invalid input : Enter " + codeSize + " digits between 1 and " + maxNumber);
                    System.out.println("Please enter a valid input below :");
                    logger.error("Saisie incorrect");
                    inputUserCode = sc.nextInt();
                }
                if (userCode[i] > maxNumber) {
                    System.out.printf("%n");
                    System.out.println("Invalid input : Enter " + codeSize + " digits between 1 and " + maxNumber);
                    System.out.println("Please enter a valid input below :");
                    logger.error("Saisie incorrect");
                    inputUserCode = sc.nextInt();
                }
            }
            logger.info("Code secret généré par l'utilisateur");
            System.out.println("YOUR CODE : " + StringUtils.join(inputUserCode, ""));

            // BOT CREATE THE SECRET CODE
            int[] botCode = new int[codeSize];
            for (int i = 0; i < codeSize; i++) {
                botCode[i] = r.nextInt(maxNumber) + 1;
            }
            logger.info("Code secret généré par l'ordinateur");

            System.out.printf("%n");
            System.out.println("The bot has generated his secret code, he plays first !");

            if (devMode) {
                System.out.printf("%n");
                System.out.println("[DEV MODE] CODE : " + Arrays.toString(botCode));
            }

            // FIRST BOT INPUT
            ArrayList<Integer> inputBot = new ArrayList<Integer>();
            for (int i = 0; i < codeSize; i++) {
                inputBot.add(r.nextInt(maxNumber) + 1);
            }
            logger.info("L'ordinateur vient d'entrer sa première saisie");

            while (userTry < maxUserTry && botTry < maxBotTry) {

                // BOT INPUT
                System.out.printf("%n");
                System.out.println("Bot : " + StringUtils.join(inputBot, ""));
                logger.info("Affichage de la saisie ordinateur");

                // RESULT FOR BOT INPUT
                System.out.print("Clues : ");
                String[] resultBot = new String[codeSize];
                String inputResultBot = sc.next();
                for (int i = 0; i < codeSize; i++) {
                    resultBot[i] = (inputResultBot.charAt(i) + "");
                }
                logger.info("Indices donnés par l'utilisateur");

                for (int i = 0; i < resultBot.length; i++) {
                    if (resultBot[i].equals("=")) {
                        inputBot.get(i);
                    } else if (resultBot[i].equals("+")) {
                        inputBot.set(i, inputBot.get(i) + 1);
                    } else if (resultBot[i].equals("-")) {
                        inputBot.set(i, inputBot.get(i) - 1);
                    }
                    if (inputBot.get(i) < 1) { // EMPECHE D'ARRIVER A 0
                        inputBot.set(i, 1);
                    }
                    if (inputBot.get(i) > maxNumber) { // EMPECHE DE DEPASSER FOURCHETTE
                        inputBot.set(i, maxNumber);
                    }
                }
                logger.info("Traitement des indices par l'ordinateur");
                int numberOfCorrectBot = StringUtils.countMatches(inputResultBot, "="); // COMPTE LE NOMBRE DE "="
                botTry++;
                logger.trace("Coups ordinateur : " + botTry);

                if (botTry == maxBotTry) {
                    System.out.printf("%n");
                    logger.info("La partie est terminée (Victoire, l'ordinateur a utilisé ses coups)");
                    System.out.println("Victory, the bot have reached the " + maxBotTry + " allowed try");
                    Menu menu = new Menu();
                    menu.endMenuResearchDuel();
                }
                if (numberOfCorrectBot == codeSize) {
                    System.out.printf("%n");
                    logger.info("La partie est terminée (Défaite, l'ordinateur a trouvé le code)");
                    System.out.println("Le code secret adverse était " + Arrays.toString(botCode));
                    System.out.println("Defeat, the bot have found your secret code in only " + botTry + " try !");
                    Menu menu = new Menu();
                    menu.endMenuResearchDuel();
                }

                // USER INPUT
                int[] inputUser = new int[codeSize];
                System.out.printf("%n");
                int scannerUser = sc.nextInt();
                for (int i = 0; i < codeSize; i++) {
                    inputUser[i] = (int) (scannerUser / (Math.pow(10, (codeSize - i - 1)))) % 10;
                    if (inputUser[i] < 1) {
                        System.out.printf("%n");
                        System.out.println("Invalid input : Enter " + codeSize + " digits between 1 and " + maxNumber);
                        System.out.println("Please enter a valid input below :");
                        logger.error("Saisie incorrect");
                        scannerUser = sc.nextInt();
                    }
                    if (inputUser[i] > maxNumber) {
                        System.out.printf("%n");
                        System.out.println("Invalid input : Enter " + codeSize + " digits between 1 and " + maxNumber);
                        System.out.println("Please enter a valid input below :");
                        logger.error("Saisie incorrect");
                        scannerUser = sc.nextInt();
                    }
                }
                logger.info("Affichage de la saisie utilisateur");

                // RESULT FOR USER INPUT
                String resultUser = "";
                for (int i = 0; i < codeSize; i++) {
                    boolean correctDigitUser = inputUser[i] == botCode[i];
                    boolean inferiorDigitUser = inputUser[i] < botCode[i];
                    boolean superiorDigitUser = inputUser[i] > botCode[i];
                    if (correctDigitUser) {
                        resultUser = resultUser + "=";
                    }
                    if (inferiorDigitUser) {
                        resultUser += "+";
                    }
                    if (superiorDigitUser) {
                        resultUser = resultUser + "-";
                    }
                }
                logger.info("Traitement des indices pour la saisie utilisateur");

                // CLUES FOR USER
                System.out.println(resultUser);
                int numberOfCorrectUser = StringUtils.countMatches(resultUser, "="); // COMPTE LE NOMBRE DE "="
                userTry++;
                logger.trace("Coups utilisateur : " + userTry);

                if (userTry == maxUserTry) {
                    System.out.printf("%n");
                    logger.info("La partie est terminée (Défaite, coups maximum atteint)");
                    System.out.println("The secret code was " + Arrays.toString(botCode));
                    System.out.println("Defeat, you have reached the " + maxUserTry + " allowed try");
                    Menu menu = new Menu();
                    menu.endMenuResearchDuel();
                }
                if (numberOfCorrectUser == codeSize) {
                    System.out.printf("%n");
                    logger.info("La partie est terminée (Victoire, code trouvé)");
                    System.out.println("Victory in only " + userTry + " try !");
                    Menu menu = new Menu();
                    menu.endMenuResearchDuel();
                }
            }
        } catch (InputMismatchException e) {
            System.out.printf("%n");
            logger.fatal("InputMismatchException catchée : Saisie incorrect, redémarrage du jeu");
            System.out.println("Invalid input, letters and digits less than 1 are forbidden !");
            ResearchDuel.researchDuel();
        }
    }
}
