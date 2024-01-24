package TwoPointer.`20181_꿈틀꿈틀_호석_애벌레_효율성`.LJH

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.max

class `20181` {
    private var n = 0
    private var k = 0
    private lateinit var array: List<Long>
    private lateinit var dp: Array<Long>
    private val br = BufferedReader(InputStreamReader(System.`in`))

    fun run() {
        input()
        search()
        println(dp[n - 1])
    }

    private fun input() {
        val line = br.readLine().split(" ")
        n = line[0].toInt()
        k = line[1].toInt()
        array = br.readLine().split(" ").map { it.toLong() }
        dp = Array(n) { 0 }
    }

    private fun search() {
        var start = 0
        var end = 0
        var sum = 0L
        while (start <= end) {
            if (end > n)
                break
            if (sum >= k) { //누적합 뚫었을 경우
                if (start == 0) {
                    dp[end - 1] = max(dp[start] + sum - k, dp[end - 1])
                } else {
                    dp[end - 1] = max(dp[start - 1] + sum - k, dp[end - 1])
                }
                sum -= array[start]
                start++
            } else {
                if (end >= n)
                    break
                sum += array[end]
                if (end != 0)
                    dp[end] = max(dp[end], dp[end - 1])
                end++
            }
        }
    }
}

fun main() {
    `20181`().run()

}
