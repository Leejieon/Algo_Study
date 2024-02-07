import java.io.*;
import java.util.*;

public class S7570 {

    static BufferedReader br;
    static StringTokenizer st;
    static int n;
    static long answer = 0;
    static Queue<Integer> q = new LinkedList<>();

    static int[] dp;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        input();
        solution();
        System.out.print(n - answer);
    }

    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        dp = new int[n + 1];
        for (int i = 0; i < n; i++) {
            q.add(Integer.parseInt(st.nextToken())); //넣을때마다 뒤로 넣어짐.
        }
    }

    public static void solution() {
        while (!q.isEmpty()) {
            int now = q.poll();
            if (dp[now - 1] == 0) { //아직까지 어린이가 나오지 않았다면
                dp[now] = 1;
            } else { //앞에 숫자가 나왔다면 이어줌.
                dp[now] = dp[now - 1] + 1;
            }
        }
        for(int i=1; i<=n; i++){
            answer = Math.max(answer,dp[i]);
        }
    }
}