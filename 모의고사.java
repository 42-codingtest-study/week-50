import java.util.*;

class 모의고사 {
    public int[] solution(int[] answers) {
        int[] a = {1, 2, 3, 4, 5};
        int[] b = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] c = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        int[] scores = new int[3];

        for (int i = 0; i < answers.length; i++) {
            int one = answers[i];
            int aIdx = i % a.length;
            int bIdx = i % b.length;
            int cIdx = i % c.length;

            if (a[aIdx] == one) scores[0]++;
            if (b[bIdx] == one) scores[1]++;
            if (c[cIdx] == one) scores[2]++;
        }
        int maxScore = -1;
        for (int score : scores) {
            maxScore = Math.max(maxScore, score);
        }
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] == maxScore) result.add(i + 1);
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}