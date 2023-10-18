

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static Queue<Edge> relation = new PriorityQueue<>(new Comparator<Edge>() {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o2.w - o1.w;
        }
    });
    static int LOVE_SCORE = 0;
    static int EDGE_COUNT = 0;
    static int[] parent;
    static final int ALREADY_FALL_IN_LOVE = 1;
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    static class Edge  {
        int v1, v2;
        int w;

        public Edge(int v1, int v2, int w) {
            this.v1 = v1;
            this.v2 = v2;
            this.w = w;
        }

    }

    public static void main(String[] args) throws IOException {
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < M; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int a = Integer.parseInt(stringTokenizer.nextToken());
            int b = Integer.parseInt(stringTokenizer.nextToken());
            int c = Integer.parseInt(stringTokenizer.nextToken());
            int d = Integer.parseInt(stringTokenizer.nextToken());
            if (d == ALREADY_FALL_IN_LOVE) {
                union(find(a), find(b));
                EDGE_COUNT++;
                continue;
            }
            relation.add(new Edge(a, b, c));
            LOVE_SCORE += c;
        }
        solution();
    }

    private static void solution() {
        while (EDGE_COUNT < N - 1) {
            Edge curRelation = relation.poll();
            int p1 = find(curRelation.v1);
            int p2 = find(curRelation.v2);
            // k각 관계를 만들지 않는다면
            if (p1 != p2) {
                LOVE_SCORE -= curRelation.w;
                union(p1,p2);
                EDGE_COUNT++;
            }
        }
        System.out.println(LOVE_SCORE);
    }

    static private int find(int n1) {
        if (n1 == parent[n1]) {
            return n1;
        }
        return parent[n1] = find(parent[n1]);
    }

    static private void union(int n1, int n2) {
        if (n1 < n2) {
            parent[n2] = n1;
            return;
        }
        parent[n1] = n2;
    }
}
