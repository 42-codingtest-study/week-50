import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }

        Stack<Integer> stack = new Stack<>();
        List<Character> result = new ArrayList<>();
        int nums_idx = 0;
        for (int i = 1; i <= n; i++) {
            stack.push(i);
            result.add('+');

            while (!stack.empty() && stack.peek() == nums[nums_idx]) {
                result.add('-');
                nums[nums_idx++] = stack.pop();
            }
        }

        if (nums_idx == n) {
            for (char c : result) {
                System.out.println(c);
            }
        } else {
            System.out.println("NO");
        }

    }
}