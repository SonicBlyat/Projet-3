package jeux;

import launcher.Menu;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.util.*;

public class ResearchDefender {

    public static void researchDefender() throws FileNotFoundException {

        Scanner sc = new Scanner(System.in);
        Random r = new Random();
        ResourceBundle bundle = ResourceBundle.getBundle("config");
        Logger logger = LogManager.getLogger();

        int botTry = 0;                                                                 // CURRENT TRY
        int maxTry = Integer.parseInt(bundle.getString("maxTryResearchDefender"));  // NUMBER OF TRY ALLOWED
        int maxNumber = Integer.parseInt(bundle.getString("maxNumber"));            // USE DIGITS BETWEEN 1 AND ...
        int codeSize = Integer.parseInt(bundle.getString("codeSize"));              // CODE SIZE

        logger.info("LANCEMENT DU JEU : RECHERCHE DEFENSEUR");
        logger.trace(maxTry + " coups maximum");
        logger.trace("Chiffres entre 1 et " + maxNumber);
        logger.trace("Taille du code : " + codeSize + " chiffres");

        System.out.printf("%n");
        System.out.println("RESEARCH +/- : DEFENDER MODE");
        System.out.println("Enter a secret code !");
        System.out.printf("%n");

        // USER CREATE THE SECRET CODE
        try {
            int[] code = new int[codeSize];
            int inputCode = sc.nextInt();
            for (int i = 0; i < codeSize; i++) {
                code[i] = (int) (inputCode / (Math.pow(10, (codeSize - i - 1)))) % 10;
                if (code[i] < 1) {
                    System.out.printf("%n");
                    System.out.println("Invalid input : " + codeSize + " digits maximum between 1 and " + maxNumber);
                    System.out.println("Please enter a valid input below :");
                    logger.error("Saisie incorrect");
                    inputCode = sc.nextInt();
                }
                if (code[i] > maxNumber) {
                    System.out.printf("%n");
                    System.out.println("Invalid input : " + codeSize + " digits maximum between 1 and " + maxNumber);
                    System.out.println("Please enter a valid input :");
                    logger.error("Saisie incorrect");
                    inputCode = sc.nextInt();
                }
            }
            logger.info("Code secret généré par l'utilisateur");

            // PREMIER ESSAI DU BOT
            ArrayList<Integer> TryBot = new ArrayList<Integer>();
            for (int i = 0; i < codeSize; i++) {
                TryBot.add(r.nextInt(maxNumber) + 1);
            }
            logger.info("L'ordinateur vient d'entrer sa saisie");

            while (botTry < maxTry) {
                for (int i = 0; i < codeSize; i++) {
                    System.out.printf("%n");
                    System.out.println("Bot : " + StringUtils.join(TryBot, ""));
                    logger.info("Affichage de la saisie ordinateur");
                    System.out.printf("%n");
                    System.out.print("Give some clues : ");
                    String[] reponse = new String[codeSize];
                    String inputReponse = sc.next();
                    for (i = 0; i < codeSize; i++) {
                        reponse[i] = (inputReponse.charAt(i) + "");
                    }
                    logger.info("Indices donnés par l'utilisateur");

                    for (i = 0; i < reponse.length; i++) {
                        if (reponse[i].equals("=")) {
                            TryBot.get(i);
                        } else if (reponse[i].equals("+")) {
                            TryBot.set(i, TryBot.get(i) + 1);
                        } else if (reponse[i].equals("-")) {
                            TryBot.set(i, TryBot.get(i) - 1);
                        }
                        if (TryBot.get(i) < 1) {         // EMPECHE D'ARRIVER A 0
                            TryBot.set(i, 1);
                        }
                        if (TryBot.get(i) > maxNumber) { // EMPECHE DE DEPASSER FOURCHETTE
                            TryBot.set(i, maxNumber);
                        }
                    }
                    logger.info("Traitement des indices par l'ordinateur");
                    int numberOfCorrectBot = StringUtils.countMatches(inputReponse, "="); // COMPTE LE NOMBRE DE "="
                    botTry++;
                    logger.trace("Coups : " + botTry);

                    if (botTry == maxTry) {
                        System.out.printf("%n");
                        logger.info("La partie est terminée (Victoire, l'ordinateur n'a pas trouvé le code)");
                        System.out.println("Victory, the bot have reached the " + maxTry + " allowed try");
                        Menu.endMenuResearchDefender();
                    }
                    if (numberOfCorrectBot == codeSize) {
                        System.out.printf("%n");
                        logger.info("La partie est terminée (Défaite, l'ordinateur a trouvé le code)");
                        System.out.println("Defeat, the bot have found your secret code in only " + botTry + " try !");
                        Menu.endMenuResearchDefender();
                    }
                }
            }
        } catch (InputMismatchException e) {
            System.out.printf("%n");
            logger.fatal("InputMismatchException catchée : Saisie incorrect, redémarrage du jeu");
            System.out.println("Invalid input, letters and digits less than 1 are forbidden !");
            ResearchDefender.researchDefender();
        }
    }
}
