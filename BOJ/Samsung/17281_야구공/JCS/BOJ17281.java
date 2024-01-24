package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ17281 {

    static int inning;
    static int[][] playerGrade;
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static int score = Integer.MIN_VALUE;
    static final int PLAYER_NUMBER = 9;

    static int[] lineUp = new int[10];
    static boolean[] selected = new boolean[10];

    public static void main(String[] args) throws IOException {
        inning = Integer.parseInt(bufferedReader.readLine());
        playerGrade = new int[inning + 1][PLAYER_NUMBER + 1];
        for (int i = 1; i <= inning; i++) {
            StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
            for (int player = 1; player <= PLAYER_NUMBER; player++) {
                playerGrade[i][player] = Integer.parseInt(st.nextToken());
            }
        }
        lineUp[4] = 1;
        selected[4] = true;
        recur(2);
        System.out.println(score);
    }

    private static void recur(int depth) {

        if (depth == 10) {
            score = Math.max(score, getTotalScore());
            return;
        }

        for (int i = 1; i <= PLAYER_NUMBER; i++) {
            if (!selected[i]) {
                lineUp[i] = depth;
                selected[i] = true;
                recur(depth + 1);
                selected[i] = false;
            }
        }

    }

    private static int getTotalScore() {
        int curInning = 1;
        int curIdx = 1;
        int totalScore = 0;

        while (curInning <= inning) {
            int outCount = 0;
            int[] base = new int[5];

            while (outCount < 3) {
                int action = playerGrade[curInning][lineUp[curIdx++]];
                if (curIdx == 10) {
                    curIdx = 1;
                }
                if (action == 0) {
                    outCount++;
                    continue;
                }
                run(base, action);
            }
            totalScore += base[4];
            curInning++;
        }

        return totalScore;

    }

    private static void run(int[] base, int option) {

        for (int i = 0; i < option; i++) {
            base[4] += base[3];
            base[3] = base[2];
            base[2] = base[1];
            base[1] = 0;
        }

        base[option]++;
    }
}
