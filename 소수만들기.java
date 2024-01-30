import java.util.*;

class 소수만들기 {
    public int solution(int[] nums) {

        ArrayList<Integer> arr = new ArrayList<>();
        int numsLen = nums.length;

        for (int i = 0; i < numsLen - 2; i++) {
            for (int j = i + 1; j < numsLen - 1; j++) {
                for (int k = j + 1; k < numsLen; k++) {
                    arr.add(nums[i] + nums[j] + nums[k]);
                }
            }
        }

        int[] eratos = sieveOfEratos(3000).stream().mapToInt(Integer::intValue).toArray();
        int result = 0;
        for (int num : arr) {
            if (binarySearch(eratos, num)) {
                result++;
            }
        }

        return result;
    }

    private List<Integer> sieveOfEratos(int max) {
        List<Integer> eratos = new ArrayList<>();
        boolean[] isPrime = new boolean[max + 1];
        Arrays.fill(isPrime, true);

        for (int i = 2; i * i <= max; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= max; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        for (int i = 2; i <= max; i++) {
            if (isPrime[i]) eratos.add(i);
        }
        return eratos;
    }

    private boolean binarySearch(int[] arr, int target) {
        int s = 0;
        int e = arr.length - 1;
        int mid;
        while (s <= e) {
            mid = (s + e) / 2;
            if (arr[mid] < target) s = mid + 1;
            else if (arr[mid] > target) e = mid - 1;
            else return true;
        }
        return false;
    }
}