import java.io.*;
import java.util.*;

public class Main{
    public static int calcLineCount(long[] arr, long length) {
        int count = 0;

        for (long num : arr) {
            count += (int) (num / length);
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int k = Integer.parseInt(line[0]);
        int n = Integer.parseInt(line[1]);

        long[] arr = new long[k];
        for (int i = 0; i < k; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        long min = 1, max = Integer.MAX_VALUE;
        long length = 0;
        long count;
        long answer = 0;

        while (min <= max) {
            length = (min + max) / 2;
            count = calcLineCount(arr, length);
            if (count < n) {
                max = length - 1;
            } else {
                answer = length;
                min = length + 1;
            }
        }

        System.out.println(answer);
    }
}