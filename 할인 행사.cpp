#include <iostream>
#include <string>
#include <unordered_map>
#include <vector>
using namespace std;

int solution(vector<string> want, vector<int> number, vector<string> discount) {
  int answer = 0;
  unordered_map<string, int> wantMap;
  for (int i = 0; i < number.size(); i++) wantMap.insert({want[i], number[i]});
  for (int i = 0; i <= discount.size() - 10; i++) {
    unordered_map<string, int> martDiscount;
    for (int j = i; j < i + 10; j++) martDiscount[discount[j]] += 1;
    if (martDiscount == wantMap) answer++;
  }
  return answer;
}

int main() {
  vector<string> want = {"banana", "apple", "rice", "pork", "pot"};
  vector<int> number = {3, 2, 2, 2, 1};
  vector<string> discount = {"chicken", "apple",  "apple",  "banana", "rice",
                             "apple",   "pork",   "banana", "pork",   "rice",
                             "pot",     "banana", "apple",  "banana"};
  cout << solution(want, number, discount) << " should be 3\n";
  want = {"apple"};
  number = {10};
  discount = {"banana", "banana", "banana", "banana", "banana",
              "banana", "banana", "banana", "banana", "banana"};
  cout << solution(want, number, discount) << " should be 0\n";
  return 0;
}
