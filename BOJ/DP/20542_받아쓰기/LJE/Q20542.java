import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q20542 {
    static int[][] dp;
    static int N, M;
    static String word, answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 작성한 답안 길이
        M = Integer.parseInt(st.nextToken()); // 정답 길이
        dp = new int[N + 1][M + 1];

        word = br.readLine();
        answer = br.readLine();

        lcs(word, answer);

        System.out.println(dp[N][M]);
    }

    static void lcs(String word, String answer) {
        for(int i=0;i<N+1;i++){
            dp[i][0] = i;
        }

        for(int i=0;i<M+1;i++){
            dp[0][i] = i;
        }

        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < M + 1; j++) {
                if (isSame(word.charAt(i - 1), answer.charAt(j - 1))) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
            }
        }
    }

    static boolean isSame(char c1, char c2) {
        if(c1 == c2)
            return true;

        if(c1 == 'i') {
            if(c2 == 'j' || c2 == 'l')
                return true;
        }

        if (c1 == 'v') {
            if (c2 == 'w')
                return true;
        }

        return false;
    }
}
