import java.io.IOException;

class Solution {
    class Pos {
        int x;
        int y;
        int nx;
        int ny;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
            this.nx = x;
            this.ny = y;
        }

        public Pos(Pos pos) {
            this.x = pos.nx;
            this.y = pos.ny;
            this.nx = pos.nx;
            this.ny = pos.ny;
        }
    }

    boolean isFrame(int[][] maze, Pos pos) {
        int n = maze.length;
        int m = maze[0].length;
        return 0 <= pos.nx && pos.nx < m && 0 <= pos.ny && pos.ny < n;
    }

    boolean isAvailable(int[][] maze, Pos red, Pos blue) {
        /**
         * 1. red와 blue가 같은 때 X
         * 2. red와 blue의 위치가 서로 바뀌었을 때 X
         * 3. red나 blue의 위치가 벽일 때 X
         */
        if (red.nx == blue.nx && red.ny == blue.ny) return false;
        if (red.nx == blue.x && red.ny == blue.y
                && blue.nx == red.x && blue.ny == red.y) return false;
        if (maze[red.ny][red.nx] == 5 || maze[blue.ny][blue.nx] == 5) return false;

        return true;
    }

    int backtracking(int[][] maze, Pos red, Pos blue, boolean[][][] visited, int depth) {
        int result = Integer.MAX_VALUE;
        boolean redArrival = false;
        boolean blueArrival = false;

//        System.out.printf("red(%d, %d) blue(%d, %d) %d\n", red.y, red.x, blue.y, blue.x, depth);

        if (maze[red.y][red.x] == 3)
            redArrival = true;
        if (maze[blue.y][blue.x] == 4)
            blueArrival = true;

        if(redArrival && blueArrival) return depth;

        for (int i = 0; i < 4; i++) {
            red.nx = !redArrival? red.x + dx[i]: red.x;
            red.ny = !redArrival? red.y + dy[i]: red.y;

            if (!isFrame(maze, red)) continue;
            if (!redArrival && visited[red.ny][red.nx][0] == true) continue;

            for (int j = 0; j < 4; j++) {
                blue.nx = !blueArrival? blue.x + dx[j]: blue.x;
                blue.ny = !blueArrival? blue.y + dy[j]: blue.y;

                if (!isFrame(maze, blue)) continue;
                if (!blueArrival && visited[blue.ny][blue.nx][1] == true) continue;

                if (!isAvailable(maze, red, blue)) continue;
                visited[red.ny][red.nx][0] = true;
                visited[blue.ny][blue.nx][1] = true;
                result = Math.min(result, backtracking(maze, new Pos(red), new Pos(blue), visited, depth + 1));
                visited[red.ny][red.nx][0] = false;
                visited[blue.ny][blue.nx][1] = false;
            }
        }

        return result;
    }

    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, -1, 0, 1};

    public int solution(int[][] maze) {
        Pos red = null;
        Pos blue = null;
        int n = maze.length;
        int m = maze[0].length;

        for (int y = 0; y < n; y++) {
            for (int x = 0; x < m; x++) {
                if (maze[y][x] == 1) {
                    red = new Pos(x, y);
                } else if (maze[y][x] == 2) {
                    blue = new Pos(x, y);
                }
            }
        }

        boolean[][][] visited = new boolean[n][m][2];
        visited[red.y][red.x][0] = true;
        visited[blue.y][blue.x][1] = true;
        int result = backtracking(maze, red, blue, visited, 0);
        if (result == Integer.MAX_VALUE) {
            return 0;
        }
        return result;
    }
}