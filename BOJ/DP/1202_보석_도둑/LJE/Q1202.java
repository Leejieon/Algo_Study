import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Q1202 {
    static int N, K;
    static int[] bags;
    static ArrayList<Jewel> list;
    static PriorityQueue<Integer> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 보석 정보 입력
        int M, V;
        list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            V = Integer.parseInt(st.nextToken());
            list.add(new Jewel(M, V));
        }
        Collections.sort(list, ((o1, o2) -> {
            if (o1.weight == o2.weight) {
                return o2.value - o1.value;
            }
            return o1.weight - o2.weight;
        }));

        // 가방 정보 입력
        bags = new int[K];
        for (int i = 0; i < K; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }
        // 가방의 무게를 기준으로 오름차순 정렬
        Arrays.sort(bags);

        // 가방 채워 나가기
        long result = 0;
        // 우선 순위 큐에는 가치를 기준으로 내림차순으로 정렬
        pq = new PriorityQueue<>(((o1, o2) -> o2 - o1));
        for (int i = 0, j = 0; i < K; i++) {
            while (j < N && list.get(j).weight <= bags[i]) {
                pq.offer(list.get(j).value);
                j++;
            }

            if (!pq.isEmpty()) {
                result += pq.poll();
            }
        }

        System.out.println(result);
    }

    static class Jewel {
        int weight;
        int value;

        public Jewel(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }
}