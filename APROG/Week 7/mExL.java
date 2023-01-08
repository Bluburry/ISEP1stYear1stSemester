import java.util.Scanner;

public class mExL {
    public static int numOfWords(String phrase) {
        int count = 0;
        phrase.trim();
        if(phrase.charAt(0) >= 33 && phrase.charAt(0) <= 126)
            count++;
        for(int i = 1; i < phrase.length(); i++) {
            if(phrase.charAt(i) >= 33 && phrase.charAt(i) <= 126 && phrase.charAt(i - 1) == 32)
                count++;
        }
        return count;
    }
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        String sentence = read.nextLine();
        if(sentence.length() == 0) 
            System.out.println(0);
        else
            System.out.println(numOfWords(sentence));
        read.close();
        System.exit(0);
    }
}
