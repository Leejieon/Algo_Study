package LJH

class Solution {
    lateinit var userList : ArrayList<User>
    lateinit var percent : Array<Pair<Int,Int>>
    var result = Pair(0,0)
    val salesAmount = listOf(10,20,30,40)

    fun solution(users: Array<IntArray>, emoticons: IntArray): IntArray {
        userList= getUser(users)
        percent = Array(emoticons.size){Pair(0,0)}
        salesEmoticons(emoticons,0)

        var answer: IntArray = intArrayOf(0,0)
        answer[0]=result.first
        answer[1]=result.second
        return answer
    }
    fun calculatePrice() : Pair<Int,Int>{
        var subscribe=0
        var totalPrice=0
        for(user in userList){
            var sum=0
            val buy = percent.filter{it.second>=user.sales} //살수 있는 확률을 찾음
            if(buy.size==0) continue
            for(emoticon in buy){
                sum+=(emoticon.first*(100-emoticon.second))/100
            }
            if(sum>=user.limit){
                subscribe++
            } else {
                totalPrice+=sum
            }
        }
        return Pair(subscribe,totalPrice)
    }
    fun salesEmoticons(emoticons : IntArray,depth : Int){
        if(depth==emoticons.size){ //끝까지 갔을때 판매금액을 구한다.
            val temp = calculatePrice()
            if(temp.first>result.first){
                result=temp
                return
            }
            if(result.first==temp.first && result.second<temp.second){
                result=temp
                return
            }
            return
        }

        for(sale in salesAmount){
            percent[depth]=Pair(emoticons[depth],sale)
            salesEmoticons(emoticons,depth+1)
            percent[depth]=Pair(emoticons[depth],0)
        }
    }
    fun getUser(users:Array<IntArray>) : ArrayList<User>{
        val list : ArrayList<User> = arrayListOf()
        for(row in users){
            list.add(User(row[0],row[1]))
        }
        return list
    }
    data class User(val sales : Int, val limit : Int)
}