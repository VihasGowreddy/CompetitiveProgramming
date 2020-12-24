import java.io.*;
import java.util.*;

class Main {

    public static Map<String, Integer> totalPossibilities = new HashMap<String, Integer>();
    public static void main(String args[]) throws Exception {
        
        InputStreamReader console = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(console);
        int numProblems = Integer.parseInt(reader.readLine());
        StringBuilder output = new StringBuilder();
        
        reverse("000000000", 0, 0);
        //Iterator iter = totalPossibilities.keySet().iterator();
        //int len = 0;
        //while (iter.hasNext()) {
        //    System.out.println(iter.next());
        //    len++;
        //}
        //System.out.println(len);
        for (int x = 0; x < numProblems; x++) {
            String line1 = reader.readLine();
            StringBuilder toSearch = new StringBuilder();
            if (line1.charAt(0) == '*')
                toSearch.append("1");
            else
                toSearch.append("0");
            if (line1.charAt(1) == '*')
                toSearch.append("1");
            else
                toSearch.append("0");
            if (line1.charAt(2) == '*')
                toSearch.append("1");
            else
                toSearch.append("0");

            String line2 = reader.readLine();
            if (line2.charAt(0) == '*')
                toSearch.append("1");
            else
                toSearch.append("0");
            if (line2.charAt(1) == '*')
                toSearch.append("1");
            else
                toSearch.append("0");
            if (line2.charAt(2) == '*')
                toSearch.append("1");
            else
                toSearch.append("0");
            
            String line3 = reader.readLine();
            if (line3.charAt(0) == '*')
                toSearch.append("1");
            else
                toSearch.append("0");
            if (line3.charAt(1) == '*')
                toSearch.append("1"); 
            else
                toSearch.append("0");
            if (line3.charAt(2) == '*')
                toSearch.append("1");
            else
                toSearch.append("0");
            //System.out.println(toSearch);
            output.append(totalPossibilities.get(toSearch.toString()) + "\n");
        }
        System.out.print(output);
    }

    public static void reverse(String currentValue, int currentSquare, int numClicks) {

        if (currentSquare > 8) {

            if (totalPossibilities.containsKey(currentValue)) {

                if(totalPossibilities.get(currentValue) > numClicks) {
                    totalPossibilities.put(currentValue, numClicks);
                }
            }
            else
                totalPossibilities.put(currentValue, numClicks);
        }
        else {
            //totalPossibilities.add(currentValue);
            reverse(currentValue, currentSquare + 1, numClicks);
            //if (currentValue.equals("100110100"))
            //    System.out.println(numClicks);
            //int place;
            StringBuilder temp = new StringBuilder();
            temp.append(currentValue);

            if (temp.charAt(currentSquare) == '0')
                temp.setCharAt(currentSquare, '1');
            else
                temp.setCharAt(currentSquare, '0');

            if (currentSquare - 3 >= 0) {
                if (temp.charAt(currentSquare - 3) == '0')
                    temp.setCharAt(currentSquare - 3, '1');
                else
                    temp.setCharAt(currentSquare - 3, '0');
                //int val = (int)Math.pow(10, currentSquare - 3);
                //int atPlace = (currentValue / val) % 10;
                //if (atPlace == 1)
                //    currentValue -= val; 
                //else
                //    currentValue += val;
            }
            if (currentSquare != 0 && currentSquare != 3 && currentSquare != 6 && currentSquare - 1 >= 0) {
                //currentValue = (currentValue + Math.pow(10, currentSquare - 1)) % 2;
                if (temp.charAt(currentSquare - 1) == '0')
                    temp.setCharAt(currentSquare - 1, '1');
                else
                    temp.setCharAt(currentSquare - 1, '0');
            }
            if (currentSquare != 2 && currentSquare != 5 && currentSquare != 8 && currentSquare + 1 < 9) {
                //currentValue = (currentValue + Math.pow(10, currentSquare + 1)) % 2;
                //System.out.println(currentSquare + 1);
                if (temp.charAt(currentSquare + 1) == '0')
                    temp.setCharAt(currentSquare + 1, '1');
                else
                    temp.setCharAt(currentSquare + 1, '0');
            }   
            if (currentSquare + 3 < 9) {
                //currentValue = (currentValue + Math.pow(10, currentSquare + 3)) % 2;
                if (temp.charAt(currentSquare + 3) == '0')
                    temp.setCharAt(currentSquare + 3, '1');
                else
                    temp.setCharAt(currentSquare + 3, '0');
            }
            //if (temp.toString().equals("100110100"))
            //    System.out.println(currentValue + currentSquare);
            //System.out.println(temp);
            reverse(temp.toString(), currentSquare + 1, numClicks + 1);
        }
    }
}