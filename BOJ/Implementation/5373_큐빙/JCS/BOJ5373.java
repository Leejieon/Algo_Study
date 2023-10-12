package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ5373 {

    static char[][] UP_SIDE;
    static char[][] DOWN_SIDE;
    static char[][] FRONT_SIDE;
    static char[][] BACK_SIDE;
    static char[][] LEFT_SIDE;
    static char[][] RIGHT_SIDE;

    static int TC;
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        TC = Integer.parseInt(bufferedReader.readLine());

        for (int i = 0; i < TC; i++) {
            init();
            int n = Integer.parseInt(bufferedReader.readLine());
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int move = 0; move < n; move++) {
                handler(stringTokenizer.nextToken());
            }
            printUpside();

        }
    }

    private static void init() {
        UP_SIDE = new char[][]{{'w', 'w', 'w'}, {'w', 'w', 'w'}, {'w', 'w', 'w'}};
        DOWN_SIDE = new char[][]{{'y', 'y', 'y'}, {'y', 'y', 'y'}, {'y', 'y', 'y'}};
        FRONT_SIDE = new char[][]{{'r', 'r', 'r'}, {'r', 'r', 'r'}, {'r', 'r', 'r'}};
        BACK_SIDE = new char[][]{{'o', 'o', 'o'}, {'o', 'o', 'o'}, {'o', 'o', 'o'}};
        LEFT_SIDE = new char[][]{{'g', 'g', 'g'}, {'g', 'g', 'g'}, {'g', 'g', 'g'}};
        RIGHT_SIDE = new char[][]{{'b', 'b', 'b'}, {'b', 'b', 'b'}, {'b', 'b', 'b'}};
    }

    private static void handler(String sideAndDirection) {
        String[] options = sideAndDirection.split("");
        String side = options[0];
        String direction = options[1];
        switch (side) {
            case "U":
                turnUpside(direction);
                break;
            case "D":
                turnDownside(direction);
                break;
            case "F":
                turnFrontside(direction);
                break;
            case "B":
                turnBackside(direction);
                break;
            case "L":
                turnLeftside(direction);
                break;
            case "R":
                turnRightside(direction);
        }

    }

    private static void turnRightside(String direction) {
        char[][] copyUpSide = new char[3][3];
        for (int i = 0; i < 3; i++) {
            copyUpSide[i] = Arrays.copyOf(RIGHT_SIDE[i], RIGHT_SIDE[i].length);
        }
        if (direction.equals("+")) {
            // RIGHT_SIDE 시계방향으로 회전시킨다.
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    RIGHT_SIDE[i][j] = copyUpSide[2 - j][i];
                }
            }

            char[] copyRow = new char[3];
            for (int i = 0; i < 3; i++) {
                copyRow[i] = UP_SIDE[2 - i][2];
            }
            for (int i = 0; i < 3; i++) {
                UP_SIDE[i][2] = FRONT_SIDE[i][2];
            }
            for (int i = 0; i < 3; i++) {
                FRONT_SIDE[i][2] = DOWN_SIDE[2 - i][0];
            }
            for (int i = 0; i < 3; i++) {
                DOWN_SIDE[i][0] = BACK_SIDE[i][0];
            }
            for (int i = 0; i < 3; i++) {
                BACK_SIDE[i][0] = copyRow[i];
            }

        } else {
            // RIGHT_SIDE를 반시계방향으로 회전시킨다.
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    RIGHT_SIDE[i][j] = copyUpSide[j][2 - i];
                }
            }
            char[] copyRow = new char[3];
            for (int i = 0; i < 3; i++) {
                copyRow[i] = UP_SIDE[i][2];
            }
            for (int i = 0; i < 3; i++) {
                UP_SIDE[i][2] = BACK_SIDE[2 - i][0];
            }
            for (int i = 0; i < 3; i++) {
                BACK_SIDE[i][0] = DOWN_SIDE[i][0];
            }
            for (int i = 0; i < 3; i++) {
                DOWN_SIDE[i][0] = FRONT_SIDE[2 - i][2];
            }
            for (int i = 0; i < 3; i++) {
                FRONT_SIDE[i][2] = copyRow[i];
            }

        }
    }

    private static void turnLeftside(String direction) {
        char[][] copyUpSide = new char[3][3];
        for (int i = 0; i < 3; i++) {
            copyUpSide[i] = Arrays.copyOf(LEFT_SIDE[i], LEFT_SIDE[i].length);
        }
        if (direction.equals("+")) {
            // LEFT_SIDE 시계방향으로 회전시킨다.
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    LEFT_SIDE[i][j] = copyUpSide[2 - j][i];
                }
            }
            char[] copyRow = new char[3];
            for (int i = 0; i < 3; i++) {
                copyRow[i] = UP_SIDE[i][0];
            }
            for (int i = 0; i < 3; i++) {
                UP_SIDE[i][0] = BACK_SIDE[2 - i][2];
            }
            for (int i = 0; i < 3; i++) {
                BACK_SIDE[i][2] = DOWN_SIDE[i][2];
            }
            for (int i = 0; i < 3; i++) {
                DOWN_SIDE[i][2] = FRONT_SIDE[2 - i][0];
            }
            for (int i = 0; i < 3; i++) {
                FRONT_SIDE[i][0] = copyRow[i];
            }


        } else {
            // LEFT_SIDE를 반시계방향으로 회전시킨다.
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    LEFT_SIDE[i][j] = copyUpSide[j][2 - i];
                }
            }
            char[] copyRow = new char[3];
            for (int i = 0; i < 3; i++) {
                copyRow[i] = UP_SIDE[2 - i][0];
            }
            for (int i = 0; i < 3; i++) {
                UP_SIDE[i][0] = FRONT_SIDE[i][0];
            }
            for (int i = 0; i < 3; i++) {
                FRONT_SIDE[i][0] = DOWN_SIDE[2 - i][2];
            }
            for (int i = 0; i < 3; i++) {
                DOWN_SIDE[i][2] = BACK_SIDE[i][2];
            }
            for (int i = 0; i < 3; i++) {
                BACK_SIDE[i][2] = copyRow[i];
            }

        }
    }

    private static void turnBackside(String direction) {
        char[][] copyUpSide = new char[3][3];
        for (int i = 0; i < 3; i++) {
            copyUpSide[i] = Arrays.copyOf(BACK_SIDE[i], BACK_SIDE[i].length);
        }
        if (direction.equals("+")) {
            // BACK_SIDE 시계방향으로 회전시킨다.
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    BACK_SIDE[i][j] = copyUpSide[2 - j][i];
                }
            }

            char[] copyRow = new char[3];
            for (int i = 0; i < 3; i++) {
                copyRow[i] = UP_SIDE[0][2 - i];
            }
            for (int i = 0; i < 3; i++) {
                UP_SIDE[0][i] = RIGHT_SIDE[i][2];
            }
            for (int i = 0; i < 3; i++) {
                RIGHT_SIDE[i][2] = DOWN_SIDE[0][i];
            }
            for (int i = 0; i < 3; i++) {
                DOWN_SIDE[0][i] = LEFT_SIDE[2-i][0];
            }
            for (int i = 0; i < 3; i++) {
                LEFT_SIDE[i][0] = copyRow[i];
            }
        } else {
            // BACK_SIDE 반시계방향으로 회전시킨다.
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    BACK_SIDE[i][j] = copyUpSide[j][2 - i];
                }
            }
            char[] copyRow = new char[3];
            for (int i = 0; i < 3; i++) {
                copyRow[i] = UP_SIDE[0][i];
            }
            for (int i = 0; i < 3; i++) {
                UP_SIDE[0][i] = LEFT_SIDE[2 - i][0];
            }
            for (int i = 0; i < 3; i++) {
                LEFT_SIDE[i][0] = DOWN_SIDE[0][2 - i];
            }
            for (int i = 0; i < 3; i++) {
                DOWN_SIDE[0][i] = RIGHT_SIDE[i][2];
            }
            for (int i = 0; i < 3; i++) {
                RIGHT_SIDE[i][2] = copyRow[i];
            }


        }
    }

    private static void turnFrontside(String direction) {
        char[][] copyUpSide = new char[3][3];
        for (int i = 0; i < 3; i++) {
            copyUpSide[i] = Arrays.copyOf(FRONT_SIDE[i], FRONT_SIDE[i].length);
        }
        if (direction.equals("+")) {
            // FRONT_SIDE 시계방향으로 회전시킨다.
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    FRONT_SIDE[i][j] = copyUpSide[2 - j][i];
                }
            }

            char[] copyRow = new char[3];

            for (int i = 0; i < 3; i++) {
                copyRow[i] = UP_SIDE[2][i];
            }

            for (int i = 0; i < 3; i++) {
                UP_SIDE[2][i] = LEFT_SIDE[2 - i][2];
            }
            for (int i = 0; i < 3; i++) {
                LEFT_SIDE[i][2] = DOWN_SIDE[2][2 - i];
            }
            for (int i = 0; i < 3; i++) {
                DOWN_SIDE[2][i] = RIGHT_SIDE[i][0];
            }
            for (int i = 0; i < 3; i++) {
                RIGHT_SIDE[i][0] = copyRow[i];
            }


        } else {
            // FRONT_SIDE를 반시계방향으로 회전시킨다.
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    FRONT_SIDE[i][j] = copyUpSide[j][2 - i];
                }
            }
            char[] copyRow = new char[3];

            for (int i = 0; i < 3; i++) {
                copyRow[i] = UP_SIDE[2][2-i];
            }

            for (int i = 0; i < 3; i++) {
                UP_SIDE[2][i] = RIGHT_SIDE[i][0];
            }
            for (int i = 0; i < 3; i++) {
                RIGHT_SIDE[i][0] = DOWN_SIDE[2][i];
            }
            for (int i = 0; i < 3; i++) {
                DOWN_SIDE[2][i] = LEFT_SIDE[2-i][2];
            }
            for (int i = 0; i < 3; i++) {
                LEFT_SIDE[i][2] = copyRow[i];
            }

        }
    }

    private static void turnDownside(String direction) {
        char[][] copyUpSide = new char[3][3];
        for (int i = 0; i < 3; i++) {
            copyUpSide[i] = Arrays.copyOf(DOWN_SIDE[i], DOWN_SIDE[i].length);
        }
        if (direction.equals("+")) {
            // DOWN_SIDE 시계방향으로 회전시킨다.
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    DOWN_SIDE[i][j] = copyUpSide[2 - j][i];
                }
            }
            char [] copyRow = BACK_SIDE[2].clone();
            BACK_SIDE[2] = RIGHT_SIDE[2].clone();
            RIGHT_SIDE[2] = FRONT_SIDE[2].clone();
            FRONT_SIDE[2] = LEFT_SIDE[2].clone();
            LEFT_SIDE[2] = copyRow;


        } else {
            // DOWN_SIDE 반시계방향으로 회전시킨다.
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    DOWN_SIDE[i][j] = copyUpSide[j][2 - i];
                }
            }
            char [] copyRow = BACK_SIDE[2].clone();
            BACK_SIDE[2] = LEFT_SIDE[2].clone();
            LEFT_SIDE[2] = FRONT_SIDE[2].clone();
            FRONT_SIDE[2] = RIGHT_SIDE[2].clone();
            RIGHT_SIDE[2] = copyRow;

        }
    }

    private static void turnUpside(String direction) {
        char[][] copyUpSide = new char[3][3];
        for (int i = 0; i < 3; i++) {
            copyUpSide[i] = Arrays.copyOf(UP_SIDE[i], UP_SIDE[i].length);
        }
        if (direction.equals("+")) {
            // UP_SIDE를 시계방향으로 회전시킨다.
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    UP_SIDE[i][j] = copyUpSide[2 - j][i];
                }
            }
            char [] copyRow = BACK_SIDE[0].clone();
            BACK_SIDE[0] = LEFT_SIDE[0].clone();
            LEFT_SIDE[0] = FRONT_SIDE[0].clone();
            FRONT_SIDE[0] = RIGHT_SIDE[0].clone();
            RIGHT_SIDE[0] = copyRow;

        } else {
            // UP_SIDE를 반시계방향으로 회전시킨다.
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    UP_SIDE[i][j] = copyUpSide[j][2 - i];
                }
            }
            char [] copyRow = BACK_SIDE[0].clone();
            BACK_SIDE[0] = RIGHT_SIDE[0].clone();
            RIGHT_SIDE[0] = FRONT_SIDE[0].clone();
            FRONT_SIDE[0] = LEFT_SIDE[0].clone();
            LEFT_SIDE[0] = copyRow;
        }

    }


    static private void printUpside() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(UP_SIDE[i][j]);
            }
            System.out.println();
        }
    }
}