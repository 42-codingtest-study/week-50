#include <iostream>
#include <vector>

using namespace std;

int main()
{
	int n,m;
	vector<int> list(n,0);
	cin >> n >> m;
    for (int i = 0; i < n; i++) {
        cin >> list[i];
    }
	int count = 0;
	int total = list[0];
	int left = 0, right = 1;
	while (1)
	{
		if(total<m)
		{
			if(right<n)
			{
				total+=list[right];
				right++;
			}
			else
				break;
		}
		else if(total == m)
		{
			count++;
			total -= list[left];
			left++;
		}
		else
		{
			total-=list[left];
			left++;
		}
	}
	cout << count;


}
