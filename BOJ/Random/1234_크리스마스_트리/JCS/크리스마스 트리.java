import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    /*
    오민식은 오늘이 크리스마스라고 생각해서, 크리스마스 트리를 만들려고 한다.
    트리는 N개의 레벨로 이루어져 있다.
    위에서부터 레벨1, ... 레벨 N이다. 또, 민식이는 빨강, 파랑, 초록색의 장난감을 가지고 있다.
    그리고 민식이는 이 장난감을 일정한 규칙에 의해서 장식하려고 한다.

    레벨 K에는 딱 K개의 장난감이 있어야 한다.
    또, 각 레벨에 놓으려고 선택한 색이 있으면, 그 색의 장난감의 수는 서로 같아야 한다.
    예를 들어, 레벨 3에 장난감을 놓으려고 할 때, 빨강 2, 파랑 1과 같이 놓으면, 빨강과 파랑의 수가 다르기 때문에 안 된다.
    하지만, 레벨 4에 빨강 2, 파랑 2와 같이 놓으면, 가능하다.

    N과, 장난감의 수 K가 주어질 때, 트리를 장식하는 경우의 수를 출력하는 프로그램을 작성하시오.

    조건 1. 레벨 k 에는 k개의 장난감
    조건 2. 각 레벨에 놓일 장난감 색은 같아야함
    2 1 1 1 의 경우
    (빨, (파,초)), (빨, (초,파)), (파, (빨,초)), (파, (초,빨)), (초, (빨,파)), (초, (파,빨)) 6 가지 가능

    각 레벨에서 놓아야 하는 장난감의 수 k
    k % 사용할 장난감 색상 수(1,2,3) => V 라고 할 떄
    V 가 0 이 아니라면 해당 수의 색깔 사용 불가
    V 가 0 이라면 해당 수의 색 사용 가능
    [] [] [] [] [] []
     */
    static long[][][][] dp;
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int N = Integer.parseInt(stringTokenizer.nextToken());
        int R = Integer.parseInt(stringTokenizer.nextToken());
        int G = Integer.parseInt(stringTokenizer.nextToken());
        int B = Integer.parseInt(stringTokenizer.nextToken());
        dp = new long[N + 1][R + 1][G + 1][B + 1];

        for (int level = 0; level <= N; level++) {
            for (int r = 0; r <= R; r++) {
                for (int g = 0; g <= G; g++) {
                    for (int b = 0; b <= B; b++) {
                        if (level == 0) {
                            dp[level][r][g][b] = 1;
                            continue;
                        }

                        if (r - level >= 0) {
                            dp[level][r][g][b] += dp[level - 1][r - level][g][b];
                        }
                        if (g - level >= 0) {
                            dp[level][r][g][b] += dp[level - 1][r][g - level][b];
                        }
                        if (b - level >= 0) {
                            dp[level][r][g][b] += dp[level - 1][r][g][b - level];
                        }

                        if (level % 2 == 0) {
                            int useNum = level / 2;
                            if (r - useNum >= 0 && g - useNum >= 0) {
                                dp[level][r][g][b] += dp[level - 1][r - useNum][g - useNum][b] * getCount(level, 2);
                            }
                            if (r - useNum >= 0 && b - useNum >= 0) {
                                dp[level][r][g][b] += dp[level - 1][r - useNum][g][b - useNum] * getCount(level, 2);
                            }
                            if (g - useNum >= 0 && b - useNum >= 0) {
                                dp[level][r][g][b] += dp[level - 1][r][g - useNum][b - useNum] * getCount(level, 2);
                            }
                        }

                        if (level % 3 == 0) {
                            int useNum = level / 3;
                            if (r - useNum >= 0 && g - useNum >= 0 && b - useNum >= 0) {
                                dp[level][r][g][b] +=
                                        dp[level - 1][r - useNum][g - useNum][b - useNum] * getCount(level, 3);
                            }
                        }
                    }
                }
            }
        }

        System.out.println(dp[N][R][G][B]);
    }

    private static long getCount(int n, int ele) {
        long total = factorial(n);
        for (int i = 0; i < ele; i++) {
            total /= factorial(n / ele);
        }
        return total;
    }

    private static long factorial(int n) {
        long result = 1;
        for (int i = n; i >= 1; i--) {
            result *= i;
        }
        return result;
    }


}
