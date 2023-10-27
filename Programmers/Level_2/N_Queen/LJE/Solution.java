class Solution {
    static final int MAX_ROW = 12;
    static int[] queen = new int[MAX_ROW + 1];

    public int solution(int n) {
        int answer = 0;

        answer = recur(n, 0);

        return answer;
    }

    static int recur(int n, int row) {
        int count = 0;

        // Base Case
        if(row == n) {
            return 1;
        }

        for(int col = 0; col < n; col++) {
            if(isValid(row, col)) {
                queen[row] = col;
                count += recur(n, row + 1);
            }
        }

        return count;
    }

    static boolean isValid(int row, int col) {
        for(int index = 0; index < row; index++) {
            // 열 확인
            if(queen[index] == col) {
                return false;
            }

            // 대각선 확인
            if(Math.abs(row - index) == Math.abs(col - queen[index])) {
                return false;
            }
        }

        return true;
    }
}