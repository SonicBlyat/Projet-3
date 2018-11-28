package jeux;

import launcher.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.util.*;

public class MastermindDefenseur {

    public static void mastermindDefenseur() throws FileNotFoundException {

        Scanner sc = new Scanner(System.in);
        Random r = new Random();

        ResourceBundle bundle = ResourceBundle.getBundle("config");

        Logger logger = LogManager.getLogger();

        int coups = 0;
        int coupsMax = Integer.parseInt(bundle.getString("coupsMaxMastermindDefenseur")); // NOMBRE DE COUPS (CONFIGURABLE)
        int fourchette = Integer.parseInt(bundle.getString("chiffreMax"));       // UTILISER DES CHIFFRES ENTRE 1 ET ... (CONFIGURABLE)
        int max = Integer.parseInt(bundle.getString("tailleCode"));              // TAILLE DU CODE (CONFIGURABLE)

        logger.info("LANCEMENT DU JEU : MASTERMIND DEFENSEUR");
        logger.trace(coupsMax + " coups maximum");
        logger.trace("Chiffres entre 1 et " + fourchette);
        logger.trace("Taille du code : " + max + " chiffres");

        System.out.printf("%n");
        System.out.println("MASTERMIND : DEFENSEUR");
        System.out.println("Choisissez une combinaison !");
        System.out.printf("%n");

        // GENERATION DU CODE PAR L'UTILISATEUR
        try {
            int[] code = new int[max];
            int inputCode = sc.nextInt();
            for (int i = 0; i < max; i++) {
                code[i] = (int) (inputCode / (Math.pow(10, (max - i - 1)))) % 10;
                if (code[i] < 1) {
                    System.out.printf("%n");
                    System.out.println("Saisie incorrect : " + max + " chiffres maximum composés de chiffres entre 1 et " + fourchette);
                    System.out.println("Veuillez entrer une nouvelle saisie ci-dessous :");
                    logger.error("Saisie incorrect");
                    inputCode = sc.nextInt();
                }
                if (code[i] > fourchette) {
                    System.out.printf("%n");
                    System.out.println("Saisie incorrect : " + max + " chiffres maximum composés de chiffres entre 1 et " + fourchette);
                    System.out.println("Veuillez entrer une nouvelle saisie ci-dessous :");
                    logger.error("Saisie incorrect");
                    inputCode = sc.nextInt();
                }
            }
            logger.info("Code secret généré par l'utilisateur");

            // PREMIERE SAISIE DE L'ORDINATEUR
            ArrayList<Integer> saisieBot = new ArrayList<Integer>();
            for (int i = 0; i < max; i++) {
                saisieBot.add(r.nextInt(fourchette) + 1);
            }
            logger.info("L'ordinateur vient d'entrer sa saisie");

            while (coups < coupsMax) {

                boolean[] codeUsed = new boolean[code.length];
                boolean[] saisieUsed = new boolean[saisieBot.size()];
                int numberOfCorrect = 0;
                int numberOfPresent = 0;
                System.out.println("Proposition de l'ordinateur : " + StringUtils.join(saisieBot, ""));
                logger.info("Affichage de la saisie ordinateur");

                // RESULTAT DE LA SAISIE ORDINATEUR
                for (int i = 0; i < code.length; i++) {
                    if (code[i] == saisieBot.get(i)) {
                        numberOfCorrect++;
                        codeUsed[i] = saisieUsed[i] = true;
                    }
                }

                for (int i = 0; i < code.length; i++) {
                    for (int j = 0; j < saisieBot.size(); j++) {
                        if (!codeUsed[i] && !saisieUsed[j] && code[i] == saisieBot.get(j)) {
                            numberOfPresent++;
                            codeUsed[i] = saisieUsed[j] = true;
                            break;
                        }
                    }
                }

                for (int i = 0; i < code.length; i++) {
                    for (int j = 0; j < saisieBot.size(); j++) {
                        boolean test = code[i] != saisieBot.get(i);
                        if (code[i] == saisieBot.get(i)) {
                            saisieBot.get(i);
                        } else if (!codeUsed[i] && !saisieUsed[j] && code[i] == saisieBot.get(j)) {
                            for (j = 0; j < saisieBot.size(); j++) {
                                if (saisieBot.indexOf(test) == j) { // SI i EST PRESENT ET PAS DE BIEN PLACE SUR INDEX j
                                    saisieBot.set(j, i); // DEPLACE i SUR INDEX j POUR TESTER SI IL PASSE EN BIEN PLACE ICI
                                }
                            }
                        } else {
                            saisieBot.set(i, r.nextInt(fourchette) + 1); // SI NI CORRECT NI PRESENT, NOUVELLE VALEUR
                        }
                    }
                }
                logger.info("Traitement des indices par l'ordinateur");
                // INDICES
                System.out.println(numberOfCorrect + " Bien placé(s)");
                System.out.println(numberOfPresent + " Présent(s) mais mal placé(s)");
                System.out.printf("%n");
                coups++;
                logger.trace("Coups : " + coups);

                if (coups == coupsMax) {
                    System.out.printf("%n");
                    logger.info("La partie est terminée (Victoire, l'ordinateur n'a pas trouvé le code)");
                    System.out.println("Victoire, l'ordinateur a atteint les " + coupsMax + " coups autorisés");
                    Menu.endMenuMastermindDefenseur();
                }
                if (numberOfCorrect == max) {
                    System.out.printf("%n");
                    logger.info("La partie est terminée (Défaite, l'ordinateur a trouvé le code)");
                    System.out.println("Défaite, l'ordinateur a trouvé le code en seulement " + coups + " coups !");
                    Menu.endMenuMastermindDefenseur();
                }
            }
        } catch (InputMismatchException e) {
            System.out.printf("%n");
            logger.fatal("InputMismatchException catchée : Saisie incorrect, redémarrage du jeu");
            System.out.println("Saisie incorrecte, les lettres et les chiffres inférieurs à 1 sont interdits !");
            MastermindDefenseur.mastermindDefenseur();
        }
    }
}
