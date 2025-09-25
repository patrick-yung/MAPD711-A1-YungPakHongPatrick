package com.example.assignment1

import android.os.Bundle
import android.view.Menu
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


        val customerEmail = intent.getStringExtra("customerEmail")
        val customerName = intent.getStringExtra("customerName")
        val customerDate = intent.getStringExtra("customerDate")
        val customerService = intent.getStringExtra("customerService")
        val customerAppointmentTime = intent.getStringExtra("customerAppointmentTime")

        val txtName = findViewById<TextView>(R.id.txtCustomerName)
        val txtEmail = findViewById<TextView>(R.id.txtCustomerEmail)
        val txtDate = findViewById<TextView>(R.id.txtAppointmentDate)
        val txtTime = findViewById<TextView>(R.id.txtAppointmentTime)
        val txtRequest = findViewById<TextView>(R.id.txtRequestService)

        txtName.text = "Name : $customerName"
        txtEmail.text = "Email : $customerEmail"
        txtDate.text = "Appointment Date : $customerDate"
        txtTime.text = "Appointment Time : $customerAppointmentTime"
        txtRequest.text = "Request Service : $customerService"

        val btnConfirmation = findViewById<Button>(R.id.btnConfirmNext)
        btnConfirmation.setOnClickListener {
            Toast.makeText(this, getString(R.string.confirmMessage, customerName, customerDate, customerAppointmentTime), Toast.LENGTH_LONG).show()
        }

    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.mainmenu, menu)
        return true
    }
}