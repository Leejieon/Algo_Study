import java.util.*;

class Solution {
    static Set<String> mappedUser = new HashSet<>();
    static Set<Set<String>> visit = new HashSet<>();
    public int solution(String[] user_id, String[] banned_id) {
        recur(0, user_id, banned_id, banned_id.length);
        return visit.size();
    }
    private static boolean isPattern(String user, String pattern) {
        if(user.length() != pattern.length()) {
            return false;
        }
        for(int i=0; i<user.length(); i++) {
            if(pattern.charAt(i) == '*') {
                continue;
            }
            if(pattern.charAt(i) != user.charAt(i)) {
                return false;
            }
        }
        return true;
    }
    private static void recur(int depth, String [] user_id, String [] banned_id, int n) {
        if(depth == n) {
            if(!visit.contains(mappedUser)) {
                visit.add(new HashSet<>(mappedUser));
            }
            return;
        }
        
        String pattern = banned_id[depth];
        for(int i=0; i<user_id.length; i++) {
            if(!mappedUser.contains(user_id[i]) && isPattern(user_id[i], pattern)) {
                mappedUser.add(user_id[i]);
                recur(depth+1, user_id, banned_id, n);
                mappedUser.remove(user_id[i]);
            }
            
        }
        return;
    }
}