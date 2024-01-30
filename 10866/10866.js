//문제: 10866
const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");

solution(input);
function solution(input) {
  const arr = [];
  let answer = "";
  input.shift();
  input.forEach((e) => {
    const command = e.split(" ");
    if (command[0] === "push_back") arr.push(command[1]);
    else if (command[0] === "push_front") arr.splice(0, 0, command[1]);
    else if (command[0] === "front") {
      if (arr.length === 0) answer += "-1\n";
      else answer += `${arr[0]}\n`;
    } else if (command[0] === "back") {
      if (arr.length === 0) answer += "-1\n";
      else answer += `${arr[arr.length - 1]}\n`;
    } else if (command[0] === "size") {
      answer += `${arr.length}\n`;
    } else if (command[0] === "pop_back") {
      if (arr.length === 0) answer += "-1\n";
      else answer += `${arr.splice(arr.length - 1, 1)[0]}\n`;
    } else if (command[0] === "pop_front") {
      if (arr.length === 0) answer += "-1\n";
      else answer += `${arr.splice(0, 1)[0]}\n`;
    } else if (command[0] === "empty") {
      if (arr.length === 0) answer += "1\n";
      else answer += "0\n";
    }
  });
  console.log(answer.trim());
}
