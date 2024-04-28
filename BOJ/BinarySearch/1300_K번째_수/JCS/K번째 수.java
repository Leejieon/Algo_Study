import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    /**
     * N*N 배열 A , A[i][j] = i*j,이 수를 일차원 B에 넣고 B를 오름차순으로 정렬했을 때 B[k]는 어떤 수일까? n^2 이하의 합성수 k 번째로 큰 수 5 * 5 1  2  3  4  5
     * 2  4  6  8 10 3  6  9 12 15 4  8 12 16 20 5 10 15 20 25 N 최대값 => 10^5
     * <p>
     * N => 5 K => 13 이라고 하자 그럼 1 ~ 25개의 수에서 13번째 수가 어떤 것인지 찾아야하는데 어떻게 찾지?
     * <p>
     * 일단, 10^10 => 10,000,000,000
     *
     *
     * B[k] = x 라고 했을 때
     * x보다 작거나 같은 값이 K개가 있다라는 말로 해석할 수 있다.
     *
     * 그럼 x보다 작거나 같은 값을 어떻게 찾을 수 있는가?
     * 이차원 행렬 A에서
     * 각 행은 구구단과 같다.
     * x 를 i 행으로 나누었을 때 몫은 각 행에서 x보다 작은 값들을 나타낸다.
     * 그런데, 각 행은 최대 N개 있을 수 있는데, N개 보다 많은 몫이 나오면은 N으로 세어줘야한다.
     * 그래서 1행부터 N행까지 x를 i행으로 나눈 몫을 세면 x보다 작은 원소들의 갯수를 얻을 수 있다.
     * 이 원소의 갯수를 세어서 K보다 많다면 x의 수를 줄여서 다시 이분탐색을진행하고, K보다 적다면 x의 수를 올려서 다시 이분탐색을 해본다.
     */

    static Integer N, K;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        // K => x보다 작거나 같은 원소들의 갯수인데 x 값의 범위는 1 <= x <= K^2이다
        int sIdx = 1;
        int eIdx = K;

        while (sIdx < eIdx) {
            int mid = (sIdx + eIdx) / 2; // x 값을 조정해가면서 x값보다 작거나 같은 원소들의 갯수가 K가 되는지 확인한다.
            int count = 0;
            for (int i = 1; i <= N; i++) {
                count += Math.min(mid / i, N);
            }

            if(count >= K) {
                //K 와 같을 때도 이분탐색을 해봐야 하는데 이는 행렬에 존재하지 않는 수가 있을 수 있기 때문이다.
                //그런데 존재하지 않는 수의 특징은 해당 수가 존재하지 않기 때문에 최근에 업데이트 된 갯수를 이어나간다는 특성이 있다.
                //따라서 같은 작거나 같은 K개의 원소를 가지는 x는 여러개가 있을 수 있지만 그 중 Lower값이 실재하는 원소가 된다.
                eIdx = mid;
            } else {
                sIdx = mid + 1;
            }
        }

        System.out.println(sIdx);

    }
}
