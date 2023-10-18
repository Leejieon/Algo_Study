

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    //    static Map<Integer, List<Edge>> graph = new HashMap<>();
    static Queue<Edge> graph = new PriorityQueue<>(new Comparator<Edge>() {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.w - o2.w;
        }
    });
    static int N;
//    static boolean[] visit;
    static int[] parent;
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    static class Edge {
        int v1;
        int v2;
        int w;

        public Edge(int v1, int v2, int w) {
            this.v1 = v1;
            this.v2 = v2;
            this.w = w;
        }
    }

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
//            graph.put(i, new ArrayList<>());
            parent[i] = i;
        }
//        visit = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 1; j <= N; j++) {
                int w = Integer.parseInt(stringTokenizer.nextToken());
                if (i != j) {
                    graph.add(new Edge(i, j, w));
                }
            }
        }
//        prim();
        kruskal();
    }

    private static void kruskal() {
        int edgeCount = 0;
        long min = 0;
        while (edgeCount < N - 1) {
            Edge e = graph.poll();
            int p1 = find(e.v1);
            int p2 = find(e.v2);
            if (p1 != p2) {
                edgeCount++;
                min += (long) e.w;
                union(p1, p2);
            }
        }
        System.out.println(min);
    }

    private static void union(int n1, int n2) {
        if (n1 < n2) {
            parent[n2] = n1;
            return;
        }
        parent[n1] = n2;
    }

    private static int find(int n1) {
        if (n1 == parent[n1]) {
            return n1;
        }
        return parent[n1] = find(parent[n1]);
    }

//    private static void prim() {
//        long min = 0;
//        Queue<Edge> queue = new PriorityQueue<>((o1, o2) -> o1.w - o2.w);
//        queue.add(new Edge(1, 0));
//        while (!queue.isEmpty()) {
//            Edge cur = queue.poll();
//            if (!visit[cur.v1]) {
//                visit[cur.v1] = true;
//                min += (long) cur.w;
//                for (Edge e : graph.get(cur.v1)) {
//                    if (!visit[e.v1]) {
//                        queue.add(e);
//                    }
//                }
//            }
//        }
//        System.out.println(min);
//    }
}
