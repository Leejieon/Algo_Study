import java.io.*
import java.util.*
class Solution {
    lateinit var alpha : Array<Int>
    var menu = mutableListOf<String>()
    var menuIdx = mutableMapOf<String,Int>()
    fun solution(orders: Array<String>, course: IntArray): Array<String> {
        var answer: Array<String> = arrayOf<String>()
        var result = mutableListOf<String>()
        for(order in orders){

            val list = order.toCharArray()
            val temp = list.sorted()
            for(i in course)
                getMenu(temp,"",0,0,i)
        }
        for(m in menu){
            if(menuIdx[m]==null)
                menuIdx[m]=1
            else menuIdx[m] = menuIdx.getValue(m)+1
        }
        var menuList = menuIdx.toList().sortedWith(compareBy({it.first.length},{-it.second})) //길이 오름차순 값 내림 차순
        for(c in course){
            var menuCount = 0
            for(menu in menuList){
                if(menu.first.length == c && menu.second>=2){
                    if(menuCount<menu.second){
                        menuCount=menu.second
                        result.add(menu.first)
                    }
                    else if(menuCount==menu.second){
                        result.add(menu.first)
                    }
                }
            }

        }
        answer = result.toTypedArray()
        answer.sort()
        return answer
    }
    fun getMenu(order : List<Char>, part: String,start: Int, depth: Int, n: Int) {
        if(depth == n){
            menu.add(part)
        }
        for (i in start until order.size) {
            getMenu(order, part + order[i],i + 1, depth + 1, n)
        }
    }
}