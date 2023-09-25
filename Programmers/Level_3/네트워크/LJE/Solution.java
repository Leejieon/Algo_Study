import java.util.*;

class Solution {
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static boolean[] visited;

    public int solution(int n, int[][] computers) {

        // graph 초기화
        for(int i = 0; i <= n; i++){
            graph.add(new ArrayList<>());
        }

        visited = new boolean[n + 1];
        Arrays.fill(visited, false);

        makeGraph(n, computers);

        int network = 0;
        for(int v = 1; v <= n; v++){
            if(!visited[v]){
                dfs(v);
                network++;
            }
        }

        return network;
    }

    static void makeGraph(int n, int[][] computers){

        for(int from = 0; from < n; from++){
            for(int to = 0; to < n; to++){

                if(from == to)
                    continue;

                // 연결되어 있는 경우
                if(computers[from][to] == 1){
                    graph.get(from + 1).add(to + 1);
                    graph.get(to + 1).add(from + 1);
                }
            }
        }
    }

    static void dfs(int v){
        visited[v] = true;

        for(int cand = 0; cand < graph.get(v).size(); cand++){
            int next = graph.get(v).get(cand);

            if(!visited[next]){
                dfs(next);
            }
        }
    }
}