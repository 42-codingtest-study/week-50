//문제: 3986
const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");

solution(input);
function solution(input) {
  input.shift();
  let answer = 0;
  input.forEach((e) => {
    const stack = [];
    e.split("").forEach((k) => {
      if (k === "A") {
        if (stack[stack.length - 1] === "A") stack.pop();
        else stack.push(k);
      } else if (k === "B") {
        if (stack[stack.length - 1] === "B") stack.pop();
        else stack.push(k);
      }
    });
    if (!stack.length) answer++;
  });
  console.log(answer);
}
