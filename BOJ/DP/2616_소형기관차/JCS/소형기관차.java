import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[] trainsSum;
    static int max;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        trainsSum = new int[N + 1];
        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
        for (int i = 1; i <= N; i++) {
            trainsSum[i] = Integer.parseInt(st.nextToken()) + trainsSum[i - 1];
        }
        max = Integer.parseInt(bufferedReader.readLine());

        int[][] dp = new int[4][N + 1];
        for (int i = 1; i <= 3; i++) {
            for (int j = i * max; j <= N; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j - max] + trainsSum[j] - trainsSum[j - max]);
            }
        }
        System.out.println(dp[3][N]);
    }
}