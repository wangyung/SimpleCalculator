package com.example.simplecalculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.simplecalculator.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar

    private lateinit var resultView: TextView

    private lateinit var viewModel: MainViewModel

    private lateinit var calculator: Calculator

    private lateinit var btnZero: Button
    private lateinit var btnOne: Button
    private lateinit var btnTwo: Button
    private lateinit var btnThree: Button
    private lateinit var btnFour: Button
    private lateinit var btnFive: Button
    private lateinit var btnSix: Button
    private lateinit var btnSeven: Button
    private lateinit var btnEight: Button
    private lateinit var btnNine: Button

    private lateinit var btnAc: Button
    private lateinit var btnAdd: Button
    private lateinit var btnSub: Button
    private lateinit var btnMul: Button
    private lateinit var btnDiv: Button
    private lateinit var btnResult: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        calculator = viewModel.calculator

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        resultView = findViewById(R.id.text_result)

        btnZero = findButtonAndBindAction(R.id.btn_zero) {
            addDigit(0)
        }

        btnOne = findButtonAndBindAction(R.id.btn_one) {
            addDigit(1)
        }

        btnTwo = findButtonAndBindAction(R.id.btn_two) {
            addDigit(2)
        }

        btnThree = findButtonAndBindAction(R.id.btn_three) {
            addDigit(3)
        }

        btnFour = findButtonAndBindAction(R.id.btn_four) {
            addDigit(4)
        }

        btnFive = findButtonAndBindAction(R.id.btn_five) {
            addDigit(5)
        }

        btnSix = findButtonAndBindAction(R.id.btn_six) {
            addDigit(6)
        }

        btnSeven = findButtonAndBindAction(R.id.btn_seven) {
            addDigit(7)
        }

        btnEight = findButtonAndBindAction(R.id.btn_eight) {
            addDigit(8)
        }

        btnNine = findButtonAndBindAction(R.id.btn_nine) {
            addDigit(9)
        }

        btnAc = findButtonAndBindAction(R.id.btn_ac) {
            calculator.reset()
            resultView.text = calculator.displayText
        }

        btnAdd = findButtonAndBindAction(R.id.btn_add) {
            calculator.add()
        }

        btnSub = findButtonAndBindAction(R.id.btn_sub) {
            calculator.substract()
        }

        btnMul = findButtonAndBindAction(R.id.btn_mul) {
            calculator.multiply()
        }

        btnDiv = findButtonAndBindAction(R.id.btn_div) {
            calculator.divid()
        }

        btnResult = findButtonAndBindAction(R.id.btn_equal) {
            calculator.calculate()
        }
        viewModel.displayText.observe(this, Observer<String> {
            resultView.text = it
        })
    }

    private inline fun findButtonAndBindAction(@IdRes resId: Int, crossinline action: () -> Unit) =
        findViewById<Button>(resId).also {
            it.setOnClickListener {
                action()
            }
        }

    private fun addDigit(value: Int) = calculator.appendDigit(value)

    companion object {
        const val TAG = "MainActivity"
    }
}
