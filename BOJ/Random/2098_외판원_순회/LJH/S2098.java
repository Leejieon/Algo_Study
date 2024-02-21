import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S2098 {

    static BufferedReader br;
    static StringTokenizer st;
    static int n;
    static int[][] dp;

    static final int INF = 999999999;


    static int[][] graph = new int[16][16];

    public static void main(String[] args) throws IOException {
        input();
        System.out.print(tsp(0, 1)); //0번부터 탐색 시작
    }

    public static void input() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        dp = new int[16][(1 << n)];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < (1 << n); j++) {
                dp[i][j] = -1;
            }
        }
    }

    public static int tsp(int now, int visited) {
        if (visited == (1 << n) - 1) { //전부다 돌았을때
            if (graph[now][0] == 0) { //출발지점으로 못돌아갈 경우
                return INF;
            }
            return graph[now][0];
        }
        if (dp[now][visited] != -1) return dp[now][visited]; //이미 값이 있는 경우 그대로 리턴함.
        dp[now][visited] = INF;
        for (int i = 0; i < n; i++) {
            if (graph[now][i] == 0) //경로가 없을 경우
                continue;
            if ((visited & 1 << i) >= 1) //visit가 i번지를 방문했는지 검사를 함. 방문했다면 일치함.
                continue;
            int next = 1 << i;
            dp[now][visited] = Math.min(dp[now][visited], graph[now][i] + tsp(i, visited | next));
        }
        return dp[now][visited];
    }
}