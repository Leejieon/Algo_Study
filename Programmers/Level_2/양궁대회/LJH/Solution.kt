class Solution {
    var score = 0
    lateinit var result: IntArray
    lateinit var rion: IntArray
    fun solution(n: Int, info: IntArray): IntArray {
        var answer: IntArray = IntArray(11){0}
        result = IntArray(12) { 0 }
        rion = IntArray(12) { 0 }

        shotArrow(info, 0, n)
        if (score == 0)
            answer = intArrayOf(-1)
        else {
            for(i in 0 until 11){
                answer[i]=result[i]
            }
        }
        return answer
    }
    fun shotArrow(apeach: IntArray, depth: Int, res: Int) {
        if (depth <= 11) {
            if (res == 0) {
                val pair = compareScore(apeach)
                if (score < pair.second - pair.first) { //점수차가 클 때
                    result = rion.copyOf()
                    score = pair.second - pair.first
                } else if (score == pair.second - pair.first) { //점수차가 같을
                    for (i in 10 downTo 0) {
                        if (rion[i] > result[i]) {
                            result=rion.copyOf()
                            return
                        } else if (result[i] > rion[i]) { //기존 방법이 좋을 경우
                            return
                        }
                    }
                }
            } else {
                for (i in 0..res) {
                    rion[depth] = i
                    shotArrow(apeach, depth + 1, res - i)
                    rion[depth] = 0
                }
            }
        }
    }

    fun compareScore(apeach: IntArray): Pair<Int, Int> {
        var apeachSum = 0
        var rionSum = 0
        for (i in 0 until 11) {
            if (apeach[i] < rion[i]) { //라이온이 클땐 라이온이 점수를 먹음
                rionSum += 10 - i
                continue
            }
            if (apeach[i] == 0) //어피치가 0일 경우 넘어감.
                continue
            apeachSum += 10 - i
        }
        return Pair(apeachSum, rionSum)
    }
}