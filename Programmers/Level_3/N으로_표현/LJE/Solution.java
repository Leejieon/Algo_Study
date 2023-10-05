import java.util.*;

class Solution {
    List<Set<Integer>> setList = new ArrayList<>();

    public int solution(int N, int number) {

        for(int i=0;i<9;i++){
            setList.add(new HashSet<>());
        }

        setList.get(1).add(N);
        if(number == N){
            return 1;
        }

        for(int i = 2; i < 9; i++) {
            for(int j = 1; j < i; j++) {
                calculate(setList.get(i), setList.get(j), setList.get(i - j));
                addNs(setList.get(i), N, i);
                if(setList.get(i).contains(number))
                    return i;
            }
        }

        return -1;
    }

    static void calculate(Set<Integer> result, Set<Integer> set1, Set<Integer> set2) {
        for(int num1 : set1) {
            for(int num2 : set2) {
                result.add(num1 + num2);
                result.add(num1 - num2);
                result.add(num1 * num2);

                if(num2 != 0){
                    result.add(num1 / num2);
                }
            }
        }
    }

    static void addNs(Set<Integer> set, int N, int count) {
        int result = 0;
        for(int i=0;i<count;i++){
            result += N*(int)Math.pow(10, i);
        }

        set.add(result);
    }
}