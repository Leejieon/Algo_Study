import java.util.*;

class Solution {
    static int N, answer = Integer.MAX_VALUE;
    static int dy[] = {-1, 0, 1, 0};
    static int dx[] = {0, 1, 0, -1};
    static int[][] val;
    static boolean[][][] isVisited;
    public int solution(int[][] board) {
        N = board.length;
        isVisited = new boolean[N][N][4];
        BFS(0, 0, board);
        return answer;
    }
    static void BFS(int x, int y, int[][] map) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x, y, -1, 0));
        isVisited[y][x][0] = isVisited[y][x][1] = isVisited[y][x][2] = isVisited[y][x][3] =true;

        while (!q.isEmpty()) {
            Node cur = q.poll();
            if (cur.x == N - 1 && cur.y == N - 1) {
                answer = Math.min(answer, cur.value);
            }
            for (int i = 0 ; i < 4 ; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx >= 0 && nx < N && ny >= 0 && ny < N && map[ny][nx] != 1) {
                    int new_cost = (cur.dir == -1 || cur.dir == i) ? cur.value + 100 : cur.value + 600;
                    if (!isVisited[ny][nx][i] || new_cost <= map[ny][nx]) {
                        q.offer(new Node(nx, ny, i, new_cost));
                        isVisited[ny][nx][i] = true;
                        map[ny][nx] = new_cost;
                    }
                }
            }
        }
    } 
}
class Node {
    int x;
    int y;
    int dir;
    int value;
    Node(int x, int y, int dir, int value) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.value = value;
    }
}