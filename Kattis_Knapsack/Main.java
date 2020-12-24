import java.io.*;
import java.util.*;

class Main {

    static StringBuilder index = new StringBuilder();
    public static void main(String[] args) throws Exception {
        

        InputStreamReader console = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(console);
        String inputVal;
        
        
        while ((inputVal = reader.readLine()) != null) {
            StringBuilder output = new StringBuilder();
            index = new StringBuilder();
            String[] initVals = inputVal.split(" ");
            int capacity = Integer.parseInt(initVals[0]);
            int size = Integer.parseInt(initVals[1]);
            //KnapsackObj[] objs = new KnapsackObj[size];
            int [][] finalArr = new int[size + 1][capacity + 1];
            KnapsackObj[] objs = new KnapsackObj[size];
            
            for (int x = 0; x <= size; x++) {

                KnapsackObj obj = new KnapsackObj(0, 0);
                if (x != 0) {
                    String[] temp = reader.readLine().split(" ");
                    obj = new KnapsackObj(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
                    objs[x - 1] = obj;
                }
                //objs[x] = new KnapsackObj(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
                for (int y = 0; y <= capacity; y++) {
                    if (x == 0 || y == 0)
                        finalArr[x][y] = 0; // no weight with 0 capacity or 0 objs used
                    else if (y < obj.weight) { // accounts for 0s added
                        finalArr[x][y] = finalArr[x - 1][y];
                    }
                    else {
                        finalArr[x][y] = Math.max(obj.val + finalArr[x - 1][y - obj.weight], finalArr[x - 1][y]);
                    }
                }
            }
            List<Integer> indices = new ArrayList<Integer>();
            recurse(finalArr, size, capacity, indices, objs);
            output.append(indices.size() + "\n" + index.toString() + "\n");
            //for (int x = 0; x < indices.size(); x++) {
            //    System.out.print(indices.get(x) + " ");
           // }
            System.out.print(output);
            //System.out.print(finalArr[size][capacity].count + "\n" + finalArr[size][capacity].indices.toString() + "\n");
        }

    }

    public static void recurse(int[][] finalArr, int size, int capacity, List<Integer> indices, KnapsackObj[] objs) {

        if (finalArr[size][capacity] == 0) { // Finished tracing back to find all indices
            return;
        }
        if (finalArr[size - 1][capacity] == finalArr[size][capacity]) { // in case where both are same (no added indices)
            recurse(finalArr, size - 1, capacity, indices, objs);
        }
        else { // in case where new weight is added to knapsack
            recurse(finalArr, size - 1, capacity - objs[size - 1].weight, indices, objs);
            indices.add(size - 1);
            index.append(size - 1 + " ");
        }
    }

}

class KnapsackObj {

    int weight;
    int val;

    public KnapsackObj(int val, int weight) {
        this.weight = weight;
        this.val = val;
    }
}

/*class FinalObj {

    int val;
    int count;
    StringBuilder indices = new StringBuilder();

    public FinalObj(int val, int count, String indices) {
        this.val = val;
        this.count = count;
        this.indices.append(indices);
    }
    public void appendToIndices(String temp) {
        indices.append(temp);
    }
}*/