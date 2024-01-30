#include <string>
#include <vector>
#include <iostream>
#include <queue>
#include <deque>
#include <stack>
using namespace std;

// stack<int> q;
vector<int> lst;

int cnt = 0;

void checkPair(vector<int> lst)
{
  stack<int> q;

  for (int i = 0; i < lst.size(); i++)
  {
    // cout<<"!!!!";
    //         if(lst[i] == '{')
    //         {
    //             cout<<"{ in \n";
    //             if (q.front() == '}')
    //                 q.pop();
    //             else
    //                 q.push('{');
    //         }
    //         else if(lst[i] == '[')
    //         {
    //             cout<<"[ in \n";
    //             if (q.front() == ']')
    //                 q.pop();
    //             else
    //                 q.push('[');
    //         }
    //         else if(lst[i] == '(')
    //         {
    //             cout<<"( in \n";
    //             if (q.front() == ')')
    //                 q.pop();
    //             else
    //                 q.push('(');
    //         }

    if (lst[i] == '}')
    {
      if (!q.empty() && q.top() == '{')
        q.pop();
      else
        q.push('}');
    }
    else if (lst[i] == ']')
    {
      if (!q.empty() && q.top() == '[')
        q.pop();
      else
        q.push(']');
    }
    else if (lst[i] == ')')
    {
      if (!q.empty() && q.top() == '(')
        q.pop();
      else
        q.push(')');
    }
    else
    {
      q.push(lst[i]);
    }
  }
  if (q.size() == 0)
  {
    cnt++;
  }
}

int solution(string s)
{

  for (int i = 0; i < s.size(); i++)
  {
    lst.push_back(s[i]);
  }

  for (int i = 0; i < s.size(); i++)
  {
    checkPair(lst);
    int item = lst.front();

    lst.erase(lst.begin(), lst.begin() + 1);
    lst.push_back(item);
  }
  // int answer = -1;
  return cnt;
}