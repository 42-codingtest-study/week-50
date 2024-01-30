import java.util.*;

public class 주사위고르기 {
    private static final int MAX = 100 * 600 + 1;
    private int N;
    private List<Integer> A, B;
    private List<Integer> Asums, Bsums;
    private Map<Set<Integer>, Boolean> visited;

    public void init() {
        A.clear();
        B.clear();
        Asums.clear();
        Bsums.clear();
    }

    public void func(int idx, int total, boolean isA, List<Integer> v, List<List<Integer>> dice) {
        if (idx == N / 2) {
            if (isA) Asums.add(total);
            else Bsums.add(total);
            return;
        }

        for (int i = 0; i < 6; i++) {
            func(idx + 1, total + dice.get(v.get(idx)).get(i), isA, v, dice);
        }
    }

    public List<Integer> solution(List<List<Integer>> dice) {
        N = dice.size();
        List<Integer> v = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            v.add(i);
        }

        List<Integer> answer = new ArrayList<>(N / 2);
        int maxWin = 0;
        visited = new HashMap<>();

        do {
            init();
            int win = 0;
            Set<Integer> ASet = new HashSet<>();
            Set<Integer> BSet = new HashSet<>();

            for (int i = 0; i < N / 2; i++) {
                A.add(v.get(i));
                ASet.add(v.get(i));

                B.add(v.get(i + N / 2));
                BSet.add(v.get(i + N / 2));
            }

            if (!visited.containsKey(ASet) && !visited.containsKey(BSet)) {
                func(0, 0, true, A, dice);
                func(0, 0, false, B, dice);

                Collections.sort(Bsums);
                Map<Integer, Integer> sum2winCnt = new HashMap<>();

                for (int Asum : Asums) {
                    if (sum2winCnt.containsKey(Asum)) {
                        win += sum2winCnt.get(Asum);
                        continue;
                    }

                    int low = 0, high = Bsums.size();
                    while (low + 1 < high) {
                        int mid = (low + high) / 2;
                        if (Asum > Bsums.get(mid)) low = mid;
                        else high = mid;
                    }

                    sum2winCnt.put(Asum, low);
                    win += sum2winCnt.get(Asum);
                }

                if (win > maxWin) {
                    for (int i = 0; i < A.size(); i++) {
                        answer.add(A.get(i) + 1);
                    }
                    maxWin = win;
                }

                visited.put(ASet, true);
                visited.put(BSet, true);
            }
        } while (nextPermutation(v));

        return answer;
    }

    private boolean nextPermutation(List<Integer> nums) {
        int i = nums.size() - 2;
        while (i >= 0 && nums.get(i) >= nums.get(i + 1)) {
            i--;
        }

        if (i >= 0) {
            int j = nums.size() - 1;
            while (j >= 0 && nums.get(i) >= nums.get(j)) {
                j--;
            }
            swap(nums, i, j);
        }

        reverse(nums, i + 1);
        return i >= 0;
    }

    private void swap(List<Integer> nums, int i, int j) {
        int temp = nums.get(i);
        nums.set(i, nums.get(j));
        nums.set(j, temp);
    }

    private void reverse(List<Integer> nums, int start) {
        int i = start, j = nums.size() - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }
}
