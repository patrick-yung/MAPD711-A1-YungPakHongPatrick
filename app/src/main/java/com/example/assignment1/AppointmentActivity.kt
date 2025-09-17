package com.example.assignment1

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CalendarView
import android.widget.Spinner
import android.widget.TextView
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class AppointmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_appointmentdetails)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Get Intent Data
        val email = intent.getStringExtra("email")
        val name = intent.getStringExtra("name")

        //Set Appointment Details Widgets
        val txtappointmentname = findViewById<TextView>(R.id.txtappointmentname)
        val txtappointmentemail = findViewById<TextView>(R.id.txtappointmentemail)
        txtappointmentname.text = "Email : " + email
        txtappointmentemail.text = "Name : " + name


        //Service Spinner
        val service_spinner = findViewById<Spinner>(R.id.service_spinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.service_type,
            android.R.layout.simple_spinner_item

        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            service_spinner.adapter = adapter
        }

        //Calendar View
        val calendarView = findViewById<CalendarView>(R.id.calendarView)
        var appointmentdate : String = ""
        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val date = "$dayOfMonth/${month + 1}/$year"
            appointmentdate = date.toString()
        }

        // Time View

        val hour = findViewById<Spinner>( R.id.spinner_hour)
        val minutes = findViewById<Spinner>( R.id.spinner_minutes)

        //Final Confirm Button
        val btnnext = findViewById<Button>(R.id.btnnext)
        btnnext.isEnabled = appointmentdate.isNotBlank()
                && service_spinner.getSelectedItem().toString().isNotBlank()
                && hour.getSelectedItem().toString().isNotBlank()
                && minutes.getSelectedItem().toString().isNotBlank()

        btnnext.setOnClickListener {
            val appointmentset = Intent( this, ConfirmActivity::class.java)

            appointmentset.putExtra("name", name)
            appointmentset.putExtra("email", email)
            appointmentset.putExtra("date", appointmentdate)
            appointmentset.putExtra("service", service_spinner.getSelectedItem().toString())
            appointmentset.putExtra("hour", email)
            appointmentset.putExtra("minutes", name)
            startActivity(appointmentset)

        }




    }
}