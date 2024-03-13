import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Q16566 {
    static int N, M, K;
    static int[] cards;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        cards = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(cards);

        visited = new boolean[N + 1];
        ArrayList<Integer> result = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int idx = binarySearch(0, M - 1, Integer.parseInt(st.nextToken()));
            while (visited[cards[idx]]){
                idx++;
            }
            visited[cards[idx]] = true;
            result.add(cards[idx]);
        }

        for (Integer i : result) {
            sb.append(i).append("\n");
        }
        System.out.print(sb);
    }

    static int binarySearch(int low, int high, int target) {
        while (low < high) {
            int mid = (low + high) / 2;
            if (target == cards[mid]) {
                return mid + 1;
            } else if (target > cards[mid]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return high;
    }
}