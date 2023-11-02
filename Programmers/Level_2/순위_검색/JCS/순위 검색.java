import java.util.*;
class Solution {
    static Map<String, List<Integer>> queryMap = new HashMap<>();
    static String [] condition = new String[4];
    static String [] infoToken = new String[4];
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        for(String s : info) {
            String [] token = s.split(" ");
            putIntoMap(token);
        }
        for(String key : queryMap.keySet()) {
            Collections.sort(queryMap.get(key));
        }
        int idx = 0;
        for(String q : query) {
            int count = 0;
            String[] strs = q.split(" and | ");
            int condScore = Integer.parseInt(strs[4]);
            String queryString = strs[0] + strs[1] + strs[2] + strs[3];
            if(queryMap.containsKey(queryString)) {
               int size = queryMap.get(queryString).size();
               int floorIdx = findFloor(queryMap.get(queryString), condScore, 0, size-1);
                count = size - floorIdx;
            }
            answer[idx++] = count;
        }
        return answer;
    }
    private static int findFloor(List<Integer> list, int value, int lo, int hi) {
        int mid ;
        while(lo <= hi) {
            mid = (lo + hi) / 2;
            if(list.get(mid) < value) {
                lo = mid + 1;
                continue;
            }
            hi = mid -1;
        }
        return lo; 
    }
  
    private static void putIntoMap(String [] token) {
        int score = Integer.parseInt(token[4]);
        infoToken = Arrays.copyOf(token,4);
        recur(0,score);
    }
    private static void recur(int depth, int score) {
        if(depth == 4) {
            String condString = toCondString(condition);
            if(! queryMap.containsKey(condString)) {
                queryMap.put(condString, new ArrayList<>());
            }
            queryMap.get(condString).add(score);
            return;
        }
        condition[depth] = infoToken[depth];
        recur(depth+1, score);
        condition[depth] = "-";
        recur(depth+1, score);
    }
    private static String toCondString(String [] temp) {
        return temp[0]+temp[1]+temp[2]+temp[3];
    }
}