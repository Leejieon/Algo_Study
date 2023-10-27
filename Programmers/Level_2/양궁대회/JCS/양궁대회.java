import java.util.*;

class Solution {
    static int[] shoot = new int[11];
    static int maxScore = 0;
    public int[] solution(int n, int[] info) {
        int[] answer = {-1};
        int [] history = new int[11];
        recur(0,n,info,0,history);
        for(int i=0;i<=10;i++) {
            if(shoot[i]!= 0) {
                return shoot;
            }
        }
        return answer;
    }
    private static void recur(int depth, int n, int [] info, int before, int [] history) {
        if(depth == n) {
            int score = getScore(info, history);
            if(score>0 && maxScore < score) {
                maxScore = score;
                shoot = Arrays.copyOf(history, history.length);
                return;
            }
            if(score>0 && maxScore == score) {
                for(int i = 10; i >= 0; i--){
                if(shoot[i] < history[i]){ 
                    shoot =  Arrays.copyOf(history, history.length);
                    break;
                }else if(shoot[i] > history[i]){ 
                    break;
                }
            }
        }
            return;
        }
        for(int i=before;i<=10;i++) {
            history[i] += 1;
            recur(depth+1, n, info, i, history);
            history[i] -= 1;
        }
        return;
    }
    private static int getScore(int [] info, int [] history) {
        int aScore = 0;
        int lScore = 0;
        for(int i=0; i<=10; i++) {
            if(info[i] < history[i]) {
                lScore += 10-i;
                continue;
            }
            if(info[i]!=0 && info[i] >= history[i]) {
                aScore += 10-i;
            }
        }
        return lScore - aScore;
    }
}
