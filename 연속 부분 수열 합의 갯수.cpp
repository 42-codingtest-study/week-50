#include <iostream>
#include <numeric>
#include <set>
#include <string>
#include <vector>

using namespace std;

int solution(vector<int> elements) {
  vector<int> expand;
  expand.insert(expand.begin(), elements.begin(), elements.end());
  expand.insert(expand.begin(), elements.begin(), elements.end());
  set<int> s;
  for (int i = 1; i <= elements.size(); i++) {
    int sum = accumulate(expand.begin(), expand.begin() + i, 0);
    s.insert(sum);
    for (int j = i; j < i + elements.size() - 1; j++) {
      sum += expand[j] - expand[j - i];
      s.insert(sum);
    }
  }
  return s.size();
}
