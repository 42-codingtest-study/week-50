import java.util.*;

class Solution {
    void generateCombination(int n, int r, int start, List<Integer> combination, List<List<Integer>> result, boolean repet) {
        if (r == 0) {
            result.add(new ArrayList<>(combination));
            return;
        }

        int i = repet? 0: start;
        while (i < n) {
            combination.add(i);
            generateCombination(n, r - 1, i + 1, combination, result, repet);
            combination.remove(combination.size() - 1);

            i++;
        }
    }

    class GameResult {
        int win;
        int other;

        double getWinRate() {
            return (double) win / (win + other);
        }
    }

    GameResult getGameResult(List<Integer> aScoreList, List<Integer> bScoreList) {
        aScoreList.sort(Comparator.naturalOrder());
        bScoreList.sort(Comparator.naturalOrder());
        GameResult gameResult = new GameResult();

        for (int aScore : aScoreList) {
            int start = 0;
            int end = bScoreList.size() - 1;
            int win = 0;
            while (start <= end) {
                int mid = (start + end) / 2;
                if (bScoreList.get(mid) < aScore) {
                    win = mid + 1;
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
            gameResult.win += win;
            gameResult.other += bScoreList.size() - win;
        }
        return gameResult;
    }

    int game(int[][] dice, List<List<Integer>> aList, List<List<Integer>> bList, List<List<Integer>> diceCombination) {
        List<Double> winRateList = new ArrayList<>();
        int maxIndex = 0;
        double maxWinRate = 0;

        for (int j = 0; j < aList.size(); j++) {
            List<Integer> aScoreList = new ArrayList<>();
            for (List<Integer> comb : diceCombination) {
                int tmp = 0;
                for (int i = 0; i < comb.size(); i++) {
                    tmp += dice[aList.get(j).get(i)][comb.get(i)];
                }
                aScoreList.add(tmp);
            }

            List<Integer> bScoreList = new ArrayList<>();
            for (List<Integer> comb : diceCombination) {
                int tmp = 0;
                for (int i = 0; i < comb.size(); i++) {
                    tmp += dice[bList.get(j).get(i)][comb.get(i)];
                }
                bScoreList.add(tmp);
            }

            GameResult gameResult = getGameResult(aScoreList, bScoreList);

            winRateList.add(gameResult.getWinRate());
            if (maxWinRate < winRateList.get(j)) {
                maxWinRate = winRateList.get(j);
                maxIndex = j;
            }
        }

        return maxIndex;
    }


    public int[] solution(int[][] dice) {
        int n = dice.length;
        List<List<Integer>> aList = new ArrayList<>();
        List<List<Integer>> bList = new ArrayList<>();


        generateCombination(n, n / 2, 0, new ArrayList<>(), aList, false);
        for (List<Integer> list : aList) {
            List<Integer> combination = new ArrayList<>();
            int k = 0;
            for (int i = 0; i < n; i++) {
                if (k < n / 2 && i == list.get(k)) {
                    k++;
                } else {
                    combination.add(i);
                }
            }
            bList.add(combination);
        }

        List<List<Integer>> diceCombination = new ArrayList<>();
        generateCombination(6, n / 2, 0, new ArrayList<>(), diceCombination, true);

        int maxWinIndex = game(dice, aList, bList, diceCombination);
        List<Integer> resultList = aList.get(maxWinIndex);
        resultList.sort(Comparator.naturalOrder());
        int[] result = new int[n / 2];
        for (int i = 0; i < resultList.size(); i++) {
            result[i] = resultList.get(i) + 1;
        }
        return result;
    }
}
