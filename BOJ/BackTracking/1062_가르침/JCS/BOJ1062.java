package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1062 {

    /*

    남극에 사는 김지민 선생님은 학생들이 되도록이면 많은 단어를 읽을 수 있도록 하려고 한다.
    그러나 지구온난화로 인해 얼음이 녹아서 곧 학교가 무너지기 때문에, 김지민은 K개의 글자를 가르칠 시간 밖에 없다.
    김지민이 가르치고 난 후에는, 학생들은 그 K개의 글자로만 이루어진 단어만을 읽을 수 있다.
    김지민은 어떤 K개의 글자를 가르쳐야 학생들이 읽을 수 있는 단어의 개수가 최대가 되는지 고민에 빠졌다.


    남극언어의 모든 단어는 "anta"로 시작되고, "tica"로 끝난다. 남극언어에 단어는 N개 밖에 없다고 가정한다.
    학생들이 읽을 수 있는 단어의 최댓값을 구하는 프로그램을 작성하시오.

    입력 :
    첫째 줄에 단어의 개수 N과 K가 주어진다.
    N은 50보다 작거나 같은 자연수이고, K는 26보다 작거나 같은 자연수 또는 0이다. '
    둘째 줄부터 N개의 줄에 남극 언어의 단어가 주어진다.
    단어는 영어 소문자로만 이루어져 있고, 길이가 8보다 크거나 같고, 15보다 작거나 같다.
    모든 단어는 중복되지 않는다.

    출력 :
    첫째 줄에 김지민이 K개의 글자를 가르칠 때, 학생들이 읽을 수 있는 단어 개수의 최댓값을 출력한다.

    예제 :
    3 6
    anta rc tica
    anta hello tica
    anta car tica

    2

    풀이 :
    a, n, t, i, c 는 최소 알아야 함 따라서 K가 5보다 작으면 어떤 단어도 읽을 수 없다.

    배워야 할 단어, 이미 배운글자, 새로 배울 글자를 인자로 받아서 깊이 K 까지 반복한다.
    */

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stringTokenizer;
    static int N, K, wordCnt;
    static boolean[] VISIT = new boolean[26];
    final static Integer MIN_NUM = 5;
    final static Integer MAX_NUM = 26;
    final static char[] MUST_KNOW_CHAR = {'a', 'n', 't', 'i', 'c'};
    static List<String> wordList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(stringTokenizer.nextToken());
        K = Integer.parseInt(stringTokenizer.nextToken());
        wordCnt = 0;

        // K 가 5보다 작으면 어떤 단어도 읽을 수 없다.
        if (K < MIN_NUM) {
            System.out.println("0");
            return;
        }
        // K 가 26이면 모든 단어를 읽을 수 있다.
        if (K == MAX_NUM) {
            System.out.println(N);
            return;
        }
        // 단어 입력받기
        for (int i = 0; i < N; i++) {
            String word = bufferedReader.readLine();
            word.replace("anta", "");
            word.replace("tica", "");
            wordList.add(word);
        }

        for (char c : MUST_KNOW_CHAR) {
            VISIT[c - 'a'] = true;
        }
        K -= MIN_NUM;


        recur(0, 'a');
        System.out.println(wordCnt);

    }

    private static void recur(int depth, int alphabet) {

        if (depth == K) {
            int cnt = 0;
            for (String word : wordList) {
                if (canRead(word)) {
                    cnt++;
                }
            }
            wordCnt = Math.max(wordCnt, cnt);
        }

        for (int i = alphabet; i <= 'z'; i++) {
            if (!VISIT[i - 'a']) {
                VISIT[i - 'a'] = true;
                recur(depth + 1, i);
                VISIT[i - 'a'] = false;
            }
        }
    }


    private static boolean canRead(String word) {
        for (int i = 0; i < word.length(); i++) {
            if (!VISIT[word.charAt(i) - 'a']) {
                return false;
            }
        }
        return true;
    }
}
