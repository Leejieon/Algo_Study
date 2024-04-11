import java.util.*;
import java.lang.*;

/*

한 라운드 당 코인선택 옵션 
코인을 0개 사용
코인을 1개 사용 - 2 가지
코인을 2개 사용

한 라운드 당 분기 4개씩
최대 라운드 수 => 약 500
모든 분기 처리하면 절대 안됨

*/


class Solution {
     
    static int N;
    static int makeNum ;
    static int maxRound = 0;
    static Set<Integer> pocket = new HashSet<>();
    static Set<Integer> garbage = new HashSet<>();
    static Queue<Integer> remainCards = new LinkedList<>();
    
    public int solution(int coin, int[] cards) {
        N = cards.length;
        makeNum = N+1;
        for(int i=0;i<N/3; i++) {
            pocket.add(cards[i]);
        }

        for(int i=N/3; i<cards.length; i++) {
            remainCards.add(cards[i]);
        }
        
        while(true) {
            maxRound++;
            if(remainCards.isEmpty()) {
                break;
            }

            garbage.add(remainCards.poll());
            garbage.add(remainCards.poll());
            
            if(canSuggest()) {
                continue;
            }
            
            if(coin>=1) {
                if(canSuggestUsingOneCoin()) {
                    coin-=1;
                    continue;
                }
            }
            
            if(coin>=2) {
               if(canSuggestUsingTwoCoin()) {
                   coin-=2;
                   continue;
               }
            }
            break;
        }
        
        return maxRound;
    }
    
    public boolean canSuggestUsingTwoCoin() {
        int card1 = -1, card2 =-1;
        for(int gCard : garbage) {
            card1 = gCard;
            if(garbage.contains(makeNum - card1)) {
                card2 = makeNum - card1;
                break;
            }
        }
        if(card2 != -1) {
            garbage.remove(card1);
            garbage.remove(card2);
            return true;
        }
        return false;
    }
    
    public boolean canSuggestUsingOneCoin() {
        int card1 = -1, card2 =-1;
        for(int gCard : garbage) {
            card1 = gCard;
            if(pocket.contains(makeNum - card1)) {
                card2 = makeNum - card1;
                break;
            }
        }
        if(card2 != -1) {
            garbage.remove(card1);
            pocket.remove(card2);
            return true;
        }
        return false;
    }
    
    public boolean canSuggest() {
        int card1 = -1, card2 = -1;
        for(int card : pocket) {
            card1 = card;
            if(pocket.contains(makeNum - card1)) {
                card2 = makeNum - card1;
                break;
            }
        }
        if(card2 != -1) {
            pocket.remove(card1);
            pocket.remove(card2);
            return true;
        }
        return false;
    }
    
    
   

}