import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Q15659 {
    static int N; // 수의 개수
    static int[] numbers;
    static int[] operators = new int[4]; // +, -, *, /
    static int[] opList = new int[11];
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        numbers = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            operators[i] = Integer.parseInt(st.nextToken());
        }

        solve(1);
        System.out.println(max + " " + min);
    }

    // operator 를 기준으로
    static void solve(int index){
        // BaseCase
        if (index == N) {
            int sum = getSum();
            max = Math.max(max, sum);
            min = Math.min(min, sum);
            return;
        }

        // RecursiveCase
        for (int i = 0; i < 4; i++) {
            if (operators[i] > 0) {
                operators[i]--;
                opList[index] = i;
                solve(index + 1);
                opList[index] = 0;
                operators[i]++;
            }
        }
    }

    static int getSum() {
        Stack<Integer> opStack = new Stack<>();
        Stack<Integer> numStack = new Stack<>();

        numStack.push(numbers[0]);

        for (int i = 1; i < N; i++) {
            int operator = opList[i];
            // +, - 일 경우 pass
            if (operator == 0 || operator == 1) {
                opStack.push(opList[i]);
                numStack.push(numbers[i]);
            } else if (operator == 2) {
                numStack.push(numStack.pop() * numbers[i]);
            } else {
                numStack.push(numStack.pop() / numbers[i]);
            }
        }

        int sum = 0;
        while (!numStack.isEmpty()) {
            // 숫자가 한 개 남았다면 종료
            if (numStack.size() == 1) {
                sum = numStack.pop();
                break;
            }

            int num1 = numStack.pop();
            int num2 = numStack.pop();

            int operator = opStack.pop();
            int nextOperator = (opStack.size() == 0) ? 0 : opStack.pop();

            // operator -> num1의 부호, nextOperator -> num2의 부호
            if (operator == 1) {
                num1 *= -1;
            }
            if (nextOperator == 1) {
                num2 *= -1;
            }

            // 둘을 더한 값을 넣고, + 기호 하나 넣기
            numStack.push(num1 + num2);
            opStack.push(0);
        }

        return sum;
    }

}
