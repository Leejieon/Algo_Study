import java.util.*

class Solution {
    var gemSet = HashSet<String>()
    fun solution(gems: Array<String>): IntArray {
        var answer = intArrayOf(0, 0)
        for (gem in gems) {
            gemSet.add(gem)
        }
        val p = searchGems(gems)
        answer[0] = p.first + 1
        answer[1] = p.second
        return answer
    }

    fun searchGems(gems: Array<String>): Pair<Int, Int> {

        var start = 0
        var end = 0
        var bag = hashMapOf<String, Int>()
        var result = Pair(0, 100000)
        while (start <= end) {
            if (compareGems(bag)) {
                result = compareLength(result, Pair(start, end))
                if (bag[gems[start]] == 1) {
                    bag.remove(gems[start])
                } else {
                    bag[gems[start]] = bag[gems[start]]!!.minus(1)
                }
                start++
                continue
            }
            if (end == gems.size)
                break
            if (end < gems.size) {
                if (bag.contains(gems[end])) {
                    bag[gems[end]] = bag[gems[end]]!!.plus(1)
                } else {
                    bag.put(gems[end], 1)
                }
                end++
            }
        }
        return result
    }

    fun compareGems(bag: HashMap<String, Int>): Boolean {
        if (bag.size == gemSet.size)
            return true
        return false
    }

    fun compareLength(old: Pair<Int, Int>, new: Pair<Int, Int>): Pair<Int, Int> {
        val oldDiff = old.second - old.first
        val newDiff = new.second - new.first
        if (oldDiff > newDiff) {
            return new
        } else if (oldDiff == newDiff && new.first < old.first) {
            return new
        }
        return old
    }
}