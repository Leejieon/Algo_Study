package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ14890 {
    /*
    N*N 맵
    길 -> 2N개
    각 칸에는 그곳의 높이
    길을 지나갈 수 있으려면 길에 속한 모든 칸의 높이가 모두 같아야 한다.
    또는, 경사로를 놓아서 지나갈 수 있는 길을 만들 수 있다.
    경사로는 높이가 항상 1이며, 길이는 L이다. 또, 개수는 매우 많아 부족할 일이 없다.

    입력
    N L
    N*N 지도

    알고리즘 map 지도와 경사로 지도 생성
    길 하나 씩 검사하면서 갈 수 있는 길인지 확인
    높이가 같다면 그냥 건너기
    높이가 같지 않다면
    1. 경사로 설치할 수 있는 지 확인
        1-1. 이미 설치한 경사로 있는지
        1-2. 평탄한 지형인지
    2-1. 설치 불가능 -> 건널 수 없는 길 -> 이전에 설치했던 경사로 철거
    2-2. 설치 가능 -> 경사로 지도에 기록하고 진행
    3.끝 도달 -> 길 Count +1

     */
    static int N, L;
    static int[][] MAP;
    static boolean[][][] SLOPE;
    static int AVAIL_WAY = 0;
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    static final int LEFT_UP_RIGHT = 0;
    static final int LEFT_DOWN_RIGHT = 1;

    public static void main(String[] args) throws IOException {
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(stringTokenizer.nextToken());
        L = Integer.parseInt(stringTokenizer.nextToken());
        MAP = new int[N][N];
        SLOPE = new boolean[N][N][2];
        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < N; j++) {
                MAP[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        solution();
    }

    private static void solution() {
        for (int option = 0; option < 2; option++) {
            for (int idx = 0; idx < N; idx++) {
                if (checkWay(idx, option)) {
//                    System.out.println("Check : " + idx + " Option : " + option + "TRUE");
                    AVAIL_WAY++;
                }
                resetSlope(idx, option);
//                System.out.println("Check : " + idx + " Option : " + option + "FALSE");
            }
        }
        System.out.print(AVAIL_WAY);
    }

    private static void resetSlope(int idx, int option) {
        if (option == 0) {
            for (int i = 0; i < N; i++) {
                SLOPE[idx][i][LEFT_DOWN_RIGHT] = false;
                SLOPE[idx][i][LEFT_UP_RIGHT] = false;
            }
        }
        else {
            for (int i = 0; i < N; i++) {
                SLOPE[i][idx][LEFT_DOWN_RIGHT] = false;
                SLOPE[i][idx][LEFT_UP_RIGHT] = false;
            }
        }
    }

    private static boolean checkWay(int idx, int option) {
        if (option == 0) {
            // 가로 길 확인
            for (int i = 0; i < N - 1; ) {
                if (MAP[idx][i] == MAP[idx][i + 1]) {
                    i++;
                    continue;
                }
                if (MAP[idx][i + 1] - MAP[idx][i] == 1) {
                    // 다음 지나가야 할 길의 높이가 1 높은경우
                    int pivot = i + 1;
                    // 오르막길 슬로프를 놓을 수 있는지 확인
                    if (canHaveSlope(idx, pivot, LEFT_UP_RIGHT, option)) {
                        for (int start = pivot - L; start < pivot; start++) {
                            // 슬로프를 설치한다.
                            SLOPE[idx][start][LEFT_UP_RIGHT] = true;
                        }
                        // 높은칸으로 보낸다.
                        i++;
                        continue;
                    }
                }
                if (MAP[idx][i] - MAP[idx][i + 1] == 1) {
                    // 다음 자나가야 할 길의 높이가 1 낮은경우
                    int pivot = i;
                    // 내리막길 슬로프를 놓을 수 있는지 확인
                    if (canHaveSlope(idx, pivot, LEFT_DOWN_RIGHT, option)) {
                        for (int start = pivot + L; start > pivot; start--) {
                            //슬로프를 설치한다.
                            SLOPE[idx][start][LEFT_DOWN_RIGHT] = true;
                        }
                        // 경사로를 설치한 칸으로 보낸다.
                        i += L;
                        continue;
                    }
                }
                // 위의 구문에 해당하지 않으면 지나갈 수 없는 길

                return false;
            }
        } else {
            //새로 길 확인
            for (int i = 0; i < N - 1; ) {
                if (MAP[i][idx] == MAP[i + 1][idx]) {
                    i++;
                    continue;
                }
                if (MAP[i + 1][idx] - MAP[i][idx] == 1) {
                    // 다음 지나가야 할 길의 높이가 1 높은경우
                    int pivot = i + 1;
                    // 오르막길 슬로프를 놓을 수 있는지 확인
                    if (canHaveSlope(idx, pivot, LEFT_UP_RIGHT, option)) {
                        for (int start = pivot - L; start < pivot; start++) {
                            //슬로프를 설치한다.
                            SLOPE[start][idx][LEFT_UP_RIGHT] = true;
                        }
                        i++;
                        continue;
                    }
                }
                if (MAP[i][idx] - MAP[i + 1][idx] == 1) {
                    // 다음 지나가야 할 길의 높이가 1 낮은경우
                    int pivot = i;
                    // 내리막길 슬로프를 놓을 수 있는지 확인
                    if (canHaveSlope(idx, pivot, LEFT_DOWN_RIGHT, option)) {
                        for (int start = pivot + L; start > pivot; start--) {
                            //슬로프를 설치한다.
                            SLOPE[start][idx][LEFT_DOWN_RIGHT] = true;
                        }
                        i += L;
                        continue;
                    }
                }

                return false;
            }

        }
        return true;
    }

    private static boolean canHaveSlope(int idx, int pivot, int direction, int option) {
        switch (option) {
            case 0:
                // 가로 길에 대해서
                if (direction == LEFT_UP_RIGHT) {
                    // pivot 왼쪽에 오르막길을 설치해야 한다면
                    if (pivot - L < 0) {
                        // 왼쪽에 놓을 공간이 없다면 false
                        return false;
                    }

                    for (int start = pivot - L; start < pivot; start++) {
                        // 다른 경사로가 놓여있는지 확인
                        if (SLOPE[idx][start][LEFT_UP_RIGHT] || SLOPE[idx][start][LEFT_DOWN_RIGHT]) {
                            return false;
                        }
                    }
                } else {
                    // pivot 오른쪽에 내리막길을 설치해야 한다면
                    if (pivot + L >= N) {
                        // 오른쪽에 놓을 공간이 없다면 false
                        return false;
                    }
                    int height = MAP[idx][pivot + 1];
                    for (int start = pivot + L; start > pivot; start--) {
                        // 평탄한지 확인 해야함
                        if (height != MAP[idx][start]) {
                            return false;
                        }
                    }
                }
                break;
            case 1:
                // 새로 길에 대해서
                if (direction == LEFT_UP_RIGHT) {
                    // pivot 위쪽에 오르막길을 설치해야 한다면
                    if (pivot - L < 0) {
                        return false;
                    }

                    for (int start = pivot - L; start < pivot; start++) {
                        // 다른 경사로가 놓여있는지 확인
                        if (SLOPE[start][idx][LEFT_UP_RIGHT] || SLOPE[start][idx][LEFT_DOWN_RIGHT]) {
                            return false;
                        }
                    }
                } else {
                    if (pivot + L >= N) {
                        // 오른쪽에 놓을 공간이 없다면 false
                        return false;
                    }
                    int height = MAP[pivot + 1][idx];
                    for (int start = pivot + L; start > pivot; start--) {
                        // 평탄한지 확인 해야함
                        if (height != MAP[start][idx]) {
                            return false;
                        }
                    }
                }
        }
        return true;
    }


}
