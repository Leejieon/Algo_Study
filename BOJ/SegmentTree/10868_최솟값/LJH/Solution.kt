package SegmentTree.`10868_최솟값`.LJH

import kotlin.math.*

class Solution {

    private val br = System.`in`.bufferedReader()
    private var n = 0
    private var m = 0
    private val sb = StringBuilder()
    private lateinit var ary: IntArray
    private lateinit var tree: IntArray
    private var minValue = Int.MAX_VALUE
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
        tree = IntArray(4 * n)
        initTree(1, n, 1)
        repeat(m) {
            br.readLine().split(" ").map { it.toInt() }.apply {
                val a = this[0]
                val b = this[1]
                minValue = Int.MAX_VALUE
                findValue(1, n, a, b, 1)
            }
            sb.append(minValue).append("\n")
        }
    }

    private fun initTree(start: Int, end: Int, nodeIdx: Int) {
        if (start == end) {
            tree[nodeIdx] = ary[start]
            return
        }
        val mid = (start + end) / 2
        initTree(start, mid, 2 * nodeIdx)
        initTree(mid + 1, end, 2 * nodeIdx + 1)
        tree[nodeIdx] = min(tree[nodeIdx * 2], tree[nodeIdx * 2 + 1])
    }

    private fun findValue(start: Int, end: Int, left: Int, right: Int, nodeIdx: Int) { //최대값 최솟값 찾기
        if (left > end || right < start) { // left right가 찾으려는 구간이고 start end 가 현재 구간 현재 구간을 벗어나면 안됨.
            return
        }
        if (left <= start && end <= right) {
            minValue = min(minValue, tree[nodeIdx])
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