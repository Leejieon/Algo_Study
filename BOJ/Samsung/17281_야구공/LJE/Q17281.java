import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q17281 {
    static int inning, answer;
    static int[] order = new int[9];
    static boolean[] select = new boolean[9];
    static int[] base;
    static int[][] score;
    /*
    1 : 안타
    2 : 2루타
    3 : 3루타
    4 : 홈런
    0 : 아웃
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        inning = Integer.parseInt(br.readLine());
        base = new int[4]; // 1 : 1루 주자, 2 : 2루 주자, 3 : 3루 주자

        score = new int[inning][9];
        for (int i = 0; i < inning; i++) {
            st = new StringTokenizer(br.readLine());
            for (int person = 0; person < 9; person++) {
                score[i][person] = Integer.parseInt(st.nextToken());
            }
        }

        // 1번 선수는 4번 타자로 고정되어 있다.
        select[3] = true;

        answer = Integer.MIN_VALUE;
        nextPermutation(1);

        System.out.println(answer);
    }

    // 순열 만들기
    static void nextPermutation(int num) {
        if(num == 9) {
            game();
            return;
        }

        for (int i = 0; i < 9; i++) {
            if (select[i]) {
                continue;
            }
            select[i] = true;
            order[i] = num;
            nextPermutation(num + 1);
            select[i] = false;
        }
    }

    static void game() {
        int totalScore = 0;
        int curInning = 1;
        int index = 0;
        int outCount = 0;

        while (curInning <= inning) {

            // Base Case : 아웃 카운트 == 3
            if (outCount == 3) {
                curInning++;
                Arrays.fill(base, 0);
                outCount = 0;
                continue;
            }

            // 현재 주자의 hit 정보
            int curHit = score[curInning - 1][order[index]];
            if (curHit == 0) {
                outCount++;
            }
            else {
                totalScore += getScoreAndSetBase(curHit);
            }
            index = index == 8 ? 0 : index + 1;
        }

        answer = Math.max(answer, totalScore);
    }

    static int getScoreAndSetBase(int hit) {
        int result = 0;

        base[0] = 1;
        switch (hit) {
            case 1:
                result += base[3];
                base[3] = base[2];
                base[2] = base[1];
                base[1] = base[0];
                base[0] = 0;
                break;
            case 2:
                result += base[2] + base[3];
                base[3] = base[1];
                base[2] = base[0];
                base[1] = base[0] = 0;
                break;
            case 3:
                result += base[3] + base[2] + base[1];
                base[3] = base[0];
                base[2] = base[1] = base[0] = 0;
                break;
            case 4:
                result = base[3] + base[2] + base[1] + base[0];
                Arrays.fill(base, 0);
                break;
        }

        return result;
    }
}