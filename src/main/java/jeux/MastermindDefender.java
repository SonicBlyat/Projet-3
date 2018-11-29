package jeux;

import launcher.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.util.*;

public class MastermindDefender {

    public static void mastermindDefender() throws FileNotFoundException {

        Scanner sc = new Scanner(System.in);
        Random r = new Random();
        ResourceBundle bundle = ResourceBundle.getBundle("config");
        Logger logger = LogManager.getLogger();

        int botTry = 0;                                                                      // CURRENT TRY
        int maxTry = Integer.parseInt(bundle.getString("coupsMaxMastermindChallenger")); // NUMBER OF TRY ALLOWED
        int maxNumber = Integer.parseInt(bundle.getString("chiffreMax"));                // USE DIGITS BETWEEN 1 AND ...
        int codeSize = Integer.parseInt(bundle.getString("tailleCode"));                 // CODE SIZE

        logger.info("LANCEMENT DU JEU : MASTERMIND DEFENSEUR");
        logger.trace(maxTry + " coups maximum");
        logger.trace("Chiffres entre 1 et " + maxNumber);
        logger.trace("Taille du code : " + codeSize + " chiffres");

        System.out.printf("%n");
        System.out.println("MASTERMIND : DEFENDER MODE");
        System.out.println("Enter a secret code !");
        System.out.printf("%n");

        // USER CREATE THE SECRET CODE
        try {
            int[] code = new int[codeSize];
            int codeScanner = sc.nextInt();
            for (int i = 0; i < codeSize; i++) {
                code[i] = (int) (codeScanner / (Math.pow(10, (codeSize - i - 1)))) % 10;
                if (code[i] < 1) {
                    System.out.printf("%n");
                    System.out.println("Invalid input : " + codeSize + " digits maximum between 1 and " + maxNumber);
                    System.out.println("Please enter a valid input below :");
                    logger.error("Saisie incorrect");
                    codeScanner = sc.nextInt();
                }
                if (code[i] > maxNumber) {
                    System.out.printf("%n");
                    System.out.println("Invalid input : " + codeSize + " digits maximum between 1 and " + maxNumber);
                    System.out.println("Please enter a valid input below :");
                    logger.error("Saisie incorrect");
                    codeScanner = sc.nextInt();
                }
            }
            logger.info("Code secret généré par l'utilisateur");

            // FIRST BOT INPUT
            ArrayList<Integer> inputBot = new ArrayList<Integer>();
            for (int i = 0; i < codeSize; i++) {
                inputBot.add(r.nextInt(maxNumber) + 1);
            }
            logger.info("L'ordinateur vient d'entrer sa saisie");

            while (botTry < maxTry) {

                boolean[] codeUsed = new boolean[code.length];
                boolean[] inputBotUsed = new boolean[inputBot.size()];
                int numberOfCorrect = 0;
                int numberOfPresent = 0;
                System.out.println("Proposition de l'ordinateur : " + StringUtils.join(inputBot, ""));
                logger.info("Affichage de la saisie ordinateur");

                // RESULT FOR BOT INPUT
                for (int i = 0; i < code.length; i++) {
                    if (code[i] == inputBot.get(i)) {
                        numberOfCorrect++;
                        codeUsed[i] = inputBotUsed[i] = true;
                    }
                }

                for (int i = 0; i < code.length; i++) {
                    for (int j = 0; j < inputBot.size(); j++) {
                        if (!codeUsed[i] && !inputBotUsed[j] && code[i] == inputBot.get(j)) {
                            numberOfPresent++;
                            codeUsed[i] = inputBotUsed[j] = true;
                            break;
                        }
                    }
                }

                for (int i = 0; i < code.length; i++) {
                    for (int j = 0; j < inputBot.size(); j++) {
                        boolean test = code[i] != inputBot.get(i);
                        if (code[i] == inputBot.get(i)) {
                            inputBot.get(i);                        // IF i IS CORRECT, KEEP i ON THIS INDEX
                        } else if (!codeUsed[i] && !inputBotUsed[j] && code[i] == inputBot.get(j)) {
                            for (j = 0; j < inputBot.size(); j++) {
                                if (inputBot.indexOf(test) == j) {  // IF i IS PRESENT AND NO CORRECT DIGIT ON INDEX j
                                    inputBot.set(j, i);             // MOVE i TO INDEX j TO TRY IF i IS CORRECT
                                }
                            }
                        } else {
                            inputBot.set(i, r.nextInt(maxNumber) + 1); // IF !CORRECT && !PRESENT = NEW DIGIT
                        }
                    }
                }
                logger.info("Traitement des indices par l'ordinateur");

                // CLUES
                System.out.println(numberOfCorrect + " Correct");
                System.out.println(numberOfPresent + " Present but wrong position");
                System.out.printf("%n");
                botTry++;
                logger.trace("Coups : " + botTry);

                if (botTry == maxTry) {
                    System.out.printf("%n");
                    logger.info("La partie est terminée (Victoire, l'ordinateur n'a pas trouvé le code)");
                    System.out.println("Victory, the bot have reached the " + maxTry + " allowed try");
                    Menu.endMenuMastermindDefender();
                }
                if (numberOfCorrect == codeSize) {
                    System.out.printf("%n");
                    logger.info("La partie est terminée (Défaite, l'ordinateur a trouvé le code)");
                    System.out.println("Defeat, the bot have found your secret code in only " + botTry + " try !");
                    Menu.endMenuMastermindDefender();
                }
            }
        } catch (InputMismatchException e) {
            System.out.printf("%n");
            logger.fatal("InputMismatchException catchée : Saisie incorrect, redémarrage du jeu");
            System.out.println("Invalid input, letters and digits less than 1 are forbidden !");
            MastermindDefender.mastermindDefender();
        }
    }
}
