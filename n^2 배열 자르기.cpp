#include <iostream>
#include <string>
#include <vector>

using namespace std;
#define ll long long

vector<int> solution(int n, ll left, ll right) {
  vector<int> answer;
  for (ll i = left; i <= right; i++) answer.push_back(max(i / n, i % n) + 1);
  return answer;
}

int main() {
  vector<int> answer1 = solution(3, 2, 5);
  cout << "answer1 should be [3,2,2,3] : ";
  for (int i = 0; i < answer1.size(); i++) cout << answer1[i] << " ";
  cout << "\n";
  vector<int> answer2 = solution(4, 7, 14);
  cout << "answer2 should be [4,3,3,3,4,4,4,4] : ";
  for (int i = 0; i < answer2.size(); i++) cout << answer2[i] << " ";
  cout << "\n";
  return 0;
}
