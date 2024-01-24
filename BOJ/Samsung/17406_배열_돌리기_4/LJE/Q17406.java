import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Q17406 {
    static int N, M, K;
    static int[][] graph; // range : 1 ~ N / 1 ~ M
    static int[][] cal;
    static int answer = Integer.MAX_VALUE;
    static List<int[]> list = new ArrayList<>(); // 순열을 저장할 리스트

    public static void main(String[] args) throws IOException{
        // 입력값 받기
        initInput();

        // 순열 만들기
        permutation(makeArray(),0 , K, K);

        int[][] curGraph = new int[N + 1][M + 1];
        for (int[] cand : list) {
            // 기존의 원래 배열 복사
            for (int i = 1; i < N + 1; i++) {
                curGraph[i] = graph[i].clone();
            }
            for (int i = 0; i < cand.length; i++) {
                curGraph = rotate(cal[cand[i] - 1], curGraph);
            }

            answer = Math.min(answer, getAnswer(curGraph));
        }

        System.out.println(answer);
    }

    // 입력 받기
    static void initInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new int[N + 1][M + 1];
        for (int y = 1; y < N + 1; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 1; x < M + 1; x++) {
                graph[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        cal = new int[K][3];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            cal[i][0] = Integer.parseInt(st.nextToken());
            cal[i][1] = Integer.parseInt(st.nextToken());
            cal[i][2] = Integer.parseInt(st.nextToken());
        }
    }

    // 1부터 K까지 순서가 있는 배열 만들기
    static int[] makeArray() {
        int[] result = new int[K];
        for (int i = 0; i < K; i++) {
            result[i] = i + 1;
        }
        return result;
    }

    // 순열
    static void permutation(int[] arr, int depth, int n, int r) {
        if (depth == r) {
            list.add(arr.clone());
            return;
        }

        for (int i = depth; i < n; i++) {
            swap(arr, depth, i);
            permutation(arr, depth + 1, n, r);
            swap(arr, depth, i);
        }
    }

    static void swap(int[] arr, int depth, int i) {
        int temp = arr[depth];
        arr[depth] = arr[i];
        arr[i] = temp;
    }

    static int[][] rotate(int[] order, int[][] prev) {
        int[][] clone = new int[N + 1][M + 1];
        for (int i = 1; i < N + 1; i++) {
            clone[i] = prev[i].clone();
        }

        // 회전 시키기
        int sy = order[0] - order[2];
        int sx = order[1] - order[2];
        int w = 2 * order[2];

        while (true) {
            // Base Case
            if (w <= 1) {
                break;
            }

            // 상단
            for (int i = sx + w; i > sx; i--) {
                clone[sy][i] = prev[sy][i - 1];
            }

            // 우측
            for (int i = sy + w; i > sy; i--) {
                clone[i][sx + w] = prev[i - 1][sx + w];
            }

            // 하단
            for (int i = sx; i < sx + w; i++) {
                clone[sy + w][i] = prev[sy + w][i + 1];
            }

            // 좌측
            for (int i = sy; i < sy + w; i++) {
                clone[i][sx] = prev[i + 1][sx];
            }

            sy++;
            sx++;
            w -= 2;
        }

        return clone;
    }

    // 결과값 구하기
    static int getAnswer(int[][] map) {
        int result = Integer.MAX_VALUE;
        for (int i = 1; i < N + 1; i++) {
            result = Math.min(result, Arrays.stream(map[i]).sum());
        }

        return result;
    }

}