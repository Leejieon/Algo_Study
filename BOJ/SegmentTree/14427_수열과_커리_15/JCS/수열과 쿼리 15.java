import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static Integer N, M;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PriorityQueue<Data> pq = new PriorityQueue<>();
    static int[] datas;

    static class Data implements Comparable<Data> {
        int idx;
        int value;

        public Data(int idx, int value) {
            this.idx = idx;
            this.value = value;
        }

        @Override
        public int compareTo(Data o) {
            if (Integer.compare(this.value, o.value) == 0) {
                return Integer.compare(this.idx, o.idx);
            }
            return Integer.compare(this.value, o.value);
        }
    }

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        try {
            N = Integer.parseInt(br.readLine());
            datas = new int[N + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                int value = Integer.parseInt(st.nextToken());
                pq.add(new Data(i, value));
                datas[i] = value;
            }
            M = Integer.parseInt(br.readLine());
            for (int i = 1; i <= M; i++) {
                String[] query = br.readLine().split(" ");
                if (query.length == 1) {
                    sb.append(doGet()).append("\n");
                    continue;
                }
                doChange(Integer.parseInt(query[1]), Integer.parseInt(query[2]));
            }
            System.out.print(sb.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static int doGet() {
        while (true) {
            Data poll = pq.peek();
            if (poll.value == datas[poll.idx]) {
                return poll.idx;
            }
            pq.remove();
            pq.add(new Data(poll.idx, datas[poll.idx]));
        }
    }

    private static void doChange(int idx, int value) {
        datas[idx] = value;
        pq.add(new Data(idx, value));
    }
}
