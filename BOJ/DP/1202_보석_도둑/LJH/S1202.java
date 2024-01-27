import java.io.*;
import java.util.*;

public class S1202 {

    static BufferedReader br;
    static StringTokenizer st;
    static int n, k;
    static long answer = 0;

    static Integer[] bags;
    static PriorityQueue<Jewelry> jewelries = new PriorityQueue<Jewelry>();

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        input();
        sortBags();
        insertJewelry();
        System.out.print(answer);
    }

    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); //보석 개수
        k = Integer.parseInt(st.nextToken()); //가방 개수
        bags = new Integer[k];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            jewelries.add(new Jewelry(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))); // 무게 정보)
        }
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            bags[i] = Integer.parseInt(st.nextToken());
        }
    }

    public static void sortBags() {
        Arrays.sort(bags, (o1, o2) -> o2 - o1);
    }

    public static void insertJewelry() {
        PriorityQueue<Jewelry> queue = new PriorityQueue<>(new Comparator<Jewelry>() {
            @Override
            public int compare(Jewelry o1, Jewelry o2) {
                return o2.v - o1.v;
            }
        });
        for (int i = k - 1; i >= 0; i--) { //가방순으로 돌림. 가방은 무게순으로 내림차순.

            while (!jewelries.isEmpty() && bags[i] >= jewelries.peek().w) {
                queue.add(jewelries.poll());
            }

            if (!queue.isEmpty()) {
                answer += queue.poll().v;
            }
        }
    }

    static class Jewelry implements Comparable<Jewelry> {
        int v;
        int w;

        public Jewelry(int w, int v) {
            this.w = w;
            this.v = v;
        }

        @Override
        public int compareTo(Jewelry o) {
            if (this.w == o.w) return o.v - this.v;
            return this.w - o.w;
        }
    }
}