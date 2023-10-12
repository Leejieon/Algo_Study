import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


/*
 * t = 1 : 1x1 ㅁ
 * t = 2 : 1x2 ㅁㅁ
 * t = 3 : 2x1 ㅁ
 *             ㅁ
 */
public class Q20061 {
    static final int MAX_ROW = 10;
    static final int MAX_COL = 10;
    static final int START_GREEN = 4;
    static final int START_BLUE = 4;
    static int N, score;
    static boolean[][] map = new boolean[MAX_ROW][MAX_COL];

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st;

            N = Integer.parseInt(br.readLine());
            score = 0;

            int t, x, y;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());

                t = Integer.parseInt(st.nextToken());
                x = Integer.parseInt(st.nextToken());
                y = Integer.parseInt(st.nextToken());

                moveToGreen(t, x, y);
                moveToBlue(t, x, y);

                removeGreenBlock();
                removeBlueBlock();

                int specialBlock = countInSpecialBlock("green");
                for (int count = 0; count < specialBlock; count++) {
                    deleteLine("green", MAX_ROW - count - 1);
                }
                if (specialBlock != 0)
                    downGreenBlock(MAX_ROW - specialBlock, specialBlock);

                specialBlock = countInSpecialBlock("blue");
                for (int count = 0; count < specialBlock; count++) {
                    deleteLine("blue", MAX_COL - count - 1);
                }
                if (specialBlock != 0)
                    rightBlueBlock(MAX_COL - specialBlock, specialBlock);
            }
        }

        System.out.println(score);
        System.out.println(findAnswer());
    }

    static int countInSpecialBlock(String color) {
        int count = 0;

        if (color.equals("green")) {
            for (boolean block : map[START_GREEN]){
                if(block){
                    count++;
                    break;
                }
            }
            for (boolean block : map[START_GREEN + 1]){
                if(block){
                    count++;
                    break;
                }
            }
        }
        else {
            for (int x = 0; x < 4; x++) {
                if (map[x][START_BLUE]) {
                    count++;
                    break;
                }
            }
            for (int x = 0; x < 4; x++) {
                if (map[x][START_BLUE + 1]) {
                    count++;
                    break;
                }
            }
        }

        return count;
    }

    static void moveToGreen(int t, int x, int y) {
        int greenRow = MAX_ROW;

        for (int i = START_GREEN; i < MAX_ROW; i++) {
            if (t == 2) {
                if (map[i][y] || map[i][y + 1]) {
                    greenRow = i;
                    break;
                }
            }
            else {
                if (map[i][y]) {
                    greenRow = i;
                    break;
                }
            }
        }

        fillGreen(t, greenRow, y);
    }

    static void fillGreen(int t, int x, int y) {
        switch (t) {
            case 1 :
                map[x - 1][y] = true;
                break;
            case 2 :
                map[x - 1][y] = true;
                map[x - 1][y + 1] = true;
                break;
            case 3 :
                map[x - 2][y] = true;
                map[x - 1][y] = true;
                break;
        }
    }

    static void moveToBlue(int t, int x, int y) {
        int blueCol = MAX_COL;

        for (int i = START_BLUE; i < MAX_COL; i++) {
            if (t == 3) {
                if (map[x][i] || map[x + 1][i]) {
                    blueCol = i;
                    break;
                }
            }
            else {
                if (map[x][i]) {
                    blueCol = i;
                    break;
                }
            }
        }

        fillBlue(t, x, blueCol);
    }

    static void fillBlue(int t, int x, int y) {
        switch (t) {
            case 1 :
                map[x][y - 1] = true;
                break;
            case 2 :
                map[x][y - 1] = true;
                map[x][y - 2] = true;
                break;
            case 3 :
                map[x][y - 1] = true;
                map[x + 1][y - 1] = true;
                break;
        }
    }

    static void deleteLine(String color, int line) {
        if (color.equals("green")) {
            Arrays.fill(map[line], false);
        }
        else {
            for (int x = 0; x < 4; x++) {
                map[x][line] = false;
            }
        }
    }

    static void removeGreenBlock() {
        int count = 0;
        int line = -1;

        for (int x = MAX_ROW - 1; x >= START_GREEN; x--) {
            boolean isFill = true;
            for (int y = 0; y < 4; y++) {
                if(!map[x][y]) {
                    isFill = false;
                    break;
                }
            }

            if(isFill) {
                deleteLine("green", x);
                line = x;
                count++;
                score++;
            }
        }

        if(count != 0)
            downGreenBlock(line, count);
    }

    static void removeBlueBlock() {
        int count = 0;
        int line = -1;

        for (int y = MAX_COL - 1; y >= START_BLUE; y--) {
            boolean isFill = true;
            for (int x = 0; x < 4; x++) {
                if(!map[x][y]) {
                    isFill = false;
                    break;
                }
            }

            if(isFill) {
                deleteLine("blue", y);
                line = y;
                count++;
                score++;
            }
        }

        if (count != 0)
            rightBlueBlock(line, count);
    }

    // line : 지워진 라인 index
    static void downGreenBlock(int line, int count) {
        for (int x = line - 1; x >= 4; x--) {
            for (int y = 0; y < 4; y++) {
                map[x + count][y] = map[x][y];
                map[x][y] = false;
            }
        }
    }

    static void rightBlueBlock(int line, int count) {
        for (int y = line - 1; y >= 4; y--) {
            for (int x = 0; x < 4; x++) {
                map[x][y + count] = map[x][y];
                map[x][y] = false;
            }
        }
    }

    static int findAnswer() {
        int result = 0;

        // greenBlock
        for (int x = START_GREEN + 1; x < MAX_ROW; x++) {
            for (int y = 0; y < 4; y++) {
                if(map[x][y])
                    result++;
            }
        }

        // blueBlock
        for (int x = 0; x < 4; x++) {
            for (int y = START_BLUE + 1; y < MAX_COL; y++) {
                if(map[x][y])
                    result++;
            }
        }

        return result;
    }
}
