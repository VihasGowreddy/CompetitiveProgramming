import java.io.*;
import java.util.*;

//import sun.security.util.Length;

class Main {

    static StringBuilder index = new StringBuilder();
    public static void main(String[] args) throws Exception {
        

        InputStreamReader console = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(console);
        int numTestCases = Integer.parseInt(reader.readLine());
        
        
        for (int x = 0; x < numTestCases; x++) {

            String [] info = reader.readLine().split(" ");
            int lengthPole = Integer.parseInt(info[0]);
            int numAnts = Integer.parseInt(info[1]);
            int largestDiff = 0;
            int shortestPath = 0;
            while (numAnts > 0) {
                String[] antDistances = reader.readLine().split(" ");
                numAnts -= antDistances.length;
                for (int y = 0; y < antDistances.length; y++) {
                    int temp = Integer.parseInt(antDistances[y]);
                    int currentDiff = Math.max(lengthPole - temp, temp);
                    int shortestDiff = Math.min(lengthPole - temp, temp);
                    if (currentDiff > largestDiff)
                        largestDiff = currentDiff;
                    if (shortestDiff > shortestPath)
                        shortestPath = shortestDiff;
                }
            }
            System.out.println(shortestPath + " " + largestDiff);
        }
    }
}