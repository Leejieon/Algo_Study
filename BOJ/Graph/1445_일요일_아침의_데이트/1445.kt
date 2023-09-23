package Graph.`1445_일요일_아침의_데이트`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

class `1445` {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private var n = 0
    private var m = 0
    lateinit var graph: Array<Array<Char>>
    lateinit var visit: Array<Array<Boolean>>
    val x = arrayOf(1, -1, 0, 0)
    val y = arrayOf(0, 0, -1, 1)
    data class Walk(var x: Int = 0, var y: Int = 0, var nearG: Int = 0, var meetG: Int = 0){
        override fun toString(): String {
            return "$x $y $nearG $meetG"
        }
    }
    val pq = PriorityQueue<Walk>(compareBy({+it.meetG}, {+it.nearG}) ) //만난 지점을 최소화하기
    lateinit var s : Pair<Int,Int>
    lateinit var f : Pair<Int,Int>

    init {
        input()
        bfs()
    }

    fun input() {
        br.readLine().split(" ").forEachIndexed { index, s ->
            when (index) {
                0 -> n = s.toInt()
                1 -> m = s.toInt()
            }
        }
        graph = Array(n) { Array(m) { ' ' } }
        visit = Array(n) { Array(m) { false } }
        for (i in 0 until n) {
            val line = br.readLine().toCharArray()
            for (j in 0 until m) {
                graph[i][j] = line[j]
                if (line[j] == 'S')
                    s = Pair(i,j)
                else if (line[j] == 'F')
                    f = Pair(i,j)
            }
        }
    }

    private fun bfs() {
        pq.add(Walk(s.first,s.second,0,0))
        visit[s.first][s.second]=true
        while (!pq.isEmpty()) {
            val now = pq.poll()
            for (dir in 0 until 4) {
                var near = now.nearG
                var goal = now.meetG
                val moveX = now.x + x[dir]
                val moveY = now.y + y[dir]
                if (moveX !in graph.indices || moveY !in graph[0].indices)
                    continue
                if(moveX==f.first && moveY ==f.second) { //f 주변은 계산 x
                    print("$goal $near")
                    return
                }
                if(visit[moveX][moveY])
                    continue
                if(graph[moveX][moveY]=='.')
                    near += checkGarbage(moveX,moveY)
                if (graph[moveX][moveY] == 'g') goal++
                pq.add(Walk(moveX,moveY,near,goal))
                visit[moveX][moveY]=true
            }
        }
    }
    private fun checkGarbage(row : Int, col : Int) : Int{
        for(dir in 0 until 4){
            val moveX = row+x[dir]
            val moveY = col+y[dir]
            if (moveX !in graph.indices || moveY !in graph[0].indices)
                continue
            if(graph[moveX][moveY]=='g')
                return 1
        }
        return 0
    }
}

fun main() {
    val solution = `1445`()
}