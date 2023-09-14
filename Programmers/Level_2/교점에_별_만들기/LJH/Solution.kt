package Level_2.교점에_별_만들기.LJH

import kotlin.math.*

class Solution {
    private val expression : MutableList<Triple<Long,Long,Long>> = mutableListOf()
    private val meet : MutableList<Pair<Int,Int>> =mutableListOf()
    private var minX =Int.MAX_VALUE
    private var minY =Int.MAX_VALUE
    private var maxX = Int.MIN_VALUE
    private var maxY=Int.MIN_VALUE
    fun solution(line: Array<IntArray>): Array<String> {
        for(i in 0 until line.size){
            expression.add(Triple(line[i][0].toLong(),line[i][1].toLong(),line[i][2].toLong()))
        }
        for(i in 0 until expression.size){
            for(j in i+1 until expression.size){
                val item = checkMeet(expression[i],expression[j])
                if(item!=null)
                    meet.add(item)
            }
        }
        val graph = Array(maxY-minY+1){CharArray(maxX-minX+1){'.'}}
        for(dot in meet){
            val r=maxY-dot.second
            val c=dot.first-minX
            graph[r][c]='*'
        }
        val answer = Array(graph.size){String(graph[it])}
        return answer
    }
    fun checkMeet(first : Triple<Long,Long,Long>,second:Triple<Long,Long,Long>) : Pair<Int,Int>?{
        //평행한 경우는 교점이 없다.
        val adbc = first.first*second.second-first.second*second.first //adbc
        if(adbc.toInt()==0) //평행 혹은 일치
            return null
        val bfed = first.second*second.third-first.third*second.second //bfed
        val ecaf=first.third*second.first-first.first*second.third

        if((bfed%adbc).toInt()!=0 || (ecaf%adbc).toInt()!=0)
            return null

        val meetX = (bfed/adbc).toInt()
        val meetY = (ecaf/adbc).toInt()
        maxX = max(maxX,meetX)
        minX = min(minX,meetX)
        maxY = max(maxY,meetY)
        minY = min(minY,meetY)
        return Pair(meetX,meetY)
    }
}