
import java.io.*;
import java.util.*;

public class S17281 {

    static BufferedReader br;
    static StringTokenizer st;
    static int N;

    static int[][] graph;

    static Base mybase = new Base();
    static int[] player = new int[10];
    static boolean[] visit = new boolean[10];
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        input();
        player[4] = 1;
        visit[4] = true;
        makePermutation(9, 2);
        System.out.print(answer);
    }

    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        graph = new int[N + 1][10];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 9; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    public static int goGame() {
        int score = 0;
        int now = 1;
        for (int i = 1; i <= N; i++) {
            int out = 0;
            mybase.init();
            while (out != 3) { // out이 3이 될때 종료
                int hit = graph[i][player[now]]; // 몇루타인지
                now = (now % 9) + 1; // 다음타순
                if (hit == 0) {
                    out++;
                    continue;
                }
                for (int h = 0; h < hit; h++) { // 잔루 주자 땡기기
                    mybase.base[4] += mybase.base[3];
                    mybase.base[3] = mybase.base[2];
                    mybase.base[2] = mybase.base[1];
                    mybase.base[1] = 0;
                }
                mybase.base[hit]++; //현재 친 루만큼 현재 주자 전진
            }
            score += mybase.base[4]; //이닝이 끝나고 홈에 쌓인 주자들 더하기
        }
        return score;
    }

    public static void makePermutation(int limit, int count) {
        if (limit + 1 == count) { //다 뽑을 경우
            answer = Math.max(answer, goGame());
            return;
        }
        for (int i = 1; i <= 9; i++) {
            if (!visit[i]) {
                player[i] = count;
                visit[i] = true;
                makePermutation(limit, count + 1);
                visit[i] = false;
            }
        }
    }

    static class Base {
        int[] base = new int[5];

        public void init() {
//            for(int b : this.base){
//                b = 0;
//            }
            for (int i = 0; i < 5; i++) {
                base[i] = 0;
            }
        }
    }
}