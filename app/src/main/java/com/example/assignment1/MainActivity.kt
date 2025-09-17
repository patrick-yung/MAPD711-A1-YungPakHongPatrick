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

        val btnnext: Button = findViewById(R.id.btnnext)
        val btnvalidnext: Button = findViewById(R.id.btnvalidnext)
        val inputemail: EditText = findViewById(R.id.editTxtEmailAddress)
        val inputname: EditText = findViewById(R.id.editTxtName)
        btnvalidnext.isEnabled = inputemail.text.isNotBlank() && inputname.text.isNotBlank()



        //Check Validation Event for Email and Name
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(s: Editable?) {
                val emailText = inputemail.text.toString().trim()
                val nameText = inputname.text.toString().trim()
                btnvalidnext.isEnabled = emailText.isNotBlank() && nameText.isNotBlank()
            }
        }
        inputemail.addTextChangedListener(textWatcher)
        inputname.addTextChangedListener(textWatcher)

        //Add meanu as well
        //Add log messages if valid

        //Main Activity Next Button and valid
        btnnext.setOnClickListener {
            val email = inputemail.text.toString()
            val name = inputname.text.toString()
            if (email.isNotBlank() && name.isNotBlank()) {
                val userinput = Intent(this, AppointmentActivity::class.java)
                userinput.putExtra("email", email)
                userinput.putExtra("name", name)
                startActivity(userinput)
            } else {
                if (email.isBlank() && name.isBlank())Toast.makeText(this, "Please Enter Email and Name", Toast.LENGTH_SHORT).show()
                else if (email.isBlank())Toast.makeText(this, "Please Enter Email", Toast.LENGTH_SHORT).show()
                else if (name.isBlank())Toast.makeText(this, "Please Enter Name", Toast.LENGTH_SHORT).show()
            }
        }
        btnvalidnext.setOnClickListener {
            val email = inputemail.text.toString()
            val name = inputname.text.toString()
            val userinput = Intent(this, AppointmentActivity::class.java)
            userinput.putExtra("email", email)
            userinput.putExtra("name", name)
            startActivity(userinput)

        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.mainmenu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.studentname -> Toast.makeText(this, "You clicked open.", Toast.LENGTH_LONG).show()
            R.id.studentid -> Toast.makeText(this, "You clicked close", Toast.LENGTH_LONG).show()
        }

        return true
    }
}

