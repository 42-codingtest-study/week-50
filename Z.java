import java.io.*;
import java.util.Arrays;

public class Main {
    static class Pos {
        int x;
        int y;
        int n;
        int v = 0;

        public Pos(int x, int y, int n, int v) {
            this.x = x;
            this.y = y;
            this.n = n;
            this.v = v;
        }
    }

    public static int dfs(Pos start, Pos find_pos) {
        int size = start.n / 2;
        int[] arr = new int[4];

        arr[0] = start.v;
        arr[1] = start.v + size * size;
        arr[2] = start.v + size * size * 2;
        arr[3] = start.v + size * size * 3;

        if (find_pos.x == start.x && find_pos.y == start.y) {
            return arr[0];
        }
        if (find_pos.x == start.x + size && find_pos.y == start.y) {
            return arr[1];
        }
        if (find_pos.x == start.x && find_pos.y == start.y + size) {
            return arr[2];
        }
        if (find_pos.x == start.x + size && find_pos.y == start.y + size) {
            return arr[3];
        }

        if (start.x <= find_pos.x && find_pos.x < start.x + size
                && start.y <= find_pos.y && find_pos.y < start.y + size){
            return dfs(new Pos(start.x, start.y, size, arr[0]), find_pos);
        } else if (start.x + size <= find_pos.x && find_pos.x < start.x + start.n
                && start.y <= find_pos.y && find_pos.y < start.y + size) {
            start.v = arr[1];
            return dfs(new Pos(start.x + size, start.y, size, arr[1]), find_pos);
        } else if (start.x <= find_pos.x && find_pos.x < start.x + size
                && start.y + size <= find_pos.y && find_pos.y < start.y + start.n) {
            start.v = arr[2];
            return dfs(new Pos(start.x, start.y + size, size, arr[2]), find_pos);
        } else {
            start.v = arr[3];
            return dfs(new Pos(start.x + size, start.y + size, size, arr[3]), find_pos);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int n = (int) Math.pow(2, Integer.parseInt(line[0]));
        int r = Integer.parseInt(line[1]);
        int c = Integer.parseInt(line[2]);

        int result = dfs(new Pos(0, 0, n, 0), new Pos(c, r, 0, 0));
        System.out.println(result);
    }
}