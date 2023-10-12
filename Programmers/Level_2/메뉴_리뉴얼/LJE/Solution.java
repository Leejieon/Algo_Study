import java.util.*;

class Solution {

    static List<String> result = new ArrayList<>();
    static Map<String, Integer> map = new HashMap<>();

    public List<String> solution(String[] orders, int[] course) {
        for (int i = 0; i < orders.length; i++) {
            char[] arr = orders[i].toCharArray();
            Arrays.sort(arr);
            orders[i] = String.valueOf(arr);
        }

        for(int courseLen : course) {
            for(String order : orders){
                combination("", order, courseLen);
            }
            findMaxValue();
            map.clear();

        }

        Collections.sort(result);

        return result;
    }

    // order : 현재까지 조합된 course
    // others : 아직까지 사용되지 않는 것
    // count : 조합할 개수
    static void combination(String order, String others, int count) {
        if (count == 0) {
            map.put(order, map.getOrDefault(order, 0) + 1);
            return;
        }

        // 0 ~ length까지 조합
        for (int i = 0; i < others.length(); i++)
            combination(order + others.charAt(i), others.substring(i + 1), count - 1);
    }

    // 가장 많은 조합 찾기
    static void findMaxValue() {
        if(map.isEmpty())
            return;

        List<Integer> list = new ArrayList<>(map.values());
        int max = Collections.max(list);

        if(max > 1){
            for(String key : map.keySet()) {
                if(map.get(key) == max)
                    result.add(key);
            }
        }
    }
}