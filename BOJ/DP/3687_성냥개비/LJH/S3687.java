import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

public class S3687 {

    static StringBuffer sb;
    static BufferedReader br;
    static int t, n;
    static long dp[] = new long[101];

    public static void main(String[] args) throws IOException {
        init();
        input();
    }

    public static void init() {
        for (int i = 2; i < 101; i++) {
            dp[i] = Long.MAX_VALUE;
        }
        dp[2] = 1;
        dp[3] = 7;
        dp[4] = 4;
        dp[5] = 2;
        dp[6] = 6;
        dp[7] = 8;
        dp[8] = 10;
        //dp[i]는 성냥 i개 사용해서 만들 수 있는 최소 숫자.
        String[] sticks = {"1", "7", "4", "2", "0", "8"};
        for (int i = 9; i <= 100; i++) {
            for (int j = 2; j <= 7; j++) {
                String line = "" + dp[i - j] + "" + sticks[j - 2];
                dp[i] = Math.min(dp[i], Long.parseLong(line));
            }
        }
    }

    public static void input() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            n = Integer.parseInt(br.readLine());
            System.out.println(dp[n] + " " + printMaxValue());
        }
    }

    public static String printMaxValue() {
        int rem = n / 2;
        StringBuilder sb = new StringBuilder();
        if (n % 2 == 0) { // 짝수면 1로 도배
            for (int j = 0; j < rem; j++) {
                sb.append("1");
            }
        } else {
            sb.append("7");
            for (int j = 0; j < rem - 1; j++) {
                sb.append("1");
            }
        }
        return sb.toString();
    }


}