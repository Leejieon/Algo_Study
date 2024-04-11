import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(br.readLine());

        int[] sum = new int[N + 1]; // 객차에 따른 손님 수의 누적합
        int[] trains = new int[N + 1]; // 각 객차의 손님 수
        for (int i = 1; i <= N; i++) {
            trains[i] = Integer.parseInt(st.nextToken());
            sum[i] = sum[i - 1] + trains[i];
        }

        // dp[i][j] := 소형 기관차를 i개 이용했을 때, j번 째 객차 번호에 해당하는 위치에서의 최대
        int[][] dp = new int[4][N + 1];
        for (int num = 1; num < 4; num++) {
            for (int t = 1; t <= N; t++) {
                if (t - K > 0) {
                    dp[num][t] = Math.max(dp[num][t - 1],
                            dp[num - 1][t - K] + (sum[t] - sum[t - K]));
                } else {
                    dp[num][t] = sum[t];
                }
            }
        }

        System.out.println(dp[3][N]);

    }
}