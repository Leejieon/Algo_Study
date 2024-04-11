package DP.`1029_그림_교환`.LJH

import kotlin.math.max

class Solution {

    private val br = System.`in`.bufferedReader()
    private var n = 0
    private lateinit var ary: Array<IntArray>
    private lateinit var dp: Array<Array<IntArray>>
    fun run() {
        input()
        print(solve(1,0,1,0))
    }

    private fun input() {
        n = br.readLine().toInt()
        ary = Array(n) { IntArray(n) }
        for (i in 0 until n) {
            val line = br.readLine()
            for (j in 0 until n) {
                ary[i][j] = line[j].toString().toInt()
            }
        }
        dp = Array(1 shl n) { Array(n) { IntArray(10) } }
    }

    private fun solve(visited: Int, curPeople: Int, depth: Int, lastPrice: Int): Int {

        if (visited == 1 shr n - 1) { //다 방문했을 경우
            return 0
        }

        if (dp[visited][curPeople][lastPrice] != 0) {
            return dp[visited][curPeople][lastPrice]
        }

        dp[visited][curPeople][lastPrice] = depth

        for (i in 0 until n) {
            val next = 1 shl i
            val nowPrice = ary[curPeople][i]
            if (visited and next != 0) { //이미 산 사람이라면
                continue
            }
            if (lastPrice > nowPrice) { //마지막에 산 가격보다 크다면
                continue
            }
            dp[visited][curPeople][lastPrice] = max(dp[visited][curPeople][lastPrice],solve(visited or next, i, depth + 1, nowPrice))
        }
        return dp[visited][curPeople][lastPrice]
    }
}

fun main() {
    val solution = Solution().run()
}