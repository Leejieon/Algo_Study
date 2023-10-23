import kotlin.math.*

class Solution {
    lateinit var graph: Array<Array<Int>>
    lateinit var dist: Array<Int>
    lateinit var visit: Array<Boolean>
    var answer = 100000000
    val INF = 100000000
    fun solution(n: Int, s: Int, a: Int, b: Int, fares: Array<IntArray>): Int {
        graph = Array(n + 1) { Array(n + 1) { INF } }
        makeGraph(fares)
        val distS = dijkstra(s)
        val distA = dijkstra(a)
        val distB = dijkstra(b)
        for (i in 1 until n + 1) {
            answer = min(answer, distS[i] + distA[i] + distB[i])
        }
        return answer
    }

    private fun makeGraph(fares: Array<IntArray>) {
        for (input in fares) {
            graph[input[0]][input[1]] = input[2]
            graph[input[1]][input[0]] = input[2]
        }
    }

    private fun dijkstra(from: Int): Array<Int> {
        dist = Array(graph.size) { INF }
        for (i in 1 until dist.size)
            dist[i] = graph[from][i]
        visit = Array(dist.size) { false }
        visit[from] = true
        dist[from] = 0

        for (i in 1 until dist.size) {
            val minNode = findMinNode()
            visit[minNode] = true
            update(minNode)
        }
        return dist
    }

    private fun update(minNode: Int) {
        for (j in 1 until dist.size) {
            if (dist[j] > dist[minNode] + graph[j][minNode]) {
                dist[j] = dist[minNode] + graph[j][minNode]
            }
        }
    }

    private fun findMinNode(): Int {
        var min = INF
        var minIdx = 0
        for (i in 1 until dist.size) {
            if (visit[i]) continue
            if (min > dist[i]) {
                min = dist[i]
                minIdx = i
            }
        }
        return minIdx
    }
}