import java.util.*;
//3:37 PM

/*
단품 메뉴 조합 -> 코스요리 재구성
가장 많이 함께 주문한 단품메뉴를 코스요리 메뉴로 구성
최소 2가지 이상
최소 2명 이상의 손님으로부터 주문된 메뉴 조합
손님 여섯명
1번 손님	A, B, C, F, G
2번 손님	A, C
3번 손님	C, D, E
4번 손님	A, C, D, E
5번 손님	B, C, F, G
6번 손님	A, C, D, E, H
일 때,
요리 2개 코스	A, C	1번, 2번, 4번, 6번 손님으로부터 총 4번 주문됐습니다.
요리 3개 코스	C, D, E	3번, 4번, 6번 손님으로부터 총 3번 주문됐습니다.
요리 4개 코스	B, C, F, G	1번, 5번 손님으로부터 총 2번 주문됐습니다.
요리 4개 코스	A, C, D, E	4번, 6번 손님으로부터 총 2번 주문됐습니다.

손님이 주문한 메뉴들 -> orders
구성하고 싶은 코스 요리 수 -> course

course를 하나 씩 가져온다.
가져온 요리 수에 대해서 orders를 하나 씩 돌며 코스 조합을 생성한다.

요리 수 , orders 에 대해서 조합을 생성하는 메서드


*/
class Solution {
    private static void makeSet(String order, int count, int idx, Set<Character> set, List<Set<Character>> setList) {
        if(set.size() == count) {
            setList.add(new HashSet<>(set));
            return;
        }
       
        for(int i=idx; i<order.length();i++) {
            set.add(order.charAt(i));
            makeSet(order,count,i+1,set,setList);
            set.remove(order.charAt(i));
        }
    }
    
    static Map<Integer,Integer> courseNumKeyMap = new HashMap<>();
    static Map<Set<Character>, Integer> menuSetKeyMap = new HashMap<>();
    
    public String[] solution(String[] orders, int[] course) {
        List<String> answer = new ArrayList<>();
        
        for(int courseNum : course) {
            int maxCnt = 1;
            for(String order : orders) {
                List<Set<Character>> setList = new ArrayList<>();
                makeSet(order, courseNum, 0, new HashSet<>(),setList);
                //courseNum 개의 메뉴 조합을 생성해서 setList에 저장
                for(Set<Character> set : setList) {
                    menuSetKeyMap.put(set, menuSetKeyMap.getOrDefault(set,0)+1);
                    maxCnt = Math.max(maxCnt, menuSetKeyMap.get(set));
                }
            } 
            // courseNum 개의 모든 조합들에 대해서 menuSetKeyMap의 value에 count가 되었음
            courseNumKeyMap.put(courseNum, maxCnt);
        }
        for(Set<Character> set : menuSetKeyMap.keySet()) {
            if(menuSetKeyMap.get(set)>= 2 &&menuSetKeyMap.get(set)==courseNumKeyMap.get(set.size())) {
                //해당 코스가 2명이상에게 주문되었고, 가장 많이 주문한 코스라면
                char [] courseMenu = new char[set.size()];
                int cnt = -1;
                for(char menu : set) {
                    courseMenu[++cnt] = menu;
                }
                Arrays.sort(courseMenu);
                answer.add(new String(courseMenu));
            }
        }
        Collections.sort(answer);
                return answer.toArray(new String[0]);
        }
    }
