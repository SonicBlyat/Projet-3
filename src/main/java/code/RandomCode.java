package code;

import java.util.Random;
import java.util.ResourceBundle;

/**
 * Cette classe genere une combinaison aleatoire
 * La taille du code et la fourchette de chiffres sont modifiables via config.properties
 *
 * @author Axel Allain
 */

public class RandomCode {

    private Random random = new Random();
    private int maxNumber;
    private int codeSize;

    public RandomCode(int maxNumber, int codeSize) {
        ResourceBundle bundle = ResourceBundle.getBundle("config");
        this.maxNumber = Integer.parseInt(bundle.getString("maxNumber"));
        this.codeSize = Integer.parseInt(bundle.getString("codeSize"));
    }

    /**
     *
      * @return Une combinaison aleatoire de taille codeSize entre 1 et maxNumber
     */
    public int[] randomCode() {
        int[] code = new int[codeSize];
        for (int i = 0; i < codeSize; i++) {
            code[i] = random.nextInt(maxNumber) + 1;
        }
        return code;
    }
}
