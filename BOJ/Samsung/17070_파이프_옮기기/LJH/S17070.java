import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S17070 {

    static int n;
    static BufferedReader br;
    static StringTokenizer st;
    static int[][] graph;
    static int[][][] dp;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        input();
        search();
        System.out.print(answer);
    }

    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        graph = new int[n + 1][n + 1];
        dp = new int[n + 1][n + 1][3];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    public static void search() { // n * n으로 탐색
        //파이프가 가로로 놓아진 경우 이동방법은 2개. 가로 or 아래 대각선
        //세로로 놓아진 경우 이동방법은 2개. 세로 or 아래 대각선
        //대각선으로 놓아진 경우 이동방법은 3개. 가로 , 세로 , 대각선
        //대각선인 경우 도착지점에서 3개 블록을 사용하지 못함.
        //(n,n) -> (n,n-1), (n+1,n+1), (n-1,n+1)
        dp[1][2][0] = 1; //(1,2)에 파이프가 있다.
        for (int i = 1; i <= n; i++) {
            for (int j = 2; j <= n; j++) { //3가지 방향에서 올 수 있다. 가로 , 세로 , 대각선
                if (graph[i][j] == 1) //벽에는 놓을 수가 없음.
                    continue;                //
                dp[i][j][0] = Math.max(dp[i][j][0], dp[i][j - 1][0] + dp[i][j - 1][2]);// 들어오는 좌표는 (i,j-1)이고 유효한 파이프는 가로와 대각선
                dp[i][j][1] = Math.max(dp[i][j][1], dp[i - 1][j][1] + dp[i - 1][j][2]);//들어오는 좌표는 (i-1,j)이고 유효한 파이프는 세로와 대각선
                if (graph[i - 1][j] == 1 || graph[i][j - 1] == 1) //도착하는 지점에서 왼쪽칸과 위칸이 비어야 함.
                    continue;
                dp[i][j][2] = Math.max(dp[i][j][2], dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2]); //들어오는 좌표는 (i-1,j-1)이고, 유효한 파이프는 가로,세로,대각선
            }
        }
        answer = dp[n][n][0] + dp[n][n][1] + dp[n][n][2];
    }
}