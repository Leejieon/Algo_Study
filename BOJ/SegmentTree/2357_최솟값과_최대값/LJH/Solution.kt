package SegmentTree.`2357_최솟값과_최대값`.LJH

import kotlin.math.*

class Solution {

    private val br = System.`in`.bufferedReader()
    private var n = 0
    private var m = 0
    private val sb = StringBuilder()
    private lateinit var ary: IntArray
    private lateinit var maxTree: IntArray
    private lateinit var minTree: IntArray
    private var minValue = Int.MAX_VALUE
    private var maxValue = Int.MIN_VALUE
    fun run() {
        input()
        print(sb.toString())
    }

    private fun input() {
        br.readLine().split(" ").map { it.toInt() }.apply {
            n = this[0]
            m = this[1]
        }
        ary = IntArray(n + 1)
        for (i in 1..n) {
            ary[i] = br.readLine().toInt()
        }
        maxTree = IntArray(4 * n)
        minTree = IntArray(4 * n)
        initTree(1, n, 1)
        repeat(m) {
            br.readLine().split(" ").map { it.toInt() }.apply {
                val a = this[0]
                val b = this[1]
                minValue = Int.MAX_VALUE
                maxValue = Int.MIN_VALUE
                findValue(1, n, a, b, 1)
            }
            sb.append(minValue).append(" ").append(maxValue).append("\n")
        }
    }

    private fun initTree(start: Int, end: Int, nodeIdx: Int) {
        if (start == end) {
            minTree[nodeIdx] = ary[start]
            maxTree[nodeIdx] = ary[start]
            return
        }
        val mid = (start + end) / 2
        initTree(start, mid, 2 * nodeIdx)
        initTree(mid + 1, end, 2 * nodeIdx + 1)
        minTree[nodeIdx] = min(minTree[nodeIdx * 2], minTree[nodeIdx * 2 + 1])
        maxTree[nodeIdx] = max(maxTree[nodeIdx * 2], maxTree[nodeIdx * 2 + 1])
    }

    private fun findValue(start: Int, end: Int, left: Int, right: Int, nodeIdx: Int) { //최대값 최솟값 찾기
        if (left > end || right < start) { // left right가 찾으려는 구간이고 start end 가 현재 구간 현재 구간을 벗어나면 안됨.
            return
        }
        if (left <= start && end <= right) {
            minValue = min(minValue, minTree[nodeIdx])
            maxValue = max(maxValue, maxTree[nodeIdx])
            return
        }
        val mid = (start + end) / 2
        findValue(start, mid, left, right, nodeIdx * 2)
        findValue(mid + 1, end, left, right, nodeIdx * 2 + 1)
    }
}

fun main() {
    Solution().run()
}