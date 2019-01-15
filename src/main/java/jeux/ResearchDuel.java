package jeux;

import code.RandomCode;
import launcher.Menu;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.util.*;

public class ResearchDuel {

    private Scanner sc = new Scanner(System.in);
    private Random r = new Random();
    private Logger logger = LogManager.getLogger();
    private int maxTry;
    private int userTry = 0;
    private int botTry = 0;
    private int maxNumber;
    private int codeSize;
    private boolean devMode;
    private RandomCode randomCode;
    private ResearchDefender researchDefender = new ResearchDefender();
    private int[] botCode;
    private ArrayList<Integer> inputBot;
    private String[] resultBot;
    private String inputResultBot;
    private int[] inputUser;
    private int scannerUser;
    private String resultUser;

    public ResearchDuel() {
        ResourceBundle bundle = ResourceBundle.getBundle("config");
        this.maxTry = Integer.parseInt(bundle.getString("maxTryResearchDuel")); // NUMBER OF TRY ALLOWED
        this.maxNumber = Integer.parseInt(bundle.getString("maxNumber"));             // USE DIGITS BETWEEN 1 AND ...
        this.codeSize = Integer.parseInt(bundle.getString("codeSize"));               // CODE SIZE
        this.devMode = Boolean.parseBoolean(bundle.getString("devMode"));         // DEVELOPER MODE
        randomCode = new RandomCode(maxNumber, codeSize);
        researchDefender = new ResearchDefender();
    }

    public void main() throws Exception {

        logger.info("LANCEMENT DU JEU : RECHERCHE DUEL");
        logger.trace(maxTry + " coups maximum");
        logger.trace("Chiffres entre 1 et " + maxNumber);
        logger.trace("Taille du code : " + codeSize + " chiffres");
        logger.trace("Mode développeur : " + devMode);

        System.out.printf("%n");
        System.out.println("RESEARCH +/- : DUEL MODE");
        System.out.println("Enter a secret code so the bot can choose he's own !");
        System.out.printf("%n");

        try {
            // USER CREATE THE SECRET CODE
            researchDefender.createCode();
            logger.info("Code secret généré par l'utilisateur");

            // BOT CREATE THE SECRET CODE
            botCode = randomCode.randomCode();
            logger.info("Code secret généré par l'ordinateur");

            System.out.printf("%n");

            System.out.println("The bot has generated his secret code, he plays first !");

            if (devMode) {
                System.out.printf("%n");
                System.out.println("[DEV MODE] CODE : " + Arrays.toString(botCode));
            }

            // FIRST BOT INPUT
            firstBotInput();
            logger.info("L'ordinateur vient d'entrer sa première saisie");

            while (userTry < maxTry && botTry < maxTry) {

                // BOT INPUT
                System.out.printf("%n");

                System.out.println("Bot : " + StringUtils.join(inputBot, ""));
                logger.info("Affichage de la saisie ordinateur");

                System.out.printf("%n");

                // RESULT FOR BOT INPUT
                getCluesForBot();
                logger.info("Indices donnés par l'utilisateur");

                // COMPARE
                compareForBot();
                logger.info("Traitement des indices par l'ordinateur");

                int numberOfCorrectBot = StringUtils.countMatches(inputResultBot, "="); // COMPTE LE NOMBRE DE "="
                botTry++;
                logger.trace("Coups ordinateur : " + botTry);

                if (botTry == maxTry) {
                    System.out.printf("%n");
                    logger.info("La partie est terminée (Victoire, l'ordinateur a utilisé ses coups)");
                    System.out.println("Victory, the bot have reached the " + maxTry + " allowed try");
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
                userInput();
                logger.info("Affichage de la saisie utilisateur");

                // RESULT FOR USER INPUT
                compareForUser();
                logger.info("Traitement des indices pour la saisie utilisateur");

                // CLUES FOR USER
                System.out.println(resultUser);
                int numberOfCorrectUser = StringUtils.countMatches(resultUser, "="); // COMPTE LE NOMBRE DE "="
                userTry++;
                logger.trace("Coups utilisateur : " + userTry);

                if (userTry == maxTry) {
                    System.out.printf("%n");
                    logger.info("La partie est terminée (Défaite, coups maximum atteint)");
                    System.out.println("The secret code was " + Arrays.toString(botCode));
                    System.out.println("Defeat, you have reached the " + maxTry + " allowed try");
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
            main();
        }
    }

    /**
     * Premier essai de l'ordinateur
     * Généré aléatoirement d'une taille codeSize et de chiffres allant de 1 à maxNumber
     */
    private void firstBotInput() {
        inputBot = new ArrayList<Integer>();
        for (int i = 0; i < codeSize; i++) {
            inputBot.add(r.nextInt(maxNumber) + 1);
        }
    }

    /**
     * Récupère la saisie de l'utilisateur dans un tableau de type entier
     */
    private void userInput() {
        inputUser = new int[codeSize];
        System.out.printf("%n");
        scannerUser = sc.nextInt();
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
    }

    /**
     * Récupère les indices donnés par l'utilisateur
     */
    private void getCluesForBot() {
        for (int i = 0; i < codeSize; i++) {
            System.out.print("Give some clues : ");
            resultBot = new String[codeSize];
            inputResultBot = sc.next();
            for (i = 0; i < codeSize; i++) {
                resultBot[i] = (inputResultBot.charAt(i) + "");
            }
        }
    }

    /**
     * L'ordinateur compare sa saisie aux indices qui lui ont été donnés
     * Il prépare sa prochaine saisie
     */
    private void compareForBot() {
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
    }

    /**
     * Compare le code secret et la saisie utilisateur puis génère les indices
     */
    private void compareForUser() {
        resultUser = "";
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
    }
}
