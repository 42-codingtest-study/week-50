#include <iostream>
#include <vector>

using namespace std;

int n, m;
vector<int> N_list;
int main()
{
	cin >> n;
	for(int i = 0; i < n; i++)
	{
		int tmp;
		cin >> tmp;
		N_list.push_back(tmp);
	}
	cin >> m;
	for(int i = 0; i < m; i++)
	{
		int tmp;
		cin >> tmp;
		N_list.push_back(tmp);
	}
	sort(N_list.begin(), N_list.end());
	for(int i = 0; i < N_list.size(); i++)
	{
		cout << N_list[i] << " ";
	}
}
