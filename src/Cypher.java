import java.util.*;

public class Cypher {

    /**
     * Encodes and decodes user inputted text
     */

    //originally this was going to be a queue but an array might be better
    private char[] textChars;

    private Map<Character, Character> keyMap;

    private String key;

    private Scanner scanner;

    public Cypher (){
        keyMap = new HashMap<>();
        scanner = new Scanner(System.in);
    }

    public void start(){
        System.out.println("Enter text to be encoded");
        if(scanner.hasNext()) {
            textChars = scanner.next().toCharArray();
        }
        System.out.println("Enter key");
        if (scanner.hasNext()){
            key = scanner.next();
        }
    }

    public static void main(String args[]){

    }


}
