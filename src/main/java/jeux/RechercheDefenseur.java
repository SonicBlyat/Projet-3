package jeux;

import launcher.Menu;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.util.*;

public class RechercheDefenseur {

    public static void rechercheDefenseur() throws FileNotFoundException {

        Scanner sc = new Scanner(System.in);
        Random r = new Random();

        ResourceBundle bundle = ResourceBundle.getBundle("config");

        Logger logger = LogManager.getLogger();

        int coups = 0;
        int coupsMax = Integer.parseInt(bundle.getString("coupsMaxRechercheDefenseur")); // NOMBRE DE COUPS (CONFIGURABLE)
        int fourchette = Integer.parseInt(bundle.getString("chiffreMax"));       // UTILISER DES CHIFFRES ENTRE 1 ET ... (CONFIGURABLE)
        int max = Integer.parseInt(bundle.getString("tailleCode"));              // TAILLE DU CODE (CONFIGURABLE)

        logger.info("LANCEMENT DU JEU : RECHERCHE DEFENSEUR");
        logger.trace(coupsMax + " coups maximum");
        logger.trace("Chiffres entre 1 et " + fourchette);
        logger.trace("Taille du code : " + max + " chiffres");

        System.out.printf("%n");
        System.out.println("RECHERCHE : DEFENSEUR");
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

            // PREMIER ESSAI DU BOT
            ArrayList<Integer> TryBot = new ArrayList<Integer>();
            for (int i = 0; i < max; i++) {
                TryBot.add(r.nextInt(fourchette) + 1);
            }
            logger.info("L'ordinateur vient d'entrer sa saisie");

            while (coups < coupsMax) {
                for (int i = 0; i < max; i++) {
                    System.out.printf("%n");
                    System.out.println("Ordinateur : " + StringUtils.join(TryBot, ""));
                    logger.info("Affichage de la saisie ordinateur");
                    System.out.printf("%n");
                    System.out.print("Donnez des indices : ");
                    String[] reponse = new String[max];
                    String inputReponse = sc.next();
                    for (i = 0; i < max; i++) {
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
                        if (TryBot.get(i) < 1) { // EMPECHE D'ARRIVER A 0
                            TryBot.set(i, 1);
                        }
                        if (TryBot.get(i) > fourchette) { // EMPECHE DE DEPASSER FOURCHETTE
                            TryBot.set(i, fourchette);
                        }
                    }
                    logger.info("Traitement des indices par l'ordinateur");
                    int numberOfCorrectBot = StringUtils.countMatches(inputReponse, "="); // COMPTE LE NOMBRE DE "="
                    coups++;
                    logger.trace("Coups : " + coups);

                    if (coups == coupsMax) {
                        System.out.printf("%n");
                        logger.info("La partie est terminée (Victoire, l'ordinateur n'a pas trouvé le code)");
                        System.out.println("Victoire, l'ordinateur a atteint les " + coupsMax + " coups autorisés");
                        Menu.endMenuRechercheDefenseur();
                    }
                    if (numberOfCorrectBot == max) {
                        System.out.printf("%n");
                        logger.info("La partie est terminée (Défaite, l'ordinateur a trouvé le code)");
                        System.out.println("Défaite, l'ordinateur a trouvé le code en seulement " + coups + " coups !");
                        Menu.endMenuRechercheDefenseur();
                    }
                }
            }
        } catch (InputMismatchException e) {
            System.out.printf("%n");
            logger.fatal("InputMismatchException catchée : Saisie incorrect, redémarrage du jeu");
            System.out.println("Saisie incorrecte, les lettres et les chiffres inférieurs à 1 sont interdits !");
            RechercheDefenseur.rechercheDefenseur();
        }
    }
}
