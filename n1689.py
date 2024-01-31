n, m = map(int, input().split())
height = [[0] * m for _ in range(n)]
arr = [input().strip() for _ in range(n)]
ans = 0
stack = []

def findheight():
    for j in range(m):
        if arr[0][j] == '0':
            height[0][j] = 1

    for j in range(m):
        for i in range(1, n):
            if arr[i][j] == '0':
                height[i][j] = height[i - 1][j] + 1

def findMaxheight(start):
    global ans
    pos = n
    top = -1

    for i in range(m):
        a = height[start][i]

        if top == -1:
            top += 1
            stack[top] = (i, a)
        else:
            if stack[top][1] > a:
                while top >= 0 and stack[top][1] > a:
                    pos = stack[top][0]
                    t = (i - stack[top][0]) * stack[top][1]
                    ans = max(ans, t)
                    top -= 1

                top += 1
                stack[top] = (pos, a)
            elif stack[top][1] == a:
                continue
            else:
                top += 1
                stack[top] = (i, a)

    for i in range(top + 1):
        t = (m - stack[i][0]) * stack[i][1]
        ans = max(ans, t)

findheight()
for i in range(n):
    findMaxheight(i)

print(ans)
