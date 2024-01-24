package TwoPointer.`20366_같이_눈사람_만들래?`.LJH

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.abs
import kotlin.math.min

class `20366` {
    private var n = 0
    private lateinit var array : List<Long>
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val comb  = arrayOf(0,0)
    private var sum = 2000000001L
    init{
        input()
    }
    fun input(){
        n = br.readLine().toInt()
        array = br.readLine().split(" ").map { it.toLong() }.sorted()
        makeCombination(0,0,2)
        println(abs(sum))
    }
    private fun makeCombination(cnt : Int , depth : Int, limit : Int){
        if(cnt == limit){
            search()
            return
        }
        for(i in depth until n){
            comb[cnt] = i
            makeCombination(cnt+1,i+1,limit)
            comb[cnt]=0
        }
    }
    private fun search(){
        var start = 0
        var end = n-1
        while(start<end){
            if(comb.contains(start)){
                start++
                continue
            }
            if(comb.contains(end)){
                end--
                continue
            }
            val result = array[comb[0]]+array[comb[1]]- (array[start]+array[end])
            sum = min(abs(sum),abs(result))
            if(result == 0L)
                break
            if(result<0) {
                end--
            } else {
                start++
            }
        }
    }
}

fun main(){
    val solution = `20366`()
}
