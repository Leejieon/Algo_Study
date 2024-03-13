import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringJoiner;
import java.util.TreeMap;


public class Main {

    /**
     * 철수가 낸 카드마다 최대 400만개의 숫자에서 이분탐색으로 ceil숫자를 찾아야함 쿼리의 최대 갯수가 10000개 400만개 정렬 시간복잡도 N*log(N) 이분탐색의 시간복잡도 log(N) 그럼
     * 최종적으로 k*log(N) + N*log(N)인데 k*log(N) 에서 N이 400만이니까 이 상수를 조금 줄여야 할듯
     */
    static int N, M, K;
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static int[] parent;

    public static void main(String[] args) throws IOException {
        int[] inputs = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = inputs[0];
        M = inputs[1];
        K = inputs[2];
        parent = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }
        int[] numbers = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        assert numbers.length == M;
        int[] kNumbers = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(numbers);

        StringJoiner sj = new StringJoiner("\n");
        for (int num : kNumbers) {
            int find = findByBinarySearch(numbers, num, 0, numbers.length - 1);
            sj.add(Integer.toString(find));

        }
        System.out.println(sj);

    }

    public static int findByBinarySearch(int[] numbers, int target, int left, int right) {
        while (left < right) {
            int mid = (left + right) / 2;

            if (find(numbers[mid]) <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        int findNum = find(numbers[right]);
        union(right == 0 ? 0 : numbers[right - 1], numbers[right]);
        return findNum;

    }

    public static int find(int num) {
        if (parent[num] == num) {
            return num;
        } else {
            return parent[num] = find(parent[num]);
        }
    }

    public static void union(int n1, int n2) {
        n1 = find(n1);
        n2 = find(n2);
        if (n1 != n2) {
            parent[n2] = n1;
        }
    }
}
