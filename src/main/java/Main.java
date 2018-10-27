import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Menu menu = new Menu();
        menu.mainMenu();
    }

    public static void rechercheChallenger() throws FileNotFoundException {

        Scanner sc = new Scanner(System.in);
        Random r = new Random();

        int coups = 0;
        int coupsMax;   // NOMBRE DE COUPS   (CONFIGURABLE)
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
        System.out.println("Combien d'essais souhaitez-vous pour trouver le code secret ?");
        coupsMax = sc.nextInt();
        System.out.println("Vous avez " + coupsMax + " essais pour trouver le code secret, à vous de jouer !");
        System.out.println("------------------------------------------------------------------------");
        System.out.printf("%n");
        System.out.println("RECHERCHE +/- : CHALLENGER");
        System.out.println("Trouvez le code secret en 10 coups maximum !");
        System.out.printf("%n");

        // GENERATION DU CODE SECRET
        int[] code = new int[max];
        for (int i = 0; i < max; i++) {
            code[i] = r.nextInt(fourchette) + 1;
        }

        while (coups < coupsMax) {

            int[] saisie = new int[max];
            System.out.printf("%n");
            int inputSaisie = sc.nextInt();
            for(int i = 0; i < max; i++) {
                saisie[i] = (int) (inputSaisie / (Math.pow(10, (max - i - 1)))) % 10;
            }

            for(int i = 0; i < max; i++) {
                boolean bonChiffre = saisie[i] == code[i];
                boolean inferieurChiffre = saisie[i] < code[i];
                boolean superieurChiffre = saisie[i] > code[i];
                if (bonChiffre) { System.out.println("="); }
                if (inferieurChiffre) { System.out.println(">"); }
                if (superieurChiffre) { System.out.println("<"); }
            }

            coups++;
            if (coups == coupsMax) {
                System.out.println("Le code secret était " + code[0] + code[1] + code[2] + code[3]);
                System.out.println("Défaite, vous avez atteint les 10 coups autorisés");
                Menu.endMenuMastermindChallenger();
            }
            if (saisie[0] == code[0] && saisie[1] == code[1] && saisie[2] == code[2] && saisie[3] == code[3]) {
                System.out.printf("%n");
                System.out.printf("%n");
                System.out.println("Victoire en seulement " + coups + " coups !");
                Menu.endMenuMastermindChallenger();
            }
        }
    }

    public static void rechercheDefenseur() {
        System.out.println("RECHERCHE +/- : DÉFENSEUR");
    }

    public static void rechercheDuel() {
        System.out.println("RECHERCHE +/- : DUEL");
    }

    public static void mastermindChallenger() throws FileNotFoundException {

        Properties p = new Properties();
        OutputStream os = new FileOutputStream("config.properties");

        Scanner sc = new Scanner(System.in);
        Random r = new Random();

        int coups = 0;
        int coupsMax;   // NOMBRE DE COUPS   (CONFIGURABLE)
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
        System.out.println("Combien d'essais souhaitez-vous pour trouver le code secret ?");
        coupsMax = sc.nextInt();
        System.out.println("Vous avez " + coupsMax + " essais pour trouver le code secret, à vous de jouer !");
        System.out.println("------------------------------------------------------------------------");
        System.out.printf("%n");
        System.out.println("MASTERMIND : CHALLENGER");
        System.out.println("Trouvez le code secret en 10 coups maximum !");
        System.out.printf("%n");

        // GENERATION DU CODE SECRET
        int[] code = new int[max];
        for (int i = 0; i < max; i++) {
            code[i] = r.nextInt(fourchette) + 1;
        }

            while (coups < coupsMax) {

                int[] saisie = new int[max];
                System.out.printf("%n");
                int inputSaisie = sc.nextInt();
                for(int i = 0; i < max; i++) {
                    saisie[i] = (int) (inputSaisie / (Math.pow(10, (max - i - 1)))) % 10;
                }

                for(int i = 0; i < max; i++) {
                    boolean bonChiffre = saisie[i] == code[i];
                    System.out.print((bonChiffre ? saisie[i] : "*") + " "); // BON CHIFFRE ? SI OUI AFFICHE SAISIE[i]
                }                                                           // SINON AFFICHE "*"

                coups++;
                if (coups == coupsMax) {
                    System.out.println("Le code secret était " + code[0] + code[1] + code[2] + code[3]);
                    System.out.println("Défaite, vous avez atteint les 10 coups autorisés");
                    Menu.endMenuMastermindChallenger();
                }
                if (saisie[0] == code[0] && saisie[1] == code[1] && saisie[2] == code[2] && saisie[3] == code[3]) {
                    System.out.printf("%n");
                    System.out.printf("%n");
                    System.out.println("Victoire en seulement " + coups + " coups !");
                    Menu.endMenuMastermindChallenger();
                }
            }
        }

    public static void mastermindDefenseur() throws FileNotFoundException {

        Scanner sc = new Scanner(System.in);

        int coups = 0;        // NOMBRE D'ESSAIS
        int s;                // SAISIE UTILISATEUR

        System.out.println("MASTERMIND : DEFENSEUR");
        System.out.println("Entrez un code secret pour que l'ordinateur puisse jouer !");
        System.out.println("N'utilisez que des chiffres entre 1 et 4, votre combinaison doit faire 4 chiffres");

        Random r = new Random();
        int possibilites[] = new int[]{1111, 1112, 1113, 1114, 1121, 1122, 1123, 1124, 1131, 1132, 1133, 1134, 1141, 1142, 1143, 1144, 1211, 1212, 1213, 1214, 1221, 1222, 1223, 1224, 1231, 1232, 1233, 1234, 1241, 1242, 1243, 1244, 1311, 1312, 1313, 1314, 1321, 1322, 1323, 1324, 1331, 1332, 1333, 1334, 1341, 1342, 1343, 1344, 1411, 1412, 1413, 1414, 1421, 1422, 1423, 1424, 1431, 1432, 1433, 1434, 1441, 1442, 1443, 1444, 2111, 2112, 2113, 2114, 2121, 2122, 2123, 2124, 2131, 2132, 2133, 2134, 2141, 2142, 2143, 2144, 2211, 2212, 2213, 2214, 2221, 2222, 2223, 2224, 2231, 2232, 2233, 2234, 2241, 2242, 2243, 2244, 2311, 2312, 2313, 2314, 2321, 2322, 2323, 2324, 2331, 2332, 2333, 2334, 2341, 2342, 2343, 2344, 2411, 2412, 2413, 2414, 2421, 2422, 2423, 2424, 2431, 2432, 2433, 2434, 2441, 2442, 2443, 2444, 3111, 3112, 3113, 3114, 3121, 3122, 3123, 3124, 3131, 3132, 3133, 3134, 3141, 3142, 3143, 3144, 3211, 3212, 3213, 3214, 3221, 3222, 3223, 3224, 3231, 3232, 3233, 3234, 3241, 3242, 3243, 3244, 3311, 3312, 3313, 3314, 3321, 3322, 3323, 3324, 3331, 3332, 3333, 3334, 3341, 3342, 3343, 3344, 3411, 3412, 3413, 3414, 3421, 3422, 3423, 3424, 3431, 3432, 3433, 3434, 3441, 3442, 3443, 3444, 4111, 4112, 4113, 4114, 4121, 4122, 4123, 4124, 4131, 4132, 4133, 4134, 4141, 4142, 4143, 4144, 4211, 4212, 4213, 4214, 4221, 4222, 4223, 4224, 4231, 4232, 4233, 4234, 4241, 4242, 4243, 4244, 4311, 4312, 4313, 4314, 4321, 4322, 4323, 4324, 4331, 4332, 4333, 4334, 4341, 4342, 4343, 4344, 4411, 4412, 4413, 4414, 4421, 4422, 4423, 4424, 4431, 4432, 4433, 4434, 4441, 4442, 4443, 4444};
        int code = r.nextInt(possibilites.length);           // GENERATION DU CODE SECRET (INDEX ALEATOIRE DU TABLEAU)

        System.out.printf("%n");

        try {
            s = sc.nextInt(); // SAISIE UTILISATEUR ( CREATION DU CODE SECRET )
            while (coups < 10) {
                int b = possibilites[code]; // BUG ! // NE VEUT PAS GENERER UN NOUVEAU CODE A CHAQUE TOUR DE BOUCLE !
                System.out.println(b);
                int chiffre1 = Integer.parseInt(Integer.toString(b).substring(0, 1));  // PREMIER CHIFFRE DU CODE
                int saisie1 = Integer.parseInt(Integer.toString(s).substring(0, 1));                    // PREMIER CHIFFRE DE LA SAISIE
                if (chiffre1 == saisie1) {
                    System.out.println(chiffre1);
                } else {
                    System.out.println("X");
                }

                int chiffre2 = Integer.parseInt(Integer.toString(b).substring(1, 2));  // DEUXIEME CHIFFRE DU CODE
                int saisie2 = Integer.parseInt(Integer.toString(s).substring(1, 2));                    // DEUXIEME CHIFFRE DE LA SAISIE
                if (chiffre2 == saisie2) {
                    System.out.println(chiffre2);
                } else {
                    System.out.println("X");
                }

                int chiffre3 = Integer.parseInt(Integer.toString(b).substring(2, 3));  // TROISIEME CHIFFRE DU CODE
                int saisie3 = Integer.parseInt(Integer.toString(s).substring(2, 3));                    // TROISIEME CHIFFRE DE LA SAISIE
                if (chiffre3 == saisie3) {
                    System.out.println(chiffre3);
                } else {
                    System.out.println("X");
                }

                int chiffre4 = Integer.parseInt(Integer.toString(b).substring(3, 4));  // QUATRIEME CHIFFRE DU CODE
                int saisie4 = Integer.parseInt(Integer.toString(s).substring(3, 4));                    // QUATRIEME CHIFFRE DE LA SAISIE
                if (chiffre4 == saisie4) {
                    System.out.println(chiffre4);
                } else {
                    System.out.println("X");

                }
                coups++;
                if (coups == 10) {
                    System.out.println("Victoire, l'ordinateur a utilisé ses 10 coups !");
                    Menu.endMenuMastermindDefenseur();
                }
                if (saisie1 == chiffre1 && saisie2 == chiffre2 && saisie3 == chiffre3 && saisie4 == chiffre4) {
                    System.out.println("Défaite, l'ordinateur a trouvé votre code en " + coups + " coups !");
                    Menu.endMenuMastermindDefenseur();
                }
            }
    } catch (StringIndexOutOfBoundsException e) {}
    }}