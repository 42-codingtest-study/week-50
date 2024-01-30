//문제: 15650
const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");

solution(input);
function solution(input) {
  const [n, m] = input[0].split(" ").map(Number);

  let result = [];

  const pickOrNot = (idx, bucket) => {
    if (bucket.length === m) {
      // 탈출조건
      result.push(bucket);
      return;
    }

    if (idx === n) return; // 재귀를 멈추게 하는 조건문

    pickOrNot(idx + 1, bucket.concat(idx + 1));
    pickOrNot(idx + 1, bucket);
  };

  pickOrNot(0, []);

  result.forEach((e) => {
    console.log(e.join(" "));
  });
}
