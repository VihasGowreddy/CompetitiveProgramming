import java.io.*;
import java.util.*;

class Main {

    static StringBuilder index = new StringBuilder();
    public static void main(String[] args) throws Exception {
        

        InputStreamReader console = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(console);
        String inputVal = reader.readLine();
        double val = Double.parseDouble(inputVal);
        if (val > 1)
            val = 1.0;
        double xy = val/2;
        System.out.println(xy*xy);

    }
}