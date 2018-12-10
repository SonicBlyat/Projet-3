package jeux.recherche;

import java.util.Random;
import java.util.Scanner;

public class RechercheModel {

    public int[] generateCode(int codeSize, int maxNumber, Random r) {
        int[] code = new int[codeSize];
        for (int i = 0; i < codeSize; i++) {
            code[i] = r.nextInt(maxNumber) + 1;
        }
        return code;
    }

    public int[] userInput(int codeSize, int maxNumber, Scanner sc) {
        int[] input = new int[codeSize];
        System.out.printf("%n");
        int inputScanner = sc.nextInt();
        for (int i = 0; i < codeSize; i++) {
            input[i] = (int) (inputScanner / (Math.pow(10, (codeSize - i - 1)))) % 10;
            if (input[i] < 1) {
                System.out.printf("%n");
                System.out.println("Invalid input : Enter " + codeSize + " digits between 1 and " + maxNumber);
                System.out.println("Please enter a valid input below :");
                inputScanner = sc.nextInt();
            }
            if (input[i] > maxNumber) {
                System.out.printf("%n");
                System.out.println("Invalid input : Enter " + codeSize + " digits between 1 and " + maxNumber);
                System.out.println("Please enter a valid input below :");
                inputScanner = sc.nextInt();
            }
        }
        return input;
    }
}
