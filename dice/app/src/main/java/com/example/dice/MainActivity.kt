package com.example.dice

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {
    lateinit var diceImage: ImageView
    lateinit var diceImage_1: ImageView
    lateinit var diceImage_2: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        diceImage = findViewById(R.id.dice_image)
        diceImage_1 = findViewById(R.id.dice_image_1)
        diceImage_2 = findViewById(R.id.dice_image_2)
        val rollButton: Button = findViewById(R.id.roll_button)
        val countUpButton: Button = findViewById(R.id.count_up_button)
        val resetButton: Button = findViewById(R.id.reset_button)
        rollButton.setOnClickListener {
            rollDice()
        }
        countUpButton.setOnClickListener {
            countUp()
        }
        resetButton.setOnClickListener {
            reset()
        }
    }

    private fun rollDice() {
        val random_value: EditText = findViewById(R.id.random_value)
        val total_value: EditText = findViewById(R.id.total_value)
        val resultText: TextView = findViewById(R.id.result_text)
        //dice value
        var arr: List<String> = random_value.text.toString().split("-")
        var from = 1
        var to = 6
        if (arr.size == 2) {
            try {
                from = arr[0].toString().toInt()
                to = arr[1].toString().toInt()
            } catch (e: NumberFormatException) {
                from = 1
                to = 6
            }
        }
        if (from > 6 || to > 6 || from < 1 || to < 1) {
            from = 1
            to = 6
        }
        if (from > to) {
            var temp = from
            from = to
            to = temp
        }
        //dice value

        //total value
        var arr_1: List<String> = total_value.text.toString().split("-")
        var value_start = 3
        var value_end = 18
        if (arr_1.size == 2) {
            try {
                value_start = arr_1[0].toString().toInt()
                value_end = arr_1[1].toString().toInt()
            } catch (e: NumberFormatException) {
                value_start = 3
                value_end = 18
            }
        }
        if (value_start > 18 || value_end > 18 || value_start < 3 || value_end < 3) {
            value_start = 3
            value_end = 18
        }
        if (value_start > value_end) {
            var temp = value_start
            value_start = value_end
            value_end = temp
        }
        //total value
        var dice_value: Int = getRandomInt(from, to)
        var dice_value_1: Int = getRandomInt(from, to)
        var dice_value_2: Int = getRandomInt(from, to)
        if (to * 3 < value_start || from * 3 > value_end) {
            Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
            return
        }
        if (((dice_value + dice_value_1 + dice_value_2) > value_end) || ((dice_value + dice_value_1 + dice_value_2) < value_start)) {
            return rollDice()
        }
        diceImage.setImageResource(getRandomDiceImage(dice_value))
        diceImage_1.setImageResource(getRandomDiceImage(dice_value_1))
        diceImage_2.setImageResource(getRandomDiceImage(dice_value_2))
        resultText.text = (dice_value + dice_value_1 + dice_value_2).toString()
        //        Toast.makeText(this, "Button clicked", Toast.LENGTH_SHORT).show()
    }

    private fun countUp() {
        val resultText: TextView = findViewById(R.id.result_text)
        if (resultText.text == "Hello World!") {
            resultText.text = "1"
        } else {
            var resultInt: Int = resultText.text.toString().toInt()
            if (resultInt < 18) {
                resultInt++
                resultText.text = resultInt.toString()
            }
        }
    }

    private fun reset() {
        val resultText: TextView = findViewById(R.id.result_text)
        resultText.text = "Hello World!"
        diceImage.setImageResource(R.drawable.dice_1)
        diceImage_1.setImageResource(R.drawable.dice_1)
        Toast.makeText(this, "Reset to default", Toast.LENGTH_SHORT).show()
    }

    private fun getRandomInt(a: Int, b: Int): Int {
        return (a..b).random()
    }

    private fun getRandomDiceImage(number: Int): Int {
        return when (number) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
    }
}