import java.util.Scanner;

public class Cypher {

    //test
    public Cypher (){
        // Constructor
    }

    private String encypher(String text){


        return "Implementation coming soon :)";

    }

    public static void main(String[] arg) {
        Cypher cypher = new Cypher();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the text you want to encode: ");
        String text = scanner.next();
        System.out.println(cypher.encypher(text));
    }
}
