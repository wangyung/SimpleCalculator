package com.example.simplecalculator

interface DataObserver<T> {
    fun onNotify(data: T)
}

class Calculator(private val dataObserver: DataObserver<String>? = null) {

    private var operation: Operation = Operation.None

    var displayText: String = ""
        private set(value) {
            field = value
            dataObserver?.onNotify(field)
        }

    private var isValueSet = false

    fun calculate() {
        val op = this.operation
        when(op) {
            is Operation.Add -> op.value + displayText.toDouble()
            is Operation.Subtract -> op.value - displayText.toDouble()
            is Operation.Multiply -> op.value * displayText.toDouble()
            is Operation.Divide -> op.value / displayText.toDouble()
            else -> { null /* do nothing */ }
        }?.also {
            reset()
            displayText = it.toString()
        }
    }

    fun add() = setOperation(Operation.Add(displayText.toDouble()))

    fun subtract() = setOperation(Operation.Subtract(displayText.toDouble()))

    fun multiply() = setOperation(Operation.Multiply(displayText.toDouble()))

    fun divide() = setOperation(Operation.Divide(displayText.toDouble()))

    fun point() {
        if (displayText.isNotEmpty() && !displayText.contains(".")) {
            displayText += "."
        }
    }

    private fun setOperation(operation: Operation) {
        if (isValueSet) {
            this.operation = operation
            displayText = ""
        }
    }

    fun appendDigit(value: Int) {
        displayText += value.toString()
        isValueSet = true
    }

    fun reset() {
        operation = Operation.None
        isValueSet = false
        displayText = ""
    }
}

sealed class Operation {
    class Add(val value: Double) : Operation() // with state
    class Subtract(val value: Double) : Operation()
    class Multiply(val value: Double) : Operation()
    class Divide(val value: Double) : Operation()
    object None : Operation() // no state
}
