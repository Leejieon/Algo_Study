import java.io.*;

public class Q1562 {
    static final int MOD = 1_000_000_000;
    static int N;
    static long[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        // dp[n][k][visit] := n번 째 자리에 k가 있고, visit를 거쳐 옴.
        dp = new long[N + 1][10][1 << 10];

        // 1번 째 자리 채워 넣기
        for (int i = 1; i <= 9; i++) {
            dp[1][i][1 << i] = 1;
        }

        // 2번 째 자리부터 시작
        for (int n = 2; n <= N; n++) {
            for (int k = 0; k <= 9; k++) {
                for (int visit = 0; visit < (1 << 10); visit++) {
                    int curVisit = visit | (1 << k);

                    // 0인 경우 k - 1 불가능
                    if (k == 0) {
                        dp[n][k][curVisit] += dp[n - 1][k + 1][visit] % MOD;
                    }
                    // 9인 경우 k + 1 불가능
                    else if (k == 9) {
                        dp[n][k][curVisit] += dp[n - 1][k - 1][visit] % MOD;
                    }
                    // 그 외의 경우
                    else {
                        dp[n][k][curVisit] += dp[n - 1][k + 1][visit] % MOD + dp[n - 1][k - 1][visit] % MOD;
                    }
                    dp[n][k][curVisit] %= MOD;
                }
            }
        }

        long sum = 0;
        // 0 ~ 9까지 모두 채워져 있는 값 더하기
        for (int i = 0; i <= 9; i++) {
            sum += dp[N][i][(1 << 10) - 1] % MOD;
            sum %= MOD;
        }
        System.out.println(sum);
    }
}