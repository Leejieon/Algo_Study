import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    /**
     * 불특정 도시에서 출발해 모든 도시를 돌고 다시 출발로 돌아왔을 때 드는 최소 비용 최소비용의 싸이클 경로를 찾는 문제, 출발점을 어느 정점으로 두던 싸이클의 비용은 같다. 이미 방문한 도시 Set(i),
     * 현재 위치한 도시(j) dp[i][j] = > 방문하지 않은 나머지 도시들을 모두 방문한 뒤 출발 도시로 돌아올 때 드는 최소 비용
     */
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[][] graph;
    static int[][] dp;
    static final int MAX = 20000000;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bf.readLine());
        graph = new int[N][N];
        dp = new int[1 << N][N];

        for (int i = 0; i < N; i++) {
            int[] dist = Arrays.stream(bf.readLine().split(" ")).mapToInt(num -> Integer.parseInt(num)).toArray();
            for (int j = 0; j < N; j++) {
                graph[i][j] = dist[j];
            }
        }

        int ans = dfs(0, 0);
        System.out.println(ans);

    }

    private static int dfs(int now, int visited) {

        //현재 위치 방문 표시
        visited |= (1 << now);

        if (visited == (1 << N) - 1) {
            //모든 도시를 방문한 경우 (N이 5이면 11111)
            if (graph[now][0] > 0) {
                //현재 위치 에서 출발지(0)으로 돌아가는 경로가 존재하면
                return graph[now][0];
            }
            return MAX;
        }

        // 이전에 발견한 최소비용경로가 있다면 바로 반환
        if (dp[visited][now] > 0) {
            return dp[visited][now];
        }

        dp[visited][now] = MAX;

        for (int i = 0; i < N; i++) {
            if (i != now && ((visited & (1 << i)) == 0) && graph[now][i] > 0) {
                // 현재 위치기 아니면서 방문한 적 없고, 방문할 수 있는 경로가 있다면 방문해보기
                dp[visited][now] = Math.min(dp[visited][now], dfs(i, visited) + graph[now][i]);
            }
        }
        return dp[visited][now];
    }
}
