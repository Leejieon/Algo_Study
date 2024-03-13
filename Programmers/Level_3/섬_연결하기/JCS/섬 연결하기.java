import java.util.*;

class Solution {
    static class Edge implements Comparable<Edge>{
        int from;
        int to;
        int w;
        public Edge(int from, int to, int w) {
            this.from = from;
            this.to = to;
            this.w = w;
        }
        public int compareTo(Edge e) {
            return Integer.compare(this.w, e.w);
        }
    }
    static int [] parent;
    
    public int solution(int n, int[][] costs) {
        int answer = 0;
        parent = new int[n+1];
        for(int i=0; i<=n; i++) {
            parent[i] = i;
        }
        List<Edge> edges = new ArrayList<>();
        for(int [] cost : costs) {
            edges.add(new Edge(cost[0], cost[1], cost[2]));
        }
        Collections.sort(edges);
        int cnt = 0;
        while(cnt < n-1) {
            for(Edge e : edges) {
                int p1 = find(e.from);
                int p2 = find(e.to);
                if(p1 != p2) {
                    cnt ++;
                    answer += e.w;
                    union(p1,p2);
                }
            }
        }
        
        return answer;
    }
    
    public int find(int v) {
        if(parent[v] == v) {
            return parent[v];
        }
        return parent[v] = find(parent[v]);
    }
    
    public void union(int p1, int p2) {
        if(p1<p2) {
            parent[p2] = p1;
            return;
        }
        parent[p1] = p2;
    }
}