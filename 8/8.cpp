#include <iostream>
#include <vector>

using namespace std;

int main()
{
	int n;
	cin >> n;

	vector<vector<int> > a(n, vector<int>(n));

	for(int i = 0; i < n; i++)
	{
		for (int j = 0; j < n; j++)
		{
			cin >> a[i][j];
		}
	}
	int m;
	cin >> m;
	for(int i = 0; i < m; i++)
	{
		int h, t, k;
		cin >> h >> t >> k;

		if (t == 0)
		{
            for (int x = 0; x < k; x++) {
				a[h-1].push_back(a[h-1].front());
				a[h-1].erase(a[h-1].begin());
            }
        }
		else
		{
			for (int x = 0; x < k; x++)
			{
				a[h-1].insert(a[h-1].begin(),a[h-1].back());
				a[h-1].pop_back();
			}
		}

	}
	int total = 0;
	int start = 0;
	int end = n -1;

	for(int i = 0; i < n; i++)
	{
		for(int j = start; j <= end ; j++)
		{
			total += a[i][j];
		}
		if(i < n/2)
		{
			start++;
			end--;
		}
		else
		{
			start--;
			end++;
		}
	}
	cout << total;
}
