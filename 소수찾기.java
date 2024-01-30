import java.util.*;

class 소수찾기 {
    public int solution(int n) {
        int result = 0;
        boolean[] isPrime = getEratos(n);
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) result++;
        }
        return result;
    }

    private boolean[] getEratos(int max) {
        boolean[] isPrime = new boolean[max + 1];
        Arrays.fill(isPrime, true);

        for (int i = 2; i * i <= max; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= max; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        return isPrime;
    }
}