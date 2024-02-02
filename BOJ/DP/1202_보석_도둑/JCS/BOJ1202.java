package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class BOJ1202 {

    static class Jewel {
        int value;
        int weight;

        public Jewel(int weight, int value) {
            this.value = value;
            this.weight = weight;
        }

    }

    static int N, K;
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static TreeMap<Integer, Integer> bags = new TreeMap<>(Comparator.reverseOrder());
    static PriorityQueue<Jewel> jewels = new PriorityQueue<>(new Comparator<Jewel>() {
        @Override
        public int compare(Jewel o1, Jewel o2) {
            if (o1.value == o2.value) {
                return o2.weight - o1.weight;
            }
            return o2.value - o1.value;
        }
    });

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        long sum = 0l;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bufferedReader.readLine());
            jewels.add(new Jewel(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        for (int i = 0; i < K; i++) {
            int bagWeight = Integer.parseInt(bufferedReader.readLine());
            bags.put(bagWeight, bags.getOrDefault(bagWeight, 0) + 1);
        }
        
        while (!jewels.isEmpty()) {
            Jewel j = jewels.poll();
            Integer key = bags.floorKey(j.weight);
            if (key == null) {
                continue;
            }
            if (bags.get(key) > 0) {
                bags.put(key, bags.get(key) - 1);
                sum += j.value;
                if (bags.get(key) <= 0) {
                    bags.remove(key);
                }
            }
        }
        System.out.println(sum);
    }
}
