import java.util.Arrays;
import java.util.Scanner;

public class Q2228 {
    static int[] numbers, prefixSum;
    static int[][] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        numbers = new int[N + 1];
        prefixSum = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            numbers[i] = sc.nextInt();
            prefixSum[i] = prefixSum[i - 1] + numbers[i];
        }

        dp = new int[N + 1][M + 1];
        for (int[] ints : dp) {
            Arrays.fill(ints, -1);
        }

        System.out.println(calculate(N, M));

    }

    // n까지 갔을 때 m개의 구간으로 나눴을 때 최대 값
    static int calculate(int n, int m) {
        if (m == 0)
            return 0;

        if(n <= 0)
            return -10000000;

        // 이미 계산이 되어있는 경우
        if(dp[n][m] != -1)
            return dp[n][m];

        // 1. N을 고르지 않고 계산하는 경우
        dp[n][m] = calculate(n - 1, m);

        // 2. 새로운 [i, n] 구간을 추가할 경우
        for (int i = 1; i <= n; i++) {
            dp[n][m] = Math.max(dp[n][m], calculate(i - 2, m - 1) + sum(i, n));
        }

        return dp[n][m];
    }

    // i ~ j 까지의 합 구하기
    static int sum(int i, int j) {
        return prefixSum[j] - prefixSum[i - 1];
    }
}
