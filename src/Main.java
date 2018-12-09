import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int a = 5, b = 8;
       /* ArrayList<String> firstList;
        try {
            firstList = readText("/home/exynox/IdeaProjects/Affine-Cipher-Encryption/src/input1.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            firstList = null;
        }
        ArrayList<String> secondList = new ArrayList<String>(700);
        for (int i = 0; i < firstList.size(); i++) {
            secondList.add(encryption(firstList.get(i), a, b));
        }
        for (int i = 0; i < secondList.size(); i++) {
//            System.out.println(firstList.get(i));
        }*/
        String word1 = encryption("affine", a, b);
        String word2 = decryption(word1, a, b);
        System.out.println(word1);
        System.out.println(word2);

    }

    static ArrayList<String> readText(String path) throws FileNotFoundException {
        File file = new File(path);
        ArrayList<String> list = new ArrayList<>(700);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            list.add(scanner.next());
        }
        return list;

    }

    static String encryption(String word, int a, int b) {
        word = word.toLowerCase(); // karışıklık olmasın diye lower case
        char[] chars = word.toCharArray();//değiştirmek için char array
        for (int i = 0; i < word.length(); i++) {
            if (!Character.isLetter(chars[i])) continue;
            int value = ((chars[i] - 97) * a + b) % 26; //hesaplama
            chars[i] = (char) (value + 97); // yeni değer
        }
        word = String.valueOf(chars); //stringi yeniden oluşturma
        return word;
    }

    static int modInverse(int a, int m) { //eucledean mod inverse algorithm
        a = a % m;
        for (int x = 1; x < m; x++)
            if ((a * x) % m == 1)
                return x;
        return 1;
    }

    static String decryption(String word, int a, int b) {
        char chars[] = word.toCharArray();
        int alpha = modInverse(a, 26);
        for (int i = 0; i < word.length(); i++) {
            if (!Character.isLetter(chars[i])) continue;
            int value = alpha * ((chars[i] - 97) - b);
            value = Math.floorMod(value, 26);
            chars[i] = (char) (value + 97);
        }
        word = String.valueOf(chars);
        return word;
    }
}

