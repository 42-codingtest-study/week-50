#include <string>
#include <vector>
#include <algorithm>
#include <map>

using namespace std;

vector<int> calculateScore(vector<vector<int>>& A, vector<vector<int>>& B) {
    vector<int> score(1, 0);
    int maxSum = A.size() * 100;
    vector<int> sumA(maxSum + 1, 0), sumB(maxSum + 1, 0);

    sumA[0] = 1;
    for (const auto& dice : A) {
        vector<int> nextSumA(maxSum + 1, 0);
        for (int num : dice) {
            for (int j = 0; j <= maxSum - num; ++j) {
                nextSumA[j + num] += sumA[j];
            }
        }
        sumA = nextSumA;
    }

    sumB[0] = 1;
    for (const auto& dice : B) {
        vector<int> nextSumB(maxSum + 1, 0);
        for (int num : dice) {
            for (int j = 0; j <= maxSum - num; ++j) {
                nextSumB[j + num] += sumB[j];
            }
        }
        sumB = nextSumB;
    }

    for (int i = 0; i <= maxSum; ++i) {
        for (int j = 0; j <= maxSum; ++j) {
            if (i > j) {
                score[0] += sumA[i] * sumB[j];
            }
        }
    }
    return score;
}

vector<int> findIndex(vector<int> selection) {
    vector<int> index;
    for (int i = 0; i < selection.size(); ++i) {
        if (selection[i] == 0) {
            index.push_back(i + 1);
        }
    }
    return index;
}

vector<int> solution(vector<vector<int>> dice) {
    int n = dice.size();
    vector<int> selection(n, 0);
    int maxWin = -1;
    vector<int> bestSelection;

    if (n > 2) {
        fill(selection.begin() + n / 2, selection.end(), 1);
    } else if (n == 2) {
        selection[1] = 1;
    }

    do {
        vector<vector<int>> A, B;
        for (int i = 0; i < n; ++i) {
            if (selection[i] == 0) {
                A.push_back(dice[i]);
            } else {
                B.push_back(dice[i]);
            }
        }

        vector<int> score = calculateScore(A, B);

        if (maxWin < score[0]) {
            maxWin = score[0];
            bestSelection = findIndex(selection);
        }

    } while (next_permutation(selection.begin(), selection.end()));

    return bestSelection;
}
