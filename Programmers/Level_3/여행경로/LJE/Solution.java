package Level_3.여행경로.LJE;

import java.util.*;

class Solution {
    static PriorityQueue<String> result = new PriorityQueue<>();
    static boolean[] visited;

    public String[] solution(String[][] tickets) {
        String[] answer = {};
        visited = new boolean[tickets.length];

        dfs("ICN", "ICN", 1, tickets);
        answer = result.poll().split(" ");
        return answer;
    }

    static void dfs(String start, String path, int count, String[][] tickets) {
        // Base Case
        if(count == tickets.length + 1) {
            result.offer(path);
            return;
        }

        // Recursive Case
        for(int i = 0; i < tickets.length; i++) {
            if(!visited[i] && start.equals(tickets[i][0])) {
                visited[i] = true;
                dfs(tickets[i][1], path + " " + tickets[i][1], count + 1, tickets);
                visited[i] = false;
            }
        }
    }
}