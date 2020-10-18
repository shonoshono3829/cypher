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

    private String encipherCaesar(String text, int key) {
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

    private String encipherVignere(String text, String key) {
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

    private String decipher(String text, String key) {
        String type = key.substring(0,1);
        type = type.trim().toLowerCase();
        if (type.equals("1")) {
            int caesarKey = Integer.parseInt(key.substring(1));
            return decipherCaesar(text, caesarKey);
        }
        else if (type.equals("2")) {
            String vignereKey = key.substring(1);
            return decipherVignere(text, vignereKey);
        }
        else
            return null;
    }

    private String decipherCaesar(String text, int key) {       // Works! But can be way more simple.
        text = text.trim().toLowerCase();
        char[] chars = text.toCharArray();
        key = 26 - key % 26;
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

    private String decipherVignere(String text, String key) {
        //TODO: implement decypherVignere
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

    private void printFrequencyAnalysis (String input){
        input = input.toLowerCase().trim();
        char[] text = input.toCharArray();
        int size = 0;
        Map <Character, Integer> frequencies = new HashMap<>();
        for(char letter : text){
            if(letter >= 'a' && letter <= 'z') {
                size++;
                //increments the frequency for that letter
                if(frequencies.containsKey(letter)){
                    frequencies.put(letter, frequencies.get(letter) + 1);
                } else{
                    frequencies.put(letter, 1);
                }
            }
        }
        int total = 0;
        for(char letter : alphabet){
            if(frequencies.containsKey(letter)){
                double freq = frequencies.get(letter);
                double percent = (freq/size) * 100.0;
                //prints the frequency for the letter as a percentage
                System.out.println(letter + ": " + percent + "%");
                //later might add a suggestion based on a comparison with English relative frequencies
                total += frequencies.get(letter);
            } else{
                System.out.println(letter + ": " + "0.0%");
            }
        }
        System.out.println(total);
    }

    public static void main(String[] arg) {
        Cypher cypher = new Cypher();
        Scanner inputTextScanner = new Scanner(System.in);
        System.out.println("Enter the text you want to encode:");
        String text = inputTextScanner.nextLine();

        Scanner optionScanner = new Scanner(System.in);
        System.out.println("\nTo encypher the entered text, enter E. To decypher the entered text, enter D." +
                "To run a frequency analysis on the entered text, enter F.");
        String option = optionScanner.nextLine();

        if (option.equals("E")) {
            System.out.println("Enter the the number of the cipher you want to use: \n" +
                    "1) Caesar: shifts alphabet by specified key (int)" +
                    "\n2) Vignere square: shifts every letter by a different Caesar-shifted alphabet" +
                    " according to the specified key (string)");

            String type = inputTextScanner.nextLine();
            type = type.trim().toLowerCase();
            if (type.equals("1")) {
                System.out.println("Enter a number you want to use as a key for encoding: ");
                int key = inputTextScanner.nextInt();
                System.out.println(cypher.encipherCaesar(text, key));
                System.out.println("Key for decrypting: " + "1" + key);
            }
            if (type.equals("2")) {
                System.out.println("Enter a word to use as a key for encoding: ");
                String key = inputTextScanner.nextLine();
                System.out.println(cypher.encipherVignere(text, key));
                System.out.println("Key for decrypting: " + "2" + key);
            }
        }

        else if (option.equals("D")) {
            System.out.println("Enter the key to decipher the input text: ");
            String key = inputTextScanner.nextLine();
            System.out.println(cypher.decipher(text, key));
        }

        else if(option.equals("F")){
            cypher.printFrequencyAnalysis(text);
        }

        else
            System.out.println("Entry invalid");
    }
}