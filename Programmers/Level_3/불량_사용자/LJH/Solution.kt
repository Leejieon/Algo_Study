class Solution {

    private val idSet = mutableSetOf<MutableSet<String>>()
    private lateinit var visit : Array<Boolean>
    private val candidate = mutableListOf<String>()
    var limit = 0
    fun solution(user_id: Array<String>, banned_id: Array<String>): Int {
        var answer = 0
        limit = user_id.size
        visit=Array(limit){false}
        makeCombination(user_id,banned_id)
        return idSet.size
    }

    fun makeCombination(user_id : Array<String>, banned_id : Array<String>){
        if(candidate.size == banned_id.size){
            if(matchingId(user_id,banned_id)) idSet.add(candidate.toMutableSet())
            return
        }

        for(i in 0 until user_id.size){
            if(visit[i])
                continue
            candidate.add(user_id[i])
            visit[i]=true
            makeCombination(user_id,banned_id)
            visit[i]=false
            candidate.remove(user_id[i])

        }
    }
    fun matchingId(user_id : Array<String>,banned_id : Array<String>) : Boolean{
        for(i in 0 until banned_id.size){ //후보군들중에서. 순서를 해야하는구나..
            if(candidate[i].length != banned_id[i].length) //길이가 다를 경우
                return false
            for(j in 0 until candidate[i].length){
                if(banned_id[i][j]=='*')
                    continue
                if(banned_id[i][j]!=candidate[i][j])
                    return false
            }

        }
        return true
    }
}