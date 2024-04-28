import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    /**
     * N 개의 집 C 개의 공유기 설치, 최대한 많은 곳에서 와이파이 사용하고 싶다. 가장 인접한 두 공유기 사이의 거리를 가능한 크게하여 설치 최소 거리를 1, 최대 거리를 가장 멀리 있는 집의
     * 거리(max)로 설정하여 최소거리(mid = (1+max)/2)를 만족하도록 공유기를 설치할 수 있는가 ? 설치할 수 있다면 최소거리를 mid+1로 높여서 다시 확인한다. 설치할 수 없다면 최대거리를
     * mid-1로 낮추어서 다시 확인해본다.
     */

    static int N, C;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] homes;
    static int dist;

    public static void main(String[] args) throws IOException {
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stringTokenizer.nextToken());
        C = Integer.parseInt(stringTokenizer.nextToken());
        homes = new int[N];

        for (int i = 0; i < N; i++) {
            homes[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(homes);

        int min = 1;
        int max = homes[homes.length - 1] - homes[0] + 1;

        while (min < max) {
            int mid = (max + min) / 2;
            int cnt = 1;
            int lastHome = homes[0];
            for (int i = 1; i < homes.length; i++) {
                if (homes[i] - lastHome >= mid) {
                    cnt++;
                    lastHome = homes[i];
                }
            }
            if (cnt >= C) {
                dist = mid;
                min = mid + 1;
            } else {
                max = mid;
            }
        }
        System.out.println(dist);
    }
}
