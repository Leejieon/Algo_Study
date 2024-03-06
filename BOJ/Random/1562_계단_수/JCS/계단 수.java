import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    /**
     * 45656이란 수를 보자. 이 수는 인접한 모든 자리의 차이가 1이다. 이런 수를 계단 수라고 한다. N이 주어질 때, 길이가 N이면서 0부터 9까지 숫자가 모두 등장하는 계단 수가 총 몇 개 있는지
     * 구하는 프로그램을 작성하시오. 0으로 시작하는 수는 계단수가 아니다. 1<= N <= 100 0부터 9까지의 숫자가 모두 등장 => 최소길이 10
     * <p>
     * 길이가 10 -> 9876543210 ... 1 개 길이가 11 -> 89876543210 / 98765432101 ... 2개 (8 + N(10)) , (N(10) + 1) 길이가 12 ->
     * 989876543210 / 898765432101 / 987654321012 / 898765432101 / 101 234 567 898
     */
    static int[][][] dp;
    static int N;
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static final int MOD = 1000000000;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bf.readLine());

        // dp[i][j][k] 방문현황이 i이고 j번 째의 수(왼쪽)가 k일 때의 계단 수
        dp = new int[1 << 10][N][10];

        int ans = 0;
        for (int i = 1; i < 10; i++) {
            ans += recur(i, 0, 0);
            ans %= MOD;
        }

        System.out.println(ans);

    }

    private static int recur(int curUseNum, int depth, int visited) {

        visited |= 1 << curUseNum;

        int ret = dp[visited][depth][curUseNum];
        if (ret != 0) {
            return ret;
        }

        if (depth == N-1) {
            if (visited == (1 << 10) - 1) {
                return 1;
            }
            return 0;
        }

        if (curUseNum < 9) {
            dp[visited][depth][curUseNum] += recur(curUseNum + 1, depth + 1, visited) % MOD;
        }
        if (curUseNum > 0) {
            dp[visited][depth][curUseNum] += recur(curUseNum - 1, depth + 1, visited) % MOD;
        }
        return dp[visited][depth][curUseNum] % MOD;
    }

}
