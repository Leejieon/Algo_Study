package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ20542 {
    /*
    지원자가 작성한 답안을 몇 번이나 수정해야 정답과 같아지는지 확인하는 방법이다.
    수정에는 추가, 삭제, 변환 세 가지 방법이 있다.
    추가는 한 글자를 추가하는 것이고,
    삭제는 한 글자를 삭제하는 것이며,
    변환은 한 글자를 다른 글자로 바꾸는 것을 의미한다.
    추가, 삭제, 변환은 모두 동일하게 1회의 수정으로 취급한다.
    받아쓰기 테스트에서의 수정 횟수는 추가, 삭제, 변환 세 가지 수정 횟수의 합이 가장 최소로 일어난 경우를 말한다.
    갈겨 쓴 i는 i, j, l 모두와 매칭된다.
    예를 들어 정답이 'james'일 때 답안이 'iames'라면 수정 횟수는 0회로 채점된다.
    대신 답안에 작성한 j와 l은 정확하게 인식한다.
    마찬가지로 휘갈겨 쓴 v는 v, w와 매칭된다.
    정답이 'warren'일 때 답안이 'varren'이라면 채점 결과는 0점이다.
    단, w는 정확히 인식하기 때문에, 정답이 'vaccine'일 때 답안이 'waccine'이라면 점수는 1점으로 채점된다.

    입력 :
    첫 번째 줄에 승연이가 작성한 답안의 길이 n, 정답의 길이 m이 공백을 두고 차례로 주어진다.
    두 번째 줄에 승연이가 작성한 답안이, 세 번째 줄에 정답이 주어진다.
    승연이가 작성한 답안과 정답은 모두 영어 소문자로만 이루어진다.
    출력 :
    첫 번째 줄에 승연이의 점수를 출력한다.

    수정, 삭제, 변환 가능
    i -> i,j,l 로 인식
    v -> v,w로 인식
    1 <= n,m <= 1,000,000
    1 <= n*m <= 10,000,000
    시간 제한 : 1초
    알고리즘
    LCS 응용 문제
    정답 * 답안 2차원 배열 생성
       f i s h c a k e
     0 1 2 3 4 5 6 7 8
   t 1 1 2 3 4 5 6 7 8
   a 2 2 2 3 4 5 5 6 7
   k 3 3 3 3 4 5 6 5 6
   e 4 4 4 4 4 5 6 6 5
   n 5 5 5 5 5 5 6 6 6
   비교하는 것이 같다면 수정할 필요 없음 -> n-1 m-1 값 사용
   비교하는 것이 다르다면 추가 or 변환 or 삭제 필요 -> (n-1, m-1) or (n-1, m) or (n, m-1) 값 중 최소 값 + 1 사용
     */
    static int N;
    static int M;
    static String answer;
    static String write;
    static int[][] board;
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());
        board = new int[N + 1][M + 1];
        for (int i = 0; i <= M; i++) {
            board[0][i] = i;
        }
        for (int i = 0; i <= N; i++) {
            board[i][0] = i;
        }

        write = bufferedReader.readLine();
        answer = bufferedReader.readLine();

        System.out.println(findLCS());

    }

    private static int findLCS() {

        for (int i = 1; i < board.length; i++) {
            char writeAt = write.charAt(i - 1);
            for (int j = 1; j < board[0].length; j++) {
                char answerAt = answer.charAt(j - 1);
                if (writeAt == answerAt) {
                    // 수정할 필요가 없다면
                    board[i][j] = board[i - 1][j - 1];
                } else {
                    // writeAt이 i이고 answerAt는 l or j 인 경우
                    if (writeAt == 'i' && (answerAt == 'l' || answerAt == 'j')) {
                        board[i][j] = board[i - 1][j - 1];
                        continue;
                    }
                    //writeAt이 v이고 answerAt 은 w 인 경우
                    if (writeAt == 'v' && answerAt == 'w') {
                        board[i][j] = board[i - 1][j - 1];
                        continue;
                    }
                    //수정할 필요가 있다면
                    board[i][j] = findMin(i, j) + 1;
                }
            }
        }
        return board[N][M];
    }


    private static int findMin(int i, int j) {
        int[] arr = new int[3];
        arr[0] = board[i - 1][j - 1];
        arr[1] = board[i - 1][j];
        arr[2] = board[i][j - 1];
        Arrays.sort(arr);
        return arr[0];
    }

}
