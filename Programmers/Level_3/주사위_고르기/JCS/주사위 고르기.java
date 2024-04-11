import java.util.*;
/**
주사위에 쓰인 수의 구성은 모두 다르다. -> 각 주사위는 구분된다.
*/
class Solution {
    static int N;
    static List<DicePick> dicePicks = new ArrayList<>();
    
    static class DicePick {
        final List<Integer> pick;
        public DicePick(List<Integer> pick){
            this.pick = pick;
        };
        
        public boolean contains(int num) {
            return this.pick.contains(num);
        }
        
        public List<Integer> getOppositePick() {
            List<Integer> opposite = new ArrayList<>();
            for(int i=0; i<pick.size()*2; i++) {
                if(!pick.contains(i)) {
                    opposite.add(i);
                }
            }
            return opposite;
        }
        
        public boolean equals(List<Integer> list) {
            return this.pick.equals(list);
        }
    }
    
    static class ScoreBoard {
        int win;
        int draw;
        int lose;
        public ScoreBoard(int win, int draw, int lose) {
            this.win = win;
            this.draw = draw;
            this.lose = lose;
        }
        
        public ScoreBoard otherSide() {
            return new ScoreBoard(this.lose, this.draw, this.win);
        }
    }
    
    public int[] solution(int[][] dice) {
        
        N = dice.length;
        int[] answer = new int[N/2];
        Map<DicePick, ScoreBoard> scores = new HashMap<>();
        
        //Find All Possible DicePick and Put into Map
        recur(-1, new ArrayList<>());
        
        List<int[]> combis = new ArrayList<>();
        makeCombination(0, new int[N/2], N/2, combis);
        
        for(DicePick dp : dicePicks) {
            if(!scores.containsKey(dp)) {
                ScoreBoard sb = findScoreBoard(dp, dice, combis);
                scores.put(dp, sb);
                DicePick opposite = dicePicks.stream()
                    .filter(pick -> {return pick.equals(dp.getOppositePick());}).findAny().get();

                scores.put(opposite, sb.otherSide());
            }
        }
        
        DicePick mostWinDp = null;
        int maxWin = -1;
        for(DicePick dp : scores.keySet()) {
            if(scores.get(dp).win > maxWin) {
                mostWinDp = dp;
                maxWin = scores.get(dp).win;
            }
        }
        for(int idx = 0; idx<mostWinDp.pick.size(); idx++) {
            answer[idx] = mostWinDp.pick.get(idx) + 1;
        }
        
        return answer;
    }
    
    public ScoreBoard findScoreBoard(DicePick dp, int[][] dice, List<int[]> combis) {
        
        HashMap<Integer, Integer> mySum = new HashMap<>();
        
        for(int[] combi : combis) {
            int sum = 0;
            for(int idx = 0; idx<combi.length; idx++) {
                sum += dice[dp.pick.get(idx)][combi[idx]];
            }
            mySum.put(sum, mySum.getOrDefault(sum, 0) +1);
        }
        
        List<Integer> opposite = dp.getOppositePick();
        int win = 0;
        int draw = 0;
        int lose = 0;
        
        for(int[] combi : combis) {
            int sum = 0;
            for(int idx = 0; idx<combi.length; idx++) {
                sum += dice[opposite.get(idx)][combi[idx]];
            }
            
            for(int key : mySum.keySet()) {
                if(key<sum) {
                    lose += mySum.get(key);
                } else if(key>sum) {
                    win += mySum.get(key);
                }
                else {
                    draw += mySum.get(key);
                }
            }
        }  

        return new ScoreBoard(win, draw, lose);
        
    }
    
    public void makeCombination(int depth, int[] combi, int length, List<int []> combis) {
        if(depth == length) {
            combis.add(combi);
            return;
        }
        for(int i=0; i<6; i++) {
            combi[depth] = i;
            makeCombination(depth+1, Arrays.copyOf(combi, combi.length), length, combis);
        }
    } 
    
    public void recur(int depth, List<Integer> pick) {
        if(pick.size() == N/2) {
            dicePicks.add(new DicePick(pick));
            return;
        }
        
        for(int i=depth+1; i<N; i++) {
            pick.add(i);
            recur(i, new ArrayList<>(pick));
            pick.remove(pick.indexOf(i));
        }
    }
}