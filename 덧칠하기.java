import java.util.*;

class 덧칠하기 {
    public int solution(int n, int m, int[] section) {
        int result = 0;
        int idx = 0;

        for (int i = 0; i < section.length; i++) {
            if (section[i] != 0) {
                int limit = section[i] + m - 1;
                for (int j = i; j < section.length && section[j] <= limit; j++) {
                    section[j] = 0;
                }
                result++;
            }
        }
        return result;
    }
}