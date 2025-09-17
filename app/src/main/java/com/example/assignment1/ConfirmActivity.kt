package com.example.assignment1

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ConfirmActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_confirm)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val email = intent.getStringExtra("email")
        val name = intent.getStringExtra("name")
        val date = intent.getStringExtra("date")
        val service = intent.getStringExtra("service")
        val appointmenttime = intent.getStringExtra("appointmenttime")

        val txtname = findViewById<TextView>(R.id.customername)
        val txtemail = findViewById<TextView>(R.id.customeremail)
        val txtdate = findViewById<TextView>(R.id.appointmentdate)
        val txttime = findViewById<TextView>(R.id.appointmenttime)
        val txtrequest = findViewById<TextView>(R.id.requestservice)

        txtname.text = "Name : $name"
        txtemail.text = "Email : $email"
        txtdate.text = "Appointment Date : $date"
        txttime.text = "Appointment Time : $appointmenttime"
        txtrequest.text = "Request Service : $service"

        val btnconfirmation = findViewById<Button>(R.id.btnconfirmnext)
        btnconfirmation.setOnClickListener {
            Toast.makeText(this, "Your Booking is Confirmed $name, see you on $date on $appointmenttime", Toast.LENGTH_SHORT).show()
        }

    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.mainmenu, menu)
        return true
    }
}