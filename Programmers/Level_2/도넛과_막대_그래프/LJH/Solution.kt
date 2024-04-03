import kotlin.math.*
class Solution {

    lateinit var graph : Array<ArrayList<Int>>
    lateinit var visit : BooleanArray
    var maxVertex = 0
    var newVertex = 0
    var answer: IntArray = intArrayOf(0,0,0,0)
    lateinit var from : IntArray
    fun solution(edges: Array<IntArray>): IntArray {
        graph = Array(1000001,{ArrayList<Int>()})
        from = IntArray(1000001)
        makeGraph(edges)
        findStartVertex()
        visit = BooleanArray(maxVertex+1)
        for(i in 0 until graph[newVertex].size){
            answer[decideGraph(graph[newVertex][i])]++
        }
        answer[0] = newVertex
        return answer
    }
    fun makeGraph(edges: Array<IntArray>){
        edges.forEach{ edge->
            val start = edge[0]
            val end = edge[1]
            from[end]++
            graph[start].add(end)
            maxVertex = max(maxVertex,max(end,start))
        }
    }

    fun findStartVertex() {
        var maxEdges = 0
        for (i in 1..maxVertex) {
            if (maxEdges < graph[i].size && from[i] == 0) {
                maxEdges = graph[i].size
                newVertex = i
            }
        }
    }

    fun decideGraph(vertex : Int) : Int{
        if(graph[vertex].size==0){    //막대
            return 2
        }
        if(graph[vertex].size==2){ //두개로 뻗어나가는 점이 있을 경우 8자 그래프
            return 3
        }
        if(visit[vertex]){ //사이클을 돈다면 도넛
            return 1
        }
        visit[vertex]=true
        return decideGraph(graph[vertex][0])
    }
}