import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Q12100 {
    static int N;
    static int answer;
    static int[][] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        graph = new int[N][N];
        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++) {
                graph[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        answer = 0;
        recur(0);
        System.out.println(answer);
    }

    static void recur(int count) {
        // Base Case
        if (count == 5) {
            for (int y = 0; y < N; y++) {
                answer = Math.max(answer, Arrays.stream(graph[y]).max().getAsInt());
            }
            return;
        }

        // Recursive Case
        // 0 : Up, 1 : Down, 2 : Left, 3 : Right
        int[][] original = new int[N][N];
        for (int y = 0; y < N; y++) {
            original[y] = graph[y].clone();
        }
        for (int dir = 0; dir < 4; dir++) {
            proceed(dir);
            recur(count + 1);
            // 배열 복구하기
            for (int y = 0; y < N; y++) {
                graph[y] = original[y].clone();
            }
        }
    }

    static void proceed(int dir) {
        int index, prev;
        switch (dir) {
            case 0: // Up
                for (int x = 0; x < N; x++) {
                    index = 0; // 값을 넣게 될 위치
                    prev = 0; // 이전(최근)의 블록 숫자
                    for (int y = 0; y < N; y++) {
                        if (graph[y][x] != 0) { // 블록 숫자가 0이 아닌 경우
                            if (prev == graph[y][x]) { // 이전 숫자와 같으면,
                                graph[index - 1][x] = prev * 2; // 값 넣기
                                prev = 0; // 한 번 더했기 때문에 초기화
                                graph[y][x] = 0; // 기존 그래프에서 지우기
                            } else {
                                prev = graph[y][x];
                                graph[y][x] = 0;
                                graph[index][x] = prev;
                                index++;
                            }
                        }
                    }
                }
                break;
            case 1: // Down
                for (int x = 0; x < N; x++) {
                    index = N - 1; // 값을 넣게 될 위치
                    prev = 0; // 이전(최근)의 블록 숫자
                    for (int y = N - 1; y >= 0; y--) {
                        if (graph[y][x] != 0) { // 블록 숫자가 0이 아닌 경우
                            if (prev == graph[y][x]) { // 이전 숫자와 같으면,
                                graph[index + 1][x] = prev * 2; // 값 넣기
                                prev = 0; // 한 번 더했기 때문에 초기화
                                graph[y][x] = 0; // 기존 그래프에서 지우기
                            } else {
                                prev = graph[y][x];
                                graph[y][x] = 0;
                                graph[index][x] = prev;
                                index--;
                            }
                        }
                    }
                }
                break;
            case 2: // Left
                for (int y = 0; y < N; y++) {
                    index = 0; // 값을 넣게 될 위치
                    prev = 0; // 이전(최근)의 블록 숫자
                    for (int x = 0; x < N; x++) {
                        if (graph[y][x] != 0) { // 블록 숫자가 0이 아닌 경우
                            if (prev == graph[y][x]) { // 이전 숫자와 같으면,
                                graph[y][index - 1] = prev * 2; // 값 넣기
                                prev = 0; // 한 번 더했기 때문에 초기화
                                graph[y][x] = 0; // 기존 그래프에서 지우기
                            } else {
                                prev = graph[y][x];
                                graph[y][x] = 0;
                                graph[y][index] = prev;
                                index++;
                            }
                        }
                    }
                }
                break;
            case 3: // Right
                for (int y = 0; y < N; y++) {
                    index = N - 1; // 값을 넣게 될 위치
                    prev = 0; // 이전(최근)의 블록 숫자
                    for (int x = N - 1; x >= 0; x--) {
                        if (graph[y][x] != 0) { // 블록 숫자가 0이 아닌 경우
                            if (prev == graph[y][x]) { // 이전 숫자와 같으면,
                                graph[y][index + 1] = prev * 2; // 값 넣기
                                prev = 0; // 한 번 더했기 때문에 초기화
                                graph[y][x] = 0; // 기존 그래프에서 지우기
                            } else {
                                prev = graph[y][x];
                                graph[y][x] = 0;
                                graph[y][index] = prev;
                                index--;
                            }
                        }
                    }
                }
                break;
        }
    }

}
