import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class S2342 {

    static BufferedReader br;
    static StringTokenizer st;
    static int max_value = 100000 * 4;
    static ArrayList<Integer> ary;
    static int answer = max_value;
    static int[][][] dp;
    static int size;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        ary = new ArrayList<Integer>();
        ary.add(-1);
        input();
        run();
        System.out.print(answer);
    }

    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        int op = 0;
        while (st.hasMoreTokens()) {
            op = Integer.parseInt(st.nextToken());
            if (op == 0)
                break;
            ary.add(op);
        }
        size = ary.size();
        dp = new int[5][5][size]; // dp 배열 : 왼쪽발, 오른쪽발, 입력받은 명령의 수
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < size; k++) {
                    dp[i][j][k] = max_value;
                }
            }
        }
    }

    public static void run() {
        dp[0][0][0] = 0;
        for (int idx = 1; idx < size; idx++) {
            int op = ary.get(idx); // 옮길 발의 위치.
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    dp[i][op][idx] = Math.min(dp[i][op][idx], dp[i][j][idx - 1] + moveCost(j, op));
                    dp[op][j][idx] = Math.min(dp[op][j][idx], dp[i][j][idx - 1] + moveCost(i, op));
                }
            }
        }
        int op = ary.get(size - 1);
        for (int i = 0; i < 5; i++) {
            answer = Math.min(answer, dp[op][i][size - 1]);
            answer = Math.min(answer, dp[i][op][size - 1]);
        }
    }

    public static int moveCost(int pastOp, int nowOp) {
        if (pastOp == 0) { // 0에서 뻗어나가면 비용은 2
            return 2;
        } else if (pastOp == nowOp) { //발판이 같다면 비용은 1
            return 1;
        } else if ((pastOp + nowOp) % 2 == 0) { // 반대방향은 비용이 4
            return 4;
        } else { // 인접방향은 비용이3
            return 3;
        }
    }
}