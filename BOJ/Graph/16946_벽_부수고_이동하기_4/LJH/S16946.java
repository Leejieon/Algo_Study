import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {

    static BufferedReader br;
    static StringTokenizer st;
    static int n, m;
    static int[][] graph;
    static HashMap<Integer, Integer> mark = new HashMap<Integer, Integer>();
    static int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static Queue<Point> q = new LinkedList<Point>();

    public static void main(String[] args) throws IOException {
        input();
        groupMapping();
        solution();
    }

    public static void input() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new int[n][m];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                graph[i][j] = str.charAt(j) - '0';
                if (graph[i][j] == 0) {
                    q.add(new Point(i, j));
                }
            }
        }

    }

    public static void solution() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (graph[i][j] == 1) {
                    sb.append(String.valueOf(bfs(i, j)));
                } else {
                    sb.append("0");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    public static void groupMapping() {
        int cnt = 2;
        while (!q.isEmpty()) {
            Point now = q.poll();
            if (graph[now.x][now.y] != 0) {
                continue;
            }
            mark.put(cnt, makeGroup(now.x, now.y, cnt));
            cnt++;
        }
    }

    public static int makeGroup(int r, int c, int group) {
        int cnt = 0;
        Queue<Point> temp = new LinkedList<Point>();
        temp.add(new Point(r, c));
        while (!temp.isEmpty()) {
            Point cur = temp.poll();
            if (graph[cur.x][cur.y] != 0)
                continue;
            graph[cur.x][cur.y] = group;
            cnt++;
            for (int d = 0; d < 4; d++) {
                int mx = cur.x + dir[d][0];
                int my = cur.y + dir[d][1];
                if (isInGraph(mx, my) || graph[mx][my] != 0) { //
                    continue;
                }
                temp.add(new Point(mx, my));
            }
        }
        return cnt;
    }

    public static int bfs(int r, int c) {
        int cnt = 1;
        HashSet<Integer> s = new HashSet<>();
        for (int d = 0; d < 4; d++) {
            int mx = r + dir[d][0];
            int my = c + dir[d][1];
            if (isInGraph(mx, my) || graph[mx][my] == 1 || s.contains(graph[mx][my])) {
                continue;
            }
            cnt += mark.get(graph[mx][my]);
            s.add(graph[mx][my]);
        }
        return cnt % 10;
    }

    public static boolean isInGraph(int x, int y) {
        if (x >= n || x < 0 || y >= m || y < 0)
            return true;
        return false;
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
