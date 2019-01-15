package jeux;

import code.RandomCode;
import launcher.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.util.*;

public class MastermindChallenger {

    private Scanner sc = new Scanner(System.in);
    private Random r = new Random();
    private ResourceBundle bundle = ResourceBundle.getBundle("config");
    private Logger logger = LogManager.getLogger();
    private int userTry = 0;
    private int maxTry;
    private int maxNumber;
    private int codeSize;
    private boolean devMode;
    private RandomCode randomCode;
    private int[] code;
    private int[] input;
    private int inputScanner;
    private int numberOfCorrect;
    private int numberOfPresent;

    public MastermindChallenger() {
        this.maxTry = Integer.parseInt(bundle.getString("maxTryMastermindChallenger")); // NUMBER OF TRY ALLOWED
        this.maxNumber = Integer.parseInt(bundle.getString("maxNumber")); // USE DIGITS BETWEEN 1 AND..
        this.codeSize = Integer.parseInt(bundle.getString("codeSize")); // CODE SIZE
        this.devMode = Boolean.parseBoolean(bundle.getString("devMode")); // DEVELOPER MODE
        this.randomCode = new RandomCode(maxNumber, codeSize);
    }

    public void main() throws Exception {

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
        code = randomCode.randomCode();
        logger.info("Code secret généré par l'ordinateur");

        if (devMode) {
            System.out.println("[DEV MODE] CODE : " + Arrays.toString(code));
            System.out.printf("%n");
        }

        while (userTry < maxTry) {

            try {
                // USER INPUT
                userInput();
                logger.info("L'utilisateur vient d'entrer sa saisie");

                // RESULT FOR USER INPUT
                compare();
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
                main();
            }
        }
    }

    /**
     * Recupere la saisie de l'utilisateur dans un tableau de type entier
     */
    private void userInput() {
        input = new int[codeSize];
        inputScanner = sc.nextInt();
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
    }

    /**
     * Compare le code secret et la saisie de l'utilisateur
     * Stockage des bien places dans numberOfCorrect
     * Stockage des presents dans numberOfPresent
     *
     * Les tableaux de type boolean permettent d'eviter les doublons et de traiter chaque chiffre un à un
     */
    private void compare() {
        boolean[] codeUsed = new boolean[code.length];
        boolean[] inputUsed = new boolean[input.length];
        numberOfCorrect = 0;
        numberOfPresent = 0;

        for (int i = 0; i < code.length; i++) {
            if (code[i] == input[i]) {
                numberOfCorrect++;
                codeUsed[i] = inputUsed[i] = true;
            }
        }

        for (int i = 0; i < code.length; i++) {
            for (int j = 0; j < input.length; j++) {
                if (!codeUsed[i] && !inputUsed[j] && code[i] == input[j]) {
                    numberOfPresent++;
                    codeUsed[i] = inputUsed[j] = true;
                    break;
                }
            }
        }
    }
}
