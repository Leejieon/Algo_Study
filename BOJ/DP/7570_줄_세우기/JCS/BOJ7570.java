package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ7570 {
    static int N;
    static int[] dp;
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        dp = new int[N + 1];
        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
        int max = 0;
        while (st.hasMoreTokens()) {
            int number = Integer.parseInt(st.nextToken());
            dp[number] = dp[number - 1] + 1;

            max = Math.max(max, dp[number]);
        }
        System.out.println(N - max);
    }
}
