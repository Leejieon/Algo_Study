package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class BOJ16946 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static int N, M;
    static int[] dx = new int[]{0, 0, -1, 1};
    static int[] dy = new int[]{-1, 1, 0, 0};
    static List<Pos> walls = new ArrayList<>();
    static int[][] MAP;
    static int[][] ANSWER;

    static Map<Integer, Integer> componentMap = new HashMap<>();

    static class Pos {
        int x;
        int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

    public static void main(String[] args) throws IOException {
        int[] input = Arrays.stream(bf.readLine().split(" ")).mapToInt(s -> Integer.parseInt(s)).toArray();
        N = input[0];
        M = input[1];
        MAP = new int[N][M];
        ANSWER = new int[N][M];

        for (int i = 0; i < N; i++) {
            String line = bf.readLine();
            for (int j = 0; j < M; j++) {
                MAP[i][j] = Integer.parseInt(Character.toString(line.charAt(j)));
                if (MAP[i][j] == 1) {
                    walls.add(new Pos(i, j));
                }
            }
        }

        if (!walls.isEmpty()) {
            makeComponent();
            findMoveWay();
        }
        printAnswer();
    }

    private static void makeComponent() {
        int id = 2;
        boolean[][] visit = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (MAP[i][j] == 0 && !visit[i][j]) {
                    int size = getSizeByBfs(new Pos(i, j), id, visit);
                    componentMap.put(id, size);
                    id++;
                }
            }
        }

    }

    private static int getSizeByBfs(Pos pos, int id, boolean[][] visit) {
        Queue<Pos> queue = new LinkedList<>();
        queue.add(pos);
        visit[pos.x][pos.y] = true;
        int size = 0;
        while (!queue.isEmpty()) {
            Pos cur = queue.poll();
            MAP[cur.x][cur.y] = id;
            size += 1;
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx < 0 || ny < 0 || ny >= M || nx >= N || visit[nx][ny] || MAP[nx][ny] == 1) {
                    continue;
                }
                visit[nx][ny] = true;
                queue.add(new Pos(nx, ny));
            }
        }
        return size;
    }

    private static void printAnswer() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(ANSWER[i][j]);
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    private static void findMoveWay() {
        for (Pos wall : walls) {
            ANSWER[wall.x][wall.y] = find(wall) % 10;
        }
    }

    private static int find(Pos wall) {
        Set<Integer> adjacentComponents = new HashSet<>();
        int moveCnt = 1;

        for (int i = 0; i < 4; i++) {
            int nx = wall.x + dx[i];
            int ny = wall.y + dy[i];
            if (nx < 0 || ny < 0 || ny >= M || nx >= N || MAP[nx][ny] == 1) {
                continue;
            }
            if (adjacentComponents.add(MAP[nx][ny])) {
                moveCnt += componentMap.get(MAP[nx][ny]);
            }

        }
        return moveCnt;
    }

}
