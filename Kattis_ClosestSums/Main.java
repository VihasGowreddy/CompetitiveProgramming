import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;

public class Main
{
    public static void main (String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String input;
        int cases = 0;
        while ((input = in.readLine()) != null)
        {
            int numNums = Integer.parseInt(input);
            int[] nums = new int[numNums];
            for (int i = 0; i < numNums; i++)
            {
                nums[i] = Integer.parseInt(in.readLine());
            }
//            System.out.println(Arrays.toString(nums));
            Arrays.sort(nums);
            int numQueries = Integer.parseInt(in.readLine());
            System.out.printf("Case %d:\n", cases + 1);
            for (int i = 0; i < numQueries; i++)
            {
                int query = Integer.parseInt(in.readLine());
                int result = search(nums, query);
                System.out.printf("Closest Sum to %d is %d.\n", query, result);
            }
            cases++;
        }
    }
    public static int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        int answer = 0;
        int minDistance = Integer.MAX_VALUE;
        while (low < high) {
            int mid = (low + high) / 2;
            int sum = nums[low] + nums[high];
            int diff = Math.abs(target - sum);
            if (diff < minDistance) {
                minDistance = diff;
                answer = sum;
            }
            if (sum > target) {
                high--;
            }
            else if (sum < target) {
                low++;
            }
            else {
                return sum;
            }
        }
        return answer;
    }
}