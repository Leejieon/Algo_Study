package Level_3.네트워크.LJH

class Solution {
    val nodeList = mutableMapOf<Int,Node>()
    lateinit var visit : Array<Boolean>
    data class Node(val value : Int){
        val list = mutableListOf<Node>()
    }

    fun containNode(value : Int){
        if(!nodeList.contains(value)){
            nodeList[value]=Node(value)
        }
    }
    fun addEdge(from : Int, to: Int){
        val fromNode = nodeList[from]
        if(fromNode==null) return
        val toNode = nodeList[to]
        if(toNode == null) return
        fromNode.list.add(toNode)
        toNode.list.add(fromNode)
    }
    fun solution(n: Int, computers: Array<IntArray>): Int {
        var answer = 0
        visit = Array(n){false}
        for(i in 0 until n){
            containNode(i) //i노드가 있는지 확인함.
            for(j in i+1 until n){
                if(!nodeList.contains(j)){
                    nodeList[j] = Node(j) //노드 생성
                }
                if(computers[i][j]==1){ //연결됨
                    computers[j][i]=0 //방문처리
                    computers[i][j]=0 //방문 처리
                    addEdge(i,j)      //연결
                }
            }
        }
        for(node in nodeList){
            if(visit[node.key])
                continue
            visit[node.key]=true
            for(item in node.value.list){
                if(visit[item.value])
                    continue
                visit[item.value]=true
                search(item)
            }
            answer++
        }
        return answer
    }
    fun search(node : Node){
        for(item in node.list){
            if(visit[item.value])
                continue
            visit[item.value]=true
            search(item)
        }
    }
}