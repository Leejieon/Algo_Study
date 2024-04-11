import java.util.*;

class Solution {
    static int[] inDegrees = new int[1_000_001];
    static int N = -1; // 전체 노드의 개수
    static int startNode, cycle; // 생성한 정점, 사이클의 개수
    static boolean[] visited;
    static ArrayList<Integer>[] graph = new ArrayList[1_000_001];

    public int[] solution(int[][] edges) {
        // [생성한 정점의 번호, 도넛 모양 그래프의 수, 막대 모양 그래프의 수,  8자 모양 그래프의 수]
        int[] answer = new int[4];

        for(int i = 0; i < 1_000_001; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];

            N = Math.max(N, Math.max(from, to));
            graph[from].add(to);
            inDegrees[to]++;
        }

        // 시작점
        startNode = findStartNode();
        answer[0] = startNode;

        // 시작점과 연결된 노드 순차 탐색
        for(int start : graph[startNode]) {
            visited = new boolean[N + 1];
            cycle = 0;
            countCycle(start);
            switch(cycle) {
                case 0:
                    answer[2]++;
                    break;
                case 1:
                    answer[1]++;
                    break;
                default:
                    answer[3]++;
                    break;
            }
        }

        return answer;
    }

    static int findStartNode() {
        for(int i = 1; i <= N; i++) {
            if(inDegrees[i] == 0 && graph[i].size() > 1) {
                return i;
            }
        }
        return -1;
    }

    static void countCycle(int node) {
        // Base Case
        if(visited[node]) {
            cycle++;
            return;
        }
        if(graph[node] == null) {
            return;
        }

        // Recursive Case
        visited[node] = true;
        for(int next : graph[node]) {
            countCycle(next);
        }
    }
}