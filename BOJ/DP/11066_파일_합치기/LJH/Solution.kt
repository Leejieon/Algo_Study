package DP.`11066_파일_합치기`.LJH

class Solution {

    private val br = System.`in`.bufferedReader()
    private var t = 0
    private var k = 0
    private lateinit var ary: IntArray
    private lateinit var answer: IntArray
    private lateinit var dp: Array<IntArray>
    private lateinit var sum: IntArray
    fun run() {
        t = br.readLine().toInt()
        answer = IntArray(t)
        repeat(t) {
            input()
            solve()
            answer[it] = dp[1][k]
        }
        repeat(t){
            println(answer[it])
        }
    }

    private fun input() {
        k = br.readLine().toInt()
        ary = br.readLine().split(" ").map { it.toInt() }.toIntArray()
        dp = Array(k + 1) { IntArray(k + 1) }
        sum = IntArray(k + 1)
    }

    private fun solve() {
        for (i in 1..k) {
            sum[i] = sum[i - 1] + ary[i - 1]
        }
        for (i in 1..k) {
            for (j in 1..k - i) {
                dp[j][i + j] = Int.MAX_VALUE
                for (divide in j..<i + j) {
                    dp[j][j + i] = minOf(dp[j][j + i], dp[j][divide] + dp[divide + 1][i + j] + sum[i + j] - sum[j - 1])
                }
            }
        }
    }
}

fun main() {
    val solution = Solution().run()
}