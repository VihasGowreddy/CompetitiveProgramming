import java.util.Random;
import java.util.Scanner;

public class Main
{
    public static void main (String[] args)
    {
        int rounds = 1000;
        Random ran = new Random();
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < rounds; i++)
        {
            int x = ran.nextInt(3);
            if (x == 0)
            {
                guessA(in);
            }
            else if (x== 1) {
                guessB(in);
            }
            else if (x== 2) {
                guessC(in);
            }
        }
    }
    public static void guessA(Scanner in) {
        System.out.println("A");
        String[] line = in.nextLine().split(" ");
        String character = line[0];
        String truth = line[1];
        // switch
        if (truth.equals("0")) {
            // she chose C
            if (character.equals("B")) {
                System.out.println("C");
            }
            // she chose C
            else {
                System.out.println("B");
            }
        }
        else {
            System.out.println(character);
        }
        in.nextLine();
    }
    public static void guessB(Scanner in) {
        System.out.println("B");
        String[] line = in.nextLine().split(" ");
        String character = line[0];
        String truth = line[1];
        // switch
        if (truth.equals("0")) {
            // she chose A
            if (character.equals("A")) {
                System.out.println("C");
            }
            // she chose C
            else {
                System.out.println("A");
            }
        }
        else {
            System.out.println(character);
        }
        in.nextLine();
    }
    public static void guessC(Scanner in) {
        System.out.println("C");
        String[] line = in.nextLine().split(" ");
        String character = line[0];
        String truth = line[1];
        // switch
        if (truth.equals("0")) {
            // she chose B
            if (character.equals("B")) {
                System.out.println("A");
            }
            // she chose A
            else {
                System.out.println("B");
            }
        }
        else {
            System.out.println(character);
        }
        in.nextLine();
    }
}