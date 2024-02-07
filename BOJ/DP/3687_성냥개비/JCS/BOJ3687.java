package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BOJ3687 {

    static int TC;
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        TC = Integer.parseInt(bufferedReader.readLine());
        int N;
        long[] minDp = new long[101];
        String[] maxDp = new String[101];
        findMin(minDp);
        calculateMax(maxDp);

        for (int i = 0; i < TC; i++) {
            N = Integer.parseInt(bufferedReader.readLine());
            sb.append(minDp[N] + " " + maxDp[N]);
            sb.append("\n");
        }

        System.out.print(sb);
    }

    private static void findMin(long[] minDp) {
        // bottom-up 하기 위한 초기 세팅
        Arrays.fill(minDp, Long.MAX_VALUE);
        minDp[2] = 1;
        minDp[3] = 7;
        minDp[4] = 4;
        minDp[5] = 2;
        minDp[6] = 6;
        minDp[7] = 8;
        minDp[8] = 10;
        int[] count = {1, 7, 4, 2, 0, 8, 10};
        for (int usingMatch = 9; usingMatch <= minDp.length - 1; usingMatch++) {
            for (int matchNum = 2; matchNum < 8; matchNum++) {
                minDp[usingMatch] = Math.min(minDp[usingMatch],
                        (minDp[usingMatch - matchNum] * 10) + count[matchNum - 2]);
            }
        }
    }

    private static void calculateMax(String[] max) {
        max[2] = "1";
        max[3] = "7";
        for (int i = 4; i <= 100; ++i) {
            if (i % 2 != 0) {
                max[i] = "7" + max[i - 3];
            } else {
                max[i] = max[i - 2] + "1";
            }
        }

    }
}
