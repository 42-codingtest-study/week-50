#include <iostream>
#include <vector>

using namespace std;

int calculateScore(vector<int>& dice) {
    int score = 0;
    sort(dice.begin(), dice.end());

    for (int i = 0; i < 3; i++) {
        score += dice[i];
    }

    return score;
}
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
	int total = 0;
	int start = n/2;
	int end = n/2;
	for(int i = 0; i < n; i++)
	{
		for(int j = start; j < end+1; j++)
			total += a[i][j];
		if(i < n /2)
		{
			start--;
			end++;
		}
		else
		{
			start++;
			end--;
		}
	}
	cout << total;
}
