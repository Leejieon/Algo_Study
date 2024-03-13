package Random.`1208_부분수열의_합_2`.LJH

class Solution {

    private val br = System.`in`.bufferedReader()
    private lateinit var ary: IntArray
    private var n = 0
    private var s = 0
    private var answer = 0L
    private val leftSum = hashMapOf<Long, Long>()
    private val rightSum = hashMapOf<Long, Long>()

    init {
        input()
        divideSet()
        merge()
        if (s == 0)
            answer -= 1
        print(answer)
    }

    private fun input() {
        val line = br.readLine().split(" ")
        n = line[0].toInt()
        s = line[1].toInt()
        ary = br.readLine().split(" ").map { it.toInt() }.toIntArray()
    }

    private fun divideSet() {
        divide(0, n / 2, 0, leftSum)
        divide(n / 2, n, 0, rightSum)
    }

    private fun divide(idx: Int, limit: Int, sum: Long, result: HashMap<Long, Long>) {
        if (idx == limit) { //다 뽑을 경우
            result[sum] = result.getOrDefault(sum, 0) + 1
            return
        }
        divide(idx + 1, limit, sum + ary[idx], result)
        divide(idx + 1, limit, sum, result)
    }

    private fun merge() {
        val leftSumList = leftSum.keys.toList().sorted()
        val rightSumList = rightSum.keys.toList().sorted()
        var left = 0
        var right = rightSumList.size - 1
        while (left < leftSumList.size && right >= 0) {
            val sum = leftSumList[left] + rightSumList[right]
            if (sum.toInt() == s) { //합일 때
                answer += leftSum[leftSumList[left]]!! * rightSum[rightSumList[right]]!!
                left += 1
                right -= 1
            } else if (sum > s) {
                right--
            } else if (sum < s) {
                left++
            }
        }
    }
}

fun main() {
    val solution = Solution()
}