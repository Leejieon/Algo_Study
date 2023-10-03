package DP.`2228_구간_나누기`.LJH

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.max

class `2228` {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private var n =0
    private var m= 0
    private lateinit var nums : Array<Int>
    private lateinit var sum : Array<IntArray>
    private lateinit var dp : Array<IntArray>
    private val min_value = -32768*100
    private var answer = min_value
    init{
        input()
        makeSum()
        for(i in 0 until n){
            answer = max(answer,solution(i,0))
        }
        println(answer)
    }
    fun input(){
        br.readLine().split(" ").forEachIndexed { index, s ->
            when(index){
                0-> n =s.toInt()
                1-> m=s.toInt()
            }
        }
        nums = Array(n){0}
        sum = Array(n) { IntArray(n) { 0 } }
        dp = Array(n) { IntArray(n) { -1} }
        for(i in 0 until n){
            nums[i] = br.readLine().toInt()
        }
    }
    fun makeSum(){
        for(i in 0 until n){ //구간의 합 i~j까지의 합.
            sum[i][i] = nums[i]
            for(j in i+1 until n){
                sum[i][j]+=sum[i][j-1]+nums[j]
            }
        }
    }
    fun solution(start : Int, line : Int) : Int{ // start는 시작지점. line은 구간.
        if(line==m)
            return 0
        if(start>=n)
            return min_value
        if(dp[start][line]!= -1) //값이 이미 있다면 리턴해야 함.
            return dp[start][line]
        dp[start][line] = solution(start+1,line)
        for(i in start until n){ //start부터 n까지 쫙 돌림.
            dp[start][line] = max(dp[start][line],sum[start][i]+solution(i+2,line+1))
        }
        return dp[start][line]
    }
}
fun main(){
    val solution = `2228`()
}