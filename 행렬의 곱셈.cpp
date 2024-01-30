#include <iostream>
#include <string>
#include <vector>

using namespace std;

vector<vector<int>> solution(vector<vector<int>> arr1,
                             vector<vector<int>> arr2) {
  vector<vector<int>> answer;
  for (int i = 0; i < arr1.size(); i++) {
    vector<int> temp;
    for (int j = 0; j < arr2[0].size(); j++) {
      int sum = 0;
      for (int k = 0; k < arr1[0].size(); k++) {
        sum += arr1[i][k] * arr2[k][j];
      }
      temp.push_back(sum);
    }
    answer.push_back(temp);
  }
  return answer;
}

int main() {
  vector<vector<int>> arr1 = {{1, 4}, {3, 2}, {4, 1}};
  vector<vector<int>> arr2 = {{3, 3}, {3, 3}};
  vector<vector<int>> answer = solution(arr1, arr2);
  cout << "[[15, 15], [15, 15], [15, 15]] should be [";
  for (int i = 0; i < answer.size(); i++) {
    cout << "[";
    for (int j = 0; j < answer[i].size(); j++) {
      cout << answer[i][j];
      if (j != answer[i].size() - 1) cout << ", ";
    }
    cout << "]";
    if (i != answer.size() - 1) cout << ", ";
  }
  cout << "]\n";
  arr1 = {{2, 3, 2}, {4, 2, 4}, {3, 1, 4}};
  arr2 = {{5, 4, 3}, {2, 4, 1}, {3, 1, 1}};
  answer = solution(arr1, arr2);
  cout << "[[22, 22, 11], [36, 28, 18], [29, 20, 14]] should be [";
  for (int i = 0; i < answer.size(); i++) {
    cout << "[";
    for (int j = 0; j < answer[i].size(); j++) {
      cout << answer[i][j];
      if (j != answer[i].size() - 1) cout << ", ";
    }
    cout << "]";
    if (i != answer.size() - 1) cout << ", ";
  }
  cout << "]\n";
  return 0;
}
