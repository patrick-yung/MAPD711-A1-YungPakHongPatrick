package com.example.assignment1

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
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

        val btnNext: Button = findViewById(R.id.btnMainNext)
        val btnValidNext: Button = findViewById(R.id.btnValidNext)
        val inputEmail: EditText = findViewById(R.id.editTxtEmailAddress)
        val inputName: EditText = findViewById(R.id.editTxtName)
        btnValidNext.isEnabled = inputEmail.text.isNotBlank() && inputName.text.isNotBlank()


        //Check Validation Event for Email and Name
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(s: Editable?) {
                val emailText = inputEmail.text.toString().trim()
                val nameText = inputName.text.toString().trim()
                btnValidNext.isEnabled = emailText.isNotBlank() && nameText.isNotBlank()
            }
        }
        inputEmail.addTextChangedListener(textWatcher)
        inputName.addTextChangedListener(textWatcher)

        //Main Activity Next Button and valid
        btnNext.setOnClickListener {
            val customerEmail = inputEmail.text.toString()
            val customerName = inputName.text.toString()
            if (customerEmail.isNotBlank() && customerName.isNotBlank()) {
                val userinput = Intent(this, AppointmentActivity::class.java)
                userinput.putExtra("customerEmail", customerEmail)
                userinput.putExtra("customerName", customerName)
                startActivity(userinput)
            } else {
                if (customerEmail.isBlank() && customerName.isBlank())Toast.makeText(this, getString(R.string.invalid_name_email), Toast.LENGTH_SHORT).show()
                else if (customerEmail.isBlank())Toast.makeText(this, getString(R.string.invalid_email), Toast.LENGTH_SHORT).show()
                else if (customerName.isBlank())Toast.makeText(this, getString(R.string.invalid_name), Toast.LENGTH_SHORT).show()
            }
        }
        btnValidNext.setOnClickListener {
            val customerEmail = inputEmail.text.toString()
            val customerName = inputName.text.toString()
            val userInput = Intent(this, AppointmentActivity::class.java)
            userInput.putExtra("customerEmail", customerEmail)
            userInput.putExtra("customerName", customerName)
            startActivity(userInput)

        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.mainmenu, menu)
        return true
    }
}

