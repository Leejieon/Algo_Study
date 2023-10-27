import java.util.*;

class Solution {
    static HashSet<HashSet<String>> result = new HashSet<>();

    public int solution(String[] user_id, String[] banned_id) {
        recurUser(user_id, banned_id, new HashSet<>(), 0);

        return result.size();
    }

    static void recurUser(String[] user_id, String[] banned_id, HashSet<String> set, int index) {
        // Base Case
        if(index == banned_id.length) {
            result.add(set);
            return;
        }

        // Recursive Case
        for(int i = 0; i < user_id.length; i++) {
            if(set.contains(user_id[i])){
                continue;
            }

            if(check(user_id[i], banned_id[index])) {
                set.add(user_id[i]);
                recurUser(user_id, banned_id, new HashSet<>(set), index + 1);
                set.remove(user_id[i]);
            }
        }
    }

    static boolean check(String userId, String bannedId) {
        // 둘의 길이가 다른 경우
        if(userId.length() != bannedId.length()) {
            return false;
        }

        for(int i = 0; i < userId.length(); i++) {
            char bannedChar = bannedId.charAt(i);
            char userChar = userId.charAt(i);

            if(bannedChar != '*' && userChar != bannedChar) {
                return false;
            }
        }
        return true;
    }
}