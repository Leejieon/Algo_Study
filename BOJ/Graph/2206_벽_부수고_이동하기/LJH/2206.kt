package Graph.`2206_벽_부수고_이동하기`.LJH

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

class `2206` {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private var n=0
    private var m=0
    private var answer=-1
    private lateinit var visit : Array<Array<IntArray>>
    private lateinit var graph : Array<Array<Int>>
    var q : Queue<Move> = LinkedList()
    val x = arrayOf(1,-1,0,0)
    val y = arrayOf(0,0,1,-1)
    init{
        input()
        bfs()
        print(answer)
    }
    private fun input()
    {
        br.readLine().split(" ").forEachIndexed { index, s ->
            when(index){
                0 -> n=s.toInt()
                1->m=s.toInt()
            }
        }
        if(n==1 && m==1)
            answer=1
        graph = Array(n){ Array(m,{0})}
        visit = Array(n){ Array(m) {IntArray(2,{-1}) }}
        for(i in 0 until n){
            val line = br.readLine().toString()
            for(j in 0 until m){
                graph[i][j] = line[j].digitToInt()
            }
        }
    }

    private fun bfs(){
        q.add(Move(0,0,0,1))
        while(!q.isEmpty()){
            val now = q. poll()
            //println("now : $now")
            if(visit[now.x][now.y][now.wall]!=-1)
                continue
            visit[now.x][now.y][now.wall]=now.room
            for(dir in 0 until 4){
                val moveX = now.x+x[dir]
                val moveY = now.y+y[dir]
                if(moveX !in graph.indices || moveY !in graph[0].indices) //범위가 아닐 때
                    continue
                //println("move : $moveX $moveY graph : ${graph[moveX][moveY]}")
                if(moveX==n-1 && moveY == m-1){
                    //println(now.room+1)
                    answer=now.room+1
                    return
                }
                if(now.wall==1 && graph[moveX][moveY]==1) //벽을 이미 깨부수고, 가는 곳이 벽일 경우 못감
                    continue
                if(visit[moveX][moveY][now.wall]!=-1) //이미 방문했다면
                    continue
                if(now.wall==0 && graph[moveX][moveY]==1){
                    q.add(Move(moveX,moveY,1,now.room+1))
                }
                else if(now.wall==0 && graph[moveX][moveY]==0)
                    q.add(Move(moveX,moveY,0,now.room+1))
                else // 1 0
                    q.add(Move(moveX,moveY,now.wall,now.room+1))
            }
        }
    }
    data class Move(val x: Int, val y : Int, val wall : Int, val room : Int){
        override fun toString(): String {
            return "x : $x y: $y wall : $wall room: $room"
        }
    }
}

fun main(){
    val solution = `2206`()
}

