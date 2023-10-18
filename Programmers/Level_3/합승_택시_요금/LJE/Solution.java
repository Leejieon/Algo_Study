class Solution {
    static final int MAX_PRICE = 100001 * 200;
    static int[][] distance;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        initDistance(n);
        insertFares(fares);
        floydWarshall(n);

        return getResult(n, s, a, b);
    }

    static void initDistance(int n) {
        // distance 배열을 INF로 초기화
        // row == col 인 경우, 0으로 초기화
        distance = new int[n+1][n+1];
        for(int y = 1; y < n+1; y++) {
            for(int x = 1; x < n+1; x++) {
                if(y == x) {
                    distance[y][x] = 0;
                    continue;
                }
                distance[y][x] = MAX_PRICE;
            }
        }
    }

    static void insertFares(int[][] fares) {
        for(int cand = 0; cand < fares.length; cand++) {
            int node1 = fares[cand][0];
            int node2 = fares[cand][1];
            int price = fares[cand][2];

            distance[node1][node2] = price;
            distance[node2][node1] = price;
        }
    }

    static void floydWarshall(int n) {
        for(int k = 1; k < n + 1; k++) {
            for(int a = 1; a < n + 1; a++) {
                for(int b = 1; b < n + 1; b++) {
                    distance[a][b] = Math.min(distance[a][b],
                            distance[a][k] + distance[k][b]);
                }
            }
        }
    }

    static int getResult(int n, int s, int a, int b){
        int result = MAX_PRICE;

        for(int node = 1; node < n + 1; node++) {
            int newPrice = distance[s][node] + distance[node][a] + distance[node][b];
            result = Math.min(result, newPrice);
        }

        return result;
    }
}