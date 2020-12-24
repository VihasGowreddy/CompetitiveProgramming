import java.io.*;
import java.util.*;

class Main {
    public static StringBuilder output = new StringBuilder();
    public static void main(String[] args) throws Exception{

        List<Integer> primeList = new ArrayList<>();
        primeList.add(3);
        primeList.add(5);
        primeList.add(7);
        primeList.add(11);
        primeList.add(13);
        primeList.add(17);
        primeList.add(19);
        primeList.add(23);
        primeList.add(29);
        primeList.add(31);
        
        //boolean [] nodesAdded = new boolean[n + 1];
        //nodesAdded[0] = true;
        //nodesAdded[1] = true;

        //int largetPossiblePrime = n + n-1;
        //int [] solutionArray = new int[n];
        InputStreamReader console = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(console);
        String readIn;
        int caseNum = 0;
        while ((readIn = reader.readLine()) != null) {
            int n = Integer.parseInt(readIn);
            if (n < 1 || n > 16 || n % 2 != 0)
            {
                System.out.println("Inputted N did not meet specifications");
                return;
            }   
            caseNum++;
            List <Integer> nodes = new ArrayList<>();
            for (int x = 1; x < n; x++)
            {
                nodes.add(x+1);
            }
            List<Integer> solution = new ArrayList<>();
            solution.add(1);
            output.append("\n");
            if (caseNum == 1)
                output.setLength(0);
            output.append("Case " + caseNum + ":\n");
            backtrackingHelper(primeList, 1, nodes, solution);
            System.out.print(output);
            output.setLength(0);
        }
        
    }

    public static void backtrackingHelper(List<Integer> primeList, int n, List <Integer> nodes, List<Integer> solution) {

        if (nodes.size() == 1)
        {
            if (primeList.contains(nodes.get(0) + 1) && primeList.contains(nodes.get(0) + n))
            {
                solution.add(nodes.get(0));
                for (int x = 0; x < solution.size(); x++)
                {
                    output.append((solution.get(x)));
                    if (x != solution.size() - 1)
                        output.append(" ");
                }
                output.append("\n");
                solution.remove(solution.size() - 1);
            }
        }
        
        for (int i = 0; i < nodes.size(); i++)
        {
            if (primeList.contains(nodes.get(i) + n))
            {
                solution.add(nodes.get(i));
                int index = i;
                int temp = nodes.remove(i);

                backtrackingHelper(primeList, temp, nodes, solution);

                solution.remove(solution.size() - 1);
                nodes.add(index, temp);
            }
        }
    }
}