import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class Main {

    static Map<Integer, Integer> lines = new HashMap<>();
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static List<Integer> LIS = new ArrayList<>();
    static int N;
    static class BacktrackInfo {
        int number, order;

        public BacktrackInfo(int number, int order) {
            this.number = number;
            this.order = order;
        }
    }


    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bf.readLine());

        for (int i = 0; i < N; i++) {
            int[] inputs = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            lines.put(inputs[0], inputs[1]);
        }

        List<Integer> sortKey = new ArrayList<>(lines.keySet());
        Collections.sort(sortKey);

        Stack<BacktrackInfo> backtrack = new Stack<>();
        for (int i : sortKey) {
            int idx;
            if (LIS.isEmpty()) {
                LIS.add(lines.get(i));
                idx = 0;
            } else if (LIS.get(LIS.size() - 1) < lines.get(i)) {
                LIS.add(lines.get(i));
                idx = LIS.size()-1;
            } else {
                int floorIdx = findFloorIdx(0, LIS.size(), lines.get(i));
                LIS.set(floorIdx, lines.get(i));
                idx = floorIdx;
            }
            backtrack.push(new BacktrackInfo(lines.get(i),idx));
        }
        Set<Integer> noCrossingLine = new HashSet<>();
        int len = LIS.size()-1;
        while (!backtrack.isEmpty() || len >= 0) {
            if(backtrack.peek().order == len) {
                noCrossingLine.add(backtrack.peek().number);
                len--;
            }
            backtrack.pop();
        }

        StringBuilder sb = new StringBuilder();
        sb.append(lines.keySet().size() - LIS.size()).append("\n");
        for(int key : sortKey) {
            if(!noCrossingLine.contains(lines.get(key))) {
                sb.append(key).append("\n");
            }
        }

        System.out.print(sb);
    }

    private static int findFloorIdx(int left, int right, int target) {
        if (right == 0) {
            return 0;
        }
        while (left < right) {
            int mid = (left + right) / 2;

            if (LIS.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right;
    }
}
