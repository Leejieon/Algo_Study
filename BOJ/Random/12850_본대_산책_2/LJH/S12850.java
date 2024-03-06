import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S12850 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    ;
    static StringTokenizer st;
    static int d;
    final static long mod = 1000000007;

    static long[][] graph = new long[8][8];

    public static void main(String[] args) throws IOException {
        input();
        makeGraph();
        graph = divide(graph, d);
        System.out.print(graph[0][0]);
    }

    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        d = Integer.parseInt(st.nextToken());

    }

    /*
    0: 정보과학관, 1: 전산관, 2: 미래관, 3: 신앙관, 4: 한경직기념관, 5: 진리관, 6: 형남공학관, 7:학생회관
     */
    public static void makeGraph() {
        graph[0][1] = graph[0][2] = 1;
        graph[1][0] = graph[1][2] = graph[1][3] = 1;
        graph[2][0] = graph[2][1] = graph[2][3] = graph[2][4] = 1;
        graph[3][1] = graph[3][2] = graph[3][4] = graph[3][5] = 1;
        graph[4][2] = graph[4][3] = graph[4][5] = graph[4][6] = 1;
        graph[5][7] = graph[5][3] = graph[5][4] = 1;
        graph[6][4] = graph[6][7] = 1;
        graph[7][5] = graph[7][6] = 1;
    }

    public static long[][] divide(long[][] ary, int n) {
        if (n == 1)
            return ary;
        if (n % 2 == 0) {
            long[][] temp = divide(ary, n / 2);
            return merge(temp, temp);
        } else {
            return merge(divide(ary, n - 1), ary);
        }
    }

    public static long[][] merge(long[][] ary1, long[][] ary2) {
        long[][] temp = new long[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < 8; k++) {
                    temp[i][j] = ((temp[i][j] + (ary1[i][k] * ary2[k][j])) % mod) % mod;
                }
            }
        }
        return temp;
    }
}