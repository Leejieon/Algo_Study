import java.util.*;
import java.lang.*;
class Solution {
    static List<Queen> list = new ArrayList<>();
    static int count = 0;
    static class Queen {
        int row;
        int col;
        public Queen(int row, int col) {
            this.row = row;
            this.col = col;
        }
        
        public boolean canAttack(int row, int col) {
            if(this.row == row) {
                return true;
            }
            if(this.col == col) {
                return true;
            }
            if(Math.abs(this.row-row) == Math.abs(this.col-col)) {
                return true;
            }
            return false;
        }
    }
    public int solution(int n) {
        recur(1,n);
        return count;
    }
    private void recur(int depth, int n) {
        if (depth == n+1) {
            count ++;
            return;
        }
        for(int i=1; i<=n; i++) {
                boolean flag = false;
                for(Queen q : list) {
                    if(q.canAttack(depth,i)) {
                        flag = true;
                        break;
                    }
                }
                if(flag) {
                    continue;
                }
                Queen queen = new Queen(depth,i);
                list.add(queen);
                recur(depth+1, n);
                list.remove(queen);
            }
        return;
    }
}