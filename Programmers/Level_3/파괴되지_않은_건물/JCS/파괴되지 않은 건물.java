import java.util.*;
class Solution {
    /*
    각 칸마다 내구도
    공격 -> 내구도가 0이하가 되면 파괴
    회복 스킬 -> 내구도 상승
    공격 및 회복 -> 직사각형 모양
    공격과 회복 반복
    파괴되지 않은 건물의 수를 반환
    맵의 크기 1000 * 1000 -> 1,000,000
    내구도 최대 1,000
    skill -> 최대 250,000개
    
    공격 및 회복을 실제로 업데이트하면 시간초과
    스킬에 대해서만 처리해주어야 할듯
    [0,0,0,0] 1 ~ 2 -1 
    */
    int [][] attackAndRecovery;
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        attackAndRecovery = new int[board.length+1][board[0].length+1];
        
        for(int [] action : skill) {
            process(action);
        }
        for(int row = 0; row<attackAndRecovery.length; row++) {
            int sum = 0;
            for(int col=0; col<attackAndRecovery[row].length; col++) {
                sum += attackAndRecovery[row][col];
                attackAndRecovery[row][col] = sum;
            }
        }
        for(int col = 0; col<attackAndRecovery[0].length; col++) {
            int sum = 0;
            for(int row=0; row<attackAndRecovery.length; row++) {
                sum += attackAndRecovery[row][col];
                attackAndRecovery[row][col] = sum;
            }
        }
        for(int row = 0; row<board.length; row++) {
            for(int col=0; col<board[row].length; col++) {
                if(board[row][col] +attackAndRecovery[row][col] > 0) {
                    answer ++;
                }
            }
        }
       
        return answer;
    }
    private void process(int[] action) {
        int type = action[0];
        int startRow = action[1];
        int startCol = action[2];
        int endRow = action[3];
        int endCol = action[4];
        int degree = type == 1 ? action[5]*-1 : action[5];
        attackAndRecovery[startRow][startCol] += degree;
        attackAndRecovery[startRow][endCol+1] += degree*(-1);
        attackAndRecovery[endRow+1][startCol] += degree*(-1);
        attackAndRecovery[endRow+1][endCol+1] += degree;
    }
}
