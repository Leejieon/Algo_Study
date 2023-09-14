package BackTracking.`15659_연산자_끼워넣기(3)`.LJH

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.max
import kotlin.math.min

class `15659`(){
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private var n = 0
    private lateinit var array : Array<Int>
    private val operators : Array<Int> = Array(4,{0})
    var minResult = Int.MAX_VALUE
    var maxResult = Int.MIN_VALUE
    private var expression = ""
    private val numStack : MutableList<Int> = mutableListOf()
    private val tempOperatorStack : MutableList<Char> = mutableListOf()
    init{
        input()
        backtracking(0)
    }
    private fun input(){
        n = br.readLine().toInt()
        array = Array(n,{0})
        var line = br.readLine().split(" ")
        for(i in 0 until n){
            array[i] =line[i].toInt()
        }
        line = br.readLine().split(" ")
        for(i in 0 until 4){
            operators[i]=line[i].toInt()
        }
    }
    private fun backtracking(depth : Int){
        if(depth==n-1){
            val result = calculate()
            minResult = min(minResult,result)
            maxResult = max(maxResult,result)
            return
        }
        else{
            for(i in operators.indices){
                if(operators[i]==0)
                    continue
                var op = ""
                when(i){
                    0->op="+"
                    1->op="-"
                    2->op="*"
                    3->op="/"
                }
                operators[i]--
                expression+=op
                backtracking(depth+1)
                expression=expression.dropLast(1)
                operators[i]++
            }
        }
    }
    private fun calculate() : Int{
        numStack.add(array[0])
        for(i in expression.indices){
            when(expression[i]){
                '*'->{numStack.add(numStack.removeLast()*array[i+1])}
                '/'->{numStack.add(numStack.removeLast()/array[i+1])}
                '+'->{
                    numStack.add(array[i+1])
                    tempOperatorStack.add('+')
                }
                '-'->{
                    numStack.add(array[i+1])
                    tempOperatorStack.add('-')
                }
            }
        }
        while(!tempOperatorStack.isEmpty()){
            val now=tempOperatorStack.removeFirst()
            when(now){
                '+'->{
                    val num1= numStack.removeFirst()
                    val num2= numStack.removeFirst()
                    numStack.add(0,num1+num2)
                }
                '-' ->{
                    val num1=numStack.removeFirst()
                    val num2=numStack.removeFirst()
                    numStack.add(0,num1-num2)
                }
            }
        }
        return numStack.removeFirst()
    }
}
fun main(){
    val solution = `15659`()
    print("${solution.maxResult}\n${solution.minResult}")
}