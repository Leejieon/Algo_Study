class Solution {
    static int[] lion = new int[11];
    static int[] answer = {-1};
    static int savedDiff = Integer.MIN_VALUE;

    public int[] solution(int n, int[] info) {
        recurArrow(n, info, 0);

        if(savedDiff == -1) {
            answer = new int[1];
            answer[0] = -1;
        }

        return answer;
    }

    static void recurArrow(int n, int[] info, int arrow) {
        // Base Case
        if(arrow == n) {
            int diff = getScoreDiff(info);

            // lion win
            if(diff >= savedDiff) {
                savedDiff = diff;
                answer = lion.clone();
            }
            return;
        }

        // Recursive Case
        for(int i=0; i<info.length && lion[i] <= info[i]; i++) {
            lion[i] += 1;
            recurArrow(n, info, arrow + 1);
            lion[i] -= 1;
        }

    }

    static int getScoreDiff(int[] info) {
        int lionScore = 0;
        int apeachScore = 0;

        for(int i=0;i<11;i++) {
            if(lion[i] == 0 && info[i] == 0) {
                continue;
            }

            if(lion[i] > info[i]) {
                lionScore += 10 - i;
                continue;
            }
            apeachScore += 10 - i;
        }

        int diff = lionScore - apeachScore;
        if(diff <= 0)
            return -1;

        return diff;
    }
}