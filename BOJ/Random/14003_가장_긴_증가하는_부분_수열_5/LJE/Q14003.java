import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

class Q14003 {
    static int N;
    static int[] numbers;
    static Stack<Node>[] LISStack;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        numbers = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        // 결과를 넣을 Stack List
        LISStack = new Stack[N];
        int curIndex = 0;

        // 처음 초기값 넣기
        LISStack[0] = new Stack<>();
        LISStack[0].push(new Node(numbers[0], 0));

        // 각 자리에 올 수 있는 숫자들을 stack 에 넣기
        for (int i = 1; i < N; i++) {
            int curNum = numbers[i];
            // 현재 결과 리스트의 마지막 인덱스보다 큰 경우
            if (LISStack[curIndex].peek().value < curNum) {
                LISStack[++curIndex] = new Stack<>();
                LISStack[curIndex].push(new Node(curNum, i));
                continue;
            }
            // 아닌 경우, 이분 탐색을 통해 들어갈 수 있는 위치 찾기
            int findIndex = binarySearch(curNum, 0, curIndex);
            LISStack[findIndex].push(new Node(curNum, i));
        }

        // 결과 찾기
        int[] result = new int[curIndex + 1];
        // 완성된 결과 stack 배열의 뒷 인덱스부터 탐색 시작
        Node prev = LISStack[curIndex].pop();
        result[curIndex] = prev.value;
        for (int index = curIndex - 1; index >= 0; index--) {
            Node cur;
            while (true) {
                cur = LISStack[index].pop();
                if (cur.index < prev.index) {
                    result[index] = cur.value;
                    break;
                }
            }
            prev = cur;
        }

        // 결과 출력하기
        sb.append(curIndex + 1).append("\n"); // curIndex 는 인덱스 값이기 때문에 +1 해야 개수가 된다.
        for (int number : result) {
            sb.append(number).append(" ");
        }

        System.out.println(sb);
    }

    // 현재 찾는 수가 들어갈 수 있는 위치 찾기
    static int binarySearch(int target, int low, int high) {
        while (low < high) {
            int mid = (low + high) / 2;

            if (target > LISStack[mid].peek().value) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return high;
    }

    static class Node {
        int value; // 값
        int index; // 해당 값의 인덱스

        Node(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }
}