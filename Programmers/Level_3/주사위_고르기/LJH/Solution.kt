class Solution {
    var n = 0
    val myDice = mutableListOf<IntArray>()
    val diceNumber = mutableListOf<Int>()
    var win = 0
    var answer: IntArray = intArrayOf()
    fun solution(dice: Array<IntArray>): IntArray {
        n = dice.size
        makeCombination(0, 0, n / 2, dice)
        return answer
    }

    fun makeCombination(cnt: Int, cur: Int, depth: Int, dice: Array<IntArray>) {
        if (cnt == depth) {
            val result = makeSide(dice)
            if (win < result) {
                win = result
                answer = diceNumber.toIntArray()
                return
            }
        }
        for (i in cur until dice.size) {
            myDice.add(dice[i])
            diceNumber.add(i + 1)
            makeCombination(cnt + 1, i + 1, depth, dice)
            myDice.remove(dice[i])
            diceNumber.remove(i + 1)
        }
    }

    fun makeSide(dice: Array<IntArray>): Int {
        val side = mutableListOf<IntArray>()
        for (i in 0 until dice.size) {
            var isSame = false
            for (j in 0 until myDice.size) {
                if (dice[i].contentEquals(myDice[j])) {
                    isSame = true
                    break
                }
            }
            if (!isSame) {
                side.add(dice[i])
            }
        }
        return makeProb(side)
    }

    fun makeProb(side: MutableList<IntArray>): Int {
        var win = 0
        val bResults = generateAllCombinations(side) // A경우의수
        val aResults = generateAllCombinations(myDice) // B경우의수
        val bSum = hashMapOf<Int,Int>()  //A합
        val aSum = hashMapOf<Int, Int>() //합
        for (aResult in aResults) {
            val sum = aResult.sum()
            aSum[sum] = aSum.getOrDefault(sum,0)+1
        }
        for (bResult in bResults) {
            val sum = bResult.sum()
            bSum[sum] = bSum.getOrDefault(sum, 0) + 1
        }
        val aSumList = mutableListOf<Pair<Int,Int>>()
        var bSumList = mutableListOf<Pair<Int,Int>>()
        for(asum in aSum){
            aSumList.add(Pair(asum.key,asum.value))
        }
        for(bsum in bSum){
            bSumList.add(Pair(bsum.key,bsum.value))
        }
        aSumList.sortBy{it.first}
        bSumList.sortBy{it.first}
        for (i in 1 until bSumList.size) {
            bSumList[i] = Pair(bSumList[i].first, bSumList[i].second + bSumList[i - 1].second)
        }
        for(list in aSumList){
            var start = 0;
            var end = bSumList.size-1
            while(start <= end){
                var mid = (start + end) / 2
                if(bSumList[mid].first < list.first){
                    start = mid + 1
                } else{
                    end = mid - 1
                }
            }
            if(end >= 0){
                win += list.second * bSumList[end].second
            }
        }
        return win
    }

    fun generateAllCombinations(side: MutableList<IntArray>): List<List<Int>> {
        val allCombinations = mutableListOf<List<Int>>()
        generateAllCombinationsHelper(side, 0, mutableListOf(), allCombinations)
        return allCombinations
    }

    fun generateAllCombinationsHelper(
        side: MutableList<IntArray>,
        currentIndex: Int,
        currentCombination: MutableList<Int>,
        allCombinations: MutableList<List<Int>>
    ) {
        if (currentIndex == side.size) {
            allCombinations.add(currentCombination.toList())
            return
        }

        for (value in side[currentIndex]) {
            currentCombination.add(value)
            generateAllCombinationsHelper(side, currentIndex + 1, currentCombination, allCombinations)
            currentCombination.removeAt(currentCombination.size - 1)
        }
    }
}