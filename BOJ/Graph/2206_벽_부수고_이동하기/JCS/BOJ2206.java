package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2206 {
    /*
    N×M의 행렬로 표현되는 맵이 있다.
    맵에서 0은 이동할 수 있는 곳을 나타내고, 1은 이동할 수 없는 벽이 있는 곳을 나타낸다.
    당신은 (1, 1)에서 (N, M)의 위치까지 이동하려 하는데, 이때 최단 경로로 이동하려 한다.
    최단경로는 맵에서 가장 적은 개수의 칸을 지나는 경로를 말하는데, 이때 시작하는 칸과 끝나는 칸도 포함해서 센다.

    만약에 이동하는 도중에 한 개의 벽을 부수고 이동하는 것이 좀 더 경로가 짧아진다면, 벽을 한 개 까지 부수고 이동하여도 된다.
    한 칸에서 이동할 수 있는 칸은 상하좌우로 인접한 칸이다.
    맵이 주어졌을 때, 최단 경로를 구해 내는 프로그램을 작성하시오.

    입력 :
    첫째 줄에 N(1 ≤ N ≤ 1,000), M(1 ≤ M ≤ 1,000)이 주어진다.
    다음 N개의 줄에 M개의 숫자로 맵이 주어진다. (1, 1)과 (N, M)은 항상 0이라고 가정하자.
    출력 :
    첫째 줄에 최단 거리를 출력한다. 불가능할 때는 -1을 출력한다.
    예시 :
    Input :
    6 4
0100
1110
1000
0000
0111
0000
    Output :
    15

    1. 최단경로로 이동
    2. 벽 한 개까지 부수고 이동 가능
    BFS로 이동 + 벽을 부수는 행위 했는지 안 했는지 확인
     */
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int[][] map;
    static int[][] visit;
    static int[][] breakVisit;

    public static class Node {
        int x, y, dist;
        boolean breakWall = false;

        public Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        public boolean alreadyBreak() {
            return breakWall;
        }

        public void setBreakWall(boolean flag) {
            this.breakWall = flag;
        }
    }

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static int N, M;

    public static void main(String[] args) throws IOException {
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());
        map = new int[N][M];
        visit = new int[N][M];
        breakVisit = new int[N][M];

        for (int i = 0; i < N; i++) {
            String input = bufferedReader.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(Character.toString(input.charAt(j)));
            }
        }
        int result = BFS();
        if (result == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }
        System.out.println(result);
    }

    private static int BFS() {
        int result = Integer.MAX_VALUE;
        Queue<Node> queue = new LinkedList<>();
        Node start = new Node(0, 0, 1);
        queue.add(start);
        visit[0][0] = 1;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if (cur.x == N - 1 && cur.y == M - 1) {
                result = Math.min(result, cur.dist);
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                int nDist = cur.dist + 1;
                boolean flag = cur.breakWall;

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                    continue;
                }
                // 이전에 부순 적이 있다면
                if (flag) {
                    if (map[nx][ny] != 1 && (breakVisit[nx][ny] == 0 || breakVisit[nx][ny] > nDist)) {
                        breakVisit[nx][ny] = nDist;
                        Node node = new Node(nx, ny, nDist);
                        node.setBreakWall(true);
                        queue.add(node);
                    }
                } else {
                    if (map[nx][ny] == 1 && (breakVisit[nx][ny] == 0 || breakVisit[nx][ny] > nDist)) {
                        breakVisit[nx][ny] = nDist;
                        Node node = new Node(nx, ny, nDist);
                        node.setBreakWall(true);
                        queue.add(node);
                    }
                    else if(map[nx][ny] == 0 && (visit[nx][ny] == 0 || visit[nx][ny] > nDist)) {
                        visit[nx][ny] = nDist;
                        queue.add(new Node(nx,ny,nDist));
                    }
                }
            }
        }
        return result;
    }
}
