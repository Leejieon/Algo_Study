

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[] foods;
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static long answer = 0;

    public static void main(String[] args) throws IOException {
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(stringTokenizer.nextToken());
        K = Integer.parseInt(stringTokenizer.nextToken());
        foods = new int[N];
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < foods.length; i++) {
            foods[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        System.out.println(findMaxEnergy());
    }

    private static long findMaxEnergy() {
        long[] dp = new long[foods.length];
        int start = 0, end = 0;
        long curSum = 0;
        while (end < foods.length) {
            curSum += foods[end];
            if (end > 0) {
                dp[end] = dp[end - 1];
            }

            while (curSum >= K) {
                dp[end] = Math.max(dp[end], dp[Math.max(0,start-1)] + curSum - K);
                curSum -= foods[start];
                start +=1;
            }
            end++;
        }
        return dp[foods.length-1];
    }
}
