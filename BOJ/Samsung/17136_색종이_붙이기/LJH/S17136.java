import java.io.*;
import java.util.*;

public class S17136 {

    static BufferedReader br;
    static StringTokenizer st;

    static boolean[][] graph = new boolean[10][10];

    static ArrayList<Point> candidate = new ArrayList<>();

    static ArrayList<Paper> paper;
    static int limit;

    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        input();
        DFS(0, 0);
        if (answer == Integer.MAX_VALUE) {
            System.out.print(-1);
        } else {
            System.out.print(answer);
        }
    }

    public static void input() throws IOException {
        int num;
        for (int i = 0; i < 10; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                num = Integer.parseInt(st.nextToken());
                if (num == 0) {
                    graph[i][j] = false;
                } else {
                    graph[i][j] = true;
                    candidate.add(new Point(i, j));
                }
            }
        }
        paper = new ArrayList<Paper>();
        for (int i = 1; i <= 5; i++) {
            paper.add(new Paper(i));
        }
        limit = candidate.size();
    }

    public static void DFS(int depth, int count) {
        if (depth == limit) { //끝까지 왔을때
            answer = Math.min(answer, count);
            return;
        }
        Point now = candidate.get(depth);
        if (answer <= count) { //최소개수보다 많을 경우 더이상 탐색하지 않아도 됨.
            return;
        }
        if (!graph[now.x][now.y]) { //이미 채워져있다면
            DFS(depth + 1, count);
        } else {
            for (int i = 4; i >= 0; i--) {
                if (paper.get(i).amount == 0 || !resemblePaper(paper.get(i).size, now.x, now.y)) { //종이가 없을 때,해당 좌표에 종이를 끼워넣을 수 없을 때
                    continue;
                }
                paper.get(i).amount--; //종이 사용하기 ,
                fillPaper(paper.get(i).size, now.x, now.y); //종이 넣었다고 표시
                DFS(depth + 1, count + 1); // 깊이 증가, 종이 갯수 추가
                paper.get(i).amount++; //종이 채우기.
                fillPaper(paper.get(i).size, now.x, now.y); // 종이 넣은거 표시 취소
            }
        }
    }

    public static boolean resemblePaper(int size, int row, int col) { //주어진 범위 내에 종이를 넣지 못하는지 파악.
        for (int i = row; i < row + size; i++) {
            for (int j = col; j < col + size; j++) {
                if (i >= 10 || j >= 10 || !graph[i][j])
                    return false;
            }
        }
        return true;
    }

    public static void fillPaper(int size, int row, int col) {
        for (int i = row; i < row + size; i++) {
            for (int j = col; j < col + size; j++) {
                graph[i][j] = !graph[i][j];
            }
        }
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Paper {
        int size;
        int amount = 5;

        public Paper(int size) {
            this.size = size;
        }
    }
}