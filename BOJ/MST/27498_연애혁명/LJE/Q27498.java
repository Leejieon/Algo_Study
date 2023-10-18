import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q27498 {
    static int[] parents;
    static int N, M, answer, affections;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        initInput();
        makeMst();

        System.out.println(affections - answer);
    }

    static void initParents() {
        parents = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            parents[i] = i;
        }
    }

    static void initInput() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        answer = 0;
        affections = 0; // 전체 애정도(가중치)의 합

        initParents();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int student1 = Integer.parseInt(st.nextToken());
            int student2 = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            int isLove = Integer.parseInt(st.nextToken());

            affections += weight;

            prevUnion(student1, student2, weight, isLove);
        }
    }

    static void makeMst() {
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();

            if (!isSameParent(find(edge.student1), find(edge.student2))) {
                answer += edge.weight;
                union(edge.student1, edge.student2);
            }
        }
    }

    static void union(int n1, int n2) {
        n1 = find(n1);
        n2 = find(n2);

        if(n1 != n2)
            parents[n2] = n1;
    }

    static int find(int node) {
        if(parents[node] == node)
            return node;

        return parents[node] = find(parents[node]);
    }

    static void prevUnion(int n1, int n2, int weight, int check) {
        // 성사 여부가 1인 경우, 무조건 트리에 포함시켜야 한다.
        if(check == 1){
            union(n1, n2);
            answer += weight;
            return;
        }
        pq.add(new Edge(n1, n2, weight));
    }

    static boolean isSameParent(int n1, int n2) {
        return find(n1) == find(n2);
    }

    static class Edge implements Comparable<Edge> {
        int student1;
        int student2;
        int weight;

        public Edge(int student1, int student2, int weight) {
            this.student1 = student1;
            this.student2 = student2;
            this.weight = weight;
        }


        @Override
        public int compareTo(Edge o) {
            return o.weight - this.weight;
        }
    }
}
