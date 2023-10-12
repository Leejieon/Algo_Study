import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
윗 면 U : 흰색 w
아랫 면 D : 노란색 y
앞 면 F : 빨간색 r
뒷 면 B : 오렌지 색 o
왼쪽 면 L : 초록색 g
오른쪽 면 R : 파란색 b
 */
public class Q5373 {
    /*
    char[0] : U
    char[1] : D
    char[2] : F
    char[3] : B
    char[4] : L
    char[5] : R
     */
    static char[][][] cube;
    static final String DELIMITER = " ";
    static final int U = 0, D = 1, F = 2, B = 3, L = 4, R =5;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 개수

        for (int test = 0; test < T; test++) {
            initCube();

            int N = Integer.parseInt(br.readLine()); // 큐브를 돌린 횟수

            char face, dir;
            String[] manuals = br.readLine().split(DELIMITER); // 큐브를 돌린 방법
            for (int cand = 0; cand < N; cand++) {
                face = manuals[cand].charAt(0);
                dir = manuals[cand].charAt(1);
                spinCube(face, dir);
            }

            printUpFace();
        }

    }

    static void initCube() {
        cube = new char[][][]
                {{{'w', 'w', 'w'}, {'w', 'w', 'w'}, {'w', 'w', 'w'}},
                {{'y', 'y', 'y'}, {'y', 'y', 'y'}, {'y', 'y', 'y'}},
                {{'r', 'r', 'r'}, {'r', 'r', 'r'}, {'r', 'r', 'r'}},
                {{'o', 'o', 'o'}, {'o', 'o', 'o'}, {'o', 'o', 'o'}},
                {{'g', 'g', 'g'}, {'g', 'g', 'g'}, {'g', 'g', 'g'}},
                {{'b', 'b', 'b'}, {'b', 'b', 'b'}, {'b', 'b', 'b'}}};
    }

    static void spinCube(char face, char dir) {
        char[][] newFace = new char[3][3];
        char[] savedLine;

        switch (face) {
            case 'U':
                // 시계 방향
                if (dir == '+') {
                    newFace = spinCubeByDir(U, '+');
                    savedLine = swap(cube[F][0], cube[L], 0, 0);
                    savedLine = swap(savedLine, cube[B], 0, 1);
                    savedLine = swap(savedLine, cube[R], 0, 1);
                    swap(savedLine, cube[F], 0, 0);
                }
                else {
                    newFace = spinCubeByDir(U, '-');
                    savedLine = swap(cube[F][0], cube[R], 0, 0);
                    savedLine = swap(savedLine, cube[B], 0, 1);
                    savedLine = swap(savedLine, cube[L], 0, 1);
                    swap(savedLine, cube[F], 0, 0);
                }
                cube[U] = newFace;
                break;

            case 'D':
                if (dir == '+') {
                    newFace = spinCubeByDir(D, '+');
                    savedLine = swap(cube[F][2], cube[R], 2, 0);
                    savedLine = swap(savedLine, cube[B], 2, 1);
                    savedLine = swap(savedLine, cube[R], 2, 1);
                    swap(savedLine, cube[F], 2, 0);
                }
                else {
                    newFace = spinCubeByDir(D, '-');
                    savedLine = swap(cube[F][2], cube[L], 2, 0);
                    savedLine = swap(savedLine, cube[B], 2, 1);
                    savedLine = swap(savedLine, cube[R], 2, 1);
                    swap(savedLine, cube[F], 2, 0);
                }
                cube[D] = newFace;
                break;

            case 'F':
                if (dir == '+') {
                    newFace = spinCubeByDir(F, '+');
                    savedLine = swap(cube[U][2], cube[R], 1, 2);
                    savedLine = swap(savedLine, cube[D], 0, 1);
                    savedLine = swap(savedLine, cube[L], 3, 2);
                    swap(savedLine, cube[U], 2, 1);
                }
                else {
                    newFace = spinCubeByDir(F, '-');
                    savedLine = swap(cube[U][2], cube[L], 3, 3);
                    savedLine = swap(savedLine, cube[D], 0, 0);
                    savedLine = swap(savedLine, cube[R], 1, 3);
                    swap(savedLine, cube[U], 2, 0);
                }
                cube[F] = newFace;
                break;

            case 'B':
                if (dir == '+') {
                    newFace = spinCubeByDir(B, '+');
                    savedLine = swap(cube[U][0], cube[L], 1, 3);
                    savedLine = swap(savedLine, cube[D], 2, 0);
                    savedLine = swap(savedLine, cube[R], 3, 3);
                    swap(savedLine, cube[U], 0, 0);
                }
                else {
                    newFace = spinCubeByDir(B, '-');
                    savedLine = swap(cube[U][0], cube[R], 3, 2);
                    savedLine = swap(savedLine, cube[D], 2, 1);
                    savedLine = swap(savedLine, cube[L], 1, 2);
                    swap(savedLine, cube[U], 0, 1);
                }
                cube[B] = newFace;
                break;

            case 'L':
                if (dir == '+') {
                    newFace = spinCubeByDir(L, '+');
                    savedLine = swap(makeLine(U, 1), cube[F], 1, 2);
                    savedLine = swap(savedLine, cube[D], 1, 2);
                    savedLine = swap(savedLine, cube[B], 1, 3);
                    swap(savedLine, cube[U], 1, 3);
                }
                else {
                    newFace = spinCubeByDir(L, '-');
                    savedLine = swap(makeLine(U, 1), cube[B], 1, 3);
                    savedLine = swap(savedLine, cube[D], 1, 3);
                    savedLine = swap(savedLine, cube[F], 1, 2);
                    swap(savedLine, cube[U], 1, 2);                }
                cube[L] = newFace;
                break;

            case 'R':
                if (dir == '+') {
                    newFace = spinCubeByDir(R, '+');
                    savedLine = swap(makeLine(U, 3), cube[B], 3, 3);
                    savedLine = swap(savedLine, cube[D], 3, 3);
                    savedLine = swap(savedLine, cube[F], 3, 2);
                    swap(savedLine, cube[U], 3, 2);
                }
                else {
                    newFace = spinCubeByDir(R, '-');
                    savedLine = swap(makeLine(U, 3), cube[F], 3, 2);
                    savedLine = swap(savedLine, cube[D], 3,2);
                    savedLine = swap(savedLine, cube[B], 3, 3);
                    swap(savedLine, cube[U], 3, 3);                }
                cube[R] = newFace;
                break;
        }
    }

    static char[][] spinCubeByDir(int face, char dir) {
        char[][] newFace = new char[3][3];
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                if(dir == '+')
                    newFace[y][x] = cube[face][3 - x - 1][y];
                else
                    newFace[y][x] = cube[face][x][3 - y - 1];
            }
        }

        return newFace;
    }

    static char[] makeLine(int face, int line) {
        char[] arr = new char[3];
        for (int i = 0; i < 3; i++) {
            if(line == 1){
                arr[i] = cube[face][i][0];
            }
            if (line == 3) {
                arr[i] = cube[face][i][2];
            }
        }

        return arr;
    }

    static char[] swap(char[] from, char[][] to, int line, int direction) {
        // direction = 0 : 왼쪽에서 오른쪽 (->)
        // direction = 1 : 오른쪽에서 왼쪽 (<-)
        // direction = 2 : 위에서 아래
        // direction = 3 : 아래에서 위

        char[] saveLine = new char[3];

        switch (line) {
            case 0:
                if(direction == 1){
                    from = reverseArray(from);
                }
                saveLine = to[0];

                to[0] = from;
                break;

            case 1:
                // col 값으로 배열 만들기
                for (int i = 0; i < 3; i++) {
                    if(direction == 2)
                        saveLine[i] = to[i][0];
                    else
                        saveLine[i] = to[2 - i][0];
                }

                for (int i = 0; i < 3; i++) {
                    to[i][0] = from[i];
                }
                break;

            case 2:
                if(direction == 1){
                    from = reverseArray(from);
                }

                saveLine = to[2];

                to[2] = from;
                break;

            case 3:
                for (int i = 0; i < 3; i++) {
                    if (direction == 2) {
                        saveLine[i] = to[i][2];
                    }
                    else {
                        saveLine[i] = to[2 - i][2];
                    }
                }
                if (direction == 3)
                    from = reverseArray(from);

                for (int i = 0; i < 3; i++) {
                    to[i][2] = from[i];
                }
                break;
        }

        return saveLine;
    }

    static char[] reverseArray(char[] arr) {
        char[] reverseArr = new char[3];
        for (int i = arr.length - 1, j = 0; i >= 0; i--, j++) {
            reverseArr[j] = arr[i];
        }
        return reverseArr;
    }

    static void printUpFace() {
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                System.out.print(cube[U][y][x]);
            }
            System.out.println();
        }
    }
}
