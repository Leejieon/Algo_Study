import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q14890 {
    static int N, L, answer;
    static int[][] graph;

    public static void main(String[] args) throws IOException{
        initInput();
        countPath();

        System.out.println(answer);

    }

    static void initInput() throws IOException{
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken()); // 경사로 길이

            graph = new int[N][N];
            for (int y = 0; y < N; y++) {
                st = new StringTokenizer(br.readLine());
                for (int x = 0; x < N; x++) {
                    graph[y][x] = Integer.parseInt(st.nextToken());
                }
            }
        }
    }

    static void countPath() {
        int prevHeight; // 이전의 길의 높이
        boolean isPossiblePath;
        int length; // 같은 높이가 연속된 길의 길이

        // check row path
        for (int y = 0; y < N; y++) {
            prevHeight = graph[y][0];
            isPossiblePath = true;
            length = 1;

            for (int x = 1; x < N; x++) {
                int curHeight = graph[y][x];

                // 이전 길과 높이가 같을 경우
                if (prevHeight == curHeight) {
                    length++;
                    prevHeight = curHeight;
                    continue;
                }
                // 두 길의 높이 차이가 1이 아니라면 경사로를 이용할 수 없다.
                if (Math.abs(prevHeight - curHeight) != 1) {
                    isPossiblePath = false;
                    break;
                }

                // 오르막길일 경우
                if (prevHeight - curHeight == -1) {
                    // 연속된 길의 길이가 주어진 경사로의 길이보다 짧으면 길을 만들 수 없다.
                    if (length < L) {
                        isPossiblePath = false;
                        break;
                    }

                    // 연속된 길의 길이 초기화
                    length = 1;
                    prevHeight = curHeight;
                    continue;
                }

                // 내리막길일 경우
                if (prevHeight - curHeight == 1) {
                    int[] result = lengthOfNextContinuousPathAndIndex("row", y, x);

                    if (result[0] < L) {
                        isPossiblePath = false;
                        break;
                    }

                    x = result[1] - 1;
                    prevHeight = curHeight;
                    length = result[0] - L; // 경사로를 제외한 연속된 높이의 길의 길이
                    continue;
                }

            }

            if (isPossiblePath)
                answer++;
        }

        // check col path
        for (int x = 0; x < N; x++) {
            prevHeight = graph[0][x];
            isPossiblePath = true;
            length = 1;

            for (int y = 1; y < N; y++) {
                int curHeight = graph[y][x];

                // 이전 길과 높이가 같을 경우
                if (prevHeight == curHeight) {
                    length++;
                    prevHeight = curHeight;
                    continue;
                }
                // 두 길의 높이 차이가 1이 아니라면 경사로를 이용할 수 없다.
                if (Math.abs(prevHeight - curHeight) != 1) {
                    isPossiblePath = false;
                    break;
                }

                // 오르막길일 경우
                if (prevHeight - curHeight == -1) {
                    // 연속된 길의 길이가 주어진 경사로의 길이보다 짧으면 길을 만들 수 없다.
                    if (length < L) {
                        isPossiblePath = false;
                        break;
                    }

                    // 연속된 길의 길이 초기화
                    length = 1;
                    prevHeight = curHeight;
                    continue;
                }

                // 내리막길일 경우
                if (prevHeight - curHeight == 1) {
                    int[] result = lengthOfNextContinuousPathAndIndex("col", x, y);

                    if (result[0] < L) {
                        isPossiblePath = false;
                        break;
                    }

                    y = result[1] - 1;
                    prevHeight = curHeight;
                    length = result[0] - L; // 경사로를 제외한 연속된 높이의 길의 길이
                    continue;
                }

            }

            if (isPossiblePath)
                answer++;
        }

    }

    static int[] lengthOfNextContinuousPathAndIndex(String type, int line, int index) {
        int length = 1; // 같은 높이가 연속된 길의 길이
        int nextIndex = index + 1;

        // row path
        if (type.equals("row")) {
            int curHeight = graph[line][index];

            for (int x = index + 1; x < N; x++) {
                if (curHeight != graph[line][x]) {
                    break;
                }
                length++;
                nextIndex++;
            }
        }

        // col path
        else {
            int curHeight= graph[index][line];

            for (int y = index + 1; y < N; y++) {
                if (curHeight != graph[y][line]) {
                    break;
                }
                length++;
                nextIndex++;
            }
        }

        return new int[]{length, nextIndex};
    }

}
