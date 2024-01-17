import java.util.*;

class Solution {
    public int solution(int[] stones, int k) {
        int answer = 1;
        int left = 0;
        int right = Arrays.stream(stones).max().orElse(0);
        int[] newStones = new int[stones.length];

        while(left <= right) {
            int mid = (left + right) / 2;
            newStones = crossStones(stones, mid);

            if(canCross(newStones, k)) {
                left = mid + 1;
                answer = mid;
            } else {
                right = mid - 1;
            }
        }

        return answer + 1;
    }

    int[] crossStones(int[] stones, int person) {
        return Arrays.stream(stones)
                .map(value -> value - person)
                .toArray();
    }

    boolean canCross(int[] stones, int k) {
        int jump = 0;
        int maxJump = Integer.MIN_VALUE;

        for(int index = 0; index < stones.length; index++) {
            if(stones[index] <= 0) {
                jump++;
            } else {
                jump = 0;
            }

            maxJump = Math.max(maxJump, jump);
        }

        return k > maxJump;
    }
}