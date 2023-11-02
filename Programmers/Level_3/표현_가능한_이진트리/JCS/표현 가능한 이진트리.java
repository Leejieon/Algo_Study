import java.util.*;

class Solution {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        int idx = 0;
        for(long num : numbers) {
            List<String> binaryString = toBinaryNum(num);
            int level = getTreeLevel(binaryString.size());
            makeCompleteBinaryString(binaryString, (int) findFitCompleteSize(level));
            if(canBeTree(binaryString, level)) {
                answer[idx] = 1;
            }
            idx++;
        }
        return answer;
    }
    private static boolean canBeTree(List<String> binaryString, int level) {
        int rootIdx = binaryString.size() / 2;
        if(binaryString.get(rootIdx).equals("0")) {
            return false;
        }
        int child =  (int)Math.pow(2, level - 2);
        return check(binaryString, rootIdx, child);     
    }
    private static boolean check(List<String> binary, int rootIdx, int child) {
        boolean valid = true;
        int left = rootIdx - child;
        int right = rootIdx + child;
        
        if(binary.get(rootIdx).equals("0") && (binary.get(left).equals("1") || binary.get(right).equals("1"))) {
            return false;
        }
        if(child >= 2) {
            valid = check(binary, left, child / 2);
            if(valid) {
                valid = check(binary, right, child / 2);
            }
        }
        return valid;
    }
    private static int getTreeLevel(int curSize) {
        double fitSize = 1d;
        int level = 2;
        while((double) curSize > fitSize) {
            fitSize = findFitCompleteSize(level++);
        }
        return level-1;
    }
    private static double findFitCompleteSize(int n) {
        return Math.pow(2,n) -1d;
    }
    private static void makeCompleteBinaryString(List<String> binaryString, int completeSize) {
        while(binaryString.size() != completeSize) {
            binaryString.add(0,String.valueOf(0));
        }
    }
    
    private static List<String> toBinaryNum(long num) {
        List<String> binaryString = new ArrayList<>();
        long bi = 2l;
        while(num >0) {
            binaryString.add(0, String.valueOf((num % bi)));
            num = num/bi;
        }
        return binaryString;
    }
}