package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class BOJ16932 {

    /*
    N×M인 배열에서 모양을 찾으려고 한다. 배열의 각 칸에는 0과 1 중의 하나가 들어있다. 두 칸이 서로 변을 공유할때, 두 칸을 인접하다고 한다.
    1이 들어 있는 인접한 칸끼리 연결했을 때, 각각의 연결 요소를 모양이라고 부르자. 모양의 크기는 모양에 포함되어 있는 1의 개수이다.
    배열의 칸 하나에 들어있는 수를 변경해서 만들 수 있는 모양의 최대 크기를 구해보자.

    첫째 줄에 배열의 크기 N과 M이 주어진다. 둘째 줄부터 N개의 줄에는 배열에 들어있는 수가 주어진다.

    첫째 줄에 수 하나를 변경해서 만들 수 있는 모양의 최대 크기를 출력한다.

    제한 :
    2 <= N, M <= 1,000

    예제
    입력 :
    3 3
0 1 1
0 0 1
0 1 0
    출력 :
    5
    시간 제한 2 초
    최대 크기 1,000,000

    방법 1.
    연달아있는 가장 긴 1 찾기
    0인 칸을 1로 바꿔가면서 BFS로 최대 크기 찾기 -> 시간 초과

    방법 2.
    기존 모양의 크기를 센다. O(N*M)
    세면서 해당 칸에 인덱스를 부여한다.
    인덱스 별 크기를 MAP 자료구조에 저장해놓는다.

    0 인 칸 상하좌우를 살펴 연결할 수 있는 모양이 있는지 확인한 후 O(0의 갯수 : 최대 N*M-1)
    인덱스로 접근해 크기를 더한다
    가장 큰 크기를 반환한다.
    시간 복잡도 : O(N*M)

     */
    static int N, M;
    static int[][] map;
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static List<Pos> zeroPosList = new ArrayList<>();
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static Map<Integer, Integer> figureSize = new HashMap<>();
    static boolean[][] visit;

    static class Pos {
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {

        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());
        map = new int[N][M];
        visit = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                if (map[i][j] == 0) {
                    zeroPosList.add(new Pos(i, j));
                }
            }
        }
        measureSize();

        System.out.println(findMaxSize());

    }

    private static int findMaxSize() {
        int maxSize = Integer.MIN_VALUE;
        for (Pos zeroPos : zeroPosList) {
            maxSize = Math.max(maxSize, getSize(zeroPos));
        }
        return maxSize;
    }

    private static int getSize(Pos pos) {
        Set<Integer> visitFigure = new HashSet<>();
        int size = 1;
        for (int i = 0; i < 4; i++) {
            int nx = pos.x + dx[i];
            int ny = pos.y + dy[i];
            if (nx < 0 || nx >= N || ny < 0 || ny >= M || map[nx][ny] == 0 || visitFigure.contains(map[nx][ny])) {
                continue;
            }
            size += figureSize.get(map[nx][ny]);
            visitFigure.add(map[nx][ny]);
        }
        return size;
    }

    private static void measureSize() {
        int index = 1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visit[i][j] && map[i][j] != 0) {
                    figureSize.put(index, findFigureSize(index, new Pos(i, j)));
                    index++;
                }
            }
        }
    }

    private static int findFigureSize(int index, Pos pos) {
        Queue<Pos> queue = new LinkedList<>();
        queue.add(pos);
        visit[pos.x][pos.y] = true;
        int size = 0;
        while (!queue.isEmpty()) {
            Pos cur = queue.poll();
            map[cur.x][cur.y] = index;
            size++;
            for (int num = 0; num < 4; num++) {
                int nx = cur.x + dx[num];
                int ny = cur.y + dy[num];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M || visit[nx][ny] || map[nx][ny] == 0) {
                    continue;
                }
                queue.add(new Pos(nx, ny));
                visit[nx][ny] = true;
            }
        }
        return size;
    }
}
