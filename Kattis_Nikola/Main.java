import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String args[]) throws Exception {

        InputStreamReader console = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(console);
        //StringBuilder output = new StringBuilder();
        int numSquares = Integer.parseInt(reader.readLine());
        int [] entryFee = new int[numSquares];
        Map<String, Integer> problems = new HashMap<String, Integer>();
        //int [][] dp = new int[numSquares][numSquares - 1];
        //int jump = 2;

        for (int x = 0; x < numSquares; x++) {
            entryFee[x] = Integer.parseInt(reader.readLine());
            //dp[x] = Integer.MAX_VALUE;
        }
        Comparator<int[]> compareVals = (v1, v2) -> {
            //int squareDiff = v1[0] - v2[0];
            //if (squareDiff == 0) {
            /*    if (v1[2] > v2[2])
                    return 1;
                else
                    return -1;*/
            //}
            //else
            //    return squareDiff * -1;
            return v1[2] - v2[2];
        };
        PriorityQueue<int[]> subProblems = new PriorityQueue<int[]>(compareVals);

        //subProblems.add(new int[]{0, 0, 0}); // initial square
        subProblems.add(new int[]{2, 1, entryFee[1]}); // going to second square
        problems.put("2 1", entryFee[1]);

        while (subProblems.peek()[0] != numSquares) { // while least cost value isn't a completed solution
            int [] temp = subProblems.poll();
            int jump = temp[1];
            int square = temp[0];

            if (square + jump + 1 <= numSquares) {
                String squareJump = (square + jump + 1) + " " + (jump + 1);
                int [] toAdd = new int[]{square + jump + 1, jump + 1, temp[2] + entryFee[square + jump + 1 - 1]};
                //if (!problems.contains(toAdd))
                if (!problems.containsKey(squareJump) || (problems.get(squareJump) > (temp[2] + entryFee[square + jump + 1 - 1]))) {
                    subProblems.add(toAdd);
                    problems.put(squareJump, temp[2] + entryFee[square + jump + 1 - 1]);
                }
            }
            if (square - jump > 0) {
                String squareJump = (square - jump) + " " + jump;
                int[] toAdd = new int[]{square - jump, jump, temp[2] + entryFee[square - jump - 1]};
                if (!problems.containsKey(squareJump) || (problems.get(squareJump) > (temp[2] + entryFee[square - jump - 1]))) {
                    subProblems.add(toAdd);
                    problems.put(squareJump, temp[2] + entryFee[square - jump - 1]);
                }
            }
        }

        System.out.println(subProblems.peek()[2]);
        // base case
        /*dp[0] = 0; // no cost for initialize
        dp[1] = entryFee[1]; // cost for going to 2 square always dp[1] bc negative cost doesn't exist
        //GOING BACKWARDS DOESN'T MAKE A PREVIOUSLY VISITED NODE LESS EXPENSIVE // THIS IS WRONG
        //MAKE NESTED LOOP AND GO BACK TO BEGINNING
        for (int x = 1; x < numSquares; x++) {
            int nextJump = x + jump;
            int plusOneJump = x + 1;
            if (dp[nextJump] > dp[x] + entryFee[nextJump])
                dp[nextJump] = dp[x] + entryFee[nextJump];
            if (dp[plusOneJump] > dp[x] + entryFee[x - (jump - 1)] + entryFee[plusOneJump]])
                dp[plusOneJump] = dp[x] + entryFee[x - (jump - 1)] + entryFee[plusOneJump];
            jump++;
        }
    }

    public static void recursivePossibilities(int spaces, int[][] dp, int[] entryFee) {

        if (spaces == 0)
            dp[0] = 0;
        if (spaces == 1)
            dp[1] = entryFee[1];
    }*/
    }
}