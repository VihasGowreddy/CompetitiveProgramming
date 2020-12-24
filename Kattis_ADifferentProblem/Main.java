import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String args[]) throws Exception {

        InputStreamReader console = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(console);
        StringBuilder output = new StringBuilder();
        String readIn;

        while ((readIn = reader.readLine()) != null && !readIn.equals("")) {

            String [] input = readIn.split(" ");
            long input1 = Long.parseLong(input[0]);
            long input2 = Long.parseLong(input[1]);
            output.append((long)Math.abs(input1 - input2) + "\n");
        }
        System.out.print(output);
    }
    
}