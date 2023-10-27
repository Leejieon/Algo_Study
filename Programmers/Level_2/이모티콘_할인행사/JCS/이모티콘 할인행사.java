import java.util.*;
import java.lang.*;
class Solution {
    static int [] ratioList = {10,20,30,40};
    static List<User> userList = new ArrayList<>();
    static int maxMember = 0;
    static int maxProfit = 0;
    static class User {
        final int ratio;
        final int price;
        int total = 0;
        public User(int ratio, int price) {
            this.ratio = ratio;
            this.price = price;
        }
        public void reset() {
            total = 0;
        }
        
        public boolean canBuy(int eRatio) {
            return ratio <= eRatio;
        }
        public void buy(int ePrice, int eRatio) {
            total += ePrice - ((ePrice/10) * (eRatio/10));
            return;
        }
        public boolean isMember() {
            return total >= price;
        }
        
    }
    public List<Integer> solution(int[][] users, int[] emoticons) {
        List<Integer> answer = new ArrayList<>();
        for(int i=0; i< users.length; i++) {
            userList.add(new User(users[i][0], users[i][1]));
        }
        cal(emoticons);
        answer.add(maxMember);
        answer.add(maxProfit);
        return answer;
    }
    
    private static void cal(int[] emoticons) {
        int[][] combi = new int[emoticons.length][2];
        for(int i=0; i<combi.length; i++) {
            combi[i][0] = emoticons[i];
        }
        make(0,combi);
    }
    private static void make(int depth, int[][] combi) {
        if(depth == combi.length) {
            maxProfit(combi);
            return;
        }
        
        for(int j= 0; j<4;j++) {
            combi[depth][1] = ratioList[j];
            make(depth+1, combi);
            }
    
    }
    
    private static void maxProfit(int [][] combi) {
        int profit = 0;
        int memberCnt = 0;
        for(User user : userList) {
            user.reset();
            for(int idx = 0; idx<combi.length; idx++) {
                int eRatio = combi[idx][1];
                int ePrice = combi[idx][0];
                if(user.canBuy(eRatio)) {
                    user.buy(ePrice, eRatio);
                }
            }
            if(user.isMember()) {
                memberCnt++;
                continue;
            }
            profit += user.total;
        }
        if(maxMember < memberCnt) {
            maxMember = memberCnt;
            maxProfit = profit;
            return;
        }
        if(maxMember == memberCnt) {
            maxProfit = Math.max(maxProfit, profit);
        }
    }
}