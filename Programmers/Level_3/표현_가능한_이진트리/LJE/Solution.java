class Solution {
    int result;

    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];

        for(int index = 0; index < numbers.length; index++) {
            String binaryStr = Long.toBinaryString(numbers[index]);
            int len = binaryStr.length();

            int treeLen = getTreeLength(len);

            boolean[] binary = makeTree(treeLen, binaryStr);
            result = 1;
            validateBinaryTree(binary, 0, treeLen - 1, false);
            answer[index] = result;
        }
        return answer;
    }

    int getTreeLength(int length) {
        int depth = 1;
        int result = 0;

        while(result < length){
            result = (int) Math.pow(2, depth++) - 1;
        }

        return result;
    }

    boolean[] makeTree(int treeLen, String binaryStr) {
        boolean[] result = new boolean[treeLen];
        int index = treeLen - binaryStr.length();

        for(int i = 0; i < binaryStr.length(); i++) {
            result[index++] = binaryStr.charAt(i) == '1';
        }

        return result;
    }

    void validateBinaryTree(boolean[] tree, int start, int end, boolean isEnd) {
        int mid = (start + end) / 2;
        boolean current = tree[mid];

        if(isEnd && current) {
            result = 0;
            return;
        }

        if(start != end) {
            validateBinaryTree(tree, start, mid - 1, !current);
            validateBinaryTree(tree, mid + 1, end, !current);
        }
    }
}
