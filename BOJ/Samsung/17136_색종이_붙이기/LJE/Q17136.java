import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q17136 {
    static final int LEN = 10;
    static int answer;
    static int[] papers = {0, 5, 5, 5, 5, 5};
    static int[][] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        graph = new int[LEN][LEN];
        for (int y = 0; y < LEN; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < LEN; x++) {
                graph[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        answer = -1;
        backTracking(0, 0, 0);
        System.out.println(answer);

    }

    static void backTracking(int y, int x, int count) {
        // Base Case
        // 가로 끝에 도달하면 다음 줄로 내려가기
        if (x > 9) {
            backTracking(y + 1, 0, count);
            return;
        }
        // 마지막 줄까지 검사를 마친 경우
        if (y > 9) {
            if (answer == -1) {
                answer = count;
            } else if (answer > count) {
                answer = count;
            }
            return;
        }
        // 현재 위치가 종이를 놓을 수 없으면 가로로 한 칸 이동
        if (graph[y][x] == 0) {
            backTracking(y, x + 1, count);
            return;
        }

        // Recursive Case
        // 큰 종이부터 붙여 나아가야 최소로 덮을 수 있다.
        for (int size = 5; size > 0; size--) {
            // 보유한 색종이를 모두 사용한 경우
            if(papers[size] == 0) continue;
            // 색종이를 붙였을 때 범위를 벗어나는 경우
            if(y + size > LEN || x + size > LEN) continue;
            // 색종이로 채울 수 없는 경우
            if(!canFill(y, x, size)) continue;

            // 색종이로 채우기
            papers[size]--;
            fill(y, x, size, 0);
            backTracking(y, x + 1, count + 1);
            // 돌아와서 다시 1로 채우기
            papers[size]++;
            fill(y, x, size, 1);
        }
    }

    static boolean canFill(int y, int x, int size) {
        for (int row = y; row < y + size; row++) {
            for (int col = x; col < x + size; col++) {
                if (graph[row][col] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    static void fill(int y, int x, int size, int flag) {
        for (int row = y; row < y + size; row++) {
            for (int col = x; col < x + size; col++) {
                graph[row][col] = flag;
            }
        }
    }
}