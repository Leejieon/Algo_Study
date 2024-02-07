import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Q1509 {
    static String str;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        str = br.readLine();
        int[] dp = new int[str.length() + 1]; // i 번 째까지의 분할 개수의 최소값

        dp[1] = 1; // 1번 째는 팰린드롬 최대 1개
        for (int i = 2; i < str.length() + 1; i++) {
            for (int j = 1; j < i; j++) {
                // j~i가 팰린드롬인지 확인
                if (isPalindrome(j, i)) {
                    // 아직 체크한 이력이 없을 경우, 맨 뒤에 분할의 개수 추가
                    if (dp[i] == 0) {
                        dp[i] = dp[i - 1] + 1;
                    }
                    // 기존 계산값과 새로운 계산값 비교
                    dp[i] = Math.min(dp[i], dp[j - 1] + 1);
                }
            }
            // 팰린드롬이 만들어지지 않았을 경우, 1개 추가
            if (dp[i] == 0) {
                dp[i] = dp[i - 1] + 1;
            }
        }

        System.out.println(dp[str.length()]);
    }

    static boolean isPalindrome(int L, int R) {
        boolean result = true;
        L--; R--; // dp 배열의 인덱스가 문자열 인덱스보다 1 크기 때문에
        while (L <= R) {
            if (str.charAt(L) != str.charAt(R)) return false;
            L++;
            R--;
        }
        return result;
    }
}