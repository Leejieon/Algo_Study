package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ2661 {
    /*
    숫자 1, 2, 3으로만 이루어지는 수열이 있다.
    임의의 길이의 인접한 두 개의 부분 수열이 동일한 것이 있으면, 그 수열을 나쁜 수열이라고 부른다.
    그렇지 않은 수열은 좋은 수열이다.

    다음은 나쁜 수열의 예이다.

    33
    32121323
    123123213

    다음은 좋은 수열의 예이다.

    2
    32
    32123
    1232123
    길이가 N인 좋은 수열들을 N자리의 정수로 보아 그중 가장 작은 수를 나타내는 수열을 구하는 프로그램을 작성하라.
    예를 들면, 1213121과 2123212는 모두 좋은 수열이지만 그 중에서 작은 수를 나타내는 수열은 1213121이다.

    입력 :
    입력은 숫자 N하나로 이루어진다. N은 1 이상 80 이하이다.

    출력 :
    첫 번째 줄에 1, 2, 3으로만 이루어져 있는 길이가 N인 좋은 수열들 중에서 가장 작은 수를 나타내는 수열만 출력한다.
    수열을 이루는 1, 2, 3들 사이에는 빈칸을 두지 않는다.

    입출력 예 :
    Input :
    7
    Output :
    1213121

    1 -> 2 -> 3 순으로 숫자 입력
    수열 생성 -> 좋은 수열인지 아닌지 확인
    좋은 수열이면 계속 진행

     */
    static int N;
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static int[] availNum = {1, 2, 3};
    static String answer = "";

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        recur(0, "1");
        System.out.println(answer);

    }

    private static void recur(int depth, String series) {

        //좋은 수열이 아니라면 종료
        if (!isGoodSeries(series)) {
            return;
        }
        //좋은 수열이고 길이가 N이라면 answer를 등록하고 종료
        if (depth == N - 1) {
            answer = series;
            return;
        }

        for (int i : availNum) {
            //마지막 글자와 다른 것만 붙혀서 재귀 호출
            if (answer.equals("") && series.charAt(series.length() - 1) != Integer.toString(i).charAt(0)) {
                recur(depth + 1, series + Integer.toString(i));
            }
        }
    }

    private static boolean isGoodSeries(String series) {
        if (series.length() == 1) {
            return true;
        }
        // 부분 수열 검사
        int len = series.length() / 2;
        while (len > 1) {
            String h1 = series.substring(series.length() - len, series.length());
            String h2 = series.substring(series.length() - (2 * len), series.length() - len);
            if (h1.equals(h2)) {
                return false;
            }
            len--;
        }
        return true;
    }
}
