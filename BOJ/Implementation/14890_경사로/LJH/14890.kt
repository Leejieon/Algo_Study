package Implementation.`14890_경사로`.LJH

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.Arrays
import kotlin.math.abs

class `14890` {

    private val br = BufferedReader(InputStreamReader(System.`in`))
    private var n = 0
    private var l = 0
    private var answer=0

    init {

        input()
        print(answer)

    }

    fun input() {
        val line = br.readLine().split(" ")
        n = line[0].toInt()
        l = line[1].toInt()
        var graph = Array(n) { Array(n) { 0 } }
        var graph2 = Array(n) { Array(n) { 0 } }
        for (i in 0 until n) {
            val input = br.readLine().split(" ")
            for (j in 0 until n) {
                graph[i][j] = input[j].toInt()
                graph2[j][i] = graph[i][j]
            }
        }
        makeRoad(graph)
        makeRoad(graph2)
    }
    fun makeRoad(graph : Array<Array<Int>>){
        for(i in 0 until n){
            var connect = 1
            var flag = true
            var check = -1
            for(j in 0 until n-1){
                if(j<=check) continue
                if(abs(graph[i][j]-graph[i][j+1])>=2){
                    flag=false
                    break
                }
                else if(graph[i][j] == graph[i][j+1]) connect++
                else if(graph[i][j] == graph[i][j+1]+1) {// 앞쪽 블록의 높이가 높을때
                    if(isConnectRoad(i,j,graph)){ //만들수 있음.
                        //j를 바꿔줘야함.
                        check = j+l-1
                        connect=0
                    }
                    else{
                        flag=false
                        break
                    }
                }
                else if(graph[i][j]+1 == graph[i][j+1]){

                    if(connect>=l){
                        connect=1
                    }
                    else{
                        flag=false
                        break
                    }
                }
            }
            if(flag) answer++
        }
    }
    fun isConnectRoad(row:Int,col:Int,graph:Array<Array<Int>>) : Boolean{
        var temp = graph[row][col+1] //
        for(c in col+1 until col+1+l){
            if(c>=n)
                return false
            if(graph[row][c] != temp) return false
        }
        return true
    }
}


fun main() {
    val solution = `14890`()
    val list= mutableListOf<String>()
    var answer: Array<String> = arrayOf<String>()
    list.add("a")
    answer=list.toTypedArray()

}