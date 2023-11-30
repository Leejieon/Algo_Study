
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    /*
    한 배열 A[1], A[2], …, A[n]에 대해서, 부 배열은 A[i], A[i+1], …, A[j-1], A[j] (단, 1 ≤ i ≤ j ≤ n)을 말한다.
    이러한 부 배열의 합은 A[i]+…+A[j]를 의미한다.
    각 원소가 정수인 두 배열 A[1], …, A[n]과 B[1], …, B[m]이 주어졌을 때,
    A의 부 배열의 합에 B의 부 배열의 합을 더해서 T가 되는 모든 부 배열 쌍의 개수를 구하는 프로그램을 작성하시오.
    예를 들어 A = {1, 3, 1, 2}, B = {1, 3, 2}, T=5인 경우, 부 배열 쌍의 개수는 다음의 7가지 경우가 있다.
    T(=5)
      = A[1] + B[1] + B[2]
      = A[1] + A[2] + B[1]
      = A[2] + B[3]
      = A[2] + A[3] + B[1]
      = A[3] + B[1] + B[2]
      = A[3] + A[4] + B[3]
      = A[4] + B[2]
    -1,000,000,000 ≤ T ≤ 1,000,000,000
    1 ≤ n,m ≤ 1,000
    -1,000,000 <= A[i], B[i] <= 1,000,000

    각 배열에 대해서 s - e 까지의 합을 2차원 배열에 나타낼 수 있다.
    A(1, 3, 1, 2)의 경우
    1 4 5 7
    0 3 4 6
    0 0 1 3
    0 0 0 2
    B(1, 3, 2)의 경우
    1 4 6
    0 3 5
    0 0 2
    1 -> 2
    2 -> 1
    3 -> 2
    4 -> 2
    5 -> 1
    6 -> 1
    7 -> 1

    5-1 -> 2
    5-4 -> 2
    5-6 -> 0
    5-3 -> 1
    5-5 -> 0
    5-2 -> 2
    A에 대해서 부배열의 합으로 나올 수 있는 값 -> 해쉬테이블에 저장 (부배열의 합, 갯수)
    (T - B에 대해서 부배열의 합으로 나올 수 있는 값) 으로 해쉬테이블 조회해서 값이 있으면 해당 값 더하기

    시간 복잡도 -> N + N-1 + N-2 + ... + 1 => N^2
     */
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static Map<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        long answer = 0;
        int T = Integer.parseInt(bufferedReader.readLine());
        int N = Integer.parseInt(bufferedReader.readLine());
        int[] A = new int[N];
        String[] arrA = bufferedReader.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(arrA[i]);
        }
        int M = Integer.parseInt(bufferedReader.readLine());
        int[] B = new int[M];
        String[] arrB = bufferedReader.readLine().split(" ");
        for (int i = 0; i < M; i++) {
            B[i] = Integer.parseInt(arrB[i]);
        }

        subSumOfA(A);
        answer = count(B, T);
        System.out.println(answer);
    }

    private static long count(int[] B, int T) {
        long count = 0;
        for (int i = 0; i < B.length; i++) {
            int sum = 0;
            for (int j = i; j < B.length; j++) {
                sum += B[j];
                count +=  map.getOrDefault(T-sum,0);
            }
        }
        return count;
    }

    private static void subSumOfA(int[] A) {
        for (int i = 0; i < A.length; i++) {
            int sum = 0;
            for (int j = i; j < A.length; j++) {
                sum += A[j];
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }
    }
}
