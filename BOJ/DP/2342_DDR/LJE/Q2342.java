import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Q2342 {
    static ArrayList<Integer> list = new ArrayList<>();
    static int answer = Integer.MAX_VALUE;
    static int[][][] dp;

    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int number;
        while ((number = Integer.parseInt(st.nextToken())) != 0) {
            list.add(number);
        }

        if (list.isEmpty()) {
            System.out.println(0);
            return;
        }

        dp = new int[5][5][list.size()];
        System.out.println(recur(0, 0, 0));
    }

    static int recur(int depth, int left, int right) {
        // Base Case
        if (depth == list.size()) {
            return 0;
        }
        if (dp[left][right][depth] != 0) {
            return dp[left][right][depth];
        }

        // Recursive Case
        int next = list.get(depth);
        return dp[left][right][depth]
                = Math.min(recur(depth + 1, next, right) + getForce(next, left),
                recur(depth + 1, left, next) + getForce(next, right));
    }

    static int getForce(int move, int pos) {
        if (pos == 0) {
            return 2;
        }

        int result = 0;
        switch (Math.abs(move - pos)) {
            case 0 :
                result = 1;
                break;
            case 1: case 3:
                result = 3;
                break;
            case 2:
                result = 4;
        }
        return result;
    }
}