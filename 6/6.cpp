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
		for(int j = 0; j < n; j++)
		{
			cin >> a[i][j];
		}
	}

	int max_value = -100000;
	for(int i = 0; i<n; i++)
	{
		int sum1 = 0, sum2 = 0;

		for(int j = 0; j < n; j++)
		{
			sum1+=a[i][j];
			sum2+=a[j][i];
		}
		if(sum1 > max_value)
			max_value = sum1;
		else if(sum2 > max_value)
			max_value = sum2;
	}
	int sum1 = 0, sum2 = 0;
	for(int i = 0; i < n; i++)
	{
		sum1 += a[i][i];
		sum2 += a[i][n -1 - i];
	}
	if(sum1 > max_value)
		max_value = sum1;
	else if(sum2 > max_value)
		max_value = sum2;

	cout << max_value;


}
