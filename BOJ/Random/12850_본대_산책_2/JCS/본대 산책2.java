

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static long[][] graph = new long[8][8];
    final static long div = 1_000_000_007;
    static int N;


    public static void main(String[] args) throws IOException {
        makeGraph();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        long[][] result = pow(N);

        System.out.println(result[0][0]);
    }

    private static void makeGraph() {
        graph[0][1] = graph[0][2] = 1;
        graph[1][0] = graph[1][2] = graph[1][3] = 1;
        graph[2][0] = graph[2][1] = graph[2][3] = graph[2][4] = 1;
        graph[3][1] = graph[3][2] = graph[3][4] = graph[3][5] = 1;
        graph[4][2] = graph[4][3] = graph[4][5] = graph[4][7] = 1;
        graph[5][3] = graph[5][4] = graph[5][6] = 1;
        graph[6][5] = graph[6][7] = 1;
        graph[7][4] = graph[7][6] = 1;
    }

    public static long[][] pow(int n) {
        if (n == 1) {
            return graph;
        }

        long[][] temp = pow(n / 2);
        temp = multiply(temp, temp);
        if (n % 2 == 1) {
            temp = multiply(temp, graph);
        }
        return temp;
    }

    public static long[][] multiply(long[][] a, long[][] b) {
        long[][] result = new long[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < 8; k++) {
                    result[i][j] = (result[i][j] + a[i][k] * b[k][j]) % div;
                }
            }
        }
        return result;
    }
}
