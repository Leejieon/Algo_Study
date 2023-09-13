package BOJ.BackTracking.`2661_좋은수열`.ljh

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.system.exitProcess

class `2661` {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private var n = 0
    private val items = arrayOf("1", "2", "3")
    private var answer = ""

    init {
        n = readln().toInt()
    }

    fun solution(cnt: Int) {
        if (cnt == n) {
            println(answer)
            exitProcess(0)
        } else {
            for (item in items) {
                answer += item
                if (check()) {
                    solution(cnt + 1)
                }
                answer = answer.dropLast(1)
            }
        }
    }

    fun check(): Boolean { //같은 패턴이 있는지 확인을 하는 법.
        if (answer.length == 1)
            return true
        for (j in answer.indices) { // == j until answer.length
            for (i in j..< answer.length) {
                val substr = answer.substring(j..i)
                if (i + 1 >= answer.length || i + substr.length >= answer.length)
                    break
                val mainstr = answer.substring(i + 1..i + substr.length) //i+1부터 i까지 검사
                if (substr == mainstr)
                    return false
            }
        }
        return true
    }
}

fun main() {
    val solution = `2661`()
    solution.solution(0)
}