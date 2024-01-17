
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static List<Integer> waters = new ArrayList<>();
    static ThreePoint answer;

    static class ThreePoint {
        int lo;
        int mid;
        int hi;

        public ThreePoint(int lo, int mid, int hi) {
            this.lo = lo;
            this.mid = mid;
            this.hi = hi;
        }
    }

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        waters = Arrays.stream(bufferedReader.readLine().split(" ")).map(s -> Integer.parseInt(s))
                .collect(Collectors.toList());
        Collections.sort(waters);

        solution();

        System.out.println(waters.get(answer.lo) + " " + waters.get(answer.mid) + " " + waters.get(answer.hi));


    }

    private static void solution() {
        long diff = Long.MAX_VALUE;

        for (int i = 0; i < waters.size(); i++) {
            int left = i + 1;
            int right = waters.size() - 1;

            while (left < right) {
                ThreePoint threePoint = new ThreePoint(i, left, right);
                long total = sumOf(threePoint);
                if(total == 0) {
                    answer = threePoint;
                    return;
                }
                if(diff > Math.abs(total)) {
                    diff = Math.abs(total);
                    answer = threePoint;
                }

                if (total < 0) {
                    left ++;
                    continue;
                }
                right --;
            }
        }


    }


    private static long sumOf(ThreePoint tp) {
        return (long) waters.get(tp.lo) + (long) waters.get(tp.mid) + (long) waters.get(tp.hi);
    }

}
