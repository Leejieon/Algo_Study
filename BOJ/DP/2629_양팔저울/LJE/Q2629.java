import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q2629 {
    static final int MAX_MARBLE = 40001;
    static int N, M;
    static int[] weights, marbles;
    static boolean[][] dp;
    static Set<Integer> resultSet = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine()); // 추의 개수
        weights = new int[N + 1]; // 1번부터 시작
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            weights[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine()); // 구슬의 개수
        marbles = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            marbles[i] = Integer.parseInt(st.nextToken());
        }

        dp = new boolean[N + 1][MAX_MARBLE];
        makeDpGraph();

        printResult();

    }

    static void makeDpGraph() {
        List<Integer> currentResult = new ArrayList<>();

        for (int row = 1; row < N + 1; row++) {
            int cur = weights[row];

            // 추 자신의 무게 넣기
            dp[row][cur] = true;
            currentResult.add(cur);

            for (Integer prev : resultSet) {
                // 1st. (row-1) 줄에서 true 인 것 그대로 true로 입력
                dp[row][prev] = true;

                // 2nd. (row-1) 줄에서 true인 값들에 대해 결과 계산
                int result1 = cur + prev;
                int result2 = Math.abs(cur - prev);

                if(result1 <= MAX_MARBLE) {
                    dp[row][result1] = true;
                    currentResult.add(result1);
                }

                dp[row][result2] = true;
                currentResult.add(result2);
            }

            resultSet.addAll(currentResult);
            currentResult.clear();
        }
    }

    static void printResult() {
        for (int marble : marbles) {
            if(dp[N][marble])
                System.out.print("Y ");
            else
                System.out.print("N ");
        }
    }

}
