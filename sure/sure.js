function solution(maze) {
    let answer = [];
  
    const n = maze.length;
    const m = maze[0].length;
  
    const dx = [1, -1, 0, 0];
    const dy = [0, 0, 1, -1];
    let bs;
    let rs;
  
    for (let i = 0; i < n; i++) {
      for (let j = 0; j < m; j++) {
        switch(maze[i][j]) {
          case 2:
            bs = [i, j];
            break;
          case 1:
            rs = [i, j];
            break;
        }
      }
    }
  
    const visited = Array.from({ length: 2 }, () => 
      Array.from({ length: n }, () => 
        Array.from({ length: m}, () => 0)
      )
    );
    
    visited[0][bs[0]][bs[1]] = 1;
    visited[1][rs[0]][rs[1]] = 1;
  
    function check(a, b, ball) {
      return (0 <= a && a < n && 0 <= b && b < m) && visited[ball][a][b] === 0
    }
  
    function dfs(x, y, depth) {
      let [bx, by] = x;
      let [rx, ry] = y;
      if (maze[bx][by] === 4 && maze[rx][ry] === 3) {
        answer.push(depth)
        return
      } else if (maze[bx][by] !== 4 && maze[rx][ry] !== 3) {
        for (let i = 0; i < 4; i++) {
          let nbx = bx + dx[i];
          let nby = by + dy[i];
          if (check(nbx, nby, 0) && maze[nbx][nby] !== 5) {
            for (let d = 0; d < 4; d++) {
              let nrx = rx + dx[d];
              let nry = ry + dy[d];
              if (check(nrx, nry, 1) && !(nbx === nrx && nby === nry) && maze[nrx][nry] !== 5) {
                if (!((nbx === rx && nby === ry) && (bx === nrx && by === nry))) {
                  visited[0][nbx][nby] = 1
                  visited[1][nrx][nry] = 1
                  dfs([nbx, nby], [nrx, nry], depth + 1)
                  visited[0][nbx][nby] = 0
                  visited[1][nrx][nry] = 0
                }
              }
            }
          }
        }
      } else if (maze[bx][by] !== 4 && maze[rx][ry] === 3) {
        for (let i = 0; i < 4; i++) {
          let nbx = bx + dx[i];
          let nby = by + dy[i];
          if (check(nbx, nby, 0) && !(nbx === rx && nby === ry) && maze[nbx][nby] !== 5) {
            visited[0][nbx][nby] = 1
            dfs([nbx, nby], [rx, ry], depth + 1)
            visited[0][nbx][nby] = 0
          }
        }
      } else if (maze[bx][by] === 4 && maze[rx][ry] !== 3) {
        for (let i = 0; i < 4; i++) {
          let nrx = rx + dx[i];
          let nry = ry + dy[i];
          if (check(nrx, nry, 1) && !(bx === nrx && by === nry) && maze[nrx][nry] !== 5) {
            visited[1][nrx][nry] = 1
            dfs([bx, by], [nrx, nry], depth + 1)
            visited[1][nrx][nry] = 0
          }
        }
      };
    };
  
    dfs(bs, rs, 0);
  
    if (answer.length === 0) {
      return 0
    } else {
      return Math.min(...answer)
    }
  };