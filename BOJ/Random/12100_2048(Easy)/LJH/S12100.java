import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S12100 {

    static BufferedReader br;
    static StringTokenizer st;
    static int n;
    static int[][] graph = new int[20][20];
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        input();
        int[][] temp = new int[20][20];
        for (int i = 0; i < n; i++) {
            temp[i] = graph[i].clone();
        }
        solution(0, temp);
        System.out.println(answer);
    }

    public static void input() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

    }

    public static void solution(int cnt, int[][] ary) {
        if (cnt == 5) { // 재귀 끝
            answer = Math.max(answer, findMax(ary));
            return;
        }
        for (int d = 1; d <= 4; d++) { // 상, 하, 좌, 우
            solution(cnt + 1, moveGraph(d, ary));
        }
    }

    public static int[][] moveGraph(int dir, int[][] ary) {
        int[][] moved = new int[n][n];
        for (int i = 0; i < n; i++) {
            moved[i] = ary[i].clone();
        }
        switch (dir) {
            case 1: {
                up(moved);
                break;
            }
            case 2: {
                down(moved);
                break;
            }
            case 3: {
                left(moved);
                break;
            }
            case 4: {
                right(moved);
                break;
            }
        }
        return moved;
    }

    public static int[][] up(int[][] ary) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) { //위에서 부터 합쳐짐.
                for (int k = j + 1; k < n; k++) {
                    if (ary[j][i] == 0) {
                        ary[j][i] = ary[k][i]; //위로 땡기고
                        ary[k][i] = 0; //땡겨진 칸은 비워줌.
                    } else if (ary[j][i] == ary[k][i] & isCorrectRow(j, k, i, ary)) { // 합칠려면 사이에 블럭이 있으면 안됨.
                        ary[j][i] *= 2;
                        ary[k][i] = 0;
                        break;
                    }
                }
            }
        }
        return ary;
    }

    public static int[][] down(int[][] ary) {
        for (int i = 0; i < n; i++) {
            for (int j = n - 1; j >= 0; j--) { // 아래에서 부터 합쳐짐.
                for (int k = j - 1; k >= 0; k--) {
                    if (ary[j][i] == 0) {
                        ary[j][i] = ary[k][i]; // 아래로 땡기고
                        ary[k][i] = 0; //땡겨진 칸은 비워줌.
                    } else if (ary[j][i] == ary[k][i] && isCorrectRow(k, j, i, ary)) { // 합칠려면 사이에 블럭이 있으면 안됨.
                        ary[j][i] *= 2;
                        ary[k][i] = 0;
                        break;
                    }
                }
            }
        }
        return ary;
    }

    public static int[][] left(int[][] ary) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) { // 왼쪽에서 부터 합쳐짐
                for (int k = j + 1; k < n; k++) {
                    if (ary[i][j] == 0) {
                        ary[i][j] = ary[i][k]; // 왼쪽으로 땡기고
                        ary[i][k] = 0; //땡겨진 칸은 비워줌.
                    } else if (ary[i][j] == ary[i][k] && isCorrectCol(j, k, i, ary)) { // 합칠려면 사이에 블럭이 있으면 안됨.
                        ary[i][j] *= 2;
                        ary[i][k] = 0;
                        break;
                    }
                }
            }
        }
        return ary;
    }

    public static int[][] right(int[][] ary) {
        for (int i = 0; i < n; i++) {
            for (int j = n - 1; j >= 0; j--) { // 아래에서부터 합쳐짐
                for (int k = j - 1; k >= 0; k--) {
                    if (ary[i][j] == 0) {
                        ary[i][j] = ary[i][k]; // 아래로 땡기고
                        ary[i][k] = 0; //땡겨진 칸은 비워줌.
                    } else if (ary[i][j] == ary[i][k] && isCorrectCol(k, j, i, ary)) { // 합칠려면 사이에 블럭이 있으면 안됨.
                        ary[i][j] *= 2;
                        ary[i][k] = 0;
                        break;
                    }
                }
            }
        }
        return ary;
    }

    public static boolean isCorrectRow(int r1, int r2, int c, int[][] ary) {
        for (int i = r1 + 1; i < r2; i++) { // r1~r2 사이에 있는지 확인
            if (ary[i][c] != 0) // 사이에 잇다면 합칠 수 없음.
                return false;
        }
        return true;
    }

    public static boolean isCorrectCol(int c1, int c2, int r, int[][] ary) {
        for (int i = c1 + 1; i < c2; i++) { // c1~c2 사이에 있는지 확인
            if (ary[r][i] != 0) // 사이에 잇다면 합칠 수 없음.
                return false;
        }
        return true;
    }

    public static int findMax(int[][] ary) {
        int m = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                m = Math.max(m, ary[i][j]);
            }
        }
        return m;
    }
}
