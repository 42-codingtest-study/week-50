#include <string>
#include <vector>
#include <iostream>

using namespace std;

vector<int> binary;
vector<int> binary2;

int countOne(int num)
{
  int cnt = 0;

  while (num != 0)
  {
    if (num % 2 == 1)
      cnt++;
    num /= 2;
  }
  return cnt;
}

int solution(int n)
{
  int answer = 0;

  int cnt = countOne(n);
  n++;
  while (1)
  {
    if (countOne(n) == cnt)
    {
      cout << n << "\n";
      return n;
    }
    n++;
  }
  return answer;
}