import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q2473 {
    static int N;
    static long[] solutions;
    static long diff = Long.MAX_VALUE; // 0과의 차이
    static long[] answer = new long[3];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        solutions = new long[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            solutions[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(solutions);

        for (int left = 0; left < N - 2; left++) {
            int mid = left + 1;
            int right = N - 1;
            while (mid < right) {
                long sum = solutions[left] + solutions[mid] + solutions[right];

                if (Math.abs(sum) < diff) {
                    diff = Math.abs(sum);
                    answer[0] = solutions[left];
                    answer[1] = solutions[mid];
                    answer[2] = solutions[right];
                }

                if (sum > 0) {
                    right--;
                } else {
                    mid++;
                }
            }
        }

        System.out.println(answer[0] + " " + answer[1] + " " + answer[2]);
    }

}