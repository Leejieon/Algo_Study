package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1445 {
    static int N, M;
    static char[][] map;
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static int[][] thrash;
    static int[][] sideThrash;

    static Info START;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static class Info implements Comparable<Info> {
        int x;
        int y;
        int tCnt;
        int sCnt;

        public Info(int x, int y, int tCnt, int sCnt) {
            this.x = x;
            this.y = y;
            this.tCnt = tCnt;
            this.sCnt = sCnt;
        }

        @Override
        public int compareTo(Info o) {
            if (this.tCnt == o.tCnt) {
                return this.sCnt - o.sCnt;
            }

            return this.tCnt - o.tCnt;
        }
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());
        map = new char[N][M];
        thrash = new int[N][M];
        sideThrash = new int[N][M];
        for (int i = 0; i < N; i++) {
            String input = bufferedReader.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j);
                if (map[i][j] == 'S') {
                    START = new Info(i, j, 0, 0);
                }
                thrash[i][j] = -1;
                sideThrash[i][j] = -1;
            }
        }
        bfs();

    }

    private static void bfs() {
        int minThrash = Integer.MAX_VALUE;
        int minSide = Integer.MAX_VALUE;
        PriorityQueue<Info> queue = new PriorityQueue<>();
        queue.add(START);
        thrash[START.x][START.y] = START.tCnt;
        sideThrash[START.x][START.y] = START.sCnt;


        while (!queue.isEmpty()) {
            Info cur = queue.poll();
            if (map[cur.x][cur.y] == 'F') {
                minThrash = Math.min(minThrash, thrash[cur.x][cur.y]);
                minSide = Math.min(minSide, sideThrash[cur.x][cur.y]);
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                int thrashCnt = cur.tCnt;
                int sideCnt = cur.sCnt;
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                    continue;
                }
                if (map[nx][ny] == 'g') {
                    thrashCnt += 1;
                }
                if (hasSideThrash(nx, ny)) {
                    sideCnt += 1;
                }
                if (thrash[nx][ny] == -1 || thrash[nx][ny] >= thrashCnt) {
                    if (sideThrash[nx][ny] == -1 || sideThrash[nx][ny] > sideCnt) {
                        thrash[nx][ny] = thrashCnt;
                        queue.add(new Info(nx, ny, thrashCnt, sideCnt));
                        sideThrash[nx][ny] = sideCnt;
                    }
                }
            }
        }
        System.out.println(minThrash + " " + minSide);
    }

    private static boolean hasSideThrash(int x, int y) {
        if (map[x][y] == 'F' || map[x][y] == 'g') {
            return false;
        }
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                continue;
            }
            if (map[nx][ny] == 'g') {
                return true;
            }
        }
        return false;
    }
}
