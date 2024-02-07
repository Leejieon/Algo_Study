import java.io.*;
import java.util.*;

class Q16946 {
    static int N, M;
    static int[][] graph, group;
    static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static HashMap<Integer, Integer> map = new HashMap<>();
    static int groupNum = 1; // 그룹의 번호

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new int[N][M];
        for (int y = 0; y < N; y++) {
            String row = br.readLine();
            for (int x = 0; x < M; x++) {
                graph[y][x] = row.charAt(x) - '0';
            }
        }

        // 0 그룹화 := map 에 그룹 번호와 그룹 크기 저장
        group = new int[N][M];
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                // (y, x)가 0이고 아직 그룹화 되지 않은 경우
                if (graph[y][x] == 0 && group[y][x] == 0) {
                    map.put(groupNum, bfs(y, x, groupNum));
                    groupNum++;
                }
            }
        }

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                if (graph[y][x] == 1) {
                    sb.append(getResult(y, x));
                } else {
                    sb.append(0);
                }
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static int bfs(int y, int x, int gNum) {
        int size = 1;
        Queue<Point> queue = new ArrayDeque<>();
        queue.offer(new Point(y, x));
        group[y][x] = gNum;

        while (!queue.isEmpty()) {
            Point point = queue.poll();

            for (int cand = 0; cand < dir.length; cand++) {
                int dy = point.y + dir[cand][0];
                int dx = point.x + dir[cand][1];

                if(isOutOfBound(dy, dx)) continue;
                if (graph[dy][dx] == 0 && group[dy][dx] == 0) {
                    queue.offer(new Point(dy, dx));
                    group[dy][dx] = gNum;
                    size++;
                }
            }
        }

        return size;
    }

    static boolean isOutOfBound(int y, int x) {
        return y < 0 || x < 0 || y >= N || x >= M;
    }

    static int getResult(int y, int x) {
        int sum = 1; // 자기 자신
        HashSet<Integer> set = new HashSet<>();

        for (int cand = 0; cand < dir.length; cand++) {
            int dy = y + dir[cand][0];
            int dx = x + dir[cand][1];

            if(isOutOfBound(dy, dx)) continue;
            if(group[dy][dx] == 0) continue; // 그룹에 해당하지 않는 경우 := 1인 경우
            // 벽이 아니고 갈 수 있는 경우
            if (graph[dy][dx] == 0) {
                set.add(group[dy][dx]);
            }
        }

        // set 에 포함된 그룹 번호에 해당하는 사이즈 더해주기
        for (int gNum : set) {
            sum += map.get(gNum);
        }

        return sum%10;
    }

    static class Point {
        int y, x;

        Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}