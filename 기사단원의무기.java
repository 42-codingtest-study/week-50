class 기사단원의무기 {
    public int solution(int number, int limit, int power) {
        int result = 0;
        for (int i = 1; i <= number; i++) {
            int divisors = getDivisor(i);
            if (divisors > limit) divisors = power;
            result += divisors;
        }
        return result;
    }

    private int getDivisor(int num) {
        int count = 0;
        for (int i = 1; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                if (num / i == i) count++;
                else count += 2;
            }
        }
        return count;
    }
}