import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q2206 {
    static int N, M;
    static int[][] graph;
    static int[][] dn = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static boolean[][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 그래프 초기화
        graph = new int[N][M];
        for (int y = 0; y < N; y++) {
            String line = br.readLine();
            for (int x = 0; x < M; x++) {
                graph[y][x] = line.charAt(x) - '0';
            }
        }

        visited = new boolean[N][M][2];

        System.out.println(bfs());

    }

    static int bfs() {
        Queue<Point> queue = new LinkedList<>();
        // 시작 지점 노드 큐에 삽입
        queue.offer(new Point(0, 0, 1, false));

        while (!queue.isEmpty()) {
            Point point = queue.poll();
            int y = point.y;
            int x = point.x;
            int distance = point.distance;
            boolean isBroken = point.isBroken;

            if (y == N - 1 && x == M - 1) {
                return distance;
            }

            for (int cand = 0; cand < 4; cand++) {
                int dy = y + dn[cand][0];
                int dx = x + dn[cand][1];

                if(isOutOfBound(dy, dx)) continue;

                // 벽일 경우
                if (graph[dy][dx] == 1) {
                    // (dy, dx) 벽을 한 번도 부순 적이 없는 경우
                    if(!isBroken){
                        queue.add(new Point(dy, dx, distance + 1, true));
                        visited[dy][dx][1] = true;
                    }
                }
                // 벽이 아닐 경우
                else {
                    // 이때까지 벽을 부수지 않았을 때
                    if (!isBroken && !visited[dy][dx][0]) {
                        queue.add(new Point(dy, dx, distance + 1, false));
                        visited[dy][dx][0] = true;
                    }
                    // 벽을 한 번 부수고 이동했을 때
                    else if (isBroken && !visited[dy][dx][1]) {
                        queue.add(new Point(dy, dx, distance + 1, true));
                        visited[dy][dx][1] = true;
                    }
                }
            }
        }

        return -1;
    }

    static boolean isOutOfBound(int y, int x) {
        return y < 0 || x < 0 || y >= N || x >= M;
    }

    static class Point {
        int y;
        int x;
        int distance;
        boolean isBroken;

        Point(int y, int x, int distance, boolean isBroken) {
            this.y = y;
            this.x = x;
            this.distance = distance;
            this.isBroken = isBroken;
        }
    }
}
