import java.util.*

class Solution {

    val x = listOf(-1, 1, 0, 0)
    val y = listOf(0, 0, 1, -1)
    var answer = 100000000
    private val pq = PriorityQueue<Road>(compareBy({ +it.price }))
    fun solution(board: Array<IntArray>): Int {
        bfs(board)
        return answer
    }

    fun bfs(board: Array<IntArray>) {
        var start = Pair(0, 0)
        val n = board.size
        val end = Pair(n - 1, n - 1) // 도착지점
        var visit = Array(n) { Array(n) { Array(4) { false } } } //n*n*4 3차원 방문 배열 생성
        for (i in 0 until 4) { // 방향 별로 초기 설정
            pq.add(Road(start, 0, i))
        }
        while (!pq.isEmpty()) {
            var cur = pq.poll()
            if (visit[cur.loc.first][cur.loc.second][cur.dir]) //방문한 경우
                continue
            if (cur.loc == end) { //도착할 경우
                answer = cur.price
                return
            }
            visit[cur.loc.first][cur.loc.second][cur.dir] = true //방문 처리
            for (d in 0 until x.size) {
                var mx = cur.loc.first + x[d]
                var my = cur.loc.second + y[d]
                if (mx !in board.indices || my !in board.indices) //이동하는 좌표가 배열의 범위를 벗어난 경우
                    continue
                if (visit[mx][my][d] || board[mx][my] == 1) //방문했거나, 벽인 경우
                    continue
                if (d == cur.dir) { //방향이 일치할때
                    pq.add(Road(Pair(mx, my), cur.price + 100, d))
                } else { //방향을 꺾어야 할 때
                    pq.add(Road(Pair(mx, my), cur.price + 600, d))
                }
            }
        }
    }

    data class Road(var loc: Pair<Int, Int>, var price: Int, var dir: Int)
}
