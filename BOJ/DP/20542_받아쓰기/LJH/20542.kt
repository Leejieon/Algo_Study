package DP.`20542_받아쓰기`.LJH

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.min

class `20542` {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private var n = 0
    private var m = 0
    private var answer="" //답변
    private var result="" //정답
    private lateinit var dp : Array<IntArray>
    private var alphaSet : HashSet<Pair<Char,Char>> = hashSetOf()
    init{
        setAlpha()
        input()
        dp()
    }
    private fun setAlpha(){ //휘날려쓸 수 있는 알파벳
        alphaSet.add(Pair('i','i'))
        alphaSet.add(Pair('i','j'))
        alphaSet.add(Pair('i','l'))
        alphaSet.add(Pair('v','w'))
        alphaSet.add(Pair('v','v'))
    }
    private fun input(){
        br.readLine().split(" ").forEachIndexed { index, s ->
            when(index){
                0-> n = s.toInt()
                1-> m=s.toInt()
            }
        }
        answer=br.readLine()
        result=br.readLine()
        dp = Array(n+1){IntArray(m+1,{0})}
    }
    private fun dp(){

        for(i in 1 until n+1)
            dp[i][0]=i
        for(i in 1 until m+1)
            dp[0][i]=i


        for(i in 1 until dp.size){
            for(j in 1  until dp[i].size){
                if(answer[i-1]==result[j-1]){ //두 글자가 같으면 비용이 0원
                    dp[i][j] = dp[i-1][j-1]
                }
                else if(alphaSet.contains(Pair(answer[i-1],result[j-1]))){ //휘날려 쓸 수 있는 경우비용이 0원
                    dp[i][j] = dp[i-1][j-1]
                }
                else { //아닌 경우
                    dp[i][j] = min(dp[i][j-1]+1,min(dp[i-1][j]+1,dp[i-1][j-1]+1))
                }
            }
        }
        print(dp[n][m])
    }
}


fun main(){
    val solution = `20542`()
}