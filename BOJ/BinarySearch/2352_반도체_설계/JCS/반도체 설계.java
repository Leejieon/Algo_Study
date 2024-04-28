
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    /**
     * n 개의 포트를 n개의 포트와 연결 n 개의 포트가 다른 n 개의 포트와 어떻게 연결되어야 하는지 주어졌을 때, 연결선이 꼬이지 않도록 하면서 최대 몇 개까지 연결할 수 있는지 1<= N <=
     * 40,000 LCS 찾기
     */
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[] ports;
    static int[] lcs;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        ports = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        lcs = new int[N];
        int curLen = 0;
        for (int p : ports) {
            if (curLen == 0) {
                lcs[curLen++] = p;
                continue;
            }
            if (lcs[curLen-1] < p) {
                lcs[curLen++] = p;
                continue;
            }
            int idx = findIdx(0,curLen-1,p);
            lcs[idx] = p;
        }
        
        System.out.println(curLen);
    }

    public static int findIdx(int l, int r, int target) {

        while (l < r) {
            int mid = (l + r) / 2;
            if (lcs[mid] < target) {
                l = mid+1;
            } else {
                r = mid;
            }
        }
        return r;
    }
}
