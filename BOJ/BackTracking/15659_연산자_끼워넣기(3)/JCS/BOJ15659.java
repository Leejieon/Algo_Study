package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ15659 {
    /*
    N개의 수로 이루어진 수열 A1, A2, ..., AN이 주어진다. 또, 수와 수 사이에 끼워넣을 수 있는 N-1개의 연산자가 주어진다.
    연산자는 덧셈(+), 뺄셈(-), 곱셈(×), 나눗셈(÷)으로만 이루어져 있다.
    우리는 수와 수 사이에 연산자를 하나씩 넣어서, 수식을 하나 만들 수 있다.
    이때, 주어진 수의 순서를 바꾸면 안 된다.

    예를 들어, 6개의 수로 이루어진 수열이 1, 2, 3, 4, 5, 6이고,
    주어진 연산자가 덧셈(+) 2개, 뺄셈(-) 1개, 곱셈(×) 1개, 나눗셈(÷) 1개인 경우에는 총 60가지의 식을 만들 수 있다.
    예를 들어, 아래와 같은 식을 만들 수 있다.

    1+2+3-4×5÷6
    1÷2+3+4-5×6
    1+2÷3×4-5+6
    1÷2×3-4+5+6
    식의 계산은 연산자 우선 순위를 이용해 계산해야 한다.
    연산자 우선 순위는 ×와 ÷가 +와 -보다 앞선다. 우선 순위가 같은 경우에는 앞에 있는 식을 먼저 계산한다.
    또, 나눗셈은 정수 나눗셈으로 몫만 취한다. 이에 따라서, 위의 식 4개의 결과를 계산해보면 아래와 같다.

    1+2+3-4×5÷6 = 3
    1÷2+3+4-5×6 = -23
    1+2÷3×4-5+6 = 2
    1÷2×3-4+5+6 = 7
    N개의 수와 N-1개의 연산자가 주어졌을 때, 만들 수 있는 식의 결과가 최대인 것과 최소인 것을 구하는 프로그램을 작성하시오.

    입력 :
    첫째 줄에 수의 개수 N(2 ≤ N ≤ 11)가 주어진다.
    둘째 줄에는 A1, A2, ..., AN이 주어진다.
    (1 ≤ Ai ≤ 100) 셋째 줄에는 합이 N-1인 4개의 정수가 주어지는데, 차례대로 덧셈(+)의 개수, 뺄셈(-)의 개수, 곱셈(×)의 개수, 나눗셈(÷)의 개수이다.

    출력 :
    첫째 줄에 만들 수 있는 식의 결과의 최댓값을, 둘째 줄에는 최솟값을 출력한다.
    최댓값과 최솟값이 항상 -10억보다 크거나 같고, 10억보다 작거나 같은 결과가 나오는 입력만 주어진다.
    또한, 식을 어떤 순서로 계산해도 중간에 계산되는 식의 결과도 항상 -10억보다 크거나 같고, 10억보다 작거나 같다.

    Input :
    2
    5 6
    0 0 1 0
    Output :
    30
    30

    중위식 생성 -> 후위식 변환 -> 계산
     */

    static int N;
    static String[] numbers;
    static int[] operatorCnt = new int[4];
    static String[] operator = {"+", "-", "*", "/"};
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static Map<String, Integer> priority = new HashMap<>();
    static int min = Integer.MAX_VALUE;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        numbers = new String[N];
        //정수 리스트 초기화
        for (int i = 0; i < N; i++) {
            numbers[i] = stringTokenizer.nextToken();
        }
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        // 연산자 입력받기
        for (int i = 0; i < 4; i++) {
            operatorCnt[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        // 우선순위 초기화
        priority.put("+", 0);
        priority.put("-", 0);
        priority.put("*", 1);
        priority.put("/", 1);

        recur(0, "");
        System.out.println(max);
        System.out.println(min);
    }

    private static void recur(int depth, String formula) {

        formula += numbers[depth] + " ";

        if (depth == N - 1) {
            min = Math.min(min, calculate(toPostFix(formula)));
            max = Math.max(max, calculate(toPostFix(formula)));
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (operatorCnt[i] != 0) {
                operatorCnt[i] -= 1;
                recur(depth + 1, formula + operator[i] + " ");
                operatorCnt[i] += 1;
            }
        }
    }

    private static List<String> toPostFix(String infix) {
        List<String> result = new ArrayList<>();
        Stack<String> stack = new Stack<>();

        String[] tokens = infix.split(" ");

        for (String token : tokens) {
            if (token.matches("^[0-9]+$")) {
                result.add(token);
            } else {
                if (stack.isEmpty()) {
                    stack.push(token);
                } else {
                    while (!stack.isEmpty() && priority.get(stack.peek()) >= priority.get(token)) {
                        result.add(stack.pop());
                    }
                    stack.push(token);
                }
            }
        }
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
    }

    private static int calculate(List<String> postFix) {

        int result = 0;
        Stack<String> stack = new Stack<>();

        for (String token : postFix) {
            if (token.matches("^[0-9]+$")) {
                stack.push(token);
                continue;
            }
            String n1 = stack.pop();
            String n2 = stack.pop();
            if (token.equals("+")) {
                stack.push(Integer.toString(Integer.parseInt(n2) + Integer.parseInt(n1)));
                continue;
            }
            if (token.equals("-")) {
                stack.push(Integer.toString(Integer.parseInt(n2) - Integer.parseInt(n1)));
                continue;
            }
            if (token.equals("*")) {
                stack.push(Integer.toString(Integer.parseInt(n2) * Integer.parseInt(n1)));
                continue;
            }
            if (token.equals("/")) {
                stack.push(Integer.toString(Integer.parseInt(n2) / Integer.parseInt(n1)));
            }
        }
        return Integer.parseInt(stack.pop());
    }
}
