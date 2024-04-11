import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int T, N;
    static int[][] dp;
    static int[] files;
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(bf.readLine());
        StringBuilder sb = new StringBuilder();
        for (int trial = 0; trial < T; trial++) {
            N = Integer.parseInt(bf.readLine());
            files = new int[N + 1];
            int[] fileInput = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int i = 1; i <= N; i++) {
                files[i] = files[i - 1] + fileInput[i - 1];
            }
            dp = new int[N+1][N+1];
            addFile();
            sb.append(dp[1][N]).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void addFile() {
        for (int i = 2; i <= N; i++) {
            for (int j = i - 1; j >= 1; j--) {
                dp[j][i] = Integer.MAX_VALUE;
                for (int s = j; s < i; s++) {
                    dp[j][i] = Math.min(dp[j][i], dp[j][s] + dp[s + 1][i]);
                }
                dp[j][i] += files[i] - files[j - 1];
            }
        }
    }
}
