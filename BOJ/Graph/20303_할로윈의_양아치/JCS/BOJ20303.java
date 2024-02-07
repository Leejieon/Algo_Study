package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class BOJ20303 {

    static class Kid implements Comparable<Kid> {
        int id;
        int candy;

        public Kid(int root, int candy) {
            this.id = root;
            this.candy = candy;
        }

        @Override
        public int compareTo(Kid o) {
            return this.id - o.id;
        }
    }

    static List<Set<Kid>> community = new ArrayList<>();
    static int[] parent;
    static int N, M, K;
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        parent = new int[N + 1];

        st = new StringTokenizer(bf.readLine());
        for (int i = 1; i <= N; i++) {
            Set<Kid> set = new TreeSet<>();
            set.add(new Kid(i, Integer.parseInt(st.nextToken())));
            community.add(set);
            parent[i] = i;
        }

        for (int i = 1; i <= M; i++) {
            int[] friend = Arrays.stream(bf.readLine().split(" ")).mapToInt(s -> Integer.parseInt(s)).toArray();
            if (!isFriend(friend[0], friend[1])) {
                makeFriend(friend[0], friend[1]);
            }
        }

        List<Set<Kid>> onlyComponent = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            if (parent[i] == i) {
                onlyComponent.add(community.get(i - 1));
            }
        }

        int[][] dp = new int[onlyComponent.size() + 1][K];

        for (int i = 0; i < onlyComponent.size(); i++) {
            int kids = onlyComponent.get(i).size();
            int candy = getTotalCandyOf(onlyComponent.get(i));
            for (int k = 1; k < K; k++) {
                if (kids > k) {
                    dp[i + 1][k] = dp[i][k];
                } else {
                    dp[i + 1][k] = Math.max(dp[i][k - kids] + candy, dp[i][k]);
                }
            }
        }

        System.out.println(dp[onlyComponent.size()][K - 1]);


    }

    private static int getTotalCandyOf(Set<Kid> kidSet) {
        int totalCandy = 0;
        for (Kid k : kidSet) {
            totalCandy += k.candy;
        }
        return totalCandy;
    }

    private static void merge(Set<Kid> dest, Set<Kid> src) {
        dest.addAll(src);
    }

    private static void makeFriend(int kid1, int kid2) {
        int rootIdx1 = getRootFriend(kid1);
        int rootIdx2 = getRootFriend(kid2);
        if (rootIdx1 <= rootIdx2) {
            parent[rootIdx2] = rootIdx1;
            merge(community.get(rootIdx1 - 1), community.get(rootIdx2 - 1));
            return;
        }
        parent[rootIdx1] = rootIdx2;
        merge(community.get(rootIdx2 - 1), community.get(rootIdx1 - 1));
    }

    private static boolean isFriend(int kid1, int kid2) {
        return getRootFriend(kid1) == getRootFriend(kid2);
    }

    private static int getRootFriend(int kidId) {
        while (parent[kidId] != kidId) {
            kidId = parent[kidId];
        }
        return kidId;
    }
}
