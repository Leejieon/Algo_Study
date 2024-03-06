import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class S2568 {

    static BufferedReader br;
    static StringTokenizer st;

    static int n;

    static Line[] lines = new Line[500001];

    static ArrayList<Line> ary;

    static ArrayList<Integer> result = new ArrayList<>();

    static int[] index = new int[500001];

    public static void main(String[] args) throws IOException {
        input();
        connection();
        adjustIndex();
        answer();
    }

    public static void answer() {
        System.out.println(n - ary.size());
        for (int i = result.size() - 1; i >= 0; i--) {
            System.out.println(result.get(i));
        }
    }

    public static void input() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        ary = new ArrayList<>();
        int a, b;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            lines[a] = new Line(a, b);
        }
    }


    public static void connection() {
        for (int i = 0; i < 500001; i++) {
            if (lines[i] == null)
                continue;
            if (ary.size() == 0 || ary.get(ary.size() - 1).b < lines[i].b) {
                ary.add(lines[i]);
                index[lines[i].a] = ary.size() - 1;
            } else {
                int idx = getCorrectIndex(0, ary.size() - 1, lines[i].b);
                ary.set(idx, lines[i]);
                index[lines[i].a] = idx;
            }
        }
    }


    public static int getCorrectIndex(int left, int right, int value) {
        int mid;
        while (left < right) {
            mid = (left + right) / 2;
            int cur = ary.get(mid).b;
            if (cur == value) { //같을 경우
                return mid;
            } else if (cur > value) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static void adjustIndex() {
        int limit = ary.size() - 1;
        for (int i = 500000; i >= 0; i--) {
            if (lines[i] == null)
                continue;
            if (index[i] == limit) {
                limit -= 1;
            } else {
                result.add(lines[i].a);
            }
        }
    }

    public static class Line {
        int a, b;

        public Line(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
}
