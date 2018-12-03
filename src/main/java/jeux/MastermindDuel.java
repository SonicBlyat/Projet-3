package jeux;

import launcher.Menu;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.util.*;

public class MastermindDuel {

    public static void mastermindDuel() throws FileNotFoundException {

        Scanner sc = new Scanner(System.in);
        Random r = new Random();
        ResourceBundle bundle = ResourceBundle.getBundle("config");
        Logger logger = LogManager.getLogger();

        int userTry = 0;                                                                  // CURRENT USER TRY
        int botTry = 0;                                                                   // CURRENT BOT TRY
        int maxUserTry = Integer.parseInt(bundle.getString("maxTryMastermindDuel"));  // NUMBER OF TRY ALLOWED (USER)
        int maxBotTry = Integer.parseInt(bundle.getString("maxTryMastermindDuel"));   // NUMBER OF TRY ALLOWED (BOT)
        int maxNumber = Integer.parseInt(bundle.getString("maxNumber"));              // USE DIGITS BETWEEN 1 AND ...
        int codeSize = Integer.parseInt(bundle.getString("codeSize"));                // CODE SIZE
        boolean devMode = Boolean.parseBoolean(bundle.getString("devMode"));          // DEVELOPER MODE

        logger.info("LANCEMENT DU JEU : MASTERMIND DUEL");
        logger.trace(maxUserTry + " coups maximum");
        logger.trace("Chiffres entre 1 et " + maxNumber);
        logger.trace("Taille du code : " + codeSize + " chiffres");
        logger.trace("Mode développeur : " + devMode);

        System.out.printf("%n");
        System.out.println("MASTERMIND : DUEL MODE");
        System.out.println("Enter a secret code so the bot can choose he's own !");
        System.out.printf("%n");

        // USER CREATE THE SECRET CODE
        try {
            int[] userCode = new int[codeSize];
            int userCodeScanner = sc.nextInt();
            for (int i = 0; i < codeSize; i++) {
                userCode[i] = (int) (userCodeScanner / (Math.pow(10, (codeSize - i - 1)))) % 10;
                if (userCode[i] < 1) {
                    System.out.printf("%n");
                    System.out.println("Invalid input : " + codeSize + " digits maximum between 1 and " + maxNumber);
                    System.out.println("Please enter a valid input below :");
                    logger.error("Saisie incorrect");
                    userCodeScanner = sc.nextInt();
                }
                if (userCode[i] > maxNumber) {
                    System.out.printf("%n");
                    System.out.println("Invalid input : " + codeSize + " digits maximum between 1 and " + maxNumber);
                    System.out.println("Please enter a valid input below :");
                    logger.error("Saisie incorrect");
                    userCodeScanner = sc.nextInt();
                }
            }
            logger.info("Code secret généré par l'utilisateur");
            System.out.println("YOUR SECRET CODE : " + StringUtils.join(userCodeScanner, ""));

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
                boolean[] userCodeUsed = new boolean[userCode.length];
                boolean[] inputBotUsed = new boolean[inputBot.size()];
                int numberOfCorrectBot = 0;
                int numberOfPresentBot = 0;

                for (int i = 0; i < userCode.length; i++) {
                    if (userCode[i] == inputBot.get(i)) {
                        numberOfCorrectBot++;
                        userCodeUsed[i] = inputBotUsed[i] = true;
                    }
                }

                for (int i = 0; i < userCode.length; i++) {
                    for (int j = 0; j < inputBot.size(); j++) {
                        if (!userCodeUsed[i] && !inputBotUsed[j] && userCode[i] == inputBot.get(j)) {
                            numberOfPresentBot++;
                            userCodeUsed[i] = inputBotUsed[j] = true;
                            break;
                        }
                    }
                }

                for (int i = 0; i < userCode.length; i++) {
                    for (int j = 0; j < inputBot.size(); j++) {
                        boolean correct = userCode[i] == inputBot.get(i);
                        boolean present = !userCodeUsed[i] && !inputBotUsed[j] && userCode[i] == inputBot.get(j);
                        if (correct) {
                            inputBot.get(i);                            // IF i IS CORRECT, KEEP i ON THIS INDEX
                        } else if (present) {
                            for (j = 0; j < inputBot.size(); j++) {
                                if (inputBot.indexOf(!correct) == j) {  // IF i IS PRESENT AND NO CORRECT DIGIT ON INDEX j
                                    inputBot.set(j, i);                 // MOVE i TO INDEX j TO TRY IF i IS CORRECT
                                }
                            }
                        } else {
                            inputBot.set(i, r.nextInt(maxNumber) + 1);  // IF !CORRECT && !PRESENT = NEW DIGIT
                        }
                    }
                }
                logger.info("Traitement des indices par l'ordinateur");

                // CLUES FOR THE BOT
                System.out.println(numberOfCorrectBot + " Correct");
                System.out.println(numberOfPresentBot + " Present but wrong position");
                botTry++;
                logger.trace("Coups ordinateur : " + botTry);

                if (botTry == maxBotTry) {
                    System.out.printf("%n");
                    logger.info("La partie est terminée (Victoire, l'ordinateur a utilisé ses coups)");
                    System.out.println("Victory, the bot have reached the " + maxBotTry + " allowed try");
                    Menu.endMenuMastermindDuel();
                }
                if (numberOfCorrectBot == codeSize) {
                    System.out.printf("%n");
                    logger.info("La partie est terminée (Défaite, l'ordinateur a trouvé le code)");
                    System.out.println("Le code secret adverse était " + Arrays.toString(botCode));
                    System.out.println("Defeat, the bot have found your secret code in only " + botTry + " try !");
                    Menu.endMenuMastermindDuel();
                }

                // USER INPUT
                int[] userInput = new int[codeSize];
                System.out.printf("%n");
                int userInputScanner = sc.nextInt();
                for (int i = 0; i < codeSize; i++) {
                    userInput[i] = (int) (userInputScanner / (Math.pow(10, (codeSize - i - 1)))) % 10;
                    if (userInput[i] < 1) {
                        System.out.printf("%n");
                        System.out.println("Invalid input : " + codeSize + " digits maximum between 1 and " + maxNumber);
                        System.out.println("Please enter a valid input below :");
                        logger.error("Saisie incorrect");
                        userInputScanner = sc.nextInt();
                    }
                    if (userInput[i] > maxNumber) {
                        System.out.printf("%n");
                        System.out.println("Invalid input : " + codeSize + " digits maximum between 1 and " + maxNumber);
                        System.out.println("Please enter a valid input below :");
                        logger.error("Saisie incorrect");
                        userInputScanner = sc.nextInt();
                    }
                }
                logger.info("Affichage de la saisie utilisateur");

                // RESULT FOR USER INPUT
                boolean[] botCodeUsed = new boolean[botCode.length];
                boolean[] userInputUsed = new boolean[userInput.length];
                int numberOfCorrectUser = 0;
                int numberOfPresentUser = 0;

                for (int i = 0; i < botCode.length; i++) {
                    if (botCode[i] == userInput[i]) {
                        numberOfCorrectUser++;
                        botCodeUsed[i] = userInputUsed[i] = true;
                    }
                }

                for (int i = 0; i < botCode.length; i++) {
                    for (int j = 0; j < userInput.length; j++) {
                        if (!botCodeUsed[i] && !userInputUsed[j] && botCode[i] == userInput[j]) {
                            numberOfPresentUser++;
                            botCodeUsed[i] = userInputUsed[j] = true;
                            break;
                        }
                    }
                }
                logger.info("Traitement des indices pour la saisie utilisateur");

                // CLUES FOR USER
                System.out.println(numberOfCorrectUser + " Correct");
                System.out.println(numberOfPresentUser + " Present but wrong position");
                userTry++;
                logger.trace("Coups utilisateur : " + userTry);

                if (userTry == maxUserTry) {
                    System.out.printf("%n");
                    logger.info("La partie est terminée (Défaite, coups maximum atteint)");
                    System.out.println("The secret code was " + Arrays.toString(botCode));
                    System.out.println("Defeat, you have reached the " + maxUserTry + " allowed try");
                    Menu.endMenuMastermindDuel();
                }
                if (numberOfCorrectUser == codeSize) {
                    System.out.printf("%n");
                    logger.info("La partie est terminée (Victoire, code trouvé)");
                    System.out.println("Victory in only " + userTry + " try !");
                    Menu.endMenuMastermindDuel();
                }
            }
        } catch (InputMismatchException e) {
            System.out.printf("%n");
            logger.fatal("InputMismatchException catchée : Saisie incorrect, redémarrage du jeu");
            System.out.println("Invalid input, letters and digits less than 1 are forbidden !");
            MastermindDuel.mastermindDuel();
        }
    }
}
