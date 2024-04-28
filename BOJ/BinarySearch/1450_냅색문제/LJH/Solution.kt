package BinarySearch.`1450_냅색문제`.LJH

class Solution {

    private val br = System.`in`.bufferedReader()
    private var n = 0
    private var c = 0
    private lateinit var graph: IntArray
    private val left = arrayListOf<Int>()
    private val right = arrayListOf<Int>()

    fun run() {
        input()
        solve()
    }

    private fun input() {
        br.readLine().split(" ").map { it.toInt() }.apply {
            n = this[0]
            c = this[1]
        }
        graph = br.readLine().split(" ").map { it.toInt() }.toIntArray()
    }

    private fun solve() {
        makeCombination(left, 0, n / 2, 0)
        makeCombination(right, n / 2, n, 0)
        left.sort()
        right.sort()
        var result = 0
        left.forEach {
            result+=binarySearch(it)
        }
        print(result)
    }

    private fun binarySearch(weight: Int): Int {
        var l = 0
        var r = right.size - 1
        while (l <= r) {
            val mid = (l + r) / 2
            if (right[mid] + weight <= c) {
                l = mid + 1
            } else {
                r = mid - 1
            }
        }
        return r+1
    }


    private fun makeCombination(list: ArrayList<Int>, depth: Int, limit: Int, sum: Int) {
        if (sum > c) { //넘을 경우 리턴
            return
        }
        if (depth == limit) {
            list.add(sum)
            return
        }
        makeCombination(list, depth + 1, limit, sum + graph[depth])
        makeCombination(list, depth + 1, limit, sum)
    }
}

fun main() {
    val solution = Solution().run()
}