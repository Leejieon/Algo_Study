import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

class Q2098 {
    static final int INF = 16_000_000;
    static int N;
    static int[][] graph, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        graph = new int[N][N];
        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++) {
                graph[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[N][(1 << N) - 1];
        for (int y = 0; y < N; y++) {
            Arrays.fill(dp[y], -1);
        }

        System.out.println(tsp(0, 1));
    }

    static int tsp(int cur, int visit) {
        // Base Case
        // 모든 도시를 방문한 경우
        if (visit == (1 << N) - 1) {
            // 다시 시작점으로 돌아가기 위해, 마지막 노드와 시작점 사이의 거리 확인
            if (graph[cur][0] == 0) {
                return INF;
            }
            return graph[cur][0];
        }

        // Memoization : 이미 계산한 값이 있는 경우
        if(dp[cur][visit] != -1) {
            return dp[cur][visit];
        }
        dp[cur][visit] = INF;

        // Recursive Case
        for (int i = 0; i < N; i++) {
            // cur 에서 i로 갈 수 없는 경우
            if(graph[cur][i] == 0) continue;
            // 이전에 이미 방문한 경우
            if((visit & (1 << i)) != 0) continue;

            dp[cur][visit] = Math.min(dp[cur][visit], graph[cur][i] + tsp(i, visit | (1 << i)));
        }
        return dp[cur][visit];
    }
}
