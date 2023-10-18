package MST.`1197_최소스패닝트리`.LJH

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

class `1197` {

    private val br = BufferedReader(InputStreamReader(System.`in`))
    val array = PriorityQueue<node>(compareBy({ +it.value }))
    lateinit var parent: Array<Int>

    init {
        input()
        print(makeTree())
    }

    fun input() {
        val line = br.readLine().split(" ")
        val v = line[0].toInt()
        val e = line[1].toInt()
        parent = Array(v + 1) { it }
        repeat(e) {
            val input = br.readLine().split(" ")
            val start = input[0].toInt()
            val end = input[1].toInt()
            val weight = input[2].toInt()
            array.add(node(start, end, weight))
        }
    }

    fun makeTree(): Int {
        var answer = 0
        while(array.isNotEmpty()) {
            val edge = array.poll()
            if (find(edge.start) != find(edge.from)) { //부모가 다를 경우
                union(edge)
                answer += edge.value
            }
        }
        return answer
    }

    fun find(child: Int): Int {
        if (parent[child] == child)
            return child
        parent[child] = find(parent[child])
        return parent[child]
    }

    fun union(edge: node) {
        val startParent = parent[edge.start]
        val fromParent = parent[edge.from]
        if (startParent > fromParent) {
            parent[startParent] = parent[fromParent]
        } else parent[fromParent] = parent[startParent]
    }

    data class node(val start: Int, val from: Int, val value: Int)
}


fun main() {
    val solution = `1197`()
}