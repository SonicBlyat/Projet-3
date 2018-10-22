import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Menu menu = new Menu();
        menu.mainMenu();
    }


    public static void challenger() {

        Scanner sc = new Scanner(System.in);

        int essais = 10;        // NOMBRE D'ESSAIS
        boolean issue;          // VICTOIRE DEFAITE
        int s;                  // SAISIE UTILISATEUR
        int i = 0;

        System.out.println("Bienvenue dans Mastermind");
        System.out.println("Trouvez le code secret !");
        System.out.println("Il vous reste " + essais + " essais.");

        randomCode();           // GENERATION DU CODE SECRET
        System.out.printf("%n");
        System.out.println("TEST GENERATION CODE : " + randomCode());
    }

    public static void defenseur() {

    }

    public static void duel() {

    }

    public static int randomCode() {

        Random r = new Random();
        int possibilites[] = new int[] {1111, 1112, 1113, 1114, 1121, 1122, 1123, 1124, 1131, 1132, 1133, 1134, 1141, 1142, 1143, 1144, 1211, 1212, 1213, 1214, 1221, 1222, 1223, 1224, 1231, 1232, 1233, 1234, 1241, 1242, 1243, 1244, 1311, 1312, 1313, 1314, 1321, 1322, 1323, 1324, 1331, 1332, 1333, 1334, 1341, 1342, 1343, 1344, 1411, 1412, 1413, 1414, 1421, 1422, 1423, 1424, 1431, 1432, 1433, 1434, 1441, 1442, 1443, 1444, 2111, 2112, 2113, 2114, 2121, 2122, 2123, 2124, 2131, 2132, 2133, 2134, 2141, 2142, 2143, 2144, 2211, 2212, 2213, 2214, 2221, 2222, 2223, 2224, 2231, 2232, 2233, 2234, 2241, 2242, 2243, 2244, 2311, 2312, 2313, 2314, 2321, 2322, 2323, 2324, 2331, 2332, 2333, 2334, 2341, 2342, 2343, 2344, 2411, 2412, 2413, 2414, 2421, 2422, 2423, 2424, 2431, 2432, 2433, 2434, 2441, 2442, 2443, 2444, 3111, 3112, 3113, 3114, 3121, 3122, 3123, 3124, 3131, 3132, 3133, 3134, 3141, 3142, 3143, 3144, 3211, 3212, 3213, 3214, 3221, 3222, 3223, 3224, 3231, 3232, 3233, 3234, 3241, 3242, 3243, 3244, 3311, 3312, 3313, 3314, 3321, 3322, 3323, 3324, 3331, 3332, 3333, 3334, 3341, 3342, 3343, 3344, 3411, 3412, 3413, 3414, 3421, 3422, 3423, 3424, 3431, 3432, 3433, 3434, 3441, 3442, 3443, 3444, 4111, 4112, 4113, 4114, 4121, 4122, 4123, 4124, 4131, 4132, 4133, 4134, 4141, 4142, 4143, 4144, 4211, 4212, 4213, 4214, 4221, 4222, 4223, 4224, 4231, 4232, 4233, 4234, 4241, 4242, 4243, 4244, 4311, 4312, 4313, 4314, 4321, 4322, 4323, 4324, 4331, 4332, 4333, 4334, 4341, 4342, 4343, 4344, 4411, 4412, 4413, 4414, 4421, 4422, 4423, 4424, 4431, 4432, 4433, 4434, 4441, 4442, 4443, 4444};
        int code = r.nextInt(possibilites.length);
        return possibilites[code];

    }
}







