package Programmers;
import java.util.*;
/*
N 과 사칙연산만 사용해서 number 표현
numbers -> 1이상 32,000 이하
0 부터 8
32001 배열
N -> 5일 때
1 개로 만들 수 있는 숫자 5
2 개로 만들 수 있는 숫자 55
                    5*5 5+5 5-5 5/5
3 개로 만들 수 있는 숫자 555
                    55*5 55/5 55+5 55-5
                    5*5*5 5*5/5 5*5+5 5*5-5
                    5+5*5 5+5/5 5+5+5 5+5-5
                    5-5*5 5-5/5 5-5+5 5-5-5
                    5/5*5 5/5/5 5/5+5 5/5-5
4 개로 만들 수 있는 숫자 5555
                    555*5 555/5 555+5 555-5
                    55*55

일반화
dp[n] -> n개로 만들 수 있는 숫자
dp [n] = n개이어 붙힌 숫자 + dp[n-1] 사칙연산 dp[1] + dp[n-2] 사칙연산 dp[2] ... dp[n/2] 사칙연산 dp[n/2]

*/
class N으로표현 {
    static Map<Integer,Set<Integer>> dp = new HashMap<>();
    public int solution(int N, int number) {
        int answer = -1;
        if(number == N) {
            return 1;
        }
        dp.put(1,new HashSet<>());
        //초기화
        dp.get(1).add(N);

        for(int n =2; n<=8; n++) {
            getNum(n,N);
            if(dp.get(n).contains(number)) {
                return n;
            }
        }
        return answer;
    }
    private static void getNum(int n,int N) {
        int defNum = getDefNum(n,N);
        Set<Integer> mergeSet = new HashSet<>();
        for(int i=1;i<n;i++) {
            mergeSet.addAll(calculate(dp.get(n-i),dp.get(i)));
        }
        dp.put(n,mergeSet);
        dp.get(n).add(defNum);
    }
    private static Set<Integer> calculate(Set<Integer> set1, Set<Integer> set2) {
        Set<Integer> result = new HashSet<>();
        for(int num1 : set1) {
            for(int num2 : set2) {
                int plus = num1 + num2;
                int minus = num1 - num2;
                int mul = num1 * num2;
                if(num2 !=0) {
                    int div = num1 / num2;
                    result.add(div);
                }
                if(minus>=0) {
                    result.add(minus);
                }
                result.add(plus);
                result.add(mul);
            }
        }
        return result;
    }
    private static int getDefNum(int n,int N) {
        int num = 0;
        while(n>0) {
            num*=10;
            num+=N;
            n--;
        }
        return num;
    }

}