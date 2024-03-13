import java.util.*
class Solution {

    data class Line(
        val from : Int,
        val to : Int,
        val weight: Int
    )
    val pq = PriorityQueue<Line>(compareBy{+it.weight})
    val parent = IntArray(101){it}
    var answer = 0
    fun solution(n: Int, costs: Array<IntArray>): Int {
        for(i in 0 until costs.size){
            pq.add(Line(costs[i][0], costs[i][1], costs[i][2]))
        }
        makeMst()
        return answer
    }
    fun makeMst(){
        while(!pq.isEmpty()){
            val now = pq.poll()
            if(isConnected(now.from,now.to)){ //연결할 수 있다면
                answer+=now.weight //비용 더하기
            }
        }
    }

    fun isConnected(from : Int, to : Int) : Boolean{
        val fromParent = findParent(from) //from의 부모 찾기
        val toParent = findParent(to) //to의 부모 찾기
        if(fromParent==toParent){ //부모가 같다면 이미 연결된것.
            return false
        }
        if(fromParent<toParent){
            parent[toParent] = parent[fromParent]
        } else{
            parent[fromParent] = parent[toParent]
        }
        return true
    }

    fun findParent(vertex : Int) : Int{
        if(vertex == parent[vertex]){ //자기 자신이 부모일 경우
            return vertex
        }
        parent[vertex] = findParent(parent[vertex])
        return parent[vertex]
    }

}