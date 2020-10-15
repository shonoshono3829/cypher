import java.util.Arrays;
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
        return result.toString();
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

    private String decypher(String text) {
        return "ABC";
    }

    private Map<Character, Character> getCaesarMap(int key) {
        Map<Character, Character> charMap = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            charMap.put(alphabet[i], alphabet[(i + key) % 26]);
        }
        return charMap;
    }

    private void printFrequencyAnalysis (String input){
        input = input.toLowerCase().trim();
        char[] text = input.toCharArray();
        int [] frequencies = new int [26];
        for(char letter : text){
            if(letter >= 'a' && letter <= 'z') {
                //increments the frequency for that letter
                frequencies[(letter - 'a')] += 1;
            }
        }
        for(int i = 0; i < 26; i ++){
            System.out.println(alphabet[i] + " occurred " + frequencies[i] +
                    " times");
        }
    }

    public static void main(String[] arg) {
        Cypher cypher = new Cypher();
        Scanner inputTextScanner = new Scanner(System.in);
        System.out.println("Enter the text you want to encode:");
        String text = inputTextScanner.nextLine();

        Scanner optionScanner = new Scanner(System.in);
        System.out.println("To encypher the entered text, enter E. To decypher the entered text, enter D." +
                "To run a frequency analysis on the entered text, enter F.");
        String option = optionScanner.nextLine();

        if (option.equals("E")) {
            System.out.println("Enter the the number of the cipher you want to use: \n" +
                    "1) Caesar: shifts alphabet by specified key (int)" +
                    "\n2) Vignere square: shifts every letter by a different Caesar-shifted alphabet" +
                    "according to the specified key (string)");
            String type = inputTextScanner.nextLine();
            type = type.trim().toLowerCase();
            if (type.equals("1")) {
                System.out.println("Enter a number you want to use as a key for encoding: ");
                int key = inputTextScanner.nextInt();
                System.out.println(cypher.encypher(text, key));
            }
            if (type.equals(2)) {
                System.out.println("Enter a word to use as a key for encoding: ");
                String key = inputTextScanner.nextLine();
                System.out.println(cypher.encypherVignere(text, key));
            }
        }
        if (option.equals("D")){
            cypher.decypher(text);
        } else if(option.equals("F")){
            cypher.printFrequencyAnalysis(text);
        }
        else
            System.out.println("Entry invalid");
    }
}
