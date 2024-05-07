import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static Integer N;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] buildings;

    static class BuildingInfo {
        int height;
        int idx;

        public BuildingInfo(int height, int idx) {
            this.height = height;
            this.idx = idx;
        }
    }

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        buildings = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            buildings[i] = Integer.parseInt(st.nextToken());
        }
        int[] near = new int[N + 1];
        int[] cnt = new int[N + 1];
        Stack<BuildingInfo> stack = new Stack<>();

        for (int i = N; i >= 1; i--) {
            while (!stack.isEmpty() && stack.peek().height <= buildings[i]) {
                stack.pop();
            }
            cnt[i] += stack.size();
            if (!stack.isEmpty()) {
                near[i] = stack.peek().idx;
            }
            stack.push(new BuildingInfo(buildings[i], i));
        }

        Stack<BuildingInfo> stack2 = new Stack<>();
        for (int i = 1; i <= N; i++) {
            while (!stack2.isEmpty() && stack2.peek().height <= buildings[i]) {
                stack2.pop();
            }
            cnt[i] += stack2.size();
            if (!stack2.isEmpty()) {
                int idx = stack2.peek().idx;
                if (Math.abs(idx - i) < Math.abs(near[i] - i)) {
                    near[i] = idx;
                } else if (Math.abs(idx - i) == Math.abs(near[i] - i)) {
                    near[i] = Math.min(near[i], idx);
                }
            }

            stack2.push(new BuildingInfo(buildings[i], i));
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= N; i++) {
            if(cnt[i] == 0) {
                sb.append(0).append("\n");
                continue;
            }
            sb.append(cnt[i]).append(" ").append(near[i]).append("\n");
        }
        System.out.print(sb.toString());
    }
}
