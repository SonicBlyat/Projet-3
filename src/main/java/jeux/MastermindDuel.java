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

        int coupsUser = 0;
        int coupsBot = 0;
        int coupsMaxUser = Integer.parseInt(bundle.getString("coupsMaxMastermindDuel")); // NOMBRE DE COUPS   (CONFIGURABLE)
        int coupsMaxBot = Integer.parseInt(bundle.getString("coupsMaxMastermindDuel"));
        int fourchette = Integer.parseInt(bundle.getString("chiffreMax")); // UTILISER DES CHIFFRES ENTRE 1 ET ... (CONFIGURABLE)
        int max = Integer.parseInt(bundle.getString("tailleCode"));        // TAILLE DU TABLEAU (CONFIGURABLE)
        boolean modeDev = Boolean.parseBoolean(bundle.getString("modeDev"));

        logger.info("LANCEMENT DU JEU : MASTERMIND DUEL");
        logger.trace(coupsMaxUser + " coups maximum");
        logger.trace("Chiffres entre 1 et " + fourchette);
        logger.trace("Taille du code : " + max + " chiffres");
        logger.trace("Mode développeur : " + modeDev);

        System.out.printf("%n");
        System.out.println("MASTERMIND : DUEL");
        System.out.println("Choisissez un code pour que l'ordinateur puisse choisir le sien");
        System.out.printf("%n");

        try {
            // GENERATION DU CODE PAR L'UTILISATEUR
            int[] userCode = new int[max];
            int inputUserCode = sc.nextInt();
            for (int i = 0; i < max; i++) {
                userCode[i] = (int) (inputUserCode / (Math.pow(10, (max - i - 1)))) % 10;
                if (userCode[i] < 1) {
                    System.out.printf("%n");
                    System.out.println("Saisie incorrect : " + max + " chiffres maximum composés de chiffres entre 1 et " + fourchette);
                    System.out.println("Veuillez entrer une nouvelle saisie ci-dessous :");
                    logger.error("Saisie incorrect");
                    inputUserCode = sc.nextInt();
                }
                if (userCode[i] > fourchette) {
                    System.out.printf("%n");
                    System.out.println("Saisie incorrect : " + max + " chiffres maximum composés de chiffres entre 1 et " + fourchette);
                    System.out.println("Veuillez entrer une nouvelle saisie ci-dessous :");
                    logger.error("Saisie incorrect");
                    inputUserCode = sc.nextInt();
                }
            }
            logger.info("Code secret généré par l'utilisateur");
            System.out.println("VOTRE CODE : " + StringUtils.join(inputUserCode, ""));

            // GENERATION DU CODE PAR L'ORDINATEUR
            int[] botCode = new int[max];
            for (int i = 0; i < max; i++) {
                botCode[i] = r.nextInt(fourchette) + 1;
            }
            logger.info("Code secret généré par l'ordinateur");

            System.out.printf("%n");
            System.out.println("L'ordinateur a généré son code, il joue en premier !");

            if (modeDev == true) {
                System.out.printf("%n");
                System.out.println("SOLUTION : " + Arrays.toString(botCode));
            }

            // GENERATION DE LA PREMIERE SAISIE ALEATOIRE DE L'ORDINATEUR
            ArrayList<Integer> inputBot = new ArrayList<Integer>();
            for (int i = 0; i < max; i++) {
                inputBot.add(r.nextInt(fourchette) + 1);
            }
            logger.info("L'ordinateur vient d'entrer sa première saisie");

            while (coupsUser < coupsMaxUser && coupsBot < coupsMaxBot) {

                // SAISIE DE L'ORDINATEUR
                System.out.printf("%n");
                System.out.println("Ordinateur : " + StringUtils.join(inputBot, ""));
                logger.info("Affichage de la saisie ordinateur");

                // RESULTAT DE LA SAISIE ORDINATEUR
                boolean[] codeUsed = new boolean[userCode.length];
                boolean[] saisieUsed = new boolean[inputBot.size()];
                int numberOfCorrectBot = 0;
                int numberOfPresentBot = 0;

                for (int i = 0; i < userCode.length; i++) {
                    if (userCode[i] == inputBot.get(i)) {
                        numberOfCorrectBot++;
                        codeUsed[i] = saisieUsed[i] = true;
                    }
                }

                for (int i = 0; i < userCode.length; i++) {
                    for (int j = 0; j < inputBot.size(); j++) {
                        if (!codeUsed[i] && !saisieUsed[j] && userCode[i] == inputBot.get(j)) {
                            numberOfPresentBot++;
                            codeUsed[i] = saisieUsed[j] = true;
                            break;
                        }
                    }
                }

                for (int i = 0; i < userCode.length; i++) {
                    for (int j = 0; j < inputBot.size(); j++) {
                        boolean test = userCode[i] != inputBot.get(i);
                        if (userCode[i] == inputBot.get(i)) {
                            inputBot.get(i);
                        } else if (!codeUsed[i] && !saisieUsed[j] && userCode[i] == inputBot.get(j)) {
                            for (j = 0; j < inputBot.size(); j++) {
                                if (inputBot.indexOf(test) == j) { // SI i EST PRESENT ET PAS DE BIEN PLACE SUR INDEX j
                                    inputBot.set(j, i); // DEPLACE i SUR INDEX j POUR TESTER SI IL PASSE EN BIEN PLACE ICI
                                }
                            }
                        } else {
                            inputBot.set(i, r.nextInt(fourchette) + 1); // SI NI CORRECT NI PRESENT, NOUVELLE VALEUR
                        }
                    }
                }
                logger.info("Traitement des indices par l'ordinateur");

                // INDICES POUR L'ORDINATEUR
                System.out.println(numberOfCorrectBot + " Bien placé(s)");
                System.out.println(numberOfPresentBot + " Présent(s) mais mal placé(s)");
                coupsBot++;
                logger.trace("Coups ordinateur : " + coupsBot);

                // SAISIE DE L'UTILISATEUR
                int[] inputUser = new int[max];
                System.out.printf("%n");
                int scannerUser = sc.nextInt();
                for (int i = 0; i < max; i++) {
                    inputUser[i] = (int) (scannerUser / (Math.pow(10, (max - i - 1)))) % 10;
                    if (inputUser[i] < 1) {
                        System.out.printf("%n");
                        System.out.println("Saisie incorrect : " + max + " chiffres maximum composés de chiffres entre 1 et " + fourchette);
                        System.out.println("Veuillez entrer une nouvelle saisie ci-dessous :");
                        logger.error("Saisie incorrect");
                        scannerUser = sc.nextInt();
                    }
                    if (inputUser[i] > fourchette) {
                        System.out.printf("%n");
                        System.out.println("Saisie incorrect : " + max + " chiffres maximum composés de chiffres entre 1 et " + fourchette);
                        System.out.println("Veuillez entrer une nouvelle saisie ci-dessous :");
                        logger.error("Saisie incorrect");
                        scannerUser = sc.nextInt();
                    }
                }
                logger.info("Affichage de la saisie utilisateur");

                // RESULTAT DE LA SAISIE UTILISATEUR
                boolean[] codeUsedUser = new boolean[botCode.length];
                boolean[] saisieUsedUser = new boolean[inputUser.length];
                int numberOfCorrectUser = 0;
                int numberOfPresentUser = 0;

                for (int i = 0; i < botCode.length; i++) {
                    if (botCode[i] == inputUser[i]) {
                        numberOfCorrectUser++;
                        codeUsedUser[i] = saisieUsedUser[i] = true;
                    }
                }

                for (int i = 0; i < botCode.length; i++) {
                    for (int j = 0; j < inputUser.length; j++) {
                        if (!codeUsedUser[i] && !saisieUsedUser[j] && botCode[i] == inputUser[j]) {
                            numberOfPresentUser++;
                            codeUsedUser[i] = saisieUsedUser[j] = true;
                            break;
                        }
                    }
                }
                logger.info("Traitement des indices pour la saisie utilisateur");

                // INDICES POUR L'UTILISATEUR
                System.out.println(numberOfCorrectUser + " Bien placé(s)");
                System.out.println(numberOfPresentUser + " Présent(s) mais mal placé(s)");
                coupsUser++;
                logger.trace("Coups utilisateur : " + coupsUser);

                if (numberOfCorrectUser == max && numberOfCorrectBot == max) {
                    System.out.printf("%n");
                    logger.info("La partie est terminée (Egalité, codes trouvés en même temps)");
                    System.out.println("Egalité ! Les deux codes ont été trouvés en même temps !");
                    Menu.endMenuMastermindDuel();
                }
                if (coupsUser == coupsMaxUser) {
                    System.out.printf("%n");
                    logger.info("La partie est terminée (Défaite, coups maximum atteint)");
                    System.out.println("Le code secret était " + Arrays.toString(botCode));
                    System.out.println("Défaite, vous avez atteint les " + coupsMaxUser + " coups autorisés");
                    Menu.endMenuMastermindDuel();
                }
                if (numberOfCorrectUser == max) {
                    System.out.printf("%n");
                    logger.info("La partie est terminée (Victoire, code trouvé)");
                    System.out.println("Victoire en seulement " + coupsUser + " coups !");
                    Menu.endMenuMastermindDuel();
                }
                if (coupsBot == coupsMaxBot) {
                    System.out.printf("%n");
                    logger.info("La partie est terminée (Victoire, l'ordinateur a utilisé ses coups)");
                    System.out.println("Victoire, l'ordinateur a utilisé ses " + coupsMaxBot + " coups autorisés !");
                    Menu.endMenuMastermindDuel();
                }
                if (numberOfCorrectBot == max) {
                    System.out.printf("%n");
                    logger.info("La partie est terminée (Défaite, l'ordinateur a trouvé le code)");
                    System.out.println("Le code secret adverse était " + Arrays.toString(botCode));
                    System.out.println("Défaite, l'ordinateur a trouvé votre code en " + coupsBot + " coups");
                    Menu.endMenuMastermindDuel();
                }
            }
        } catch (InputMismatchException e) {
            System.out.printf("%n");
            logger.fatal("InputMismatchException catchée : Saisie incorrect, redémarrage du jeu");
            System.out.println("Saisie incorrecte, les lettres et les chiffres inférieurs à 1 sont interdits !");
            MastermindDuel.mastermindDuel();
        }
    }
}
