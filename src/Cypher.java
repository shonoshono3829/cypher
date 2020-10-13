import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Cypher {

    char[] alphabet;
    Map<Character, Map<Character, Character>> vignereSquare;

    public Cypher() {
        // initialize reference alphabet
        alphabet = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
                'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

        //initialize vignere square
        vignereSquare = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            //add each letter of the alphabet and a new map to the wrapper map, then fill the second
            // map with shifted alphabets
            vignereSquare.put(alphabet[i], new HashMap<>());
            for (int j = 0; j < 26; j++) {
                vignereSquare.get(alphabet[i]).put(alphabet[j], alphabet[(j + i) % 26]);
            }
        }
    }

    private String encypher(String text, int key) {
        text = text.trim().toLowerCase();
        char[] chars = text.toCharArray();
        key = key % 26;
        Map charMap = getCaesarMap(key);
        StringBuilder result = new StringBuilder();
        for (char letter : chars) {
            if (letter >= 'a' && letter <= 'z') {
                result.append(charMap.get(letter));
            } else {
                result.append(letter);
            }
        }
        return (result.toString());
    }

    private String encypherVignere(String text, String key) {
        text = text.trim().toLowerCase();
        char[] textChars = text.toCharArray();
        key = key.trim().toLowerCase();
        char[] keyChars = key.toCharArray();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < textChars.length; i++) {
            if (textChars[i] >= 'a' && textChars[i] <= 'z') {
                result.append(vignereSquare.get(keyChars[i % keyChars.length])
                        .get(textChars[i]));
            } else {
                result.append(textChars[i]);
            }
        }
        return result.toString();
    }

    private Map<Character, Character> getCaesarMap(int key) {
        Map<Character, Character> charMap = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            charMap.put(alphabet[i], alphabet[(i + key) % 26]);
        }
        return charMap;
    }

    public static void main(String[] arg) {
        Cypher cypher = new Cypher();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the text you want to encode:");
        String text = scanner.nextLine();
        System.out.println("Enter the the number of the cipher you want to use: \n" +
                "1) Caesar: shifts alphabet by specified key (int)" +
                "\n2) Vignere square: shifts every letter by a different Caesar-shifted alphabet" +
                "according to the specified key (string)");
        String type = scanner.nextLine();
        type = type.trim().toLowerCase();
        if (type.equals("1")) {
            System.out.println("Enter a number you want to use as a key for encoding: ");
            int key = scanner.nextInt();
            System.out.println(cypher.encypher(text, key));
        }
        if (type.equals(2)) {
            System.out.println("Enter a word to use as a key for encoding: ");
            String key = scanner.nextLine();
            System.out.println(cypher.encypherVignere(text, key));
        }
    }
}
