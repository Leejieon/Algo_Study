import java.util.*;

class Solution {
    static int N;
    static int[][] dice;
    static boolean[] select; // true : A, false : B
    static int[] visited;
    static int win = Integer.MIN_VALUE;
    static ArrayList<Integer> aSum, bSum;
    static int[] answer;

    public int[] solution(int[][] dice) {
        N = dice.length;
        this.dice = dice;
        select = new boolean[N];
        answer = new int[N/2];

        combination(0, 0);

        return answer;
    }

    void combination(int next, int count) {
        // Base Case
        if(count == N/2) {
            proceed();
            return;
        }

        // Recursive Case
        for(int i = next; i < N; i++) {
            select[i] = true;
            combination(i + 1, count + 1);
            select[i] = false;
        }
    }

    void proceed() {
        aSum = new ArrayList<>();
        bSum = new ArrayList<>();

        getSum(getDices(true), true, 0, 0);
        getSum(getDices(false), false, 0, 0);

        Collections.sort(bSum);

        int result = 0;
        visited = new int[501];
        Arrays.fill(visited, -1);
        for(int a : aSum) {
            if(visited[a] != -1) {
                result += visited[a];
                continue;
            }

            visited[a] = binarySearch(a, 0, bSum.size());
            result += visited[a];
        }

        if(result > win) {
            int index = 0;
            for(int i = 0; i < select.length; i++) {
                if(select[i]) {
                    answer[index++] = i + 1;
                }
            }
            win = result;
        }
    }

    ArrayList<Integer> getDices(boolean type) {
        ArrayList<Integer> dices = new ArrayList<>();
        for(int idx = 0; idx < select.length; idx++) {
            if(select[idx] == type) {
                dices.add(idx);
            }
        }
        return dices;
    }

    void getSum(ArrayList<Integer> list, boolean type, int sum, int count) {
        // Base Case
        if(count == list.size()) {
            if(type) {
                aSum.add(sum);
            } else {
                bSum.add(sum);
            }
            return;
        }

        // Recursive Case
        for(int i = 0; i < dice[0].length; i++) {
            getSum(list, type, sum + dice[list.get(count)][i], count + 1);
        }
    }

    int binarySearch(int target, int low, int high) {
        while(low < high) {
            int mid = (low + high) / 2;

            if(target > bSum.get(mid)) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return high;
    }
}