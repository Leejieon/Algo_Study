import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Q2143 {
    static int T;
    static int[] a, b;
    static Map<Integer, Integer> b_map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        int a_length = Integer.parseInt(br.readLine());
        a = new int[a_length];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < a_length; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        int b_length = Integer.parseInt(br.readLine());
        b = new int[b_length];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < b_length; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }

        int a_sum_length = a_length * (a_length + 1) / 2;
        int[] a_sum = new int[a_sum_length];
        int index = 0;
        for (int i = 0; i < a_length; i++) {
            int sum = 0;
            for (int j = i; j < a_length; j++) {
                sum += a[j];
                a_sum[index++] = sum;
            }
        }

        int b_sum_length = b_length * (b_length + 1) / 2;
        int[] b_sum = new int[b_sum_length];
        index = 0;
        for (int i = 0; i < b_length; i++) {
            int sum = 0;
            for (int j = i; j < b_length; j++) {
                sum += b[j];
                b_sum[index++] = sum;
            }
        }

        Arrays.sort(b_sum);

        long answer = 0;
        for (int i = 0; i < a_sum_length; i++) {
            int number = T - a_sum[i];

            if (b_map.containsKey(number)) {
                answer += b_map.get(number);
            }
            else {
                int findIndex = binarySearch(b_sum, number);
                if (findIndex != -1) {
                    int count = 1 + leftCount(b_sum, findIndex, number) + rightCount(b_sum, findIndex, number);
                    b_map.put(number, count);
                    answer += count;
                }
            }
        }

        System.out.println(answer);
    }

    static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                return mid; // 찾는 값이 존재하는 경우 해당 인덱스 반환
            } else if (arr[mid] < target) {
                left = mid + 1; // 중간값이 찾는 값보다 작으면 오른쪽 영역으로 이동
            } else {
                right = mid - 1; // 중간값이 찾는 값보다 크면 왼쪽 영역으로 이동
            }
        }
        return -1; // 찾는 값이 존재하지 않는 경우 -1 반환
    }

    static int leftCount(int[] arr, int index, int target) {
        int result = 0;
        while (true) {
            index--;
            if (index < 0) {
                break;
            }
            if (arr[index] == target) {
                result++;
            }
        }
        return result;
    }

    static int rightCount(int[] arr, int index, int target) {
        int result = 0;
        while (true) {
            index++;
            if (index >= arr.length) {
                break;
            }
            if (arr[index] == target) {
                result++;
            }
        }
        return result;
    }
}
