package BinarySearch.`2352_반도체_설계`.LJH

class Solution {

    private val br = System.`in`.bufferedReader()
    private var n = 0
    private lateinit var graph: IntArray
    private val result = arrayListOf<Int>()
    fun run() {
        input()
        solve()
        print(result.size)
    }

    private fun input() {
        n = br.readLine().toInt()
        graph = br.readLine().split(" ").map { it.toInt() }.toIntArray()
    }

    private fun solve() {
        result.add(graph.first())
        for (i in 1 until n) {
            if (result.last() < graph[i]) { //새로 들어오는 원소가 더 클 경우
                result.add(graph[i])
            } else { //더 작을 경우
                val index = binarySearch(graph[i])
                result[index] = graph[i]
            }
        }
    }

    private fun binarySearch(num: Int): Int { //num이 들어갈 수 있는 공간을 찾아야 함.
        var left = 0
        var right = result.size - 1
        while (left <= right) {
            val mid = (left + right) / 2
            if (result[mid] > num) { //더 클 경우
                right = mid - 1
            } else { //작거나 같을 경우
                left = mid + 1
            }
        }
        return left
    }

}

fun main() {
    val solution = Solution().run()
}