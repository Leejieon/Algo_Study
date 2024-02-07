import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S1509 {

    static BufferedReader br;
    static StringTokenizer st;
    static String pal;
    static int[][] palindrom;
    static int length;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        input();
        solution();
        palDp();
        System.out.print(dp[length]);
    }

    public static void input() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        pal = st.nextToken();
        length = pal.length();
        palindrom = new int[length + 1][length + 1];
        dp = new int[length + 1];
        for (int i = 0; i <= length; i++) {
            for (int j = 0; j <= length; j++) {
                palindrom[i][j] = -1;
            }
        }
    }

    // 팰린드롬 분할 집합의 개수.
    public static void solution() {
        for (int i = 0; i < length; i++) {
            for (int j = i; j < length; j++) {
                pal(i, j);
            }
        }
    }

    public static int pal(int start, int end) {
        if (palindrom[start + 1][end + 1] != -1) { //이미 계산을 한 경우
            return palindrom[start + 1][end + 1];
        }

        if (pal.charAt(start) != pal.charAt(end)) {
            palindrom[start + 1][end + 1] = 0;
            return palindrom[start + 1][end + 1];
        } else { //같을 경우
            palindrom[start + 1][end + 1] = 1;
            if (start + 1 <= end - 1) { //다음 검사에서 교차하지 않는다면 계속해서 검사를 해서 누적해야함.
                palindrom[start + 1][end + 1] = palindrom[start + 1][end + 1] * pal(start + 1, end - 1);
            }
            return palindrom[start + 1][end + 1];
        }
    }

    public static void palDp() {
        for (int e = 1; e <= length; e++) { //end는 1부터 길이까지 돌리 기
            dp[e] = 2501;
            for (int s = 1; s <= e; s++) { //start는 1부터 e까지
                if (palindrom[s][e] == 1) { //팰린드롬이라는 뜻.
                    dp[e] = Math.min(dp[e], dp[s - 1] + 1); //e까지의 최소 팰린드롬의 수는
                }
            }
        }
    }
}