package BOJ;

import com.sun.jdi.InterfaceType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.WeakHashMap;

public class BOJ2228 {
    /*
N(1 ≤ N ≤ 100)개의 수로 이루어진 1차원 배열이 있다.
이 배열에서 M(1 ≤ M ≤ ⌈(N/2)⌉)개의 구간을 선택해서, 구간에 속한 수들의 총 합이 최대가 되도록 하려 한다. 단, 다음의 조건들이 만족되어야 한다.
각 구간은 한 개 이상의 연속된 수들로 이루어진다.
서로 다른 두 구간끼리 겹쳐있거나 인접해 있어서는 안 된다.
정확히 M개의 구간이 있어야 한다. M개 미만이어서는 안 된다.
N개의 수들이 주어졌을 때, 답을 구하는 프로그램을 작성하시오.

첫째 줄에 두 정수 N, M이 주어진다. 다음 N개의 줄에는 배열을 이루는 수들이 차례로 주어진다. 배열을 이루는 수들은 -32768 이상 32767 이하의 정수이다.

첫째 줄에 구간에 속한 수들의 총 합의 최댓값을 출력한다.

100 개의 수 1차원 배열
M개의 구간 선택, 구간에 속한 수들의 합이 최대가 되도록
각 구간은 한 개 이상의 연속된 수
정확히 M개의 구간
서로다른 두 구간 인접하거나 겹쳐선 안된다.
Input :
6 2
-1
3
1
2
4
-1
Output :
9

-1 3 1 2 4 -1
1차원 배열을 M개의 구간으로 나누어야 함
M -> 1이라면 1개의 구간합이 최대가 되는 구간

0 -> 구간이 없는 것 따라서 배열의 인덱스를 탐색하면서 합을 구한다.
-1 2 3 5 9 8
dp[n][m] -> n 이 m 구간에 속했을 때 최대 구간합.
배열의 인덱스 를 순회 하면서
각 인덱스의 값이 어떤 구간 m에 속했을 떄와 안 속했을 때 값을 구한다
n이 m 구간에 속하지 않는다면 dp[n][m] = dp[n-1][m]과 같다
n이 m 구간에 속한다면 m-1 구간에 속할 수 있는 Index 는 0 <= k <= n-2이다. 따라서 0부터 n-1까지 반복하면서 dp[k][m-1] + sum[n] - sum[k+1] 중 max 값을 찾는다.
     */
    static int N, M;
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static int[] sum;
    static int MIN_VALUE = -32769 * 100;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());
        sum = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            sum[i] = sum[i - 1] + Integer.parseInt(bufferedReader.readLine());
        }

        dp = new int[N + 1][M + 1];
        for (int i = 1; i <=M; i++) {
            dp[0][i] = MIN_VALUE;
        }

        for (int n = 1; n <= N; n++) {
            for (int m = 1; m <= M; m++) {
                //n을 m구간에 포함시키지 않았을 경우
                dp[n][m] = dp[n - 1][m];

                for (int k = 1; k <= n; k++) {
                    if (k >= 2) {
                        dp[n][m] = Math.max(dp[n][m], dp[k - 2][m - 1] + sum[n] - sum[k - 1]);
                    } else if (k == 1 && m == 1) {
                        dp[n][m] = Math.max(dp[n][m],sum[n]);
                    }
                }
            }
        }
        System.out.println(dp[N][M]);
    }


}