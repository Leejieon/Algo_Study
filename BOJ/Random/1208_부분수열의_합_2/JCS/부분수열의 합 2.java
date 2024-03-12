import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    /**
     * N개의 정수로 이루어진 수열이 있을 때, 크기가 양수인 부분수열 중에서 그 수열의 원소를 다 더한 값이 S가 되는 경우의 수를 구하는 프로그램을 작성하시오. 첫째 줄에 정수의 개수를 나타내는 N과 정수
     * S가 주어진다. (1 ≤ N ≤ 40, |S| ≤ 1,000,000) 둘째 줄에 N개의 정수가 빈 칸을 사이에 두고 주어진다. 주어지는 정수의 절댓값은 100,000을 넘지 않는다. 첫째 줄에 합이 S가
     * 되는 부분수열의 개수를 출력한다. 정수 (-100,000 <= Ak <= 100,000) 수열의 부분 수열 중 합이 S가 되는 부분수열의 경우의 수 부분합 문제 -> 누적합에서 투 포인터가 있으면 해당
     * 수열의 부분 합을 구할 수 있다. 모든 부분수열의 갯수 => 2^40개 찾기 어려움 절반 + 절반 => 2^20 + 2^20 그럼 수열의 원소에 대해서 해당 원소를 부분 수열에 포함할지 안할지를 결정하면
     * 될까? 어떤 기준으로 ? 딱히 기준 없음 왜냐면 절반에 대해서 모든 부분수열을 뒤져봐야하니까
     */
    static int N, S;
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static int[] numbers;
    static int size = 4_000_000;
    static int[] SUM = new int[size * 2 + 1];
    static long cnt = 0l;

    public static void main(String[] args) throws IOException {
        int[] input = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        N = input[0];
        S = input[1];
        numbers = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        if (N == 1) {
            if (S == numbers[0]) {
                System.out.println(1);
                return;
            }
            System.out.println(0);
            return;
        }

        findLeftSubSum(0, 0);
        findRightSubSum(N / 2 + 1, 0);

        if (S == 0) {
            cnt -= 1;
        }
        System.out.println(cnt);
    }

    public static void findLeftSubSum(int idx, int sum) {
        if (idx == N / 2 + 1) {
            SUM[size + sum]++;
            return;
        }

        findLeftSubSum(idx + 1, sum + numbers[idx]);
        findLeftSubSum(idx + 1, sum);
    }

    public static void findRightSubSum(int idx, int sum) {
        if (idx == N) {
            cnt += SUM[size + S - sum];
            return;
        }

        findRightSubSum(idx + 1, sum + numbers[idx]);
        findRightSubSum(idx + 1, sum);
    }

}
