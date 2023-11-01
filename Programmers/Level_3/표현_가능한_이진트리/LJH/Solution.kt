import java.io.*
import java.lang.*

class Solution {
    fun solution(numbers: LongArray): IntArray {
        var answer = IntArray(numbers.size) { 0 }
        for (i in 0 until numbers.size) {
            var str = makeBinaryNumber(numbers[i]) // 이진수로 표현
            str = makeFullTree(str) //포화트리로 표현
            if (str.length == 1 && str == "0") {
                answer[i] = 0
                continue
            }
            if (isEnabledFullBinaryTree(str)) { //이진 트리 가능
                answer[i] = 1
            } else { //이진트리 불가능
                answer[i] = 0
            }
        }
        return answer
    }

    fun makeBinaryNumber(number: Long): String { //Long을 이진수로 표현함
        var result = StringBuilder()
        var num = number
        if (num == 0L) return "0"
        while (num > 0) { //이진수로 만들기
            result.append(num % 2) //나머지를 계속 박기
            num /= 2
        }
        return result.toString().reversed() //뒤집어주기
    }

    fun makeFullTree(number: String): String { //노드의 개수는 2^n - 1 개. 꽉곽 채워준다.
        var zero = StringBuilder()
        var n = 2
        while (true) {
            if (number.length <= n - 1) break
            n *= 2
        }
        for (i in 0 until n - 1 - number.length) { // 포화 이진트리로 만들어주기.
            zero.append("0")
        }
        return zero.append(number).toString()
    }

    fun isEnabledFullBinaryTree(number: String): Boolean {
        if (number.length == 1 || !number.contains("1")) return true //길이가 1이거나, 1을 포함하지 않는 경우는 트리 형성 가능
        val root = number.length / 2  //문자열의 중간
        val left = number.substring(0, root) //왼쪽 0~root-1까지
        val right = number.substring(root + 1, number.length) //root+1부터 끝까지
        if (number[root] == '1' && isEnabledFullBinaryTree(left) && isEnabledFullBinaryTree(right))
            return true
        return false
    }
}