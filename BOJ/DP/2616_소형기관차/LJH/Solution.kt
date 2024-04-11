package DP.`2616_소형기관차`.LJH

import kotlin.math.max

class Solution {

    private val br = System.`in`.bufferedReader()
    private var n = 0
    private var k = 0
    private lateinit var ary: IntArray
    private lateinit var sum: IntArray
    private lateinit var dp: Array<IntArray>

    fun run() {
        input()
        getAccumulateSum()
        solve()
        print(dp[3][n])
    }

    private fun input() {
        n = br.readLine().toInt()
        ary = br.readLine().split(" ").map { it.toInt() }.toIntArray()
        sum = IntArray(n + 1)
        dp = Array(4) { IntArray(n + 1) }
        k = br.readLine().toInt()
    }

    private fun solve() {
        for (i in 1..3) {
            for (j in k..n) {
                dp[i][j] = maxOf(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - k] + sum[j - 1])
            }
        }
    }

    private fun getAccumulateSum() {
        for (i in 0 until k) {
            sum[k - 1] += ary[i]
        }
        for (i in k until n) {
            sum[i] = sum[i - 1] - ary[i - k] + ary[i]
        }
    }
}

fun main() {
    val solution = Solution().run()
}