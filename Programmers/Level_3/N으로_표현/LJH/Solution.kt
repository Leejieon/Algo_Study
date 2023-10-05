import java.util.*
class Solution {
    var answer = 0

    var dp = mutableListOf<num>()
    fun solution(N: Int, number: Int): Int {
        if(N==number)
            return 1
        getNum(N,number)
        return answer
    }
    fun getNum(N : Int, number : Int){
        var temp=1
        dp.add(num(0))
        for(i in 1 .. 8){
            dp.add(num(i))
            if(dp.size==2)  {
                dp[dp.size-1].setList.add(N)
            }
            else dp[dp.size-1].setList.add(dp[dp.size-2].findFirst()*10+N)
        }
        answer = calculate(N,number)
    }
    fun calculate(N:Int,number:Int) : Int{

        for(i in 1 until dp.size){
            for(j in 1 .. i){
                for(first in dp[j].setList){
                    for(second in dp[i-j].setList){
                        dp[i].addNum(first,second)
                    }
                }
                if(dp[i].findNum(number))
                    return i
            }
        }
        return -1
    }

    data class num(val id : Int){
        var setList = HashSet<Int>()

        fun addNum(first:Int, second:Int){
            setList.add(first+second)
            setList.add(first-second)
            setList.add(first*second)
            if(second!=0)
                setList.add(first/second)
        }
        fun findFirst() : Int {
            return setList.first()
        }
        fun findNum(n:Int) : Boolean{
            return setList.contains(n)
        }
    }
}