package com.example.assignment1

import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CalendarView
import android.widget.Spinner
import android.widget.TextView
import android.view.Menu
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class AppointmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_appointment_details)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Get Intent Data
        val customerEmail = intent.getStringExtra("customerEmail")
        val customerName = intent.getStringExtra("customerName")

        //Set Appointment Details Widgets
        val txtAppointmentName = findViewById<TextView>(R.id.txtAppointmentName)
        val txtAppointmentEmail = findViewById<TextView>(R.id.txtAppointmentEmail)
        txtAppointmentName.text = "Email : $customerEmail"
        txtAppointmentEmail.text = "Name : $customerName"


        //Service Spinner
        val serviceSpinner = findViewById<Spinner>(R.id.serviceSpinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.service_type,
            android.R.layout.simple_spinner_item

        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            serviceSpinner.adapter = adapter
        }

        //Calendar View
        val calendarView: CalendarView = findViewById(R.id.calendarView)
        var dateInMilliSecond = calendarView.getDate()
        val defaultDate = Calendar.getInstance().apply {
            timeInMillis = dateInMilliSecond
        }
        var appointmentDate : String = "${defaultDate.get(Calendar.DAY_OF_MONTH).toString()}/${defaultDate.get(Calendar.MONTH) + 1}/${defaultDate.get(Calendar.YEAR).toString()}"
        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val date = "$dayOfMonth/${month + 1}/$year"
            appointmentDate = date.toString()
        }

        // Time View
        val appointmentTime = findViewById<Spinner>( R.id.spinnerTime)
        ArrayAdapter.createFromResource(
            this,
            R.array.avaiableTime,
            android.R.layout.simple_spinner_item

        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            appointmentTime.adapter = adapter
        }


        //Final Confirm Button
        val btnnext = findViewById<Button>(R.id.btnAppointmentNext)
        btnnext.setOnClickListener {
            val appointmentSet = Intent( this, ConfirmActivity::class.java)

            appointmentSet.putExtra("customerName", customerName)
            appointmentSet.putExtra("customerEmail", customerEmail)
            appointmentSet.putExtra("customerDate", appointmentDate)
            appointmentSet.putExtra("customerService", serviceSpinner.getSelectedItem().toString())
            appointmentSet.putExtra("customerAppointmentTime", appointmentTime.getSelectedItem().toString())
            startActivity(appointmentSet)

        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.mainmenu, menu)
        return true
    }
}