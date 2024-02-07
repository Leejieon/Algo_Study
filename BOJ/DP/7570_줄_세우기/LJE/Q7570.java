import java.io.*;
import java.util.StringTokenizer;

public class Q7570 {
    static int N, maxLen;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        maxLen = 0;
        dp = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int number = Integer.parseInt(st.nextToken());
            dp[number] = dp[number - 1] + 1;
            maxLen = Math.max(dp[number], maxLen);
        }

        System.out.println(N - maxLen);
    }
}