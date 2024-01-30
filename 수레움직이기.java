import java.util.*;

class 수레움직이기 {
    private static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static final int MAX = 999999;
    private int[][] map;
    private boolean redEnd, blueEnd;
    private int[] dx = {-1, 1, 0, 0};
    private int[] dy = {0, 0, -1, 1};
    private boolean[][][] visited;

    public int solution(int[][] maze) {
        map = maze;
        visited = new boolean[maze.length][maze[0].length][2];

        Point cntRed = null, cntBlue = null;

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[i][j] == 1) cntRed = new Point(i, j);
                else if (maze[i][j] == 2) cntBlue = new Point(i, j);
            }
        }

        visited[cntRed.x][cntRed.y][0] = true;
        visited[cntBlue.x][cntBlue.y][1] = true;

        int answer = backtracking(cntRed, cntBlue, 0);
        return (answer == MAX) ? 0 : answer;
    }

    private Point getNext(Point p, int dir) {
        return new Point(p.x + dx[dir], p.y + dy[dir]);
    }

    private boolean isPossible(Point red, Point blue, int dirRed, int dirBlue) {
        if (map[red.x][red.y] == 5 || map[blue.x][blue.y] == 5) return false;
        if (red.x == blue.x && red.y == blue.y) return false;
        return true;
    }

    private int backtracking(Point red, Point blue, int result) {
        if (redEnd && blueEnd) return result;

        int answer = MAX;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Point nRed = getNext(red, i);
                Point nBlue = getNext(blue, j);

                if (!isPossible(nRed, nBlue, i, j)) continue;

                boolean movedRed = !nRed.equals(red);
                boolean movedBlue = !nBlue.equals(blue);

                if (movedRed) redEnd = map[nRed.x][nRed.y] == 3;
                if (movedBlue) blueEnd = map[nBlue.x][nBlue.y] == 4;

                if (!visited[nRed.x][nRed.y][0] || !visited[nBlue.x][nBlue.y][1]) {
                    visited[nRed.x][nRed.y][0] = visited[nBlue.x][nBlue.y][1] = true;
                    answer = Math.min(answer, backtracking(nRed, nBlue, result + 1));
                    visited[nRed.x][nRed.y][0] = visited[nBlue.x][nBlue.y][1] = false;
                }

                redEnd = blueEnd = false;
            }
        }

        return answer;
    }
}
