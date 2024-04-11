package DP.`2482_색상환`.LJH

class Solution {

    private val br = System.`in`.bufferedReader()
    private var n = 0
    private var k = 0
    private lateinit var dp: Array<IntArray>
    private val div = 1000000003

    fun run() {
        input()
        if (k > n / 2) {
            print(0)
            return
        }
        solve()
        print(dp[n][k])
    }

    private fun input() {
        n = br.readLine().toInt()
        k = br.readLine().toInt()
        dp = Array(n + 1) { IntArray(n + 1) }
    }

    private fun solve() {
        for (i in 0..n) {
            dp[i][1] = i
        }
        for (i in 1..n) {
            for (j in 2..i / 2) {
                dp[i][j] = (dp[i - 2][j - 1] + dp[i - 1][j]) % div
            }
        }
    }
}

fun main() {
    val solution = Solution().run()
}