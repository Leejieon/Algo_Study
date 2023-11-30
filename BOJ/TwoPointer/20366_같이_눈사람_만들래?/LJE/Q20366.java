import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q20366 {
    static int N;
    static int[] snows;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        snows = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            snows[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(snows);

        int diff = Integer.MAX_VALUE;
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                int snowman1 = snows[i] + snows[j];
                int start = 0;
                int end = N - 1;

                while (start < end) {
                    if (start == i || start == j) {
                        start++;
                        continue;
                    }
                    if (end == i || end == j) {
                        end--;
                        continue;
                    }

                    int snowman2 = snows[start] + snows[end];

                    diff = Math.min(diff, Math.abs(snowman1 - snowman2));
                    if (snowman1 > snowman2) {
                        start++;
                    } else if (snowman1 < snowman2) {
                        end--;
                    } else {
                        System.out.println(0);
                        return;
                    }
                }
            }
        }

        System.out.println(diff);
    }

}