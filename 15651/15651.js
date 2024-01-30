//문제: 15651
const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");

solution(input);
function solution(input) {
  const [n, m] = input[0].split(" ").map(Number);

  let result = "";
  const output = [];
  function dfs(cnt) {
    if (cnt === m) {
      result += `${output.join(" ")}\n`;
      return;
    }

    for (let i = 0; i < n; i++) {
      output.push(i + 1);
      dfs(output.length);
      output.pop();
    }
  }

  dfs(0);
  console.log(result.trim());
}
