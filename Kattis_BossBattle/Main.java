import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String args[]) throws Exception {

        InputStreamReader console = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(console);
        StringBuilder output = new StringBuilder();
        int pillars = Integer.parseInt(reader.readLine());

        if (pillars < 3)
            System.out.println(1);
        else
            System.out.println(pillars-2);
    }
}
        