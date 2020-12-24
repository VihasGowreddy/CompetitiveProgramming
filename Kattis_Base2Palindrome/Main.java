import java.io.*;
import java.util.*;

class Main {

    static StringBuilder index = new StringBuilder();
    public static void main(String[] args) throws Exception {
        

        InputStreamReader console = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(console);
        int num = Integer.parseInt(reader.readLine());
        
        //int numFound = 0;
        //int checking = 1;
        if (num == 1)
            System.out.println(1);
        else
            System.out.println(createTree(num));
    }

    public static int createTree(int num) {
        LinkedList<String> palindromes = new LinkedList<String>();
        palindromes.add("11");
        int found = 1;

        while (!palindromes.isEmpty()) {
            
            StringBuilder temp = new StringBuilder();
            temp.append(palindromes.remove());
            found++;
            int index;
            //System.out.println(temp.toString());
            //System.out.println(found);
            if (found == num) {
                return Integer.parseInt(temp.toString(), 2);
            }
            if (temp.length() % 2 == 1) {
                //System.out.println("ODD");
                index = temp.length() / 2;
                char doubleUp = temp.charAt(index);
                char [] toAdd = new char[1];
                toAdd[0] = doubleUp;
                temp.insert(index, toAdd, 0, 1);
                //System.out.println(temp.toString());
                palindromes.add(temp.toString());
            }
            else {
                //System.out.println("EVEN");
                index = temp.length() / 2;
                char [] toAdd = new char[1];
                toAdd[0] = '0';
                temp.insert(index, toAdd, 0, 1);
                //System.out.println(temp.toString());
                palindromes.add(temp.toString());
                temp.delete(index, index + 1);
                toAdd[0] = '1';
                temp.insert(index, toAdd, 0, 1);
                //System.out.println(temp.toString());
                palindromes.add(temp.toString());
            }
            //for(String s : palindromes) { 
            //    System.out.println(s); 
            //  }
        }
        return 0;
    }

    public static int BinaryToDecimal(int binaryNumber){
 
        int decimal = 0;
        int p = 0;
        while(true){
          if(binaryNumber == 0){
            break;
          } else {
              int temp = binaryNumber%10;
              decimal += temp*Math.pow(2, p);
              binaryNumber = binaryNumber/10;
              p++;
           }
        }
        return decimal;
      }
}