import java.util.Scanner;

class Q2661 {
    static int N;
    static int[] numbers = new int[80];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        solve(0);
    }

    static boolean solve(int endIndex) {
        // BaseCase : 구하고자 하는 길이에 도달할 경우
        if (endIndex == N) {
            // 결과 출력하기
            for (int i = 0; i < endIndex; i++) {
                System.out.print(numbers[i]);
            }
            return true;
        }

        // 마지막 index에 1, 2, 3 순서로 삽입
        for (int i = 1; i <= 3; i++) {
            numbers[endIndex] = i;
            if(!isBadNumber(endIndex)){
                if (solve(endIndex + 1)) {
                    return true;
                }
            }
        }

        return false;
    }

    // 나쁜 숫자인지 확인
    static boolean isBadNumber(int endIndex) {
        for (int i = 1; i <= (endIndex + 1) / 2; i++) {
            boolean isSame = true;

            for (int j = 0; j < i; j++) {
                if (numbers[endIndex - j] != numbers[endIndex - (i + j)]) {
                    isSame = false;
                    break;
                }
            }

            if(isSame)
                return true;
        }

        return false;
    }
}
