import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q16398 {
    static int N;
    static List<List<Edge>> graph = new ArrayList<>();
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        initInput();

        System.out.println(prim());
    }

    static void initInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        initGraph();
        visited = new boolean[N];

        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++) {
                int weight = Integer.parseInt(st.nextToken());
                graph.get(y).add(new Edge(x, weight));
            }
        }


    }

    static long prim() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        long result = 0;
        int count = 0;

        pq.add(new Edge(0, 0));

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();

            if(visited[edge.node])
                continue;

            visited[edge.node] = true;
            result += edge.weight;

            for (Edge next : graph.get(edge.node)) {
                if(!visited[next.node])
                    pq.add(next);
            }

            // 모든 정점을 방문했을 경우, 종료
            if(++count == N)
                break;
        }

        return result;
    }

    static void initGraph() {
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }
    }

    static class Edge implements Comparable<Edge> {
        int node;
        int weight;

        Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }
}
