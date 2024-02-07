package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BOJ4386 {
    static class Star {
        double x;
        double y;

        public Star(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double getDistTo(Star star) {
            double dx = Math.abs(star.x - this.x);
            double dy = Math.abs(star.y - this.y);
            return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
        }
    }

    static class Edge implements Comparable<Edge> {
        int from;
        int to;
        double weigh;

        public Edge(int from, int to, double weigh) {
            this.from = from;
            this.to = to;
            this.weigh = weigh;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.weigh, o.weigh);
        }
    }

    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static List<Star> stars = new ArrayList<>();
    static List<Edge> edges = new ArrayList<>();
    static int[] parent;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bf.readLine());
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            double[] pos = Arrays.stream(bf.readLine().split(" ")).mapToDouble(s -> Double.parseDouble(s)).toArray();
            stars.add(new Star(pos[0], pos[1]));
            parent[i] = i;
        }

        for (int i = 0; i < stars.size(); i++) {
            Star from = stars.get(i);
            for (int j = i + 1; j < stars.size(); j++) {
                Star to = stars.get(j);
                edges.add(new Edge(i, j, from.getDistTo(to)));
            }
        }

        Collections.sort(edges);

        int connectCount = 0;
        double minWeight = 0;
        for (Edge e : edges) {
            if (!isConnected(e.from, e.to)) {
                connect(e.from, e.to);
                connectCount++;
                minWeight += e.weigh;
            }
            if (connectCount == N - 1) {
                break;
            }
        }
        System.out.println(String.format("%.2f", minWeight));
    }

    private static void connect(int from, int to) {
        int p1 = find(from);
        int p2 = find(to);

        if (p1 <= p2) {
            parent[p2] = p1;
            return;
        }
        parent[p1] = p2;
    }

    private static boolean isConnected(int from, int to) {
        return find(from) == find(to);
    }

    private static int find(int id) {
        if (parent[id] == id) {
            return id;
        }
        parent[id] = find(parent[id]);
        return parent[id];
    }
}


