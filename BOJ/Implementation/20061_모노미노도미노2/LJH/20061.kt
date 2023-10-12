package Implementation.`20061_모노미노도미노2`.LJH

import java.io.BufferedReader
import java.io.InputStreamReader

class `20061` {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private var n = 0
    private lateinit var blue: Block
    private lateinit var green: Block
    var testcase = mutableListOf<Triple<Int, Int, Int>>()

    init {
        makeBlock()
        input()
    }

    fun input() {
        val input = br.readLine()
        n=input.toInt()
        for (i in 0 until n) {
            val line = br.readLine().split(" ")
            testcase.add(Triple(line[0].toInt(), line[1].toInt(), line[2].toInt()))
        }
        for (case in testcase) {
            putBlock(case.first, case.second, case.third)
        }
        println(getScore())
        print(getBlock())

    }

    fun getScore(): Int { //점수 반환
        return blue.score + green.score
    }

    fun getBlock(): Int { //블록 반환
        return blue.getBlock() + green.getBlock()
    }

    private fun putBlock(option: Int, x: Int, y: Int) { //처음에 블록 놓기, 빨간색에 놓이는 블록은 겹치는 일이 없을 거임
        blue.putBlock(x, y, option) //파란색 옮기고 파란색은 맨 끝 행부터 시작
        blue.score+=blue.checkCol()
        blue.moveBlock(x, y)
        green.putBlock(x, y, option)
        green.score+=green.checkRow()
        green.moveBlock(x, y)
    }

    private fun makeBlock() { //블록 만들기
        blue = Block("B")
        green = Block("G")
    }
    data class Block(val id: String) {
        lateinit var graph: Array<BooleanArray>
        var score = 0

        init {
            setSize()
        }

        private fun setSize() { //색깔 별로 블록의 배열을 정함. R : 4x4, B : 4x6, G : 6x4
            when (id) {
                "B" -> graph = Array(4) { BooleanArray(6) { false} }
                "G" -> graph = Array(6) { BooleanArray(4) { false} }
            }
        }


        fun moveBlock(x: Int, y: Int) { //블록에 크기만큼 위치에 넣기.
            var cnt = 0
            when (id) {
                "B" -> { //y가 0~1
                    for (col in 0..1) {
                        for (row in 0 until graph.size) {
                            if (graph[row][col]) { //true면 0~1에 있는거임.
                                cnt++
                                break
                            }
                        }
                    }
                    for (i in 0 until cnt) {
                        pushCol(graph[0].size-1)
                    }
                }

                "G" -> { //x가 0~1
                    for (row in 0..1) {
                        for (col in 0 until graph[row].size) {
                            if (graph[row][col] ) { //하나라도 잇을경우 -->
                                cnt++
                                break
                            }
                        }
                    }
                    for (i in 0 until cnt) {
                        pushRow(graph.size-1)
                    }
                }
            }
        }

        fun putBlock(x: Int, y: Int, opt: Int) {
            when (id) {
                "B" ->
                    when (opt) {
                        1 -> {
                            var max = 0
                            for (col in 0 until graph[0].size) {
                                if (!graph[x][col]) {
                                    max = col
                                } else break
                            }
                            graph[x][max] = true
                        }

                        2 -> {
                            var max = 0
                            for (col in 1 until graph[0].size) {
                                if (!graph[x][col - 1] && !graph[x][col]) {
                                    max = col
                                } else break
                            }
                            graph[x][max] = true
                            graph[x][max - 1] = true
                        }

                        3 -> {
                            var max = 0
                            for (col in 0 until graph[0].size) {
                                if (!graph[x][col] && !graph[x + 1][col]) {
                                    max = col
                                } else break
                            }
                            graph[x][max] = true
                            graph[x + 1][max] = true
                        }
                    }

                "G" -> {
                    when (opt) {
                        1 -> {
                            var max = 0
                            for (row in 0 until graph.size) {
                                if (!graph[row][y]) {
                                    max = row
                                } else break
                            }
                            graph[max][y] = true
                        }

                        2 -> {
                            var max = 0
                            for (row in 0 until graph.size) {
                                if (!graph[row][y] && !graph[row][y + 1]) {
                                    max = row
                                } else break
                            }
                            graph[max][y] = true
                            graph[max][y + 1] = true
                        }

                        3 -> {
                            var max = 0
                            for (row in 1 until graph.size) {
                                if (!graph[row][y] && !graph[row - 1][y]) {
                                    max = row
                                } else break
                            }
                            graph[max - 1][y] = true
                            graph[max][y] = true
                        }
                    }
                }
            }
        }
        fun checkRow(): Int { //열마다 한줄이 차있는지 확인
            var cnt = 0
            for (row in 0 until graph.size) {
                if (!graph[row].contains(false)) {
                    cnt++
                    clearRow(row)
                }
            }
            return cnt
        }

        fun checkCol(): Int { //행마다 한줄이 차있는지 확인
            var flag = false
            var cnt = 0
            for (col in 0 until graph[0].size) {
                flag = true
                for (row in 0 until graph.size) {
                    if (!graph[row][col]) {
                        flag = false
                        break
                    }
                }
                if (flag) {
                    cnt++
                    clearCol(col)
                }
            }
            return cnt
        }

        fun clearCol(col: Int) { //해당 행을 비운다.
            for (row in 0 until graph.size) {
                graph[row][col] = false
            }
            pushCol(col) //해당 행을 기준으로 왼쪽에 있는 블록들을 오른쪽으로 밀어준다.

        }

        fun clearRow(row: Int) { //해당 열을 비운다.
            for (col in 0 until graph[row].size) {
                graph[row][col] = false
            }
            pushRow(row) //열을 기준으로 위쪽에 있는 블록들을 아래로 밀어준다.
        }

        fun pushRow(row: Int) { // 위에있는 블록을아래로 밀기
            for (r in row downTo 1) {
                for (col in 0 until graph[r].size) {
                    graph[r][col] = graph[r - 1][col]
                }
            }
            for(c in 0 until graph[0].size){
                graph[0][c]=false
            }
        }

        fun pushCol(col: Int) { // 사라진 왼쪽에 있는 블록 오른쪽으로 밀기
            for (c in col  downTo 1) {
                for (row in 0 until graph.size) {
                    graph[row][c] = graph[row][c - 1]
                }
            }
            for(row in 0 until graph.size){
                graph[row][0]=false
            }
        }

        fun getBlock(): Int { //잔여 블록확인
            var block = 0
            for (row in graph) {
                for (col in row) {
                    if (col)
                        block++
                }
            }
            return block
        }
    }
}

fun main() {

    val solution = `20061`()

}
