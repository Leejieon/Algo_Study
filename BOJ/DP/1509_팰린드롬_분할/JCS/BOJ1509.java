package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1509 {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String input = bf.readLine();
        // dp[i][j] == i 부터 j 문자열이 팰린드롬인지 아닌지
        boolean[][] dp = new boolean[input.length() + 1][input.length() + 1];

        for (int i = 1; i <= input.length(); i++) {
            dp[i][i] = true;

            if (i < input.length() && input.charAt(i) == input.charAt(i - 1)) {
                dp[i][i + 1] = true;
                dp[i + 1][i] = true;
            }
        }

        for (int len = 3; len <= input.length(); len++) {
            for (int start = 1; start <= input.length() - len + 1; start++) {
                int end = len + start - 1;
                if (input.charAt(end - 1) == input.charAt(start - 1) && dp[start + 1][end - 1]) {
                    dp[start][end] = true;
                    dp[end][start] = true;
                }
            }
        }

        // minPal[i] => 1 부터 i까지 봤을 때 최소분할 갯수
        int[] minPal = new int[input.length() + 1];

        for (int i = 1; i <= input.length(); i++) {
            for (int j = 1; j <= i; j++) {
                if (dp[j][i]) {
                    //새로운 크기의 팰린드롬 발견시 분할갯수 업데이트
                    if (minPal[i] > minPal[j - 1] + 1 || minPal[i] == 0) {
                        minPal[i] = minPal[j - 1] + 1;
                    }
                }
            }
        }

        System.out.println(minPal[input.length()]);
    }
}
