import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Q2568 {
    static int N;
    static Stack<Pair>[] sList;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        // 전봇대 A의 위치를 기준으로 오름차순 정렬
        PriorityQueue<Pair> poles = new PriorityQueue<>((o1, o2) -> o1.a - o2.a);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            poles.offer(new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        sList = new Stack[N];
        // 초기값 넣어주기
        sList[0] = new Stack<>();
        sList[0].push(poles.poll());
        int curIndex = 0;
        while (!poles.isEmpty()){
            Pair curPair = poles.poll();
            if (sList[curIndex].peek().b < curPair.b) {
                sList[++curIndex] = new Stack<>();
                sList[curIndex].push(curPair);
            } else {
                sList[binarySearch(curPair.b, 0, curIndex)].push(curPair);
            }
        }

        // A 찾기
        ArrayList<Integer> result = new ArrayList<>();
        Pair prev = sList[curIndex].pop();
        while (!sList[curIndex].isEmpty()) {
            result.add(sList[curIndex].pop().a);
        }
        for (int i = curIndex - 1; i >= 0; i--) {
            Pair cur;
            while (true) {
                cur = sList[i].pop();
                if (cur.a < prev.a) {
                    break;
                }
                result.add(cur.a);
            }

            // A 찾기
            while (!sList[i].isEmpty()) {
                result.add(sList[i].pop().a);
            }
            prev = cur;
        }
        Collections.sort(result);

        sb.append(result.size()).append("\n");
        for (Integer i : result) {
            sb.append(i).append("\n");
        }
        System.out.print(sb);
    }

    static int binarySearch(int target, int low, int high) {
        while (low < high) {
            int mid = (low + high) / 2;

            if (target == sList[mid].peek().b) {
                break;
            } else if (target > sList[mid].peek().b) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return high;
    }

    static class Pair {
        int a, b;

        Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
}
