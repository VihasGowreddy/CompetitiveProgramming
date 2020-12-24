import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    public static void main(String args[]) throws Exception {
        
        InputStreamReader console = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(console);
        StringBuilder output = new StringBuilder();
        String readIn;

        while ((readIn = reader.readLine()) != null && !readIn.equals("0")) {

            String [] boxType1 = reader.readLine().split(" ");
            String [] boxType2 = reader.readLine().split(" ");
            long totalMarbles = Long.parseLong(readIn);
            long costBox1 = Long.parseLong(boxType1[0]);
            long costBox2 = Long.parseLong(boxType2[0]);
            long maxMarblesBox1 = Long.parseLong(boxType1[1]);
            long maxMarblesBox2 = Long.parseLong(boxType2[1]);

            //output.append("Total Marbles: " + totalMarbles + "\nCost Box 1: " + costBox1 + "    Marbles Box 1 Can Hold: " + maxMarblesBox1 + "\nCost Box 2: " + costBox2 + "    Marbles Box 2 Can Hold: " + maxMarblesBox2 + "\n");
            //NumBoxes result = dpHelper(totalMarbles, costBox1, costBox2, maxMarblesBox1, maxMarblesBox2);
            long[] gcd = new long[1];
            NumBoxes gcdResults = euclid(maxMarblesBox1, maxMarblesBox2, gcd);
            if ((double)totalMarbles/gcd[0] != Math.round((double)totalMarbles/gcd[0])) {
                output.append("failed\n");
                continue;
            }
            long box1Solution = gcdResults.numBox1 * totalMarbles/gcd[0];
            long box2Solution = gcdResults.numBox2 * totalMarbles/gcd[0];
            long box1Increment = maxMarblesBox2/gcd[0];
            if (box1Solution > 0)
                box1Increment *= -1;
            long box2Increment = maxMarblesBox1/gcd[0];
            if (box2Solution > 0)
                box2Increment *= -1;

            long multiplyByLower = 0;
            long multiplyByUpper = 0;
            if (box1Solution < box2Solution) {
                multiplyByLower = (long)Math.floor((0-box1Solution)/box1Increment);
                multiplyByUpper = (long)Math.ceil((box2Solution - 0)/(-1 * box2Increment));
            }
            else {
                multiplyByLower = (long)Math.floor((0-box2Solution)/box2Increment);
                multiplyByUpper = (long)Math.ceil((box1Solution-0)/(-1 * box1Increment));
            }
            //output.append("Multiply By Lower: " + multiplyByLower + "\nMultiply By Upper: " + multiplyByUpper + "\n");
            NumBoxes lowerBoxes = new NumBoxes(box1Solution + (box1Increment * multiplyByLower), box2Solution + (box2Increment * multiplyByLower));
            NumBoxes upperBoxes = new NumBoxes(box1Solution + (box1Increment * multiplyByUpper), box2Solution + (box2Increment * multiplyByUpper));

            if (lowerBoxes.numBox1 < 0 || lowerBoxes.numBox2 < 0) {
                lowerBoxes.numBox1 += box1Increment;
                lowerBoxes.numBox2 += box2Increment; 
            }
            if (upperBoxes.numBox1 < 0 || upperBoxes.numBox2 < 0) {
                upperBoxes.numBox1 -= box1Increment;
                upperBoxes.numBox2 -= box2Increment;
            }

            if ((lowerBoxes.numBox1 * costBox1 + lowerBoxes.numBox2 * costBox2) < (upperBoxes.numBox1 * costBox1 + upperBoxes.numBox2 * costBox2)) {
                if (lowerBoxes.numBox1 < 0 || lowerBoxes.numBox2 < 0)
                    output.append("failed\n");
                else
                    output.append(lowerBoxes.numBox1 + " " + lowerBoxes.numBox2 + "\n");
            }
            else {
                if (upperBoxes.numBox1 < 0 || upperBoxes.numBox2 < 0)
                    output.append("failed\n");
                else
                    output.append(upperBoxes.numBox1 + " " + upperBoxes.numBox2 + "\n");
            }

            //output.append("GCD: " + gcd[0] + "\nGCD Results: " + gcdResults.numBox1 + "  " + gcdResults.numBox2 + "\nBox Solution 1: " + box1Solution + "\nBox Solution 2: " + box2Solution + "\nBox Increment 1: " + box1Increment + "\nBox Incrememnt 2: " + box2Increment + "\n");
           /* while (Long.signum(box1Solution) == Long.signum(lowerBox1Solution) || Long.signum(lowerBox1Solution) == 0 || Long.signum(box2Solution) == Long.signum(lowerBox2Solution) || Long.signum(lowerBox2Solution) == 0) {

                if (lowerBox1Solution > -1 && lowerBox2Solution > -1) {

                    if ((costBox1 * lowerBox1Solution + costBox2 * lowerBox2Solution) <= currentCost) {
                        currentCost = costBox1 * lowerBox1Solution + costBox2 * lowerBox2Solution;
                        //output.append(currentCost + "\n");
                        currentBox1 = lowerBox1Solution;
                        currentBox2 = lowerBox2Solution;
                    }
                }

                lowerBox1Solution += box1Increment;
                lowerBox2Solution += box2Increment;
            }
            if (currentCost == Long.MAX_VALUE)
                output.append("failed\n");
            else
                output.append(currentBox1 + " " + currentBox2 + "\n");*/
            //

            /*if (result == null)
                output.append("failed\n");
            else
                output.append(result.numBox1 + " " + result.numBox2 + "\n");*/
        }

        System.out.print(output);
    }
/*
    public static NumBoxes dpHelper(long totalMarbles, long costBox1, long costBox2, long maxMarblesBox1, long maxMarblesBox2) {

        NumBoxes [] dp = new NumBoxes[totalMarbles + 1];
        dp[0] = null;
        dp[maxMarblesBox1] = new NumBoxes(1, 0, costBox1);
        dp[maxMarblesBox2] = new NumBoxes(0, 1, costBox2);

        for (long x = Math.min(maxMarblesBox1, maxMarblesBox2) + 1; x < totalMarbles + 1; x++) {

            long index1 = x-maxMarblesBox1;
            long index2 = x-maxMarblesBox2;
            NumBoxes calc = null;

            if (index1 < 0)
                index1 = 0;
            if (index2 < 0)
                index2 = 0;

            if (dp[index1] == null && dp[index2] == null)
                continue;
            else if (dp[index1] != null && dp[index2] == null) {
                calc = new NumBoxes(dp[index1].numBox1 + 1, dp[index1].numBox2, dp[index1].cost + costBox1);
            }
            else if (dp[index1] == null && dp[index2] != null) {
                calc = new NumBoxes(dp[index2].numBox1, dp[index2].numBox2 + 1, dp[index2].cost + costBox2);
            }
            else {
                if ((dp[index1].cost + costBox1) > (dp[index2].cost + costBox2)) {
                    calc = new NumBoxes(dp[index2].numBox1, dp[index2].numBox2 + 1, dp[index2].cost + costBox2);
                }
                else {
                    calc = new NumBoxes(dp[index1].numBox1 + 1, dp[index1].numBox2, dp[index1].cost + costBox1);
                }
            }

            if (dp[x] != null && dp[x].cost <= calc.cost)
                continue;
            dp[x] = calc;
        }

        return dp[dp.length - 1];*/

    
    
    public static NumBoxes euclid(long maxMarblesBox1, long maxMarblesBox2, long[] gcd) {

        if (maxMarblesBox1 % maxMarblesBox2 == 0)
        {
            gcd[0] = maxMarblesBox2;
            return new NumBoxes(0, 1);
        }
        NumBoxes xy = euclid(maxMarblesBox2, maxMarblesBox1 % maxMarblesBox2, gcd);
        return new NumBoxes(xy.numBox2, xy.numBox1-xy.numBox2*(maxMarblesBox1/maxMarblesBox2));
    }

}

class NumBoxes {

    long numBox1;
    long numBox2;
    //long cost;

    public NumBoxes(long numBox1, long numBox2) {
        this.numBox1 = numBox1;
        this.numBox2 = numBox2;
        //this.cost = cost;
    }

    /*public void setNumBoxes(long numBox1, long numBox2) {
        this.numBox1 = numBox1;
        this.numBox2 = numBox2;
    }
    
    public void setCost(long cost) {
        this.cost = cost;
    }*/
}

//can also use top down solution with memoization using recursion
//less optimal solution is using recursion "tree" that goes through all possible solutions