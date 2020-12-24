import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class NthPrime {
    public static void main(String args[]) throws Exception {

            //POSSIBLY OPTIMIZE BY SAVING SET OF PRIMES FOR NEXT VALUES TO GO THROUGH

        InputStreamReader console = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(console);
        StringBuffer output = new StringBuffer();
        List<Integer> primes = new ArrayList<>(); // adds all primes encountered //MIGHT CHANGE TO ARRAY LIST FOR OPTIMIZE // CHANGE AGAIN TO LINKED HASHMAP FOR OPTIMIZE
        List<Integer> inputs = new ArrayList<>();
        
        primes.add(2);
        primes.add(3);
        primes.add(5);
        primes.add(7);

        String readIn = reader.readLine();
        while (readIn != null && !readIn.equals("") && !readIn.equals("\n")) {
            inputs.add(Integer.parseInt(readIn));
            readIn = reader.readLine();
        }
        
        for (int x = 0; x < inputs.size(); x++) {

            int numToCheck;
            int input = inputs.get(x);

            if (input > primes.size()) {
                numToCheck = primes.get(primes.size() - 1) + 2;
                input -= primes.size();
            }
            else {
                output.append(primes.get(input - 1) + "\n");
                continue;
            }
            
                input -= 4;
                while (input > 0) {
                    boolean value = hasBaseFactors(numToCheck, primes);
                    if (value)
                        input--;
                    numToCheck += 2; //can't be even
                }
                output.append(numToCheck-2+"\n"); // accounts for 2 added in last iteration of while loop
        }

        System.out.print(output.toString());
    }

    public static boolean hasBaseFactors(int numToCheck, List<Integer> primes) {

        int highestFactor = (int)Math.ceil(Math.sqrt(numToCheck)); // highest factor without repeating sets of factors is sqrt of number
        for (int x = 0; x < primes.size(); x++) {
            if (primes.get(x) > highestFactor)
                break;
            if (numToCheck % primes.get(x) == 0)
                return false;
        }
        primes.add(numToCheck);
        return true;
    }
}