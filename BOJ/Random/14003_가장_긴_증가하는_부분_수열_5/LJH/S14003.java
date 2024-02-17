import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class S14003 {

    static BufferedReader br;
    static StringTokenizer st;
    static int n;
    static int[] numbers;
    static int[] idx;

    static ArrayList<Integer> result;

    static List<Integer> ary;

    public static void main(String[] args) throws IOException {
        input();
        Solution();
        findIdx();
        StringBuilder sb = new StringBuilder();
        sb.append(result.size()).append("\n");
        for (int i = result.size() - 1; i >= 0; i--) {
            sb.append(result.get(i)).append(" ");
        }
        System.out.println(sb);
    }

    public static void input() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        numbers = new int[n + 1];
        idx = new int[n + 1];
        ary = new ArrayList<>();
        result = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
    }

    public static void Solution() {
        ary.add(numbers[1]);
        idx[1] = 1;
        for (int i = 1; i <= n; i++) {
            if (ary.get(ary.size() - 1) < numbers[i]) {
                ary.add(numbers[i]);
                idx[i] = ary.size() - 1;
            } else { //아닐때는 넣을 위치를 찾아줘야 한다.
                int numberIdx = binarySearch(i, 0, ary.size() - 1);
                ary.set(numberIdx, numbers[i]);
                idx[i] = numberIdx;
            }
        }
    }

    public static int binarySearch(int idx, int left, int right) {
        int mid;
        while (left < right) {
            mid = (left + right) / 2;
            if (numbers[idx] == ary.get(mid)) {
                return mid;
            }
            if (numbers[idx] > ary.get(mid)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    public static void findIdx() { //나보다 idx값이 큰 녀석이 등장했는지 안했는지를 찾아야 함.
        int index = ary.size() - 1;
        for (int i = n; i >= 1; i--) {
            if (index == idx[i]) {
                result.add(numbers[i]);
                index--;
            }
        }
    }
}