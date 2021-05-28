package com.example.journal

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import java.util.*

class DetailsActivity : AppCompatActivity() {

    companion object {
        const val DATE_KEY = "ENTRY_DATE_KEY"
        const val RATING_KEY = "ENTRY_RATING_NUMBER"
        const val TEXT_KEY = "ENTRY_TEXT"
    }

    private lateinit var textView: TextView
    private lateinit var seekBar: SeekBar
    private lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.date_text_view)
        seekBar = findViewById(R.id.day_rating_bar)
        editText = findViewById(R.id.text_entry)

        textView.text = Date().toString()
        seekBar.max = 4

    }

    override fun onBackPressed() {
//        super.onBackPressed()
        val date = textView.text
        val rating = seekBar.progress
        val text = editText.text

        val resultIntent = Intent()

        resultIntent.putExtra(DATE_KEY, date)
        resultIntent.putExtra(RATING_KEY, rating)
        resultIntent.putExtra(TEXT_KEY, text)

        setResult(Activity.RESULT_OK, resultIntent)
        finish()
    }
}