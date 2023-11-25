import java.util.*;
class Solution {
    public int solution(int[] stones, int k) {
        int answer = 0;
        int low = 0;
        int hi = 200000000;
        
        while(low <= hi) {
            int mid = (low + hi) / 2;
            if(canAcross(stones, k, mid)) {
                low = mid + 1;
                answer = Math.max(answer, mid);
                continue;
            }
            hi = mid-1;
        }
        return answer;
    }
    private boolean canAcross(int[] stones, int k, int mid) {
        int count = 0;
        for (int stone : stones) {
            if(stone >= mid) {
                count=0;
                continue;
            }
            if(++count == k ) {
                return false;
            }
        }
        return true;
    }
}