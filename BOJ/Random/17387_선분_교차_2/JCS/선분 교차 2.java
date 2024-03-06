import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main {
    /**
     * https://gaussian37.github.io/math-algorithm-line_intersection/ 참고하기
     */
    static class Line {
        Point p1, p2;

        public Line(Point p1, Point p2) {
            this.p1 = p1;
            this.p2 = p2;
        }
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static List<Line> lines = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        for (int i = 0; i < 2; i++) {
            int[] inputs = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            lines.add(new Line(new Point(inputs[0], inputs[1]), new Point(inputs[2], inputs[3])));
        }

        boolean flag = isCross();
        if (flag) {
            System.out.println(1);
            return;
        }
        System.out.println(0);

    }

    private static boolean isCross() {
        int abc = ccw(lines.get(0).p1, lines.get(0).p2, lines.get(1).p1);
        int abd = ccw(lines.get(0).p1, lines.get(0).p2, lines.get(1).p2);
        int cda = ccw(lines.get(1).p1, lines.get(1).p2, lines.get(0).p1);
        int cdb = ccw(lines.get(1).p1, lines.get(1).p2, lines.get(0).p2);
        if ((abc * abd) < 0 && (cda * cdb) < 0) {
            return true;
        }
        if ((abc * abd) == 0 && (cda * cdb) < 0) {
            return true;
        }
        if ((abc * abd) < 0 && (cda * cdb) == 0) {
            return true;
        }
        if ((abc * abd) == 0 && (cda * cdb) == 0) {
            // 두 선분(4개의 점)이 한 직선 상에 있는 경우
            // 범위 확인 해야함
            int minX1 = Math.min(lines.get(0).p1.x, lines.get(0).p2.x);
            int minY1 = Math.min(lines.get(0).p1.y, lines.get(0).p2.y);
            int maxX2 = Math.max(lines.get(0).p1.x, lines.get(0).p2.x);
            int maxY2 = Math.max(lines.get(0).p1.y, lines.get(0).p2.y);

            int minX3 = Math.min(lines.get(1).p1.x, lines.get(1).p2.x);
            int minY3 = Math.min(lines.get(1).p1.y, lines.get(1).p2.y);
            int maxX4 = Math.max(lines.get(1).p1.x, lines.get(1).p2.x);
            int maxY4 = Math.max(lines.get(1).p1.y, lines.get(1).p2.y);

            if (minX3 <= maxX2 && minY3 <= maxY2 && minX1 <= maxX4 && minY1 <= maxY4) {
                return true;
            }
        }
        return false;

    }

    private static int ccw(Point p1, Point p2, Point p3) {
        long a = (long) p1.x * p2.y + (long) p2.x * p3.y + (long) p3.x * p1.y;
        long b = (long) p1.y * p2.x + (long) p2.y * p3.x + (long) p3.y * p1.x;
        /**
         * 세 점 p1, p2, p3 순서로 방향관계를 반환한다.
         * 반환 값이 양수이면 시계 반대방향, 음수이면 시계방향, 0이면 평행관계
         */
        long result = a - b;
        if (result > 0) {
            return 1;
        }
        if (result == 0) {
            return 0;
        }
        return -1;
    }

}
