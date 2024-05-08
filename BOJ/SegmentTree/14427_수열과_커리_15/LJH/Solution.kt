package SegmentTree.`14427_수열과_커리_15`.LJH

import java.util.Stack
import kotlin.math.*

class Solution {

    private val br = System.`in`.bufferedReader()
    private var n = 0
    private var m = 0
    private val sb = StringBuilder()
    private lateinit var ary: IntArray
    private lateinit var minTree: IntArray

    fun run() {
        input()
        solution()
        print(sb.toString())
    }

    private fun input() {
        n = br.readLine().toInt()
        ary = IntArray(n + 1)
        minTree = IntArray(4 * n)
        val line = br.readLine().split(" ").map { it.toInt() }
        for (i in 1..n) {
            ary[i] = line[i - 1]
        }
        initTree(1, n, 1)
        m = br.readLine().toInt()
        repeat(m) {
            val input = br.readLine().split(" ").map { it.toInt() }
            if (input[0] == 1) { //1번 쿼리 값 변경하기
                ary[input[1]] = input[2]
                updateTree(1, n, 1, input[1])
            } else { //2번 쿼리 가장 작은 녀석 출력하기.
                sb.append(minTree[1]).append("\n")
            }
        }
    }

    private fun initTree(start: Int, end: Int, nodeIdx: Int) {
        if (start == end) { //리프 노드일 경우
            minTree[nodeIdx] = start
            return
        }
        val mid = (start + end) / 2
        val left = 2 * nodeIdx
        val right = 2 * nodeIdx + 1
        initTree(start, mid, left)
        initTree(mid + 1, end, right)
        //minTree의 값은 인덱스를 의미함.
        //minTree에는 작은 값의 인덱스가 들어와야 함.
        //만약 값이 같다면 더 작은 인덱스 값
        if (ary[minTree[left]] == ary[minTree[right]]) { //값이 같을 경우 작은 인덱스
            minTree[nodeIdx] = minTree[left]
        } else if (ary[minTree[left]] < ary[minTree[right]]) {
            minTree[nodeIdx] = minTree[left]
        } else {
            minTree[nodeIdx] = minTree[right]
        }
    }

    private fun updateTree(start: Int, end: Int, nodeIdx: Int, targetIdx: Int) {
        if (start > targetIdx || end < targetIdx || start==end) { //범위가 아닐 경우
            return
        }

        val mid = (start + end) / 2
        val left = 2 * nodeIdx
        val right = 2 * nodeIdx + 1
        updateTree(start, mid, left, targetIdx)
        updateTree(mid + 1, end, right, targetIdx)
        if (ary[minTree[left]] == ary[minTree[right]]) { //값이 같을 경우 작은 인덱스
            minTree[nodeIdx] = minTree[left]
        } else if (ary[minTree[left]] < ary[minTree[right]]) {
            minTree[nodeIdx] = minTree[left]
        } else {
            minTree[nodeIdx] = minTree[right]
        }
    }

    private fun solution() {

    }
}

fun main() {
    Solution().run()
}