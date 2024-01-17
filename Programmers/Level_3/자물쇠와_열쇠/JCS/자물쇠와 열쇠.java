import java.util.*;
import java.lang.*;

class Solution {
    /*
    자물쇠 N * N
    열쇠 M * M
    열쇠 회전 및 이동 가능
    자물쇠의 홈 부분에 딱 맞게 채우면 열린다.
    열쇠를 회전 및 이동해서 자물쇠의 홈 부분에 맞는 모양을 만들 수 있으면 true 아니면 false 반환
    
    자물쇠의 홈 부분 => 0으로 표시
    열쇠의 돌기 부분 => 1
    M , N => 최대 20
    
    자물쇠의 홈부분을 구성하는 최소 크기 m*m 구하기
    최소 크기의 홈 부분을 90도로 3번 회전시키기 각 모양 저장
    열쇠에서 홈부분의 최소 부분을 비교하기 -> 일치한다면 true
    
    예시에서
    최소 부분 => 2*2 이고 [[1,0],[0,1]] , [[0,1], [1,0]] 이 나올수 있음
    key에서 2*2 씩 검사
    [[1,0], [0,1]] 발견 => true;
    
    lock 에서 최소부분 뽑아내는 함수
    최소부분 시계방향으로 회전시켜 나올 수 있는 부분 뽑아내는 함수
    최소 부분만큼 검사하는 함수
    */
    public boolean solution(int[][] key, int[][] lock) {
        int count = 0;
        for(int i=0;i<lock.length;i++) {
            for(int j=0; j<lock[i].length; j++) {
                if(lock[i][j] == 0) {
                    count ++;
                }
            }
        }
        if(count == 0) {
            return true;
        }
        int [][] minLock = getMinLock(lock);
        for(int i=0; i<4;i++) {
            if(canOpen(key, minLock)){
                return true;
            }
            minLock = turnClockwise(minLock);
        }
        return false;
    }
    private boolean canOpen(int [][] key, int[][] minLock) {
        if(key.length < minLock.length) {
            return false;
        }
        int row = minLock.length;
        int col = minLock[0].length;
        int [][] slice = new int[row][col];
        for(int i=0; i <= key.length - row; i++) {
            for(int j=0; j<= key[i].length - col; j++) {
                for(int k = 0; k<row; k++) {
                    slice[k] = Arrays.copyOfRange(key[i+k],j,j+col);
                }
                if(open(minLock, slice)) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean open(int [][] lock, int [][] key) {
        assert lock.length == key.length;
        assert lock[0].length == key[0].length;
        
        for(int i=0; i<lock.length; i++) {
            for(int j=0; j<lock[i].length; j++) {
                if(lock[i][j] + key[i][j] != 1) {
                    return false;
                }
            }
        }
        return true;
    }
    private int[][] turnClockwise(int [][] lock) {
        int row = lock.length;
        int col = lock[0].length;
        
        int [][] turnArr = new int[col][row];
        
        for(int i=0; i<col; i++) {
            for(int j=0; j<row; j++) {
                turnArr[i][j] = lock[row-1-j][i];
            }
        }
        return turnArr;
    }
    private int[][] getMinLock(int [][] lock) {
        int minRow = 21;
        int maxRow = 0;
        int minCol = 21;
        int maxCol = 0;
        
        for(int row = 0; row<lock.length; row++) {
            for(int col = 0; col<lock[row].length; col++) {
                if(lock[row][col] == 0) {
                    minRow = Math.min(row,minRow);
                    maxRow = Math.max(row,maxRow);
                    minCol = Math.min(col,minCol);
                    maxCol = Math.max(col,maxCol);
                }
            }
        }
        int [][] minLock = new int [maxRow-minRow +1][maxCol - minCol +1];
        for(int row = minRow; row<=maxRow; row++) {
            for(int col = minCol; col<=maxCol; col++) {
                minLock[row-minRow][col-minCol] = lock[row][col];
            }
        }
        return minLock;
    }
}