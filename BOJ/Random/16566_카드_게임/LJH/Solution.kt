package Random.`16566_카드_게임`.LJH

import java.io.*
import java.lang.StringBuilder

class Solution {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private lateinit var cards: IntArray
    private lateinit var redCards: IntArray
    private lateinit var parent: IntArray
    private val sb = StringBuilder()

    init {
        input()
        playGame()
        print(sb.toString())
    }

    private fun input() {
        val (n, m, k) = br.readLine().split(" ").map { it.toInt() }
        parent = IntArray(n) { it }
        cards = br.readLine().split(" ").map { it.toInt() }.toIntArray()
        cards.sort()
        redCards = br.readLine().split(" ").map { it.toInt() }.toIntArray()
    }

    private fun playGame() {
        for (card in redCards) {
            var idx = binarySearch(card) // 적절한 위치를 찾아주기
            idx = find(idx)
            sb.append(cards[idx]).append("\n")
            union(idx, idx + 1)
        }
    }

    private fun binarySearch(value: Int): Int {
        var left = 0
        var right = cards.size - 1
        var mid = 0
        while (left < right) {
            mid = (left + right) / 2
            if (cards[mid] <= value) {
                left = mid + 1
            } else {
                right = mid
            }
        }
        return right
    }

    private fun union(num1: Int, num2: Int) {
        val parent1 = find(num1)
        val parent2 = find(num2)
        if (parent1 < parent2) {
            parent[num1] = parent2
        } else {
            parent[num2] = parent1
        }
    }

    private fun find(value: Int): Int {
        if (value == parent[value])
            return value
        parent[value] = find(parent[value])
        return parent[value]
    }
}

fun main() {
    val solution = Solution()
}