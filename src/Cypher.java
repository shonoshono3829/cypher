import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Cypher {

    String[] alphabet;

    public Cypher (){
        // Constructor
        alphabet = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l",
        "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    }

    private String encypher(String text, int key){
        text = text.trim();
        text = text.toLowerCase();
        char[] chars = text.toCharArray();
        key = key % 26;
        Map charMap = getCaesarMap(key);
        StringBuilder result = new StringBuilder();
        for(char letter : chars){
            if(letter >= 'a' && letter <= 'z') {
                result.append(charMap.get(letter));
            }else{
                result.append(letter);
            }
        }
        return (result.toString());
    }

    private Map<Character, Character> getCaesarMap(int key) {
        Map<Character, Character> charMap = new HashMap<>();
        for(int i = 0; i < 26; i ++){
            charMap.put(alphabet[i].charAt(0), alphabet[(i + key) % 26].charAt(0));
        }
        return charMap;
    }

    public static void main(String[] arg) {
        Cypher cypher = new Cypher();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the text you want to encode: ");
        String text = scanner.nextLine();
        System.out.println("Enter the key you want to use for encoding: ");
        int key = scanner.nextInt();
        System.out.println(cypher.encypher(text, key));
    }
}
