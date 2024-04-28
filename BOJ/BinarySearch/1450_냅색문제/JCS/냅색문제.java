import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    /*
        N -> 최대 30
        N개의 물건, 최대 C 만큼의 무게를 넣을 수 있는 가방
        N개의 물건을 가방에 넣는 방법의 수
        5 5
        1 2 3 4 5

        총 경우의 수 2^30 가지
        한 물건에 대해서 가방에 넣는가 or 안 넣는가

        나올 수 있는 모든 무게를 구한다 ?
        그 중 넣을 수 있는 무게만 선별한다?


     */
    static public Long num = 0L;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, C;
    static int[] stuffs;
    static Map<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        stuffs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        List<Integer> halfSums1 = new ArrayList<>();
        List<Integer> halfSums2 = new ArrayList<>();
        findHalfSum(0, 0, N / 2, halfSums1);
        findHalfSum(N / 2, 0, N, halfSums2);

        halfSums1.sort(Integer::compareTo);
        halfSums2.sort(Integer::compareTo);

        for (int sum : halfSums1) {
            num += findCase(C - sum, halfSums2) + 1;
        }

        System.out.println(num);
    }

    private static int findCase(int target, List<Integer> halfSums2) {

        int l = 0, r = halfSums2.size() - 1;
        while (l <= r) {
            int mid = (l + r) / 2;

            if (halfSums2.get(mid) <= target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return r;
    }


    private static Long countCase(int sum) {
        long count = 0L;
        for (int key : map.keySet()) {
            if (key <= C - sum) {
                count += map.get(key);
            }
        }
        return count;
    }

    public static void findHalfSum(int depth, int sum, int length, List<Integer> halfSums) {
        if (sum > C) {
            return;
        }
        if (depth == length) {
            halfSums.add(sum);
            return;
        }

        findHalfSum(depth + 1, sum + stuffs[depth], length, halfSums);
        findHalfSum(depth + 1, sum, length, halfSums);
    }
}
