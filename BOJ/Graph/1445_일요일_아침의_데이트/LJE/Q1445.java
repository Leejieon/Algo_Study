import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q1445 {
    static int N, M, answer1, answer2;
    static char[][] graph;
    static Point startPoint, endPoint;
    static boolean[][] visited;
    static int[][] dn = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N][M];

        graph = new char[N][M];
        for (int y = 0; y < N; y++) {
            String line = br.readLine();

            for (int x = 0; x < M; x++) {
                graph[y][x] = line.charAt(x);

                if(graph[y][x] == 'S'){
                    startPoint = new Point(y, x, 0, 0);
                }
                if (graph[y][x] == 'F') {
                    endPoint = new Point(y, x);
                }
            }
        }

        answer1 = 0;
        answer2 = 0;
        bfs(startPoint);

        System.out.println(answer1 + " " + answer2);
    }

    static void bfs(Point point) {
        PriorityQueue<Point> pq = new PriorityQueue<>();
        pq.add(point);

        while (!pq.isEmpty()) {
            Point cur = pq.poll();
            int y = cur.y;
            int x = cur.x;

            if(visited[y][x]) continue;

            visited[y][x] = true;

            for (int cand = 0; cand < 4; cand++) {
                int dy = y + dn[cand][0];
                int dx = x + dn[cand][1];

                if(isOutOfBound(dy, dx)) continue;

                if (dy == endPoint.y && dx == endPoint.x) {
                    answer1 = cur.gCnt;
                    answer2 = cur.nCnt;
                    return;
                }

                if (graph[dy][dx] == 'g') {
                    pq.add(new Point(dy, dx, cur.gCnt + 1, cur.nCnt));
                } else {
                    if (checkGarbage(dy, dx)) {
                        pq.add(new Point(dy, dx, cur.gCnt, cur.nCnt + 1));
                    } else {
                        pq.add(new Point(dy, dx, cur.gCnt, cur.nCnt));
                    }
                }
            }
        }
    }

    static boolean checkGarbage(int y, int x) {
        for (int cand = 0; cand < 4; cand++) {
            int dy = y + dn[cand][0];
            int dx = x + dn[cand][1];

            if(isOutOfBound(dy, dx)) continue;

            if(graph[dy][dx] == 'g')
                return true;
        }

        return false;
    }

    static boolean isOutOfBound(int y, int x) {
        return y < 0 || x < 0 || y >= N || x >= M;
    }

    static class Point implements Comparable<Point>{
        int y;
        int x;
        int gCnt;
        int nCnt;

        Point(int y, int x) {
            this.y = y;
            this.x = x;
        }

        Point(int y, int x, int gCnt, int nCnt) {
            this.y = y;
            this.x = x;
            this.gCnt = gCnt;
            this.nCnt = nCnt;
        }

        @Override
        public int compareTo(Point o) {
            if(this.gCnt > o.gCnt) {
                return 1;
            } else if (this.gCnt == o.gCnt) {
                return this.nCnt - o.nCnt;
            } else {
                return -1;
            }
        }
    }
}
