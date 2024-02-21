import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    static enum Move {
        UP,
        DOWN,
        RIGHT,
        LEFT;
    }

    static int N;
    static int[][] blocks;
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    static int MAX_VALUE = -1;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        blocks = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] values = bufferedReader.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                blocks[i][j] = Integer.parseInt(values[j]);
            }
        }

        recur(0, new ArrayList<>());

        System.out.println(MAX_VALUE);
    }

    private static void recur(int idx, List<Move> orders) {
        if (orders.size() == 5) {
            int[][] movedMap = getMovedMap(orders);
            MAX_VALUE = Math.max(MAX_VALUE, getMaxValue(movedMap));
            return;
        }

        for (Move m : Move.values()) {
            orders.add(m);
            recur(idx+1, new ArrayList<>(orders));
            orders.remove(idx);
        }
    }

    private static int[][] getMovedMap(List<Move> orders) {
        int[][] copyMap = new int[N][N];
        for (int i = 0; i < N; i++) {
            copyMap[i] = Arrays.copyOf(blocks[i], N);
        }

        for (Move m : orders) {
            if (m.equals(Move.UP)) {
                moveUp(copyMap);
                continue;
            }
            if (m.equals(Move.DOWN)) {
                moveDown(copyMap);
                continue;
            }
            if (m.equals(Move.RIGHT)) {
                moveRight(copyMap);
                continue;
            }
            moveLeft(copyMap);
        }
        return copyMap;
    }

    private static void moveLeft(int[][] copyMap) {
        for (int i = 0; i < N; i++) {
            int index = 0;
            int block = 0;
            for (int j = 0; j < N; j++) {
                if (copyMap[i][j] != 0) {
                    if (block == copyMap[i][j]) {
                        copyMap[i][index - 1] = block * 2;
                        block = 0;
                        copyMap[i][j] = 0;
                    } else {
                        block = copyMap[i][j];
                        copyMap[i][j] = 0;
                        copyMap[i][index] = block;
                        index++;
                    }
                }
            }
        }
    }

    private static void moveRight(int[][] copyMap) {
        for (int i = 0; i < N; i++) {
            int index = N-1;
            int block = 0;
            for (int j = N-1; j >= 0; j--) {
                if (copyMap[i][j] != 0) {
                    if (block == copyMap[i][j]) {
                        copyMap[i][index + 1] = block * 2;
                        block = 0;
                        copyMap[i][j] = 0;
                    } else {
                        block = copyMap[i][j];
                        copyMap[i][j] = 0;
                        copyMap[i][index] = block;
                        index--;
                    }
                }
            }
        }
    }

    private static void moveDown(int[][] copyMap) {
        for (int i = 0; i < N; i++) {
            int index = N - 1;
            int block = 0;
            for (int j = N - 1; j >= 0; j--) {
                if (copyMap[j][i] != 0) {
                    if (block == copyMap[j][i]) {
                        copyMap[index + 1][i] = block * 2;
                        block = 0;
                        copyMap[j][i] = 0;
                    } else {
                        block = copyMap[j][i];
                        copyMap[j][i] = 0;
                        copyMap[index][i] = block;
                        index--;
                    }
                }
            }
        }
    }

    private static void moveUp(int[][] copyMap) {
        for (int i = 0; i < N; i++) {
            int index = 0;
            int block = 0;
            for (int j = 0; j < N; j++) {
                if (copyMap[j][i] != 0) {
                    if (block == copyMap[j][i]) {
                        copyMap[index - 1][i] = block * 2;
                        block = 0;
                        copyMap[j][i] = 0;
                    } else {
                        block = copyMap[j][i];
                        copyMap[j][i] = 0;
                        copyMap[index][i] = block;
                        index++;
                    }
                }
            }
        }
    }

    private static int getMaxValue(int[][] curMap) {
        int max = -1;
        for (int i = 0; i < curMap.length; i++) {
            for (int j = 0; j < curMap.length; j++) {
                max = Math.max(max, curMap[i][j]);
            }
        }
        return max;
    }

}
