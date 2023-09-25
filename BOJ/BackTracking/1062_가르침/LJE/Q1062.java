import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Q1062 {
    public static int N, K, check, result;
    public static String[] words;
    public static Set<Integer> learnedList = new HashSet<>();
    public static final int START_INDEX = 4;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 단어 수
        K = Integer.parseInt(st.nextToken()); // 가르칠 글자 수

        // 가르칠 수 있는 글자가 5보다 적은 경우, a, n, t, i, c를 배울 수 없다.
        if (K < 5) {
            System.out.println(0);
            return;
        }

        words = new String[N];
        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
        }

        result = 0;
        check = init();

        solve(5);
        System.out.println(result);

    }

    public static void solve(int index){
        // BaseCase : K 개의 글자를 가르쳤을 경우
        if (index == K) {
            // 기존에 배웠던 글자의 집합이 아닐 경우만 확인
            if (!learnedList.contains(check)) {
                learnedList.add(check);
                result = Math.max(result, getCount());
            }
            return;
        }

        // RecursiveCase : K 개수만크 글자 가르치기
        for (int i = 0; i < N; i++) {
            String word = words[i];

            for (int j = START_INDEX; j < word.length() - 4; j++) {
                int ch = word.charAt(j) - 'a';

                // 배우지 않은 글자라면, 배운 글자에 추가
                if((check & (1 << ch)) != (1 << ch)){
                    check |= (1 << ch);
                    solve(index + 1);
                    check &= ~(1 << ch);
                }
            }
        }
    }

    public static int getCount() {
        int count = 0;

        for (int i = 0; i < N; i++) {
            String word = words[i];
            boolean isLearn = true;

            for (int index = START_INDEX; index < word.length() - 4; index++) {
                int ch = word.charAt(index) - 'a';
                if ((check & (1 << ch)) != (1 << ch)) {
                    isLearn = false;
                    break;
                }
            }

            if(isLearn)
                count++;
        }

        return count;
    }

    // 무조건 배워야하는 것 초기화
    public static int init() {
        int check = 0;
        check |= (1<<0); // a
        check |= (1<<2); // c
        check |= (1<<8); // i
        check |= (1<<13); // n
        check |= (1<<19); // t

        return check;
    }
}
