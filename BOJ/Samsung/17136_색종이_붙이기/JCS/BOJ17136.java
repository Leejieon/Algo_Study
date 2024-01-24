package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.function.Function;

public class BOJ17136 {
    enum PaperType {
        FIVE(5),
        FOUR(4),
        THREE(3),
        TWO(2),
        ONE(1);

        int size;

        PaperType(int size) {
            this.size = size;
        }

    }

    static HashMap<PaperType, Integer> paperCount = new HashMap<>();
    static int[][] MAP = new int[10][10];
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static int minCount = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        initPaperCount();

        for (int i = 0; i < 10; i++) {
            StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < 10; j++) {
                MAP[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        recur(0, 0, 0);

        minCount = minCount == Integer.MAX_VALUE ? -1 : minCount;
        System.out.println(minCount);
    }

    private static void recur(int row, int col, int cnt) {
        if (row >= 9 && col >= 10) {
            minCount = Math.min(minCount, cnt);
            return;
        }
        if (minCount <= cnt) {
            return;
        }

        if (col >= 10) {
            recur(row + 1, 0, cnt);
            return;
        }
        if (MAP[row][col] == 1) {
            for (PaperType paperType : PaperType.values()) {
                if (paperCount.get(paperType) > 0 && canFetch(paperType, row, col)) {
                    fetch(paperType, row, col);
                    paperCount.put(paperType, paperCount.get(paperType) - 1);
                    recur(row, col + 1, cnt + 1);
                    detach(paperType, row, col);
                    paperCount.put(paperType, paperCount.get(paperType) + 1);
                }
            }
        } else {
            recur(row, col + 1, cnt);
        }
    }


    private static void fetch(PaperType paperType, int i, int j) {
        for (int row = i; row < i + paperType.size; row++) {
            for (int col = j; col < j + paperType.size; col++) {
                MAP[row][col] = 0;
            }
        }
        return;
    }

    private static void detach(PaperType paperType, int i, int j) {
        for (int row = i; row < i + paperType.size; row++) {
            for (int col = j; col < j + paperType.size; col++) {
                MAP[row][col] = 1;
            }
        }
        return;
    }

    private static boolean canFetch(PaperType paperType, int i, int j) {

        if (i + paperType.size > MAP.length || j + paperType.size > MAP.length) {
            return false;
        }
        for (int row = i; row < i + paperType.size; row++) {
            for (int col = j; col < j + paperType.size; col++) {
                if (MAP[row][col] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void initPaperCount() {
        for (PaperType paperType : PaperType.values()) {
            paperCount.put(paperType, 5);
        }
    }

}
