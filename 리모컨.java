import java.io.*;
import java.util.*;

public class Main {
    static int calcDistance(int a, int b) {
        return Math.abs(a - b);
    }

    static boolean is_possible(List<Character> btn, int n) {
        String numbers = String.valueOf(n);
        for (int i = 0; i < numbers.length(); i++) {
            if (!btn.contains(numbers.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        int[] broken_btn = new int[0];
        if (0 < m) {
            broken_btn = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
        }

        List<Character> btn = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int num = i;
            if (Arrays.stream(broken_btn).noneMatch(x -> x == num)) {
                btn.add((char) (num + '0'));
            }
        }

        if (calcDistance(100, n) == 0) {
            System.out.println(0);
            return;
        }

        int result = calcDistance(100, n);
        for (int i = 0; i <= 999999; i++) {
            if (is_possible(btn, i)) {
                result = Math.min(result, calcDistance(i, n) + String.valueOf(i).length());
            }
        }
        System.out.println(result);
    }
}