package jeux;

import launcher.Menu;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class ResearchDefender {

    private Scanner sc = new Scanner(System.in);
    private Random r = new Random();
    private Logger logger = LogManager.getLogger();
    private int botTry = 0;
    private int maxTry;
    private int maxNumber;
    private int codeSize;
    private ArrayList<Integer> tryBot;
    private String[] reponse;
    private String inputReponse;

    public ResearchDefender() {
        ResourceBundle bundle = ResourceBundle.getBundle("config");
        this.maxTry = Integer.parseInt(bundle.getString("maxTryResearchDefender")); // NUMBER OF TRY ALLOWED
        this.maxNumber = Integer.parseInt(bundle.getString("maxNumber")); // USE DIGITS BETWEEN 1 AND ...
        this.codeSize = Integer.parseInt(bundle.getString("codeSize")); // CODE SIZE
    }

    public void main() throws Exception {

        logger.info("LANCEMENT DU JEU : RECHERCHE DEFENSEUR");
        logger.trace(maxTry + " coups maximum");
        logger.trace("Chiffres entre 1 et " + maxNumber);
        logger.trace("Taille du code : " + codeSize + " chiffres");

        System.out.printf("%n");
        System.out.println("RESEARCH +/- : DEFENDER MODE");
        System.out.println("Enter a secret code !");
        System.out.printf("%n");

        // USER CREATE THE SECRET CODE
        createCode();
        logger.info("Code secret généré par l'utilisateur");

        // PREMIER ESSAI DU BOT
        firstBotInput();
        logger.info("L'ordinateur vient d'entrer sa saisie");

        while (botTry < maxTry) {
            try {
                getClues();
                logger.info("Indices donnés par l'utilisateur");

                compare();
                logger.info("Traitement des indices par l'ordinateur");

                int numberOfCorrectBot = StringUtils.countMatches(inputReponse, "="); // COMPTE LE NOMBRE DE "="
                botTry++;
                logger.trace("Coups : " + botTry);

                if (botTry == maxTry) {
                    endGameVictory();
                }
                if (numberOfCorrectBot == codeSize) {
                    endGameDefeat();
                }
            } catch (InputMismatchException e) {
                System.out.printf("%n");
                logger.fatal("InputMismatchException catchée : Saisie incorrect, redémarrage du jeu");
                System.out.println("Invalid input, letters and digits less than 1 are forbidden !");
                main();
            }
        }
    }

    /**
     * L'utilisateur genere le code que l'ordinateur doit trouver
     */
    public void createCode() {
        int[] code = new int[codeSize];
        int inputCode = sc.nextInt();
        for (int i = 0; i < codeSize; i++) {
            code[i] = (int) (inputCode / (Math.pow(10, (codeSize - i - 1)))) % 10;
            if (code[i] < 1) {
                System.out.printf("%n");
                System.out.println("Invalid input : Enter " + codeSize + " digits between 1 and " + maxNumber);
                System.out.println("Please enter a valid input below :");
                logger.error("Saisie incorrect");
                inputCode = sc.nextInt();
            }
            if (code[i] > maxNumber) {
                System.out.printf("%n");
                System.out.println("Invalid input : Enter " + codeSize + " digits between 1 and " + maxNumber);
                System.out.println("Please enter a valid input :");
                logger.error("Saisie incorrect");
                inputCode = sc.nextInt();
            }
        }
    }

    /**
     * Affichage de la saisie de l'ordinateur
     * Recupere les indices entres par l'utilisateur
     */
    private void getClues() {
        for (int i = 0; i < codeSize; i++) {
            System.out.printf("%n");
            System.out.println("Bot : " + StringUtils.join(tryBot, ""));
            logger.info("Affichage de la saisie ordinateur");
            System.out.printf("%n");
            System.out.print("Give some clues : ");
            reponse = new String[codeSize];
            inputReponse = sc.next();
            for (i = 0; i < codeSize; i++) {
                reponse[i] = (inputReponse.charAt(i) + "");
            }
        }
    }

    /**
     * L'ordinateur compare sa saisie aux indices qui lui ont ete donnes
     * Prepare la prochaine saisie de l'ordinateur
     */
    private void compare() {
        for (int i = 0; i < reponse.length; i++) {
            if (reponse[i].equals("=")) {
                tryBot.get(i);
            } else if (reponse[i].equals("+")) {
                tryBot.set(i, tryBot.get(i) + 1);
            } else if (reponse[i].equals("-")) {
                tryBot.set(i, tryBot.get(i) - 1);
            }
            if (tryBot.get(i) < 1) {         // EMPECHE D'ARRIVER A 0
                tryBot.set(i, 1);
            }
            if (tryBot.get(i) > maxNumber) { // EMPECHE DE DEPASSER FOURCHETTE
                tryBot.set(i, maxNumber);
            }
        }
    }

    /**
     * Premier essai de l'ordinateur
     * Il est genere aléatoirement d'une taille codeSize et de chiffres allant de 1 à maxNumber
     */
    public void firstBotInput() {
        tryBot = new ArrayList<Integer>();
        for (int i = 0; i < codeSize; i++) {
            tryBot.add(r.nextInt(maxNumber) + 1);
        }
    }

    /**
     * Affichage du menu de defaite en cas de victoire de l'ordinateur
     * Affiche le compteur de coups de l'ordinateur
     */
    private void endGameDefeat () throws Exception {
        System.out.printf("%n");
        logger.info("La partie est terminée (Défaite, l'ordinateur a trouvé le code)");
        System.out.println("Defeat, the bot have found your secret code in only " + botTry + " try !");
        Menu menu = new Menu();
        menu.endMenuResearchDefender();
    }

    /**
     * Affichage du menu de victoire en cas de defaite de l'ordinateur
     */
    private void endGameVictory () throws Exception {
        System.out.printf("%n");
        logger.info("La partie est terminée (Victoire, l'ordinateur n'a pas trouvé le code)");
        System.out.println("Victory, the bot have reached the " + maxTry + " allowed try");
        Menu menu = new Menu();
        menu.endMenuResearchDefender();
    }
}
