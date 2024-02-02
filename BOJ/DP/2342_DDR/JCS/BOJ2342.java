package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class BOJ2342 {
    /*
     지시사항을 수행하기 위한 최소의 움직임을 구하는 문제
     처음에는 양발이 중앙에 있다.
     중앙에서 다른 방향으로 이동할 때는 2의 움직임 소요
     다른 지점에서 인접 지점으로 움직일 때는 3의 움직임 소요
     반다편으로 움직일 때는 4의 움직임 소요
     같은 지점에서 움직일 때는 1의 움직임 소요
     매 선택에서 그리디한 선택을 할 수 없다.
     DP로 풀어야한다.

     */
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static int[][][] dp;
    static List<Integer> commands;
    static final int MAX = 4000001;

    public static void main(String[] args) throws IOException {
        commands = Arrays.stream(bufferedReader.readLine().split(" "))
                .map(s -> Integer.parseInt(s))
                .collect(Collectors.toList());

        dp = new int[commands.size()][5][5];

        for (int i = 0; i < commands.size(); i++) {
            for (int j = 0; j < 5; j++) {
                Arrays.fill(dp[i][j], MAX);
            }
        }
        dp[0][0][0] = 0;

        for (int i = 0; i < commands.size()-1; i++) {
            int moveSpot = commands.get(i);

            for (int left = 0; left < 5; left++) {
                for (int right = 0; right < 5; right++) {
                    dp[i + 1][left][moveSpot] = Math.min(dp[i + 1][left][moveSpot],
                            dp[i][left][right] + move(right, moveSpot));

                    dp[i + 1][moveSpot][right] = Math.min(dp[i + 1][moveSpot][right],
                            dp[i][left][right] + move(left, moveSpot));
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (int left = 0; left < 5; left++) {
            for (int right = 0; right < 5; right++) {
                min = Math.min(min, dp[commands.size()-1][left][right]);
            }
        }
        System.out.println(min);
    }


    private static int move(int from, int to) {
        if (from == 0) {
            return 2;
        }
        if (from == to) {
            return 1;
        }
        if (Math.abs(from - to) == 2) {
            return 4;
        }
        return 3;
    }
}
