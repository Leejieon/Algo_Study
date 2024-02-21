import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class Main {
    /**
     * 구현 문제인듯 객체 지향적으로 풀 수 있을 듯 시간 제한 : 1초 격자 최대 크기 : 100 * 100
     */

    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

    enum Direction {
        UP(1),
        DOWN(2),
        RIGHT(3),
        LEFT(4);
        int num;

        Direction(int num) {
            this.num = num;
        }

        static Direction of(int num) {
            return Arrays.stream(Direction.values())
                    .filter(direction -> direction.num == num)
                    .findFirst().get();
        }
    }

    static class Pos {
        int row;
        int col;

        public Pos(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public String toString() {
            return "Pos{" +
                    "row=" + row +
                    ", col=" + col +
                    '}';
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Pos)) {
                return false;
            }

            Pos pos = (Pos) obj;

            return this.row == pos.row && this.col == pos.col;
        }
    }

    static class Shark {
        private final int FORWARD = 1;
        private final int BACKWARD = -1;
        Pos pos;
        int speed;
        Direction direction;
        int size;

        public Shark(Pos pos, int speed, Direction direction, int size) {
            this.pos = pos;
            this.speed = speed;
            this.direction = direction;
            this.size = size;
        }

        public Pos moveWithin(int rowBoundary, int colBoundary) {
            if (this.direction == Direction.UP) {
                return moveVertical(rowBoundary, BACKWARD);
            }
            if (this.direction == Direction.DOWN) {
                return moveVertical(rowBoundary, FORWARD);
            }
            if (this.direction == Direction.RIGHT) {
                return moveHorizon(colBoundary, FORWARD);
            }
            return moveHorizon(colBoundary, BACKWARD);
        }


        private Pos moveVertical(int rowBoundary, int dir) {
            int dist = this.speed;
            while (dist > 0) {
                this.pos.row += dir;
                if (this.pos.row < 1 || this.pos.row > rowBoundary) {
                    this.pos.row -= dir;
                    dir *= -1;
                    this.pos.row += dir;
                }
                dist--;
            }
            this.direction = (dir == 1) ? Direction.DOWN : Direction.UP;

            return this.pos;
        }

        private Pos moveHorizon(int colBoundary, int dir) {
            int dist = this.speed;
            while (dist > 0) {
                this.pos.col += dir;
                if (this.pos.col < 1 || this.pos.col > colBoundary) {
                    this.pos.col -= dir;
                    dir *= -1;
                    this.pos.col += dir;
                }
                dist--;
            }
            this.direction = (dir == 1) ? Direction.RIGHT : Direction.LEFT;
            return this.pos;
        }

    }


    static class SharkFishingManager {
        int ROW;
        int COL;
        final List<Shark> sharks;
        Map<Pos, Shark> sharkMap = new HashMap<>();

        public SharkFishingManager(int ROW, int COL, List<Shark> sharks) {
            this.ROW = ROW;
            this.COL = COL;
            this.sharks = sharks;
            makeMap();
        }

        private void makeMap() {
            for (Shark shark : sharks) {
                sharkMap.put(shark.pos, shark);
            }
        }

        public void doCatch(FishKing king) {
            List<Shark> canCatchShark = new ArrayList<>();
            for (Pos pos : sharkMap.keySet()) {
                if (pos.col == king.position) {
                    canCatchShark.add(sharkMap.get(pos));
                }
            }
            if (canCatchShark.isEmpty()) {
                return;
            }

            Collections.sort(canCatchShark, new Comparator<Shark>() {
                @Override
                public int compare(Shark o1, Shark o2) {
                    return o1.pos.row - o2.pos.row;
                }
            });
            king.hold(canCatchShark.get(0));
            sharks.remove(canCatchShark.get(0));
        }

        public void doMove() {
            Map<Pos, Shark> movedMap = new HashMap<>();
            List<Shark> eatenShark = new ArrayList<>();
            for (Shark shark : sharks) {
                Pos pos = shark.moveWithin(ROW, COL);

                if (movedMap.containsKey(pos)) {
                    eatenShark.add(survival(movedMap, shark, pos));
                } else {
                    movedMap.put(pos, shark);
                }
            }

            for (Shark shark : eatenShark) {
                sharks.remove(shark);
            }

            sharkMap = movedMap;
        }

        private Shark survival(Map<Pos, Shark> movedMap, Shark newShark, Pos pos) {
            Shark preShark = movedMap.get(pos);

            if (preShark.size < newShark.size) {
                movedMap.put(pos, newShark);
                return preShark;
            }
            return newShark;
        }
    }

    static class FishKing {
        int position;
        Set<Shark> net = new HashSet<>();
        int lastPoint;

        public FishKing(int position, int lastPoint) {
            this.position = position;
            this.lastPoint = lastPoint;
        }

        public boolean isLastPoint() {
            return this.position == lastPoint;
        }

        public void movePoint() {
            this.position++;
        }

        public void hold(Shark shark) {
            this.net.add(shark);
        }

        public int gerResult() {
            int result = 0;
            for (Shark shark : net) {
                result += shark.size;
            }
            return result;
        }
    }

    public static void main(String[] args) throws IOException {
        int[] inputs = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        List<Shark> sharks = new ArrayList<>();
        for (int i = 0; i < inputs[2]; i++) {
            int[] sharkInfo = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            sharks.add(
                    new Shark(new Pos(sharkInfo[0], sharkInfo[1]),
                            sharkInfo[2],
                            Direction.of(sharkInfo[3]),
                            sharkInfo[4]));
        }

        SharkFishingManager fishingManager = new SharkFishingManager(inputs[0], inputs[1], sharks);
        final FishKing fishKing = new FishKing(0, inputs[1] + 1);

        while (!fishKing.isLastPoint()) {
            fishKing.movePoint();
            fishingManager.doCatch(fishKing);
            fishingManager.doMove();
        }

        System.out.println(fishKing.gerResult());
    }
}
