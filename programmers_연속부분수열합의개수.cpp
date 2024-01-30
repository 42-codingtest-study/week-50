#include <string>
#include <vector>
#include <map>
#include <set>
#include <iostream>

using namespace std;

set<int> sums;

void getSums(vector<int> arr, int len)
{
  int hap = 0;
  for (int i = 0; i < len; i++)
  {
    hap += arr[i];
  }
  sums.insert(hap);

  for (int i = 1; i < arr.size() / 2; i++)
  {

    // cout<< hap << "\n";
    hap -= arr[i - 1];
    hap += arr[i + len - 1];
    sums.insert(hap);
  }
}

int solution(vector<int> elements)
{
  int answer = 0;
  int eleSize = elements.size();
  for (int i = 0; i < eleSize; i++)
  {
    elements.push_back(elements[i]);
  }

  for (int i = 1; i <= eleSize; i++)
  {
    getSums(elements, i);
  }
  return sums.size();
}