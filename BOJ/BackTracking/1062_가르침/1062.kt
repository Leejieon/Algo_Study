package BOJ.BackTracking.`1062_가르침`.ljh

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.HashSet
import kotlin.math.max

private var n = 0
private var k = 0
private val br = BufferedReader(InputStreamReader(System.`in`))
private var words: MutableList<String> = mutableListOf()
private var result = 0
private var alpha: HashSet<Char> = hashSetOf()
private var total: HashSet<Char> = hashSetOf()
fun main() {
    //alpha = Array(26, { false })
    initAlpha()
    if (input()) {
        combination(5, k, 0)
        println(result)
    } else {
        println(result)
    }
}

fun input(): Boolean {
    val st = StringTokenizer(readLine())
    n = st.nextToken().toInt()
    k = st.nextToken().toInt()
    if (k < 5) {
        result = 0
        return false
    } else if (k == 26) {
        result = n
        return false
    }
    for (i in 0 until n) {
        words.add(br.readLine())
    }
    return true
}

fun initAlpha() {
    with(alpha) {
        add('a')
        add('c')
        add('n')
        add('t')
        add('i')
    }
    with(total) {
        for (char in 'a'..'z') {
            add(char)
        }
        removeAll(alpha)
    }
}

fun combination(cnt: Int, limit: Int, loc: Int) {
    if (cnt == limit) {
        solution()
        return
    }
    for (i in loc until total.size) {
            alpha.add(total.elementAt(i))
            combination(cnt + 1, limit, i + 1)
            alpha.remove(total.elementAt(i))
    }
}

fun solution() {
    var answer = 0
    for (word in words) {
        var isValid = true
        for (char in word) {
            if (!alpha.contains(char)) {
                isValid = false
                break
            }
        }
        if (isValid)
            answer++
    }
    result = max(result, answer)
}