package TwoPointer.`2473_세_용액`.LJH

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.abs

class `2473` {
    private var n = 0
    private lateinit var list : Array<Long>
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private lateinit var answer : Triple<Long,Long,Long>
    var sum = 3000000001
    init{
        input()
    }
    fun input(){
        n= br.readLine().toInt()
        list = Array(n){0L}
        val inputs = br.readLine().split(" ")
        for(i in 0 until n ){
            list[i] = inputs[i].toLong()
        }
        list.sort()
        for(i in 0 until n-2){
            if(sum==0L)
                break
            search(i)
        }
        println("${answer.first} ${answer.second} ${answer.third}")
    }
    fun search(start : Int){
        var end = n-1
        var mid = start+1
        while(mid<end){
            val result  = list[start] + list[mid] + list[end]
            if(abs(sum)>abs(result)){
                answer = Triple(list[start],list[mid],list[end])
                sum = result
            }
            if(result<0) {
                mid++
            } else {
                end--
            }
        }
    }
}
fun main() {
    val solution = `2473`()
}