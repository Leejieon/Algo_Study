package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2629 {
    /*
    양팔 저울과 몇 개의 추가 주어졌을 때, 이를 이용하여 입력으로 주어진 구슬의 무게를 확인할 수 있는지를 결정하려고 한다
    추들의 무게와 확인할 구슬들의 무게가 입력되었을 때, 주어진 추만을 사용하여 구슬의 무게를 확인 할 수 있는지를 결정하는 프로그램을 작성하시오.

    입력 :
    첫째 줄에는 추의 개수가 자연수로 주어진다.
    추의 개수는 30 이하이다.
    둘째 줄에는 추의 무게들이 자연수로 가벼운 것부터 차례로 주어진다.
    같은 무게의 추가 여러 개 있을 수도 있다. 추의 무게는 500g이하이며, 입력되는 무게들 사이에는 빈칸이 하나씩 있다.
    세 번째 줄에는 무게를 확인하고자 하는 구슬들의 개수가 주어진다. 확인할 구슬의 개수는 7이하이다.
    네 번째 줄에는 확인하고자 하는 구슬들의 무게가 자연수로 주어지며, 입력되는 무게들 사이에는 빈 칸이 하나씩 있다.
    확인하고자 하는 구슬의 무게는 40,000보다 작거나 같은 자연수이다.

    출력 :
    주어진 각 구슬의 무게에 대하여 확인이 가능하면 Y, 아니면 N 을 차례로 출력한다.
    출력은 한 개의 줄로 이루어지며, 각 구슬에 대한 답 사이에는 빈칸을 하나씩 둔다.

    예시 :
4
2 3 3 3
3
1 4 10

Y Y N

    알고리즘 :
    만들 수 있는 rightSide의 무게 구하기
    가지고 있는 추 2, 3, 3, 3
    추 0개 사용 -> 0
    추 1개 사용 -> 2,  3
    추 2개 사용 -> 1개 사용한 것으로부터 구할 수 있음 -> (5, 1)(2,3 사용), (6, 0)(3,3 사용)
    추 3개 사용 -> (8, 2, 4)(2,3,3 사용), (3, 9)(3,3,3, 사용)
    추 4개 사용 -> (11, 5, 7, 1) (2,3,3,3 사용)
    만들 수 있는 무게 (0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 11)


     */

    static boolean[][] availWeight;
    static int weightCnt;
    static int ballCnt;
    static int[] weightMap;
    static int[] ballArr;
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) throws IOException {
        weightCnt = Integer.parseInt(bufferedReader.readLine());
        weightMap = new int[weightCnt];
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < weightCnt; i++) {
            weightMap[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        availWeight = new boolean[weightCnt + 1][400001];

        ballCnt = Integer.parseInt(bufferedReader.readLine());
        ballArr = new int[ballCnt];
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < ballCnt; i++) {
            ballArr[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        getAvailWeight(0, 0);


        for (int ball : ballArr) {
            if (availWeight[weightCnt][ball]) {
                stringBuilder.append("Y");
            } else {
                stringBuilder.append("N");
            }
            stringBuilder.append(" ");
        }

        System.out.println(stringBuilder);
    }


    private static void getAvailWeight(int cnt, int w) {

        if (availWeight[cnt][w]) return;
        availWeight[cnt][w] = true;
        if (cnt == weightCnt) return;
        getAvailWeight(cnt + 1, w + weightMap[cnt]);
        getAvailWeight(cnt + 1, w);
        getAvailWeight(cnt + 1, Math.abs(w - weightMap[cnt]));
    }
}
