
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int V, E;
    static Queue<Edge> graph = new PriorityQueue<>(new Comparator<Edge>() {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    });
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static int[] parent;
    static int MIN_COST = 0;

    static class Edge {
        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

    }

    public static void main(String[] args) throws IOException {
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        V = Integer.parseInt(stringTokenizer.nextToken());
        E = Integer.parseInt(stringTokenizer.nextToken());
        parent = new int[V + 1];
        for (int i = 0; i < E; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int from = Integer.parseInt(stringTokenizer.nextToken());
            int to = Integer.parseInt(stringTokenizer.nextToken());
            int weight = Integer.parseInt(stringTokenizer.nextToken());
            graph.add(new Edge(from, to, weight));
        }
        // parent 배열 초기화
        for (int i = 1; i <= V; i++) {
            parent[i] = i;
        }
        findMst();
    }

    private static void findMst() {
        int edgeCount = 0;

        while (edgeCount != V - 1) {
            Edge e = graph.poll();
            int p1 = find(e.from);
            int p2 = find(e.to);
            if (p1 != p2) {
                edgeCount++;
                MIN_COST += e.weight;
                union(p1, p2);
            }
        }
        System.out.println(MIN_COST);
    }

    private static int find(int n1) {
        if (parent[n1] == n1) {
            return n1;
        }
        return parent[n1] = find(parent[n1]);
    }

    private static void union(int n1, int n2) {
        if (n1 < n2) {
            parent[n2] = n1;
        } else {
            parent[n1] = n2;
        }
    }

}
