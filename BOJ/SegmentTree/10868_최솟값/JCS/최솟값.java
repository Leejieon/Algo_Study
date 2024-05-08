import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static Integer N, M;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] arr;
    static int[] tree;

    public static void main(String[] args) throws IOException {
        int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = inputs[0];
        M = inputs[1];
        arr = new int[N+1];

        tree = new int[N*4];

        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        init(1, 1, N );

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            int[] range = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            sb.append(min(1, 1, N , range[0] , range[1] )).append("\n");
        }

        System.out.print(sb.toString());

    }

    private static int min(int node, int start, int end, int left, int right) {
        if (left > end || right < start) {
            return Integer.MAX_VALUE;
        }
        if (left <= start && end <= right) {
            return tree[node];
        }
        return Math.min(min(node * 2, start, (start + end) / 2, left, right),
                min(node * 2 + 1, (start + end) / 2 + 1, end, left, right));
    }

    private static int init(int node, int start, int end) {
        if (start == end) {
            return tree[node] = arr[start];
        } else {
            return tree[node] = Math.min(init(node * 2, start, (start + end) / 2),
                    init(node * 2 + 1, (start + end) / 2 + 1, end));
        }
    }
}
