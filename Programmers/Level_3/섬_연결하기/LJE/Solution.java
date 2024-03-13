package Level_3.섬_연결하기.LJE;

import java.util.*;

class Solution {
    static int[] parents;

    public int solution(int n, int[][] costs) {
        PriorityQueue<Edge> edges = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        for(int[] info : costs) {
            int from = info[0];
            int to = info[1];
            int cost =  info[2];

            edges.offer(new Edge(from, to, cost));
        }
        parents = new int[n];
        makeSet(n);
        return kruskal(edges);
    }

    static int kruskal(PriorityQueue<Edge> edges) {
        int totalCost = 0;

        while(!edges.isEmpty()) {
            Edge cur = edges.poll();
            int from = cur.from;
            int to = cur.to;
            int cost = cur.cost;

            if(find(from) != find(to)) {
                union(from, to);
                totalCost += cost;
            }
        }

        return totalCost;
    }

    static void makeSet(int n)  {
        for(int i = 0; i < n; i++) {
            parents[i] = i;
        }
    }

    static void union(int node1, int node2) {
        node1 = find(node1);
        node2 = find(node2);

        if(node1 < node2) {
            parents[node2] = node1;
            return;
        }
        parents[node1] = node2;
    }

    static int find(int node) {
        if(parents[node] == node)
            return node;
        return parents[node] = find(parents[node]);
    }

    static class Edge {
        int from;
        int to;
        int cost;

        Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }
}