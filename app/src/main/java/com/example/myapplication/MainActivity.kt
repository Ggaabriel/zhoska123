package com.example.myapplication

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val layout = findViewById<ConstraintLayout>(R.id.layout);
        var set: ConstraintSet = ConstraintSet()
        var b= Button(this);
        set.clone(layout);
        set.connect(b.id,ConstraintSet.LEFT,layout.id,ConstraintSet.RIGHT);
        set.applyTo(layout);
        val editTextNumber = findViewById<EditText>(R.id.editTextNumber);
        val button = findViewById<Button>(R.id.button)

        button.setOnClickListener { createButtons(Integer.parseInt(editTextNumber.text.toString())) }
    }

    private fun createButtons(rowsAndColumns: Int) {
        val layout = findViewById<ConstraintLayout>(R.id.layout);
        var set: ConstraintSet = ConstraintSet()

        layout.removeAllViews()

        set = ConstraintSet();
        set.clone(layout);

        for (i in 0 until rowsAndColumns) {
            for (j in 0 until rowsAndColumns) {
                val button = createButtonForFucksake(i * rowsAndColumns + j + 1)
                layout.addView(button);

                set.constrainWidth(button.id, 200);
                set.constrainHeight(button.id, 200);

                if (j == 0) {
                    set.connect(button.id, ConstraintSet.LEFT, layout.id, ConstraintSet.LEFT)
                } else {
                    set.connect(button.id, ConstraintSet.LEFT, layout.getChildAt(i * rowsAndColumns + j - 1).id, ConstraintSet.RIGHT)
                }

                if (i == 0) {
                    set.connect(button.id, ConstraintSet.TOP, layout.id, ConstraintSet.TOP);
                } else {
                    set.connect(button.id, ConstraintSet.TOP, layout.getChildAt((i - 1) * rowsAndColumns + j).id, ConstraintSet.BOTTOM);
                }
            }4
        }

        set.applyTo(layout)
    }

    private fun createButtonForFucksake(number: Int): Button {
        val button = Button(this)
        button.text = number.toString()
        button.id = View.generateViewId()
        button.setBackgroundColor(Color.YELLOW)
        return button
    }
}
