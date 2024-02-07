import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Q3687 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());


        for (int i = 0; i < n; i++) {
            int matchesCount = Integer.parseInt(br.readLine());

            long[] minDp = new long[101];

            Arrays.fill(minDp, Long.MAX_VALUE);
            minDp[2] = 1;
            minDp[3] = 7;
            minDp[4] = 4;
            minDp[5] = 2;
            minDp[6] = 6;
            minDp[7] = 8;
            minDp[8] = 10;

            String[] add = {"1", "7", "4", "2", "0", "8"};
            for (int j = 9; j <= 100; j++) {
                for (int k = 2; k <= 7; k++) {
                    String line = "" + minDp[j - k] + add[k - 2];
                    minDp[j] = Math.min(Long.parseLong(line), minDp[j]);
                }
            }

            StringBuilder max = new StringBuilder();
            long a = matchesCount / 2;
            long b = matchesCount % 2;
            if (b == 1) {
                max.append("7");
            } else {
                max.append("1");
            }

            for (int j = 1; j < a; j++) {
                max.append("1");
            }
            System.out.println(minDp[matchesCount] + " " + max.toString());

        }
    }
}