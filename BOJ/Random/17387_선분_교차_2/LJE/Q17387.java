import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Q17387 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        Point A, B, C, D;
        st = new StringTokenizer(br.readLine());
        A = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        B = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        st = new StringTokenizer(br.readLine());
        C = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        D = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        int ccw1 = getCCW(A, B, C) * getCCW(A, B, D);
        int ccw2 = getCCW(C, D, A) * getCCW(C, D, B);

        // 둘 다 0인 경우
        if (ccw1 == 0 && ccw2 == 0) {
            if ((Math.max(A.x, B.x) >= Math.min(C.x, D.x) && Math.max(C.x, D.x) >= Math.min(A.x, B.x))
                    && (Math.max(A.y, B.y) >= Math.min(C.y, D.y) && Math.max(C.y, D.y) >= Math.min(A.y, B.y))) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        } else if (ccw1 <= 0 && ccw2 <= 0) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    static int getCCW(Point p1, Point p2, Point p3) {
        long result = (p1.x * p2.y + p2.x * p3.y + p3.x * p1.y) - (p1.y * p2.x + p2.y * p3.x + p3.y * p1.x);
        return Long.compare(result, 0);
    }

    static class Point {
        long y, x;

        Point(long y, long x) {
            this.y = y;
            this.x = x;
        }
    }
}