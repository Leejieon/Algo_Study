import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q1197 {
    static int[] parent;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        parent = new int[V + 1];
        for (int i = 1; i < V + 1; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            pq.offer(new Edge(v1, v2, weight));
        }

        int result = 0;
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();

            if (!isSameParent(find(edge.v1), find(edge.v2))) {
                result += edge.weight;
                union(edge.v1, edge.v2);
            }
        }

        System.out.println(result);
    }


    static void union(int v1, int v2) {
        v1 = find(v1);
        v2 = find(v2);

        if (v1 != v2) {
            parent[v2] = v1;
        }
    }

    static int find(int x) {
        if(parent[x] == x)
            return x;

        return parent[x] = find(parent[x]);
    }

    static boolean isSameParent(int v1, int v2) {
        return find(v1) == find(v2);
    }

    static class Edge implements Comparable<Edge>{
        int v1;
        int v2;
        int weight;

        Edge (int v1, int v2, int weight) {
            this.v1 = v1;
            this.v2 = v2;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }
}
