class Solution {

    val result = mutableListOf<String>()
    var isFinished = false
    fun solution(tickets: Array<Array<String>>): Array<String> {
        val visited = Array(tickets.size){false}
        var start = "ICN"
        val list = tickets.sortedBy{it[1]}
        dfs(start,0,list,visited)
        return result.toTypedArray()
    }

    fun dfs(cur: String, cnt:  Int,tickets:List<Array<String>>, visited:Array<Boolean>){
        if(cnt==tickets.size){ //다 뽑았을때
            isFinished = true
        }
        result.add(cur)
        for(i in 0 until tickets.size){
            if(!visited[i] && tickets[i][0] == cur){
                visited[i] = true
                dfs(tickets[i][1], cnt+1, tickets, visited)
                if(!isFinished){
                    result.removeLast()
                    visited[i]=false
                }
            }
        }
    }
}