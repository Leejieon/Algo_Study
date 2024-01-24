import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S17406 {

    static int n;
    static int m;
    static int k;
    static int r;
    static int c;
    static int s;
    static BufferedReader br;
    static StringTokenizer st;
    static int[][] graph;
    static Rotate[] rotate;
    static int[] comb;
    static int[][] temp;
    static boolean[] visit;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        input();
        combination(k, 0, 0);
        System.out.print(answer);
    }

    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        graph = new int[n][m];
        rotate = new Rotate[k];
        temp = new int[n][m];
        visit = new boolean[k];
        comb = new int[k];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            r = Integer.parseInt(st.nextToken()) - 1;
            c = Integer.parseInt(st.nextToken()) - 1;
            s = Integer.parseInt(st.nextToken());
            rotate[i] = new Rotate(r, c, s);
        }
    }

    public static void combination(int limit, int count, int cur) {
        if (limit == count) { // 조합 뽑앗을때 돌리기
            doRotate();
            return;
        }
        for (int i = 0; i < k; i++) {
            if (!visit[i]) {
                comb[count] = i;
                visit[i] = true;
                combination(limit, count + 1, i);
                visit[i]= false;
            }
        }
    }


    public static void doRotate() {
        copy();
        for (int tc = 0; tc < k; tc++) {
            Rotate now = rotate[comb[tc]]; // 현재 회전 정보
            int r = now.r;
            int c = now.c;
            int S = now.s;
            for (int s = 1; s <= S; s++) {
                // 위
                int upTmp = temp[r - s][c + s];
                for (int y = c + s; y > c - s; y--) {
                    temp[r - s][y] = temp[r - s][y - 1];
                }
                // 오른쪽
                int rightTmp = temp[r + s][c + s];
                for (int x = r + s; x > r - s; x--) {
                    temp[x][c + s] = temp[x - 1][c + s];
                }
                temp[r - s + 1][c + s] = upTmp;
                // 아래
                int leftTmp = temp[r + s][c - s];
                for (int y = c - s; y < c + s; y++) {
                    temp[r + s][y] = temp[r + s][y + 1];
                }
                temp[r + s][c + s - 1] = rightTmp;
                // 왼쪽
                for (int x = r - s; x < r + s; x++) {
                    temp[x][c - s] = temp[x + 1][c - s];
                }
                temp[r + s - 1][c - s] = leftTmp;
            }
        }
        getSum(temp);
    }
    public static void copy() {
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                temp[i][j] = graph[i][j];
            }
        }
    }

    public static void getSum(int [][] temp) {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum = 0;
            for (int j = 0; j < m; j++) {
                sum += temp[i][j];
            }
            answer = Math.min(answer, sum);
        }
    }

    static class Rotate {
        int r;
        int s;
        int c;

        public Rotate(int r, int c, int s) {
            this.r = r;
            this.c = c;
            this.s = s;
        }
    }

    static class Point {
        int x;
        int y;
        int v;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}