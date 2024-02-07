import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

class Q20303 {
    static int N, M, K;
    static int[] candies;
    static int[] parents;
    static long[][] group;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        parents = new int[N + 1];
        candies = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            candies[i] = Integer.parseInt(st.nextToken());
            parents[i] = i;
        }

        // Union-Find 진행
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            union(node1, node2);
        }

        // group[i][0] := i 그룹의 인원 수, group[i][1] := i 그룹의 모든 사탕 수
        group = new long[N + 1][2];
        for (int index = 1; index <= N; index++) {
            int gNum = find(parents[index]);
            group[gNum][0]++; // 해당 그룹 인원 증가
            group[gNum][1] += candies[index];
        }

        // List 에 그룹을 담고 인원 수를 기준으로 정렬
        ArrayList<Node> list = new ArrayList<>();
        for (int index = 1; index <= N; index++) {
            if (parents[index] == index) {
                list.add(new Node((int)group[index][0], group[index][1]));
            }
        }
        Collections.sort(list, ((o1, o2) -> o1.cost - o2.cost));

        long[][] dp = new long[list.size() + 1][K];
        for (int i = 1; i <= list.size(); i++) {
            for (int j = 0; j < K; j++) {
                if (list.get(i - 1).cost > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j - list.get(i - 1).cost] + list.get(i - 1).value, dp[i - 1][j]);
                }
            }
        }
        System.out.println(dp[list.size()][K - 1]);

    }

    static boolean union(int node1, int node2) {
        node1 = find(node1);
        node2 = find(node2);

        if (node1 == node2)
            return false;

        if (node1 <= node2)
            parents[node2] = node1;
        else
            parents[node1] = node2;
        return true;
    }

    static int find(int node) {
        if(parents[node] == node)
            return node;
        return parents[node] = find(parents[node]);
    }

    static class Node {
        int cost;
        long value;

        Node(int cost, long value) {
            this.cost = cost;
            this.value = value;
        }
    }

}