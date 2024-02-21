import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.ToIntFunction;
import java.util.stream.Stream;

public class Main {
    /**
     * 가장 긴 증가하는 부분수열 접근 방법 -> 2가지 (DP, 이진탐색) Output : 부분수열의 길이, 해당 부분수열 제한 사항 : 시간 : 3초, N (최대 백, 각 원소 최소 -십억, +십억) 각
     * 원소들에 대하여 Lower Bound 를 구한다.
     */

    static int N;
    static int[] numbers;
    static int[] LIS;
    static int[] POS;
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bf.readLine());
        numbers = new int[N];
        LIS = new int[N + 1];
        POS = new int[N];

        numbers = Arrays.stream(bf.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

        int curLen = 0;
        for (int idx = 0; idx < numbers.length; idx++) {
            if (LIS[curLen] < numbers[idx]) {
                curLen++;
                LIS[curLen] = numbers[idx];
                POS[idx] = curLen;
            } else {
                int lowerBound = findLowerBoundOf(1, curLen, numbers[idx]);
                LIS[lowerBound] = numbers[idx];
                POS[idx] = lowerBound;
            }
        }


        List<Integer> answer = new ArrayList<>();

        for (int i = N - 1; i >= 0; i--) {
            if (POS[i] == curLen) {
                answer.add(numbers[i]);
                curLen -= 1;
            }
            if (curLen <= 0) {
                break;
            }
        }
        Collections.reverse(answer);

        sb.append(answer.size()).append("\n");
        for (int num : answer) {
            sb.append(num).append(" ");
        }
        System.out.println(sb);
    }

    private static int findLowerBoundOf(int curLeft, int curRight, int num) {
        int mid = 0;
        while (curLeft < curRight) {
            mid = (curLeft + curRight) / 2;

            if (LIS[mid] > num) {
                curRight = mid;
            }
            else if(LIS[mid] == num) {
                return mid;
            }
            else {
                curLeft = mid+1;
            }
        }
        return curRight;
    }
}
