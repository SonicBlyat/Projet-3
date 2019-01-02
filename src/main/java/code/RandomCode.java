package code;

import java.util.Random;

public class RandomCode {

    private Random random = new Random();
    private int maxNumber;
    private int codeSize;

    public RandomCode(int maxNumber, int codeSize) {
        this.maxNumber = maxNumber;
        this.codeSize = codeSize;
    }

    public int[] randomCode() {
        int[] code = new int[codeSize];
        for (int i = 0; i < codeSize; i++) {
            code[i] = random.nextInt(maxNumber) + 1;
        }
        return code;
    }
}
