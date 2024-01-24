import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q17070 {
    static int N, answer;
    static int[][] graph;
    static int[][] move = {
            {0, 1}, // 가로
            {1, 0}, // 세로
            {1, 1}  // 대각선
    };

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

        answer = 0;
        recur(0, 1, 0);
        System.out.println(answer);
    }

    static void recur(int y, int x, int type) {
        // Base Case
        if (y >= N && x >= N) return;
        if (y == N - 1 && x == N - 1) {
            answer++;
            return;
        }

        // Recursive Case
        /*
        type : 현재 파이프의 모양
        type = 0 : 가로
        type = 1 : 세로
        type = 2 : 대각선
         */
        for (int cand = 0; cand < 3; cand++) {
            // 파이프가 가로인 경우, 세로로 이동 할 수 없다.
            if(type == 0 && cand == 1) continue;
            // 파이프가 세로인 경우, 가로로 이동 할 수 없다.
            if(type == 1 && cand == 0) continue;

            int dy = y + move[cand][0];
            int dx = x + move[cand][1];

            if(isOutOfBound(dy, dx)) continue;
            if(graph[dy][dx] == 1) continue;
            // 대각선 방향일 경우, 추가적인 왼쪽과 위 공간의 확인이 필요
            if(cand == 2 && !isEmptySpace(dy, dx)) continue;

            recur(dy, dx, cand);
        }
    }

    static boolean isOutOfBound(int y, int x) {
        return y < 0 || x < 0 || y >= N || x >= N;
    }

    static boolean isEmptySpace(int y, int x) {
        return graph[y][x - 1] == 0 && graph[y - 1][x] == 0;
    }
}
