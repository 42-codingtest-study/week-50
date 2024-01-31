# 꽃을 피울 때
# 서로 다른 배양액끼리 만났을 때
# 호수 처리
# 황토색 칸, 하얀색 칸

from itertools import combinations
from collections import deque
N, M, G, R = map(int, input().split())
arr = [[0] *M for _ in range(N)]
dx = [-1, 1, 0, 0]
dy = [0,0,-1,1]

grounds = []
for i in range(N):
    line = list(map(int,input().split()))
    for j in range(M):
        arr[i][j] = line[j]
        if line[j] == 2:
            grounds.append((i,j))


def bfs(gl, rl, garden):
    visit = [[0] * M for _ in range(N)]
    q = deque()
    cnt = 0
    for g in gl:
        q.append((g[0], g[1], 'g'))
        visit[g[0]][g[1]] = -1
    for r in rl:
        q.append((r[0], r[1], 'r'))
        visit[r[0]][r[1]] = 1

    while q:
        front = q.popleft()
        x = front[0]
        y = front[1]
        color = front[2]
        for i in range(4):
            xx = x + dx[i]
            yy = y + dy[i]
            if 0 <= xx < N and 0 <= yy < M and (garden[xx][yy] == 1 or garden[xx][yy] == 2) and visit[xx][yy] != 'F' and visit[xx][yy] == 0 and visit[xx][yy] < 999999:
                if visit[x][y] < 0:
                    visit[xx][yy] = visit[x][y] - 1
                    q.append((xx, yy, color))
                elif 0<visit[x][y]<999999:
                    visit[xx][yy] = visit[x][y] + 1
                    q.append((xx, yy, color))

            if  0 <= xx < N and 0 <= yy < M and garden[xx][yy] != 0 and visit[xx][yy] + visit[x][y] + 1 == 0:
                visit[xx][yy] = 999999
                cnt += 1
    return cnt

gs = set(grounds)
res = 0
for green in combinations(grounds, G):
    groundSetMinusGreen = list(gs - set(green))
    green_list = list(green)
    for red in combinations(groundSetMinusGreen, R):
        red_list = list(red)
        result = bfs(green_list, red_list, arr)
        if res < result:
            res = result
print(res)
