class Solution {
    static final int MAX_VALUE = 10000001;
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;
        int [][] graph = new int[n+1][n+1];
        // 그래프 초기화
        for(int i=0;i<=n;i++) {
            for(int j=0;j<=n;j++) {
                if(i==j) {
                    graph[i][j] = 0;
                } else {
                    graph[i][j] = MAX_VALUE;
                }
            }
        }
        // 초기 그래프
        for(int i =0; i<fares.length; i++) {
            graph[fares[i][0]][fares[i][1]] = fares[i][2];
            graph[fares[i][1]][fares[i][0]] = fares[i][2];
        }
        // floyd-warshall 알고리즘 - 모든 정점 간 최단 거리 
        for(int i =1 ; i<=n ;i++) {
            for(int j=1; j<=n; j++) {
                for(int k=1; k<=n; k++) {
                    graph[j][k] = Math.min(graph[j][k], graph[j][i] + graph[i][k]);
                }
            }
        }
        
        for(int i =1 ;i<=n ;i++) {
            answer = Math.min(graph[s][i] + graph[i][a] + graph[i][b] ,answer);
        }
        return answer;
    }
}