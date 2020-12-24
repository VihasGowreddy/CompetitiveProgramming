import java.util.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        BigInteger low = BigInteger.ONE; // low end of range
        BigInteger high = BigInteger.ONE; // high end of range
        BigInteger zero = BigInteger.ZERO; // have to initialize these
        int answer;

        while(low.compareTo(zero)>0 || high.compareTo(zero)>0) {

            low = in.nextBigInteger();
            high = in.nextBigInteger();
            answer = 0;
            if(low.compareTo(zero)>0 || high.compareTo(zero)>0) {

                BigInteger last = BigInteger.ONE;
                BigInteger penult = BigInteger.ONE;
                BigInteger current = BigInteger.ONE;
                while(current.compareTo(low)<0) {
                    current = last.add(penult);
                    penult = last;
                    last = current;
                }
                if(current.compareTo(high)<=0) {
                    answer = 1;
                    current = last.add(penult);
                    penult = last;
                    last = current;
                    while(current.compareTo(high)<=0) {
                        current = last.add(penult);
                        penult = last;
                        last = current;
                        answer++;
                    }
                    // add a loop to continue computing
                    // Fibs until current equals or
                    // exceeds high, incrementing answer
                    // as you go.
                }
                System.out.println(answer);
            }
            
        }
    }
}