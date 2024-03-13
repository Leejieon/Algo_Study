import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Q1208 {
    static final int LEFT = 0;
    static int N, S;
    static long total;
    static int[] left,right;
    static Map<Long, Integer> map = new HashMap<>();
    static ArrayList<Long> leftSum = new ArrayList<>();
    static ArrayList<Long> rightSum = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        left = new int[N / 2];
        right = new int[N - N / 2];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            if (i < N / 2) {
                left[i] = Integer.parseInt(st.nextToken());
            } else {
                right[i - N / 2] = Integer.parseInt(st.nextToken());
            }
        }

        // 공집합
        leftSum.add(0L);
        rightSum.add(0L);
        for (int i = 0; i < N; i++) {
            if (i < left.length) {
                combination(0, 0, 0, i, 0, left);
            } else {
                combination(0,0,0, i - left.length, 1, right);
            }
        }

        Collections.sort(rightSum);
        long[] rightList = rightSum.stream()
                .mapToLong(Long::longValue)
                .toArray();

        for (Long aLong : leftSum) {
            long targetNum = S - aLong;
            if (map.containsKey(targetNum)) {
                total += map.get(targetNum);
                continue;
            }

            int targetIdx = Arrays.binarySearch(rightList, targetNum);
            if (targetIdx >= 0) {
                int count = 0;
                int idx = targetIdx;
                while (idx < rightSum.size()) {
                    if (targetNum != rightList[idx]) {
                        break;
                    }
                    count++;
                    idx++;
                }

                idx = targetIdx - 1;
                while (idx >= 0) {
                    if (targetNum != rightList[idx]) {
                        break;
                    }
                    count++;
                    idx--;
                }

                map.put(targetNum, count);
                total += count;
            }
        }

        // S가 0인 경우, 두 배열이 모두 공집합인 경우를 빼주어야 한다.
        if(S == 0) total--;

        System.out.println(total);
    }

    static void combination(int next, long sum, int count, int m, int type, int[] arr) {
        // Base Case
        if (count == m + 1) {
            if (type == LEFT) {
                leftSum.add(sum);
            } else {
                rightSum.add(sum);
            }
            return;
        }

        // Recursive Case
        for (int i = next; i < arr.length; i++) {
            combination(i + 1, sum + arr[i], count + 1, m, type, arr);
        }
    }
}