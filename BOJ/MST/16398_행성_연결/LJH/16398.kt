package MST.`16398_행성_연결`.LJH

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.PriorityQueue
import java.util.StringTokenizer

class `16398` {

    private val br = BufferedReader(InputStreamReader(System.`in`))
    data class Node(val from: Int, val to: Int, val value: Int)
    private val pq = PriorityQueue<Node>(compareBy { +it.value })
    lateinit var parent: Array<Int>

    init {
        input()
        print(makeMst())
    }

    private fun input() {
        val n = br.readLine().toInt()
        parent = Array(n + 1) { it }
        for (i in 0 until n) {
            val line = br.readLine().split(" ")
            for (j in i+1 until n) {
                pq.add(Node(i, j, line[j].toInt()))
            }
        }
    }

    private fun makeMst(): Long {
        var sum = 0L
        while (pq.isNotEmpty()) {
            val edge = pq.poll()
            if (find(edge.from) != find(edge.to)) {
                sum += edge.value
                union(edge)
            }
        }
        return sum
    }

    private fun find(id: Int): Int {
        if (parent[id] == id)
            return id
        parent[id] = find(parent[id])
        return parent[id]
    }

    private fun union(edge: Node) {
        val fromParent = find(edge.from)
        val toParent = find(edge.to)
        if (fromParent > toParent) parent[fromParent] = parent[toParent]
        else parent[toParent] = parent[fromParent]

    }
}

fun main() {
    val solution = `16398`()
}
