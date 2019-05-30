package com.example.simplecalculator

class Calculator {

    private var operation: Operation = Operation.None

    var value1 = 0.0
        private set

    private var isValue1Set = false

    @Throws
    fun calculate(): Double {
        val op = this.operation
        val result = when(op) {
            is Operation.Add -> op.value + value1
            is Operation.Substract -> op.value - value1
            is Operation.Multiply -> op.value * value1
            is Operation.Divid -> op.value / value1
            is Operation.None -> throw IllegalAccessError("Need operation")
        }
        reset()
        return result
    }

    fun add() = setOperation(Operation.Add(value1))

    fun substract() = setOperation(Operation.Substract(value1))

    fun multiply() = setOperation(Operation.Multiply(value1))

    fun divid() = setOperation(Operation.Divid(value1))

    private fun setOperation(operation: Operation) {
        if (isValue1Set) {
            this.operation = operation
            value1 = 0.0
        }
    }

    fun appendDigit(value: Int) {
        this.value1 = this.value1 * 10 + value
        isValue1Set = true
    }

    fun reset() {
        value1 = 0.0
        operation = Operation.None
        isValue1Set = false
    }
}

sealed class Operation {
    class Add(val value: Double) : Operation() // with state
    class Substract(val value: Double) : Operation()
    class Multiply(val value: Double) : Operation()
    class Divid(val value: Double) : Operation()
    object None : Operation() // no state
}
