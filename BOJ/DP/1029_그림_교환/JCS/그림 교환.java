import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    /**
     * 예술을 사랑하는 사람들이 시장에 모여서 그들의 그림을 서로 거래하려고 한다. 모든 그림의 거래는 다음과 같은 조건을 만족해야 한다. 그림을 팔 때, 그림을 산 가격보다 크거나 같은 가격으로 팔아야 한다.
     * 같은 그림을 두 번 이상 사는 것은 불가능하다.
     * <p>
     * 방금 시장에 새로운 그림이 들어왔다. 1번 아티스트는 그 그림을 외부 상인에게 가격 0을 주고 샀다. 이제 그 그림을 자신의 예술가 친구들에게 팔려고 한다. 위의 조건을 모두 만족하는 거래만 이루어진다고
     * 가정했을 때, 그림을 소유했던 사람의 수의 최댓값을 출력하는 프로그램을 작성하시오. (1번 아티스트와 마지막으로 그 그림을 소유한 사람도 포함한다).
     * <p>
     * <p>
     * dp (마지막 구매자 , 구매한 사람들(비트형태로 표현)) = 최종 구매가격
     */

    static int N;
    static int[][] business;
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static int[][] dp;
    static int MAX = 0;

    public static void main(String[] args) throws IOException {

        N = Integer.parseInt(bf.readLine());
        business = new int[N + 1][N + 1];
        dp = new int[N + 1][1 << N];

        for (int i = 1; i <= N; i++) {
            int[] price = Arrays.stream(bf.readLine().split("")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < price.length; j++) {
                business[i][j + 1] = price[j];
            }
        }

        findDeal(1, 1, 1);

        System.out.println(MAX);

    }

    private static void findDeal(int cur, int state, int visitCnt) {
        if(MAX == N) {
            return;
        }
        
        MAX = Math.max(MAX, visitCnt);
        if (state == (1 << N) - 1) {
            return;
        }

        for (int i = 1; i <= N; i++) {
            int flag = state & (1 << (i - 1));
            if (flag == 0 && business[cur][i] >= dp[cur][state]) {
                if (dp[i][state | (1 << (i - 1))] != 0 && dp[i][state | (1 << (i - 1))] <= business[cur][i]) {
                    continue;
                }
                dp[i][state | (1 << (i - 1))] = business[cur][i];
                findDeal(i, state | (1 << (i - 1)), visitCnt + 1);
            }
        }
    }
}
