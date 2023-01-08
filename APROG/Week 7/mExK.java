import java.util.Scanner;

public class mExK {
    public static int gameScore(int diceFaces) {
        int score, prevFace = 0;
        int inv = invertNum(diceFaces);
        for (score = 0; inv > 0; inv /= 10) {
            if(inv % 10 == prevFace) {
                if(score > 0)
                    score *= -1;
                score -= (inv % 10);
            }
            else
                score += (inv % 10);
            prevFace = (inv % 10);
        }
        return score;
    }
    public static int invertNum(int faces) {
        int inverted;
        for(inverted = 0; faces > 0; faces /= 10, inverted *= 10)
            inverted += (faces % 10);
        return inverted / 10;
    }
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        int faces;
        faces = read.nextInt();
        System.out.println("points=" + gameScore(faces));
        read.close();
        System.exit(0);
    }
}
