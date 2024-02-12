import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Q17143 {
    static int R, C, M;
    static Shark[][] sea;git

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 상어의 정보 입력
        sea = new Shark[R + 1][C + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            sea[r][c] = new Shark(s, d, z);
        }

        int total = 0;
        for (int loc = 1; loc < C + 1; loc++) {
            // 상어 잡기
            total += getShark(loc);
            // 모든 상어 움직이기
            moveAllSharks();
        }

        System.out.println(total);
    }

    static int getShark(int x) {
        int result = 0;
        for (int y = 1; y < R + 1; y++) {
            if (sea[y][x] != null) {
                result = sea[y][x].size;
                sea[y][x] = null;
                return result;
            }
        }
        return result;
    }

    static void moveAllSharks() {
        // 현재 순서에 이동한 상어들을 저장할 배열
        Shark[][] result = new Shark[R + 1][C + 1];

        for (int y = 1; y < R + 1; y++) {
            for (int x = 1; x < C + 1; x++) {
                if(sea[y][x] == null) continue;

                Shark shark = sea[y][x];
                int nextY = y;
                int nextX = x;

                boolean flag = true; // 현재 방향
                switch (shark.dir) {
                    case 1: // Up
                        // 첫 이동이 가능한지 확인. 불가능하다면 바로 방향 바꿔주기
                        if (nextY - 1 < 1) {
                            flag = !flag;
                        }
                        for (int i = 0; i < shark.speed; i++) {
                            // 원래 방향으로 진행되는 경우
                            if (flag) {
                                nextY--;
                            }
                            // 반대 방향으로 진행되는 경우
                            else {
                                nextY++;
                            }
                            // 경계선에 도달하면 방향 반대로 바꾸기
                            if (nextY == 1 || nextY == R) {
                                flag = !flag;
                            }
                        }
                        // 최종적으로 방향이 바뀐 경우
                        if (!flag) {
                            shark.dir = 2;
                        }
                        break;
                    case 2: // Down
                        if (nextY + 1 >= R + 1) {
                            flag = !flag;
                        }
                        for (int i = 0; i < shark.speed; i++) {
                            if (flag) {
                                nextY++;
                            } else {
                                nextY--;
                            }
                            if (nextY == 1 || nextY == R) {
                                flag = !flag;
                            }
                        }
                        if (!flag) {
                            shark.dir = 1;
                        }
                        break;
                    case 3: // Right
                        if (nextX + 1 >= C + 1) {
                            flag = !flag;
                        }
                        for (int i = 0; i < shark.speed; i++) {
                            if (flag) {
                                nextX++;
                            } else {
                                nextX--;
                            }
                            if (nextX == 1 || nextX == C) {
                                flag = !flag;
                            }
                        }
                        if (!flag) {
                            shark.dir = 4;
                        }
                        break;
                    case 4: // Left
                        if (nextX - 1 < 1) {
                            flag = !flag;
                        }
                        for (int i = 0; i < shark.speed; i++) {
                            if (flag) {
                                nextX--;
                            } else {
                                nextX++;
                            }
                            if (nextX == 1 || nextX == C) {
                                flag = !flag;
                            }
                        }
                        if (!flag) {
                            shark.dir = 3;
                        }
                        break;
                }

                // 이동한 위치에 이미 상어가 존재하는 경우
                if (result[nextY][nextX] != null) {
                    // 기존보다 사이즈가 크면 상어 교체
                    if (shark.size > result[nextY][nextX].size) {
                        result[nextY][nextX] = shark;
                    }
                } else {
                    result[nextY][nextX] = shark;
                }
            }
        }

        // 상어들의 이동이 완료된 배열을 기존 배열로 복사
        sea = result;
    }

    static class Shark {
        int speed; // 상어의 속력
        int dir;   // 상어의 이동 방향 [ 1 : 위, 2 : 아래, 3 : 오른쪽, 4 : 왼쪽 ]
        int size;  // 상어의 크기

        Shark(int speed, int dir, int size) {
            this.speed = speed;
            this.dir = dir;
            this.size = size;
        }
    }
}
