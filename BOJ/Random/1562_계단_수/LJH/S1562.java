import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class S1562 {

    static BufferedReader br;
    static StringTokenizer st;

    static int mod = 1000000000;
    static int n;

    static int result = 0;

    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        input();
        for (int i = 1; i <= 9; i++) {
            result += solution(1, i, 1 << i);
            result %= mod;
        }
        System.out.print(result);
    }


    public static void input() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        dp = new int[n][1 << 10][10];
    }

    public static int solution(int idx, int num, int visit) {
        if (idx == n) {
            if (visit == (1 << 10) - 1) {
                return 1;
            }
            return 0;
        }
        if (dp[idx][visit][num] != 0) {
            return dp[idx][visit][num];
        }
        for (int i = 0; i <= 9; i++) {
            int next = 1 << i;
            if (Math.abs(num - i) != 1)
                continue;
            dp[idx][visit][num] += solution(idx + 1, i, visit | next);
            dp[idx][visit][num] %= mod;
        }
        return dp[idx][visit][num];
    }
}
