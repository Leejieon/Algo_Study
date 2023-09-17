package Graph.`16932_모양_만들기`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList
import java.util.Queue
import kotlin.math.max

class `16932` {

    private val br = BufferedReader(InputStreamReader(System.`in`))
    private var n = 0
    private var m = 0
    private lateinit var ary: Array<Array<Int>>
    private lateinit var group: Array<Array<Int>>
    private val x = arrayOf(1, -1, 0, 0)
    private val y = arrayOf(0, 0, 1, -1)
    private var answer = 0
    private val groupMember = mutableListOf<Int>()
    private val zeroQ: Queue<Pair<Int, Int>> = LinkedList()
    private val q: Queue<Pair<Int, Int>> = LinkedList()
    var groupLabel = 0

    init {
        input()
        select()
        print(answer)
    }

    private fun input() {
        var line = br.readLine().split(" ")
        n = line[0].toInt()
        m = line[1].toInt()
        ary = Array(n, { Array(m, { 0 }) })
        group = Array(n, { Array(m, { 0 }) })
        for (i in 0 until n) {
            line = br.readLine().split(" ")
            for (j in 0 until m) {
                ary[i][j] = line[j].toInt()
                if (ary[i][j] == 0) zeroQ.add(Pair(i, j))
                else q.add(Pair(i, j))
            }
        }
    }

    private fun labelGroup(row: Int, col: Int) {
        groupLabel++
        var cnt = 1
        val tempQ: Queue<Pair<Int, Int>> = LinkedList()
        group[row][col] = groupLabel
        tempQ.add(Pair(row, col))
        while (!tempQ.isEmpty()) {
            val item = tempQ.poll()
            for (dir in 0 until 4) {
                val moveX = item.first + x[dir]
                val moveY = item.second + y[dir]
                if ((moveX !in ary.indices) || (moveY !in ary[moveX].indices))
                    continue
                if (ary[moveX][moveY] == 0)
                    continue
                if (group[moveX][moveY] != 0)
                    continue
                group[moveX][moveY] = groupLabel
                tempQ.add(Pair(moveX, moveY))
                cnt++
            }
        }
        groupMember.add(cnt)
    }

    private fun select() {
        groupMember.add(0)
        while (!q.isEmpty()) {
            val item = q.poll()
            if (group[item.first][item.second] != 0)
                continue
            labelGroup(item.first, item.second)
        }
        while (!zeroQ.isEmpty()) {
            val item = zeroQ.poll()
            bfs(item.first, item.second)
        }
    }

    private fun bfs(row: Int, col: Int) {
        val check: HashSet<Int> = hashSetOf<Int>()
        var cnt = 1
        for (dir in 0 until 4) {
            val moveX = row + x[dir]
            val moveY = col + y[dir]
            if ((moveX !in ary.indices) || (moveY !in ary[moveX].indices))
                continue
            if (ary[moveX][moveY] == 0)
                continue
            if (check.contains(group[moveX][moveY]))
                continue
            check.add(group[moveX][moveY])
            cnt += groupMember[group[moveX][moveY]]
        }
        answer = max(answer, cnt)
    }
}

fun main() {
    val solution = `16932`()
}