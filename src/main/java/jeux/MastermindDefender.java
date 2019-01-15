package jeux;

import launcher.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class MastermindDefender {

    private Scanner sc = new Scanner(System.in);
    private Random r = new Random();
    private ResourceBundle bundle;
    private Logger logger = LogManager.getLogger();
    private int botTry = 0;
    private int maxTry;
    private int maxNumber;
    private int codeSize;
    private int[] code;
    private int codeScanner;
    private List<Integer> inputBot;
    private boolean[] codeUsed;
    private boolean[] inputBotUsed;
    private int numberOfCorrect;
    private int numberOfPresent;

    public MastermindDefender() {
        this.bundle = ResourceBundle.getBundle("config");
        this.maxTry = Integer.parseInt(bundle.getString("maxTryMastermindDefender"));
        this.maxNumber = Integer.parseInt(bundle.getString("maxNumber"));
        this.codeSize = Integer.parseInt(bundle.getString("codeSize"));
    }

    public void main() throws Exception {

        logger.info("LANCEMENT DU JEU : MASTERMIND DEFENSEUR");
        logger.trace(maxTry + " coups maximum");
        logger.trace("Chiffres entre 1 et " + maxNumber);
        logger.trace("Taille du code : " + codeSize + " chiffres");

        System.out.printf("%n");
        System.out.println("MASTERMIND : DEFENDER MODE");
        System.out.println("Enter a secret code !");
        System.out.printf("%n");

        try {

            // USER CREATE THE SECRET CODE
            createCode();
            logger.info("Code secret généré par l'utilisateur");

            // FIRST BOT INPUT
            firstBotInput();
            logger.info("L'ordinateur vient d'entrer sa saisie");

            while (botTry < maxTry) {

                codeUsed = new boolean[code.length];
                inputBotUsed = new boolean[inputBot.size()];
                numberOfCorrect = 0;
                numberOfPresent = 0;

                System.out.println("Proposition de l'ordinateur : " + StringUtils.join(inputBot, ""));
                logger.info("Affichage de la saisie ordinateur");

                giveClues();

                nextBotInput();
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
                    Menu menu = new Menu();
                    menu.endMenuMastermindDefender();
                }
                if (numberOfCorrect == codeSize) {
                    System.out.printf("%n");
                    logger.info("La partie est terminée (Défaite, l'ordinateur a trouvé le code)");
                    System.out.println("Defeat, the bot have found your secret code in only " + botTry + " try !");
                    Menu menu = new Menu();
                    menu.endMenuMastermindDefender();
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
     * L'utilisateur genere le code que l'ordinateur doit trouver
     */
    private void createCode() {
        code = new int[codeSize];
        codeScanner = sc.nextInt();
        for (int i = 0; i < codeSize; i++) {
            code[i] = (int) (codeScanner / (Math.pow(10, (codeSize - i - 1)))) % 10;
            if (code[i] < 1) {
                System.out.printf("%n");
                System.out.println("Invalid input : Enter " + codeSize + " digits between 1 and " + maxNumber);
                System.out.println("Please enter a valid input below :");
                logger.error("Saisie incorrect");
                codeScanner = sc.nextInt();
            }
            if (code[i] > maxNumber) {
                System.out.printf("%n");
                System.out.println("Invalid input : Enter " + codeSize + " digits between 1 and " + maxNumber);
                System.out.println("Please enter a valid input below :");
                logger.error("Saisie incorrect");
                codeScanner = sc.nextInt();
            }
        }
    }

    /**
     * L'ordinateur prend une combinaison aleatoire dans la liste
     */
    private void firstBotInput() {
        inputBot = new ArrayList<Integer>();
        for (int i = 0; i < codeSize; i++) {
            inputBot.add(r.nextInt(maxNumber) + 1);
        }
    }

    /**
     * Compare le code secret et la saisie de l'ordinateur puis traite les indices
     */
    private void giveClues() {
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
    }

    /**
     * Retire de la liste les combinaisons ayant les meme indices que la précédente
     * Permet d'affiner la liste et de reduire le nombre de possibilites
     */
    private void nextBotInput() {
        for (int i = 0; i < code.length; i++) {
            for (int j = 0; j < inputBot.size(); j++) {
                boolean correct = code[i] == inputBot.get(i);
                boolean present = !codeUsed[i] && !inputBotUsed[j] && code[i] == inputBot.get(j);
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
    }
}