
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static final int DIVIDE = 1_000_000_003;
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static int N, K;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bf.readLine());
        K = Integer.parseInt(bf.readLine());

        if (N / 2 < K) {
            System.out.println(0);
            return;
        }

        int[][] dp = new int[N + 1][N + 1];

        for (int i = 1; i < N + 1; i++) {
            dp[i][0] = 1;
            dp[i][1] = i;
        }
        for (int i = 3; i <= N; i++) {
            for (int j = 2; j <= (i + 1) / 2; j++) {
                dp[i][j] = (dp[i - 1][j] + dp[i - 2][j - 1]) % DIVIDE;
            }
        }
        System.out.println((dp[N - 3][K - 1] + dp[N - 1][K]) % DIVIDE) ;

    }


}
