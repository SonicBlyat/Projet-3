package jeux;

import launcher.Menu;
import org.apache.commons.lang3.StringUtils;

import java.io.FileNotFoundException;
import java.util.*;

public class MastermindDuel {

    public static void mastermindDuel() throws FileNotFoundException {

        Scanner sc = new Scanner(System.in);
        Random r = new Random();

        ResourceBundle bundle = ResourceBundle.getBundle("config");

        int coupsUser = 0;
        int coupsBot = 0;
        int coupsMaxUser = Integer.parseInt(bundle.getString("coupsMaxMastermindDuel")); // NOMBRE DE COUPS   (CONFIGURABLE)
        int coupsMaxBot = Integer.parseInt(bundle.getString("coupsMaxMastermindDuel"));
        int fourchette = Integer.parseInt(bundle.getString("chiffreMax")); // UTILISER DES CHIFFRES ENTRE 1 ET ... (CONFIGURABLE)
        int max = Integer.parseInt(bundle.getString("tailleCode"));        // TAILLE DU TABLEAU (CONFIGURABLE)
        boolean modeDev = Boolean.parseBoolean(bundle.getString("modeDev"));

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
                    inputUserCode = sc.nextInt();
                }
                if (userCode[i] > fourchette) {
                    System.out.printf("%n");
                    System.out.println("Saisie incorrect : " + max + " chiffres maximum composés de chiffres entre 1 et " + fourchette);
                    System.out.println("Veuillez entrer une nouvelle saisie ci-dessous :");
                    inputUserCode = sc.nextInt();
                }
            }

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
                            if (inputBot.indexOf(test) == 0) { // SI i EST PRESENT ET PAS DE BIEN PLACE SUR INDEX 0
                                inputBot.set(0, i); // DEPLACE i SUR INDEX 0 POUR TESTER SI IL PASSE EN BIEN PLACE ICI
                            }
                            if (inputBot.indexOf(test) == 1) {
                                inputBot.set(1, i);
                            }
                            if (inputBot.indexOf(test) == 2) {
                                inputBot.set(2, i);
                            }
                            if (inputBot.indexOf(test) == 3) {
                                inputBot.set(3, i);
                            }
                        } else {
                            inputBot.set(i, r.nextInt(fourchette) + 1);
                        } // SI NI CORRECT NI PRESENT, NOUVELLE VALEUR
                    }
                }

                // INDICES POUR L'ORDINATEUR
                System.out.println(numberOfCorrectBot + " Bien placé(s)");
                System.out.println(numberOfPresentBot + " Présent(s) mais mal placé(s)");
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

                // INDICES POUR L'UTILISATEUR
                System.out.println(numberOfCorrectUser + " Bien placé(s)");
                System.out.println(numberOfPresentUser + " Présent(s) mais mal placé(s)");
                coupsUser++;

                if (numberOfCorrectUser == max && numberOfCorrectBot == max) {
                    System.out.printf("%n");
                    System.out.println("Egalité ! Les deux codes ont été trouvés en même temps !");
                    Menu.endMenuMastermindDuel();
                }
                if (coupsUser == coupsMaxUser) {
                    System.out.printf("%n");
                    System.out.println("Le code secret était " + Arrays.toString(botCode));
                    System.out.println("Défaite, vous avez atteint les " + coupsMaxUser + " coups autorisés");
                    Menu.endMenuMastermindDuel();
                }
                if (numberOfCorrectUser == max) {
                    System.out.printf("%n");
                    System.out.println("Victoire en seulement " + coupsUser + " coups !");
                    Menu.endMenuMastermindDuel();
                }
                if (coupsBot == coupsMaxBot) {
                    System.out.printf("%n");
                    System.out.println("Victoire, l'ordinateur a utilisé ses " + coupsMaxBot + " coups autorisés !");
                    Menu.endMenuMastermindDuel();
                }
                if (numberOfCorrectBot == max) {
                    System.out.printf("%n");
                    System.out.println("Le code secret adverse était " + Arrays.toString(botCode));
                    System.out.println("Défaite, l'ordinateur a trouvé votre code en " + coupsBot + " coups");
                    Menu.endMenuMastermindDuel();
                }
            }
        } catch (InputMismatchException e) {
            System.out.printf("%n");
            System.out.println("Saisie incorrecte, les lettres et les chiffres inférieurs à 1 sont interdits !");
            MastermindDuel.mastermindDuel();
        }
    }
}
