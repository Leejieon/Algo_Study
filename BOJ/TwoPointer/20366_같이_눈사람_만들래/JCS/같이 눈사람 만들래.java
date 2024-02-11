
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    static int N;
    static List<Integer> heights = new ArrayList<>();
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static int answer = 0;


    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        heights = Arrays.stream(bufferedReader.readLine().split(" "))
                .map(s -> Integer.parseInt(s))
                .collect(Collectors.toList());
        Collections.sort(heights);
        solution();
        System.out.println(answer);
    }

    private static void solution() {
        int elsaUpsnowIdx = 0;
        int elsaDownSnowIdx = heights.size() - 1;
        int diff = Integer.MAX_VALUE;

        while (elsaUpsnowIdx < elsaDownSnowIdx) {
            int elsaSnowMan = heights.get(elsaUpsnowIdx) + heights.get(elsaDownSnowIdx);

            int annaUpSnowIdx = elsaUpsnowIdx + 1;
            int annaDownSnowIdx = elsaDownSnowIdx - 1;

            while (annaUpSnowIdx < annaDownSnowIdx) {
                int annaSnowMan = heights.get(annaUpSnowIdx) + heights.get(annaDownSnowIdx);
                int curDiff = elsaSnowMan - annaSnowMan;

                if (diff > Math.abs(curDiff)) {
                    diff = Math.abs(curDiff);
                    answer = diff;
                }
                if (curDiff == 0) {
                    return;
                }
                if (curDiff < 0) {
                    annaDownSnowIdx--;
                    continue;
                }
                annaUpSnowIdx++;
            }
            elsaUpsnowIdx++;
            if (elsaUpsnowIdx == elsaDownSnowIdx) {
                elsaDownSnowIdx -= 1;
                elsaUpsnowIdx = 0;
            }
        }
    }
}
