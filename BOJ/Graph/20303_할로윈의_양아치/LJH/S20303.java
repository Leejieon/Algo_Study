import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class S20303 {

    static BufferedReader br;
    static StringTokenizer st;
    static int n, m, k;
    static int[] c;
    static int[] ary;
    static long[][] dp;
    static long answer = 0;
    static int[] totalCandy;
    static int[] people;
    static Set<Integer> s = new HashSet<Integer>();

    public static void main(String[] args) throws IOException {
        input();
        sumCandy();
        solution();
        System.out.print(answer);
    }

    public static void input() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = new int[n + 1];
        ary = new int[n + 1];
        totalCandy = new int[n + 1];
        people = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            c[i] = Integer.parseInt(st.nextToken());
            ary[i] = i; // 자기 자신을 일단 넣어둠.
        }
        int a, b;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            union(a, b);
        }
    }

    public static void union(int a, int b) {
        int rootA = find(a); // a의 부모 찾기
        int rootB = find(b); // b의 부모 찾기
        if (rootA == rootB)
            return;
        if (rootA < rootB) { // B의 부모가 크다면
            ary[rootB] = rootA;
        } else {
            ary[rootA] = rootB;
        }
    }

    public static int find(int o) {
        if (ary[o] != o) { // 연결되어있다면
            ary[o] = find(ary[o]);
            return ary[o];
        }
        return o;
    }

    public static void sumCandy() {
        for (int i = 1; i <= n; i++) {
            int parent = find(i);
            s.add(parent); // 집합에 부모 추가하기.
            people[parent]++;
            totalCandy[parent] += c[i];
        }
    }

    public static void solution() {
        dp = new long[n + 1][k];
        ArrayList<Integer> temp = new ArrayList<Integer>();
        temp.addAll(s);
        for (int i = 1; i <= temp.size(); i++) {
            int nowPeople = people[temp.get(i - 1)];
            int nowCandy = totalCandy[temp.get(i - 1)];
            for (int j = k - 1; j >= 0; j--) {
                if (j - nowPeople >= 0) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - nowPeople] + nowCandy);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
            answer = Math.max(answer, Arrays.stream(dp[i]).max().getAsLong());
        }
    }
}