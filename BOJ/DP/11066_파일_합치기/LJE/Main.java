import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int TC = Integer.parseInt(br.readLine());
        for (int t = 0; t < TC; t++) {
            int N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            int[] sum = new int[N + 1];
            int[] fileSizes = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                fileSizes[i] = Integer.parseInt(st.nextToken());
                sum[i] = sum[i - 1] + fileSizes[i];
            }

            // dp[i][j] := i ~ j번째 파일을 합칠 때의 최솟값
            int[][] dp = new int[N + 1][N + 1];
            for (int end = 0; end <= N; end++) {
                for (int start = end - 1; start > 0; start--) {
                    dp[start][end] = (int) 1e9;
                    for (int k = start; k < end; k++) {
                        dp[start][end] = Math.min(dp[start][end],
                                dp[start][k] + dp[k + 1][end] + (sum[end] - sum[start - 1]));
                    }
                }
            }
            sb.append(dp[1][N]).append("\n");
        }
        System.out.print(sb);
    }
}