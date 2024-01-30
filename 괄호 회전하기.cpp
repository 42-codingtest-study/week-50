#include <iostream>
#include <string>
#include <vector>

using namespace std;

int solution(string s) {
  int answer = 0;
  for (int i = 0; i < s.size(); i++) {
    string temp = s.substr(i) + s.substr(0, i);
    while (temp.find("()") != string::npos || temp.find("[]") != string::npos ||
           temp.find("{}") != string::npos) {
      if (temp.find("()") != string::npos) temp.replace(temp.find("()"), 2, "");
      if (temp.find("[]") != string::npos) temp.replace(temp.find("[]"), 2, "");
      if (temp.find("{}") != string::npos) temp.replace(temp.find("{}"), 2, "");
    }
    if (temp.size() == 0) answer++;
  }
  return answer;
}

int main() {
  cout << solution("[](){}");
  return 0;
}
