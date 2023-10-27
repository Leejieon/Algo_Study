import kotlin.math.*
class Solution {

    var graph : ArrayList<Queen> = arrayListOf()
    var answer =0
    data class Queen(var row : Int, var col : Int)
    fun solution(n: Int): Int {
        nQueen(n,0)
        return answer
    }

    fun nQueen(n : Int, depth : Int){
        if(depth==n){
            answer++
            return
        }
        for(col in 0 until n){
            graph.add(Queen(depth,col))
            if(!checkAttack())
                nQueen(n,depth+1)
            graph.remove(Queen(depth,col))
        }
    }
    fun checkAttack() : Boolean{
        for(queen in graph){
            val col = graph.filter{it.col==queen.col}
            val up = graph.filter{(queen.col-it.col) == (queen.row-it.row) || (it.col-queen.col)==(queen.row-it.row)}
            if(col.size>=2 || up.size>=2)
                return true
        }
        return false

    }
}