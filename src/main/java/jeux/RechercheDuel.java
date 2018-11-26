package jeux;

import launcher.Menu;
import org.apache.commons.lang3.StringUtils;

import java.io.FileNotFoundException;
import java.util.*;

public class RechercheDuel {

    public static void rechercheDuel() throws FileNotFoundException {

        Scanner sc = new Scanner(System.in);
        Random r = new Random();

        ResourceBundle bundle = ResourceBundle.getBundle("config");

        int coupsUser = 0;
        int coupsBot = 0;
        int coupsMaxUser = Integer.parseInt(bundle.getString("coupsMaxRechercheDuel")); // NOMBRE DE COUPS   (CONFIGURABLE)
        int coupsMaxBot = Integer.parseInt(bundle.getString("coupsMaxRechercheDuel"));
        int fourchette = Integer.parseInt(bundle.getString("chiffreMax")); // UTILISER DES CHIFFRES ENTRE 1 ET ... (CONFIGURABLE)
        int max = Integer.parseInt(bundle.getString("tailleCode"));        // TAILLE DU TABLEAU (CONFIGURABLE)
        boolean modeDev = Boolean.parseBoolean(bundle.getString("modeDev"));

        System.out.printf("%n");
        System.out.println("RECHERCHE : DUEL");
        System.out.println("Choisissez un code pour que l'ordinateur puisse choisir le sien");
        System.out.printf("%n");

        // GENERATION DU CODE PAR L'UTILISATEUR
        try {
            int[] userCode = new int[max];
            int inputUserCode = sc.nextInt();
            for (int i = 0; i < max; i++) {
                userCode[i] = (int) (inputUserCode / (Math.pow(10, (max - i - 1)))) % 10;
                if (userCode[i] < 1) {
                    System.out.printf("%n");
                    System.out.println("Saisie incorrect : " + max + " chiffres maximum composés de chiffres entre 1 et " + fourchette);
                    System.out.println("Veuillez entrer une nouvelle saisie ci-dessous :");
                    inputUserCode = sc.nextInt();
                }
                if (userCode[i] > fourchette) {
                    System.out.printf("%n");
                    System.out.println("Saisie incorrect : " + max + " chiffres maximum composés de chiffres entre 1 et " + fourchette);
                    System.out.println("Veuillez entrer une nouvelle saisie ci-dessous :");
                    inputUserCode = sc.nextInt();
                }
            }
            System.out.println("VOTRE CODE : " + StringUtils.join(inputUserCode, ""));

            // GENERATION DU CODE PAR L'ORDINATEUR
            int[] botCode = new int[max];
            for (int i = 0; i < max; i++) {
                botCode[i] = r.nextInt(fourchette) + 1;
            }

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

            while (coupsUser < coupsMaxUser && coupsBot < coupsMaxBot) {

                // SAISIE DE L'ORDINATEUR
                System.out.printf("%n");
                System.out.println("Ordinateur : " + StringUtils.join(inputBot, ""));

                // RESULTAT DE LA SAISIE ORDINATEUR
                System.out.print("Indices : ");
                String[] resultatBot = new String[max];
                String inputResultatBot = sc.next();
                for (int i = 0; i < max; i++) {
                    resultatBot[i] = (inputResultatBot.charAt(i) + "");
                }

                for (int i = 0; i < resultatBot.length; i++) {
                    if (resultatBot[i].equals("=")) {
                        inputBot.get(i);
                    } else if (resultatBot[i].equals("+")) {
                        inputBot.set(i, inputBot.get(i) + 1);
                    } else if (resultatBot[i].equals("-")) {
                        inputBot.set(i, inputBot.get(i) - 1);
                    }
                    if (inputBot.get(i) < 1) { // EMPECHE D'ARRIVER A 0
                        inputBot.set(i, 1);
                    }
                    if (inputBot.get(i) > fourchette) { // EMPECHE DE DEPASSER FOURCHETTE
                        inputBot.set(i, fourchette);
                    }
                }
                int numberOfCorrectBot = StringUtils.countMatches(inputResultatBot, "="); // COMPTE LE NOMBRE DE "="
                coupsBot++;

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
                        scannerUser = sc.nextInt();
                    }
                    if (inputUser[i] > fourchette) {
                        System.out.printf("%n");
                        System.out.println("Saisie incorrect : " + max + " chiffres maximum composés de chiffres entre 1 et " + fourchette);
                        System.out.println("Veuillez entrer une nouvelle saisie ci-dessous :");
                        scannerUser = sc.nextInt();
                    }
                }

                // RESULTAT DE LA SAISIE UTILISATEUR
                String resultatUser = "";
                for (int i = 0; i < max; i++) {
                    boolean bonChiffreUser = inputUser[i] == botCode[i];
                    boolean inferieurChiffreUser = inputUser[i] < botCode[i];
                    boolean superieurChiffreUser = inputUser[i] > botCode[i];
                    if (bonChiffreUser) {
                        resultatUser = resultatUser + "=";
                    }
                    if (inferieurChiffreUser) {
                        resultatUser += "+";
                    }
                    if (superieurChiffreUser) {
                        resultatUser = resultatUser + "-";
                    }
                }
                System.out.println(resultatUser); // INDICES POUR L'UTILISATEUR
                int numberOfCorrectUser = StringUtils.countMatches(resultatUser, "="); // COMPTE LE NOMBRE DE "="
                coupsUser++;

                if (numberOfCorrectUser == max && numberOfCorrectBot == max) {
                    System.out.printf("%n");
                    System.out.println("Egalité ! Les deux codes ont été trouvés en même temps !");
                    Menu.endMenuRechercheDuel();
                }
                if (coupsUser == coupsMaxUser) {
                    System.out.printf("%n");
                    System.out.println("Le code secret était " + Arrays.toString(botCode));
                    System.out.println("Défaite, vous avez atteint les " + coupsMaxUser + " coups autorisés");
                    Menu.endMenuRechercheDuel();
                }
                if (numberOfCorrectUser == max) {
                    System.out.printf("%n");
                    System.out.println("Victoire en seulement " + coupsUser + " coups !");
                    Menu.endMenuRechercheDuel();
                }
                if (coupsBot == coupsMaxBot) {
                    System.out.printf("%n");
                    System.out.println("Victoire, l'ordinateur a utilisé ses " + coupsMaxBot + " coups autorisés !");
                    Menu.endMenuRechercheDuel();
                }
                if (numberOfCorrectBot == max) {
                    System.out.printf("%n");
                    System.out.println("Défaite, l'ordinateur a trouvé votre code en " + coupsBot + " coups !");
                    Menu.endMenuRechercheDuel();
                }
            }
        } catch (InputMismatchException e) {
            System.out.printf("%n");
            System.out.println("Saisie incorrecte, les lettres et les chiffres inférieurs à 1 sont interdits !");
            RechercheDuel.rechercheDuel();
        }
    }
}
