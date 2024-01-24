package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class BOJ17406 {
    static int N, M, K;
    static int[][] NUMBERS;
    static List<Turn> turns = new ArrayList<>();
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static int MIN = Integer.MAX_VALUE;

    static class Turn {
        int row;
        int col;
        int correction;

        public Turn(int row, int col, int correction) {
            this.row = row - 1;
            this.col = col - 1;
            this.correction = correction;
        }

    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        NUMBERS = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < M; j++) {
                NUMBERS[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(bufferedReader.readLine());
            turns.add(new Turn(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())));
        }

        recur(new ArrayList<Integer>());

        System.out.println(MIN);

    }

    private static void recur(ArrayList<Integer> orders) {
        if (orders.size() == turns.size()) {
            int[][] target = copyNumbers();
            for (int order : orders) {
                target = turnClockWise(turns.get(order), target);
            }
            updateMinValue(target);
        }

        for (int i = 0; i < turns.size(); i++) {
            if (!orders.contains(i)) {
                orders.add(i);
                recur(new ArrayList<>(orders));
                orders.remove(orders.indexOf(i));
            }
        }
    }

    private static int[][] copyNumbers() {
        int[][] copyArray = new int[N][M];
        for (int row = 0; row < N; row++) {
            copyArray[row] = Arrays.copyOf(NUMBERS[row], NUMBERS[row].length);
        }
        return copyArray;
    }

    private static void updateMinValue(int[][] result) {
        for (int row = 0; row < N; row++) {
            MIN = Math.min(MIN, Arrays.stream(result[row]).sum());
        }
    }

    public static int[][] turnClockWise(Turn turn, int[][] target) {

        for (int layer = 1; layer <= turn.correction; layer++) {
            int leftTopNum = target[turn.row - layer][turn.col - layer];

            // 왼쪽 열 위로 당기기
            for (int row = turn.row - layer; row < turn.row + layer; row++) {
                target[row][turn.col - layer] = target[row + 1][turn.col - layer];
            }
            // 아랫쪽 행 왼쪽으로 당기기
            for (int col = turn.col - layer; col < turn.col + layer; col++) {
                target[turn.row + layer][col] = target[turn.row + layer][col + 1];
            }
            // 오른쪽 열 아래로 당기기
            for (int row = turn.row + layer; row > turn.row - layer; row--) {
                target[row][turn.col + layer] = target[row - 1][turn.col + layer];
            }
            //윗쪽 행 오른쪽으로 당기기
            for (int col = turn.col + layer; col > turn.col - layer; col--) {
                target[turn.row - layer][col] = target[turn.row - layer][col - 1];
            }

            target[turn.row - layer][turn.col - layer + 1] = leftTopNum;
        }
        return target;
    }
}
