import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class S17387 {

    static BufferedReader br;
    static StringTokenizer st;

    static List<Point> points;

    public static void main(String[] args) throws IOException {
        input();
        solution();
    }

    public static void input() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        points = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 2; j++) {
                long x, y;
                x = Long.parseLong(st.nextToken());
                y = Long.parseLong(st.nextToken());
                points.add(new Point(x, y));
            }
        }
    }

    public static void solution() {
        Point a = points.get(0);
        Point b = points.get(1);
        Point c = points.get(2);
        Point d = points.get(3);
        int res1 = Ccw(a, b, c) * Ccw(a, b, d);
        int res2 = Ccw(c, d, a) * Ccw(c, d ,b);

        if(res1 == 0 && res2 == 0) {
            if(Math.min(a.x, b.x) <= Math.max(c.x, d.x) && Math.min(c.x, d.x) <= Math.max(a.x, b.x) &&
                    Math.min(a.y, b.y) <= Math.max(c.y, d.y) && Math.min(c.y, d.y) <= Math.max(a.y, b.y)) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        } else if(res1 <= 0 && res2 <= 0) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    static int Ccw(Point a, Point b, Point c) {
        long result = (a.x * b.y + b.x * c.y + c.x * a.y) - (a.x * c.y + c.x * b.y + b.x * a.y);
        if (result == 0) return 0;
        else if (result > 0) return 1;
        else return -1;
    }

    static class Point {
        long x, y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }
}