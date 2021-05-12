import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static int lenInitArr;
    private static int[] initArr;
    private static int[] secArr;

    private static void checkLength() {
        do {
            System.out.println("enter length of initial array (10 < length < " + Integer.MAX_VALUE + ")");
            Scanner in = new Scanner(System.in);
            if (in.hasNextInt())
                lenInitArr = in.nextInt();
            else
                System.out.println("not int");
        } while (lenInitArr <= 10);
    }

    private static void createInitArray() {
        initArr = new int[lenInitArr];
        for (int i = 0; i < lenInitArr; i++)
            initArr[i] = i + 1;
    }

    private static void createSecArray(int randVal) {
        int offset = 0;
        secArr = new int[lenInitArr - 1];
        for (int i = 0; i < lenInitArr - 1; i++) {
            if (initArr[i] == randVal)
                offset++;
            secArr[i] = initArr[i + offset];
        }
        Random rnd = new Random();
        for (int i = 0; i < secArr.length; i++) {
            int index = rnd.nextInt(i + 1);
            int a = secArr[index];
            secArr[index] = secArr[i];
            secArr[i] = a;
        }
    }

    private static int searchNum() { //O(n)
        int sumInitArr = (lenInitArr * (lenInitArr + 1) / 2); //O(1)
        int sumSecArr = 0;
        for (int i : secArr) //O(n)
            sumSecArr += i;
        return sumInitArr - sumSecArr;
    }

    public static void main(String[] args) {
        checkLength();
        System.out.println("length initial array = " + lenInitArr);
        createInitArray();
        System.out.println("initial array: " + Arrays.toString(initArr));
        int randVal = (int) (Math.random() * lenInitArr + 1);
        System.out.println("will be deleted: " + randVal);
        createSecArray(randVal);
        System.out.println("second array: " + Arrays.toString(secArr));
        System.out.println("lost item: " + searchNum());
    }
}