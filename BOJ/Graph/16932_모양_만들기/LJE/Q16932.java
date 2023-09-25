import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q16932 {
    static int N, M;
    static int[][] graph;
    static Queue<Point> queue = new LinkedList<>();
    static Map<Integer, Integer> map = new HashMap<>();
    static boolean[][] visited;
    static int[][] dn = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new int[N][M];
        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < M; x++) {
                graph[y][x] = Integer.parseInt(st.nextToken());
                if (graph[y][x] == 0) {
                    queue.add(new Point(y, x));
                }
            }
        }

        visited = new boolean[N][M];

        int id = 1;
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                if (graph[y][x] != 0 && !visited[y][x]) {
                    int size = dfs(y, x, id);
                    map.put(id++, size);
                }
            }
        }

        int result = Integer.MIN_VALUE;
        Set<Integer> set = new HashSet<>();
        while (!queue.isEmpty()) {
            Point point = queue.poll();

            int value = 1;
            for (int cand = 0; cand < 4; cand++) {
                int dy = point.y + dn[cand][0];
                int dx = point.x + dn[cand][1];

                if(isOutOfBound(dy, dx)) continue;
                if(graph[dy][dx] == 0) continue;

                if(set.add(graph[dy][dx]))
                    value += map.get(graph[dy][dx]);
            }

            result = Math.max(result, value);
            set.clear();
        }

        System.out.println(result);
    }

    public static int dfs(int y, int x, int id) {
        visited[y][x] = true;
        graph[y][x] = id;
        int size = 1;

        for (int cand = 0; cand < 4; cand++) {
            int dy = y + dn[cand][0];
            int dx = x + dn[cand][1];

            if(isOutOfBound(dy, dx)) continue;
            if(visited[dy][dx]) continue;
            if(graph[dy][dx] == 0) continue;

            size += dfs(dy, dx, id);
        }

        return size;
    }

    static boolean isOutOfBound(int y, int x) {
        return y < 0 || x < 0 || y >= N || x >= M;
    }

    static class Point {
        int y;
        int x;

        Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
