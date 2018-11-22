package jeux;

import launcher.Menu;
import org.apache.commons.lang3.StringUtils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class RechercheDuel {

    public static void rechercheDuel() throws FileNotFoundException {

        Scanner sc = new Scanner(System.in);
        Random r = new Random();

        int coupsUser = 0;
        int coupsBot = 0;
        int coupsMaxUser;   // NOMBRE DE COUPS   (CONFIGURABLE)
        int coupsMaxBot;
        int fourchette; // UTILISER DES CHIFFRES ENTRE 1 ET ... (CONFIGURABLE)
        int max;        // TAILLE DU TABLEAU (CONFIGURABLE)

        System.out.println("Avant de commencer, combien de chiffres souhaitez-vous pour le code secret ?");
        max = sc.nextInt();
        System.out.println("Le code secret sera composé de " + max + " chiffres.");
        System.out.println("---------------------------------------------------");
        System.out.println("Ces chiffres sont compris entre 1 et ... (Entrez le chiffre de votre choix)");
        fourchette = sc.nextInt();
        System.out.println("Les chiffres seront compris entre 1 et " + fourchette + ".");
        System.out.println("----------------------------------------");
        System.out.println("Combien d'essais souhaitez-vous pour trouver le code secret de l'ordinateur ?");
        coupsMaxUser = sc.nextInt();
        System.out.println("Combien d'essais souhaitez-vous donner à l'ordinateur pour trouver votre code secret ?");
        coupsMaxBot = sc.nextInt();
        System.out.println("Vous avez " + coupsMaxUser + " essais pour trouver le code secret de l'ordinateur, à vous de jouer !");
        System.out.println("------------------------------------------------------------------------");
        System.out.printf("%n");

        System.out.println("RECHERCHE : DUEL");
        System.out.println("Choisissez un code pour que l'ordinateur puisse choisir le sien");
        System.out.printf("%n");

        // GENERATION DU CODE PAR L'UTILISATEUR
        int[] userCode = new int[max];
        String inputUserCode = sc.next();
        for (int i = 0; i < max; i++) {
            userCode[i] = (Integer.parseInt(inputUserCode.charAt(i) + ""));
        }
        System.out.println("VOTRE CODE : " + StringUtils.join(inputUserCode, ""));

        // GENERATION DU CODE PAR L'ORDINATEUR
        int[] botCode = new int[max];
        for (int i = 0; i < max; i++) {
            botCode[i] = r.nextInt(fourchette) + 1;
        }

        System.out.printf("%n");
        System.out.println("L'ordinateur a généré son code, il joue en premier !");

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
            }
            int numberOfCorrectBot = StringUtils.countMatches(inputResultatBot, "="); // COMPTE LE NOMBRE DE "="
            coupsBot++;

            // SAISIE DE L'UTILISATEUR
            int[] inputUser = new int[max];
            System.out.printf("%n");
            int scannerUser = sc.nextInt();
            for (int i = 0; i < max; i++) {
                inputUser[i] = (int) (scannerUser / (Math.pow(10, (max - i - 1)))) % 10;
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
                    resultatUser += ">";
                }
                if (superieurChiffreUser) {
                    resultatUser = resultatUser + "<";
                }
            }
            System.out.println(resultatUser); // INDICES POUR L'UTILISATEUR
            int numberOfCorrectUser = StringUtils.countMatches(resultatUser, "="); // COMPTE LE NOMBRE DE "="
            coupsUser++;

            if (numberOfCorrectUser == max && numberOfCorrectBot == max) {
                System.out.printf("%n");
                System.out.printf("%n");
                System.out.println("Egalité ! Les deux codes ont été trouvés en même temps !");
                Menu.endMenuRechercheDuel();
            }
            if (coupsUser == coupsMaxUser) {
                System.out.println("Le code secret était " + StringUtils.join(botCode, ""));
                System.out.println("Défaite, vous avez atteint les " + coupsMaxUser + " coups autorisés");
                Menu.endMenuRechercheDuel();
            }
            if (numberOfCorrectUser == max) {
                System.out.printf("%n");
                System.out.printf("%n");
                System.out.println("Victoire en seulement " + coupsUser + " coups !");
                Menu.endMenuRechercheDuel();
            }
            if (coupsBot == coupsMaxBot) {
                System.out.println("Victoire, l'ordinateur a utilisé ses " + coupsMaxBot + " coups autorisés !");
                Menu.endMenuRechercheDuel();
            }
            if (numberOfCorrectBot == max) {
                System.out.printf("%n");
                System.out.printf("%n");
                System.out.println("Défaite, l'ordinateur a trouvé votre code en " + coupsBot + " coups !");
                Menu.endMenuRechercheDuel();
            }
        }
    }
}
