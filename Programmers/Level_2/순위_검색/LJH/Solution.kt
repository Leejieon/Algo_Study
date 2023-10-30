import java.util.*

class Solution {

    data class Info(val language: String, val position: String, val career: String, val food: String)

    val infoMap = HashMap<Info, ArrayList<Int>>()
    fun solution(info: Array<String>, query: Array<String>): IntArray {
        var answer: IntArray = intArrayOf()
        for (information in info) {
            val info = information.split(" ")
            val list = mutableListOf<String>()
            makeInformation(info, list, 0)
        }
        for (map in infoMap) {
            map.value.sort()
        }
        answer = matchQuery(query)
        return answer
    }

    fun makeInformation(infos: List<String>, list: MutableList<String>, depth: Int) {
        if (depth == 4) {
            val info = Info(list[0], list[1], list[2], list[3])
            if (infoMap.contains(info)) {
                infoMap[info]!!.add(infos[4].toInt())
            } else {
                val list = ArrayList<Int>()
                list.add(infos[4].toInt())
                infoMap.put(info, list)
            }
            return
        }
        list.add("-")
        makeInformation(infos, list, depth + 1)
        list.removeLast()
        list.add(infos[depth])
        makeInformation(infos, list, depth + 1)
        list.removeLast()

    }

    fun matchQuery(querys: Array<String>): IntArray {
        val result = IntArray(querys.size) { 0 }
        for (i in 0 until querys.size) {
            val q = querys[i].replace(" and ", " ")
            val query = q.split(" ")
            val info = Info(query[0], query[1], query[2], query[3])
            result[i] = findUser(info, query[4].toInt())
        }
        return result
    }

    fun findUser(info: Info, score: Int): Int {

        if (infoMap.contains(info)) { //키가 잇을 경우
            val list = infoMap.get(info)
            if (list == null)
                return 0
            return binary(list, score)
        } else
            return 0
    }

    fun binary(list: ArrayList<Int>, score: Int): Int {
        var start = 0
        var end = list.size - 1

        while (start <= end) {
            var mid = (start + end) / 2
            if (list.get(mid) < score)
                start = mid + 1
            else
                end = mid - 1
        }
        return list.size - start
    }
}