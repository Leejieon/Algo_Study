import java.util.*;

class Solution {
    Map<String, List<Integer>> allPossibleMap = new HashMap<>();

    public int[] solution(String[] info, String[] query) {
        for(int index = 0; index < info.length; index++) {
            String[] information = info[index].split(" ");
            makeQuery(information, "", 0);
        }

        for(String key : allPossibleMap.keySet()) {
            Collections.sort(allPossibleMap.get(key));
        }

        int[] answer = replaceQuery(query);
        return answer;
    }

    void makeQuery(String[] information, String str, int depth) {
        if(depth == 4) {
            if(!allPossibleMap.containsKey(str)) {
                List<Integer> list = new ArrayList<>();
                allPossibleMap.put(str, list);
            }
            allPossibleMap.get(str).add(Integer.parseInt(information[depth]));

            return;
        }

        makeQuery(information, str + "-", depth + 1);
        makeQuery(information, str + information[depth], depth + 1);
    }

    int[] replaceQuery(String[] query) {
        int[] result = new int[query.length];

        for(int index = 0; index < query.length; index++) {
            query[index] = query[index].replaceAll(" and ", "");
            String[] oneQuery = query[index].split(" ");

            if(allPossibleMap.containsKey(oneQuery[0])) {
                result[index] = binarySearch(oneQuery[0], Integer.parseInt(oneQuery[1]));
                continue;
            }
            result[index] = 0;
        }

        return result;
    }

    int binarySearch(String key, int score) {
        List<Integer> list = allPossibleMap.get(key);

        int start = 0;
        int end = list.size() - 1;

        while(start <= end) {
            int mid = (start + end) / 2;

            if(list.get(mid) < score){
                start = mid + 1;
                continue;
            }
            end = mid - 1;
        }

        return list.size() - start;
    }
}
