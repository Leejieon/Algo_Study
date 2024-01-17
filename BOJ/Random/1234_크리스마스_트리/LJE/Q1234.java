import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1234 {
    static long[][][][] color = new long[11][101][101][101];
    static long[] dp_factorial = new long[11];
    static long[][] dp_combination = new long[11][11];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int g = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        System.out.println(solve(N, r, g, b));
    }

    static long solve(int N, int r, int g, int b) {
        // Base Case
        if (r < 0 || g < 0 || b < 0) {
            return 0;
        }
        if (N <= 0) {
            return 1;
        }

        // Memoization
        if (color[N][r][g][b] != 0) {
            return color[N][r][g][b];
        }

        // Recursive Case
        // 1가지 색상을 사용할 경우
        color[N][r][g][b] += solve(N - 1, r - N, g, b);
        color[N][r][g][b] += solve(N - 1, r, g - N, b);
        color[N][r][g][b] += solve(N - 1, r, g, b - N);

        // 2가지 색상을 사용할 경우 -> N이 2의 배수인 경우만 가능
        if (N % 2 == 0) {
            int count = N / 2; // 각 색상을 사용할 개수
            color[N][r][g][b] += solve(N - 1, r - count, g - count, b) * combination(N, count);
            color[N][r][g][b] += solve(N - 1, r, g - count, b - count) * combination(N, count);
            color[N][r][g][b] += solve(N - 1, r - count, g, b - count) * combination(N, count);
        }

        // 3가지 색상을 사용할 경우 -> N이 3의 배수인 경우만 가능
        if (N % 3 == 0) {
            int count = N / 3;
            color[N][r][g][b] += solve(N - 1, r - count, g - count, b - count)
                    * combination(N, count) * combination(N - count, count);
        }

        return color[N][r][g][b];
    }

    static long factorial(int number) {
        if (number == 0 || number == 1) {
            return 1;
        }

        if (dp_factorial[number] != 0) {
            return dp_factorial[number];
        }

        return number * factorial(number - 1);
    }

    static long combination(int n, int r) {
        if (dp_combination[n][r] != 0) {
            return dp_combination[n][r];
        }

        dp_combination[n][r] = factorial(n) / (factorial(r) * factorial(n - r));
        return dp_combination[n][r];
    }

}