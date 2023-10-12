package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ20061 {


    static boolean[][] GREEN_BOARD = new boolean[6][4];
    static boolean[][] BLUE_BOARD = new boolean[4][6];
    static int score = 0;
    static int tileCount = 0;
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(bufferedReader.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int t = Integer.parseInt(stringTokenizer.nextToken());
            int x = Integer.parseInt(stringTokenizer.nextToken());
            int y = Integer.parseInt(stringTokenizer.nextToken());
            doGreen(t, x, y);
            doBlue(t, x, y);
        }
        for (int i = 0; i < BLUE_BOARD.length; i++) {
            for (int j = 0; j < BLUE_BOARD[i].length; j++) {
                if(BLUE_BOARD[i][j])
                {
                    tileCount++;
                }
            }
        }
        for (int i = 0; i < GREEN_BOARD.length; i++) {
            for (int j = 0; j < GREEN_BOARD[i].length; j++) {
                if(GREEN_BOARD[i][j])
                {
                    tileCount++;
                }
            }
        }
        System.out.println(score+"\n"+tileCount);
    }

    private static void doBlue(int t, int x, int y) {
        int[] spot = findTileSpotOnBlue(t, x, y);
//        System.out.println(t+" " + x + " " + y);
        putDownTileOnBlue(spot, t);

        if (t == 1 || t == 3) {
            // 타일을 놓은 열 -> spot[1]
            if (isFullOfTileOnBlue(spot[1])) {
                score++;
                deleteRowOnBlue(spot[1]);
                moveOnBlue(spot[1]);
            }
        } else {
            for (int col = spot[1] - 1; col <= spot[1]; col++) {
                if (isFullOfTileOnBlue(col)) {
                    score++;
                    deleteRowOnBlue(col);
                    moveOnBlue(col);
//                    System.out.println("CATCH");
                }
            }
        }
        int specialColCount = checkSpecialSpotOnBlue();
        for (int i = 0; i < specialColCount; i++) {
            moveOnBlue(5);
        }
        if(specialColCount == 2) {
            for(int row = 0;row<BLUE_BOARD.length;row++) {
                BLUE_BOARD[row][0] = false;
                BLUE_BOARD[row][1] = false;
            }
        }
//        for (int i = 0; i < BLUE_BOARD.length; i++) {
//            for (int j = 0; j < BLUE_BOARD[i].length; j++) {
//                System.out.print(BLUE_BOARD[i][j] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println("==================");

    }

    private static void doGreen(int t, int x, int y) {
        int[] spot = findTileSpotOnGreen(t, x, y);

        putDownTileOnGreen(spot, t);


        if (t == 1 || t == 2) {
            //타일을 놓은 행 -> spot[0] 행
            if (isFullOfTileOnGreen(spot[0])) {
                score++;
                deleteRowOnGreen(spot[0]);
                moveOnGreen(spot[0]);
            }
        } else {
            for (int row = spot[0] - 1; row <= spot[0]; row++) {
                if (isFullOfTileOnGreen(row)) {
                    score++;
                    deleteRowOnGreen(row);
                    moveOnGreen(row);
                }
            }
        }

        int specialRowCount = checkSpecialSpotOnGreen();
        for (int i = 0; i < specialRowCount; i++) {
            moveOnGreen(5);
        }
        if(specialRowCount == 2) {
            for(int col = 0;col<GREEN_BOARD[0].length;col++) {
                GREEN_BOARD[0][col] = false;
                GREEN_BOARD[1][col] = false;
            }
        }
    }
    /*
    1 1 1 ->
    크기 1인 타일을 1,1위치 에 놓는다
    2 1 1 ->
    크기 1*2인 타일을 1,1 위치에 놓는다
    3 1 1 ->
    크기 2*1인 타일을 1,1 위치에 놓는다

    1. 타일이 놓일 위치를 찾아야함
    크기가 1인 타일이라면 각 행의 y 좌표만 탐색하면 됨
    크기가 2인 타일 중 1*2 인 타일이라면 각 행의 y, y+1 좌표 탐색 후 타일을 만날 때까지 탐색
    크기가 2인 타일 중 2*1 인 타일이라면 y 좌표만 탐색하면 됨

    2. 해당 위치에 타일을 놓기
    3. 타일을 놓은 위치에 대해서 해당하는 행에 타일이 가득차있는지 확인
    3-1. 가득 차있다면 점수를 올리고 해당 행 삭제
    3-2. 삭제한 행 개수 만큼 해당 행 위쪽의 타일들 내리기

    4. 특별구역에 타일이 있는지 확인 후 처리
     */

    static private int checkSpecialSpotOnGreen() {
        int cnt = 0;
        for (int row = 0; row < 2; row++) {
            for (int col = 0; col < GREEN_BOARD[row].length; col++) {
                if (GREEN_BOARD[row][col]) {
                    cnt++;
                    break;
                }
            }
        }
        return cnt;
    }

    static private int checkSpecialSpotOnBlue() {
        int cnt = 0;
        for (int col = 0; col < 2; col++) {
            for (int row = 0; row < BLUE_BOARD.length; row++) {
                if (BLUE_BOARD[row][col]) {
                    cnt++;
                    break;
                }
            }
        }
        return cnt;
    }

    static private void deleteRowOnGreen(int row) {
        // 해당 라인 지워준 후
        Arrays.fill(GREEN_BOARD[row], false);
        // 내리기

    }

    static private void moveOnGreen(int row) {
        for (int start = row; start > 0; start--) {
            for (int col = 0; col < GREEN_BOARD[row].length; col++) {
                GREEN_BOARD[start][col] = GREEN_BOARD[start - 1][col];
            }
        }
    }

    static private void deleteRowOnBlue(int col) {
        // 해당 라인 지워준 후
        for (int row = 0; row < BLUE_BOARD.length; row++) {
            BLUE_BOARD[row][col] = false;
        }
        // 오른쪽으로 당기기
    }

    static private void moveOnBlue(int col) {
        for (int start = col; start > 0; start--) {
            for (int row = 0; row < BLUE_BOARD.length; row++) {
                BLUE_BOARD[row][start] = BLUE_BOARD[row][start - 1];
            }
        }
    }

    static private boolean isFullOfTileOnGreen(int row) {

        for (int col = 0; col < GREEN_BOARD[row].length; col++) {
            if (!GREEN_BOARD[row][col]) {
                return false;
            }
        }
        return true;
    }

    static private boolean isFullOfTileOnBlue(int col) {

        for (int row = 0; row < BLUE_BOARD.length; row++) {
            if (!BLUE_BOARD[row][col]) {
                return false;
            }
        }
        return true;
    }


    static private void putDownTileOnGreen(int[] spot, int t) {
        if (t == 1) {
            GREEN_BOARD[spot[0]][spot[1]] = true;
        } else if (t == 2) {
            GREEN_BOARD[spot[0]][spot[1]] = true;
            GREEN_BOARD[spot[0]][spot[1] + 1] = true;
        } else {
            GREEN_BOARD[spot[0]][spot[1]] = true;
            GREEN_BOARD[spot[0] - 1][spot[1]] = true;
        }
    }

    static private void putDownTileOnBlue(int[] spot, int t) {
        if (t == 1) {
            BLUE_BOARD[spot[0]][spot[1]] = true;
        } else if (t == 2) {
            BLUE_BOARD[spot[0]][spot[1]] = true;
            BLUE_BOARD[spot[0]][spot[1] - 1] = true;
        } else {
            BLUE_BOARD[spot[0]][spot[1]] = true;
            BLUE_BOARD[spot[0] + 1][spot[1]] = true;
        }
    }

    static private int[] findTileSpotOnGreen(int t, int x, int y) {
        int[] result = new int[2];

        if (t == 1 || t == 3) {
            for (int row = 0; row < GREEN_BOARD.length; row++) {
                if (GREEN_BOARD[row][y]) {
                    result[0] = row - 1;
                    result[1] = y;
                    return result;
                }
            }
        } else {
            for (int row = 0; row < GREEN_BOARD.length; row++) {
                for (int col = y; col < y + 2; col++) {
                    if (GREEN_BOARD[row][col]) {
                        result[0] = row - 1;
                        result[1] = y;
                        return result;
                    }
                }
            }
        }
        result[0] = GREEN_BOARD.length - 1;
        result[1] = y;
        return result;
    }

    static private int[] findTileSpotOnBlue(int t, int x, int y) {
        int[] result = new int[2];

        if (t == 1 || t == 2) {
            for (int col = 0; col < BLUE_BOARD[0].length; col++) {
                if (BLUE_BOARD[x][col]) {
                    result[0] = x;
                    result[1] = col - 1;
                    return result;
                }
            }
        } else {
            for (int col = 0; col < BLUE_BOARD[0].length; col++) {
                for (int row = x; row < x + 2; row++) {
                    if (BLUE_BOARD[row][col]) {
                        result[0] = x;
                        result[1] = col - 1;
                        return result;
                    }
                }
            }
        }
        result[0] = x;
        result[1] = BLUE_BOARD[0].length - 1;
        return result;
    }

}