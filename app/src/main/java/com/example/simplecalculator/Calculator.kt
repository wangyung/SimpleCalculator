package com.example.simplecalculator

interface DataObserver<T> {
    fun onNotify(data: T)
}

class Calculator(private val dataObserver: DataObserver<String>? = null) {

    private var operation: Operation = Operation.None

    private var value1 = 0.0

    var displayText: String = value1.toString()
        private set(value) {
            field = value
            dataObserver?.onNotify(field)
        }

    private var isValue1Set = false

    fun calculate() {
        val op = this.operation
        when(op) {
            is Operation.Add -> op.value + value1
            is Operation.Substract -> op.value - value1
            is Operation.Multiply -> op.value * value1
            is Operation.Divid -> op.value / value1
            is Operation.None -> { null /* do nothing */ }
        }?.also {
            reset()
            displayText = it.toString()
        }
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
        displayText = value1.toString()
    }

    fun reset() {
        value1 = 0.0
        operation = Operation.None
        isValue1Set = false
        displayText = value1.toString()
    }
}

sealed class Operation {
    class Add(val value: Double) : Operation() // with state
    class Substract(val value: Double) : Operation()
    class Multiply(val value: Double) : Operation()
    class Divid(val value: Double) : Operation()
    object None : Operation() // no state
}
