package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ17070 {

    static int mapSize;
    static int[][] map;
    static int moveCount = 0;
    static final int WALL = 1;

    enum PipeType {
        HORIZON,
        VERTICAL,
        DIAGONAL;

        public List<MoveWay> getPossibleMove() {
            if (this == HORIZON) {
                return List.of(MoveWay.RIGHT, MoveWay.DIAGONAL);
            }
            if (this == VERTICAL) {
                return List.of(MoveWay.DOWN, MoveWay.DIAGONAL);
            }
            return List.of(MoveWay.RIGHT, MoveWay.DOWN, MoveWay.DIAGONAL);
        }
    }

    enum MoveWay {
        RIGHT(0, 1),
        DIAGONAL(1, 1),
        DOWN(1, 0);

        int row;
        int col;

        MoveWay(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static class Pos {
        PipeType pipeType;
        int row;
        int col;

        public Pos(PipeType pipeType, int row, int col) {
            this.pipeType = pipeType;
            this.row = row;
            this.col = col;
        }

        public List<int[]> getSafeMoveRange(MoveWay moveWay) {
            if (moveWay == MoveWay.RIGHT) {
                return List.of(new int[]{this.row, this.col + 1});
            }
            if (moveWay == MoveWay.DOWN) {
                return List.of(new int[]{this.row + 1, this.col});
            }

            return List.of(new int[]{this.row + 1, this.col + 1}, new int[]{this.row, this.col + 1},
                    new int[]{this.row + 1, this.col});

        }

        public Pos move(MoveWay moveWay) {
            if (moveWay == MoveWay.RIGHT) {
                return new Pos(PipeType.HORIZON, this.row + moveWay.row, this.col + moveWay.col);
            }
            if (moveWay == MoveWay.DOWN) {
                return new Pos(PipeType.VERTICAL, this.row + moveWay.row, this.col + moveWay.col);
            }
            return new Pos(PipeType.DIAGONAL, this.row + moveWay.row, this.col + moveWay.col);
        }

        boolean isArrivedAt(int row, int col) {
            return this.row == row && this.col == col;
        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        mapSize = Integer.parseInt(bufferedReader.readLine());
        map = new int[mapSize][mapSize];

        for (int i = 0; i < mapSize; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < mapSize; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        if(map[mapSize-1][mapSize-1]==WALL) {
            System.out.println(moveCount);
            return;
        }
        recur(new Pos(PipeType.HORIZON, 0, 1));

        System.out.println(moveCount);

    }

    public static void recur(Pos pos) {

        if (pos.isArrivedAt(mapSize - 1, mapSize - 1)) {
            moveCount += 1;
            return;
        }

        for (MoveWay moveWay : pos.pipeType.getPossibleMove()) {
            if (canMove(pos.getSafeMoveRange(moveWay))) {
                recur(pos.move(moveWay));
            }
        }
    }

    public static boolean canMove(List<int[]> moveRanges) {
        final int ROW_INDEX = 0;
        final int COL_INDEX = 1;
        for (int[] moveRange : moveRanges) {
            if (moveRange[ROW_INDEX] >= mapSize || moveRange[COL_INDEX] >= mapSize
                    || map[moveRange[ROW_INDEX]][moveRange[COL_INDEX]] == WALL) {
                return false;
            }
        }
        return true;
    }
}
