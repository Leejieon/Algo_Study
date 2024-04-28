package BinarySearch.`2110_공유기_설치`.LJH

import kotlin.math.max

class Solution {

    private val br = System.`in`.bufferedReader()
    private var n = 0
    private var c = 0
    private lateinit var graph: IntArray


    fun run() {
        input()
        print(solve())
    }

    private fun input() {
        br.readLine().split(" ").map { it.toInt() }.apply {
            n = this[0]
            c = this[1]
        }
        graph = IntArray(n) {
            br.readLine().toInt()
        }
        graph.sort()
    }

    private fun solve(): Int {
        var left = 1
        var right = graph.last() - graph.first()
        var result = 0
        while (left <= right) {
            val mid = (left + right) / 2
            val cnt = count(mid)
            if (cnt < c) { //설치하는 공유기의 수가 적다면
                right = mid-1
            } else {
                result = max(result, mid)
                left = mid+1
            }
        }
        return result
    }

    private fun count(dist: Int): Int {
        var cnt = 1
        var startDist = graph[0]
        for (i in 1 until n) {
            if (graph[i] - startDist >= dist) {
                cnt++
                startDist = graph[i]
            }
        }
        return cnt
    }


}

fun main() {
    val solution = Solution().run()
}