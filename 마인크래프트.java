import java.io.*;

public class Main{

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);
        int b = Integer.parseInt(line[2]);

        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            line = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(line[j]);
            }
        }

        int resultTime = Integer.MAX_VALUE;
        int resultHeight = 0;
        
        for (int height = 0; height <= 256; height++) {
            int f1 = 0;
            int f2 = 0;
            int b_copy = b;

            for (int[] row : map) {
                for (int value : row) {
                    int tmp = Math.abs(value - height);
                    if (height < value) {
                        f1 += tmp;
                        b_copy += tmp;
                    } else if (value < height) {
                        f2 += tmp;
                    }
                }
            }
            if (b_copy < f2) continue;

            int time = f1 * 2 + f2;
            if (time <= resultTime) {
                resultTime = time;
                resultHeight = height;
            }
        }

        System.out.printf("%d %d\n", resultTime, resultHeight);
    }
}