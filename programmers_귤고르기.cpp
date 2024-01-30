#include <string>
#include <vector>
#include <algorithm>
#include <iostream>

using namespace std;

int arr[10000002];
int maxVal = -1;

int solution(int k, vector<int> tangerine) {
    int answer = 0;
    
    for (int i = 0; i < tangerine.size(); i++)
    {
        arr[tangerine[i]]++;
       // maxVal = max(maxVal, tangerine[i]);
    }

    sort(arr+1, arr + 10000002, greater<>());
 
    
    int cnt = 0;
    
    for (int i = 1; i <= 10000002; i++)
    {
        if (arr[i] >= k)
        {
            cnt++;
            return cnt;
        }
        else
        {
            k -= arr[i];
            cnt++;
        }
    }
    return cnt;
   
}