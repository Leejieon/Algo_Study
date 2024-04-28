package BinarySearch.`1300_K번째_수`.LJH

class Solution {

    private val br = System.`in`.bufferedReader()
    private var n = 0L
    private var k = 0L
    fun run() {
        input()
        print(solve())
    }

    private fun input() {
        n = br.readLine().toLong()
        k = br.readLine().toLong()
    }

    private fun solve(): Long {
        var left = 1L
        var right = n * n
        while (left <= right) {
            val mid = (left + right) / 2
            if (checkIndex(mid) >= k) {
                right = mid - 1
            } else {
                left = mid + 1
            }
        }
        return left
    }

    private fun checkIndex(number: Long): Long {
        var sum = 0L
        for (i in 1..n) {
            var rem = number / i
            if (rem >= n) {
                rem = n.toLong()
            }
            sum += rem
        }
        return sum
    }

}

fun main() {
    val solution = Solution().run()
}