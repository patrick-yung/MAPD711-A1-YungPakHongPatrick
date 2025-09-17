package com.example.assignment1

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mainclient)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Main Activity Widgets
        val btnnext: Button = findViewById(R.id.btnnext)
        val inputemail: EditText = findViewById(R.id.editTxtEmailAddress)
        val inputname: EditText = findViewById(R.id.editTxtName)
        btnnext.isEnabled = inputemail.text.isNotBlank() && inputname.text.isNotBlank()



        //Check Validation Event for Email and Name
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(s: Editable?) {
                val emailText = inputemail.text.toString().trim()
                val nameText = inputname.text.toString().trim()
                btnnext.isEnabled = emailText.isNotBlank() && nameText.isNotBlank()
            }
        }
        inputemail.addTextChangedListener(textWatcher)
        inputname.addTextChangedListener(textWatcher)

        //Main Activity Next Button
        btnnext.setOnClickListener {
            val email = inputemail.text.toString()
            val name = inputname.text.toString()
            val userinput = Intent( this, AppointmentActivity::class.java)
            userinput.putExtra("email", email)
            userinput.putExtra("name", name)
            startActivity(userinput)
        }



    }
}