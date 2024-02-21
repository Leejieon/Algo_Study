import java.io.*;
import java.util.*;

public class S17143 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int r, c, m;
    static int dir[][] = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
    static Shark[][] sharks;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        input();
        fishing();
        System.out.print(answer);
    }

    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        sharks = new Shark[r + 1][c + 1];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            sharks[r][c] = new Shark(r, c, s, d, z);
        }
    }

    public static void fishing() {
        for (int i = 1; i <= c; i++) {
            Shark find = findsharks(i);
            if (find != null) {
                sharks[find.r][find.c] = null;
                answer += find.z;
            }
            sMove();
        }
    }
    public static Queue<Shark> selectShark() {
        Queue<Shark> q = new LinkedList<Main.Shark>();
        for (int row = 0; row <= r; row++) {
            for (int col = 0; col <= c; col++) {
                if (sharks[row][col] != null) {
                    q.add(sharks[row][col]);
                }
            }
        }
        return q;
    }
    public static void sMove() {
        Queue<Shark> q = selectShark();
        Shark[][] temp = new Shark[r + 1][c + 1];
        while (!q.isEmpty()) {
            Shark shark = q.poll();
            for (int i = 0; i < shark.s; i++) {
                shark.r += dir[shark.d - 1][0];
                shark.c += dir[shark.d - 1][1];

                if (shark.r == 0) { //
                    shark.r = 2;
                    shark.d = 2;
                } else if (shark.r == r + 1) {
                    shark.r = r - 1;
                    shark.d = 1;
                }

                if (shark.c == 0) {
                    shark.c = 2;
                    shark.d = 3;
                } else if (shark.c == c + 1) {
                    shark.c = c - 1;
                    shark.d = 4;
                }
            }
            if (temp[shark.r][shark.c] == null || temp[shark.r][shark.c].z < shark.z)
                temp[shark.r][shark.c] = shark;
        }
        sharks = temp;
    }

    public static Shark findsharks(int col) {
        for (int i = 1; i <= r; i++) {
            if (sharks[i][col] != null) {
                return sharks[i][col];
            }
        }
        return null;
    }

    static class Shark {
        int r, c, s, d, z; // r,c는 위치 s는 속력, d는 이동방향, z는 크기

        public Shark(int r, int c, int s, int d, int z) {
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }
}
