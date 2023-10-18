package MST.`27498_연애혁명`.LJH

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.PriorityQueue
import java.util.StringTokenizer

class `27498` {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val pq = PriorityQueue<Node>(compareBy({-it.weight})) //성공한 놈들을 우선적으로
    private lateinit var parent: Array<Int>
    private var answer =0
    private var include =0
    init {
        input()
        makeMst()
        print(answer-include)
    }

    private fun input() {
        val line = br.readLine().split(" ")
        val v = line[0].toInt()
        val e = line[1].toInt()
        parent = Array(v+1){it}
        repeat(e) {
            val input = StringTokenizer(br.readLine())
            val from = input.nextToken().toInt()
            val to =  input.nextToken().toInt()
            val weight = input.nextToken().toInt()
            val success = input.nextToken().toInt()
            if(success==1){
                union(from,to)
            }
            else {
                answer+=weight
                pq.add(Node(from,to,weight,success))
            }
        }
    }
    fun makeMst(){
        while(pq.isNotEmpty()){
            val edge = pq.poll()
            if(find(edge.from)!=find(edge.to)){
                union(edge.from, edge.to)
                include+=edge.weight
            }
        }
    }
    private fun find(id : Int) : Int{
        if(parent[id]==id)
            return id
        parent[id] = find(parent[id])
        return parent[id]
    }
    private fun union(from : Int,to : Int){
        val fromParent = find(from)
        val toParent = find(to)
        if(fromParent>toParent)  parent[fromParent] = parent[toParent]
        else parent[toParent] = parent[fromParent]
    }
    data class Node(val from: Int, val to: Int, val weight: Int, val success: Int)
}

fun main() {
    val solution = `27498`()

}