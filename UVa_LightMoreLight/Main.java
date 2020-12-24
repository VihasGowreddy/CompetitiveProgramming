import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    public static void main(String args[]) throws Exception {
        
        InputStreamReader console = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(console);
        String readIn;
        StringBuilder output = new StringBuilder();
        while ((readIn = reader.readLine()) != null && !readIn.equals("0")) {
            
            long numBulbs = Long.parseLong(readIn);
            /*boolean on = true;
            long numNo= 1;
            long i = 1;
            while (i <= numBulbs) {
                if (!on) {
                    i += 1;
                    on = true;
                }
                if(on) {
                    if (i == numBulbs) {
                        output.append("yes\n");
                        break;
                    }
                    else {
                        if ((i + 2*numNo) >= numBulbs) {
                            output.append("no\n");
                            break;
                        }
                        i += (2*numNo);
                        numNo+=1;
                        on = false;
                        continue;
                    }
                }

            }*/

            // I'm dumb easier way just to check if something is a perfect square
            if (Math.sqrt(numBulbs) == Math.round(Math.sqrt(numBulbs)))
                output.append("yes\n");
            else
                output.append("no\n");
        }
        System.out.print(output);

    }
}