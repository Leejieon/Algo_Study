import java.util.*;

class Solution {
    static int[] discountRate = {10, 20, 30, 40};
    static int[] answer = new int[2];
    static List<Emoticon> emoList = new ArrayList<>();

    public int[] solution(int[][] users, int[] emoticons) {
        recurEmoticons(users, emoticons, 0);
        return answer;
    }

    static void recurEmoticons(int[][] users, int[] emoticons, int index) {
        // Base Case
        if(index == emoticons.length) {
            int[] result = proceedUser(users);
            if(answer[0] < result[0]) {
                answer = result.clone();
            }
            if(answer[0] == result[0] && answer[1] < result[1]) {
                answer = result.clone();
            }
            return;
        }

        for(int i = 0; i < 4; i++) {
            int rate = discountRate[i];
            int discountPrice = emoticons[index] * (100 - rate)/100;

            emoList.add(new Emoticon(rate, discountPrice));
            recurEmoticons(users, emoticons, index + 1);
            emoList.remove(emoList.size() - 1);
        }
    }

    static int[] proceedUser(int[][] users) {
        int plusMember = 0;
        int totalPrice = 0;

        for(int i = 0; i < users.length; i++) {
            int userWishRate = users[i][0];
            int userMaxPrice = users[i][1];

            int userPurchasePrice = 0;
            for(Emoticon emo : emoList) {
                if(userWishRate <= emo.rate) {
                    userPurchasePrice += emo.price;
                }
            }

            // 사용자의 허용 가격 이상의 돈을 구매에 사용한 경우
            if(userPurchasePrice >= userMaxPrice) {
                plusMember++; // 임티플 가입
            }
            else {
                totalPrice += userPurchasePrice; // 아닐 경우, 이모티콘 구매
            }
        }

        return new int[]{plusMember, totalPrice};
    }

    static class Emoticon {
        int rate;
        int price;

        Emoticon(int rate, int price) {
            this.rate = rate;
            this.price = price;
        }
    }
}