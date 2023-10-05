package DP.`2629_양팔저울`.LJH

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.abs

class `2629` {
    private val br =BufferedReader(InputStreamReader(System.`in`))
    private var n = 0
    private var m=0
    private var weight = Array(31) { 0 }
    private var bead =Array(8) { 0 }
    private  var dp = Array(31){ BooleanArray(40001) { false } }
    init{
        input()
        dp()
        solution()
    }
    fun input(){
        n = br.readLine().toInt()
        val line = br.readLine().split(" ")
        for(i in 0 until n)
        {
            weight[i] = line[i].toInt()
        }
        m=br.readLine().toInt()
        val token = br.readLine().split(" ")
        for(i in 0 until m){
            bead[i] = token[i].toInt()
        }
    }
    fun dp(){ //만들수 있는 무게를 구함.
        for(i in 1 .. n){
            val temp = weight[i-1]
            dp[i-1][0]=true
            for(j in 0 until dp[i].size){ //무게는 그대로, 절대값 빼기, 그냥 더하기
                dp[i][j]=dp[i-1][j] || dp[i-1][abs(j-temp)]
                if(j+temp< dp[i].size)
                    dp[i][j]= dp[i][j] || dp[i-1][j+temp]
            }
        }
    }
    fun solution(){
        for(i in 0 until m){
            if(dp[n][bead[i]])
                print("Y ")
            else print("N ")
        }
    }
}

fun main() {
    val solution = `2629`()
}
