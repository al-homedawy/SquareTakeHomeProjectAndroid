package com.example.squaretakehomeproject

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.squaretakehomeproject.databinding.EmployeeActivityBinding

class EmployeeActivity : AppCompatActivity() {

    private lateinit var employeeActivityBinding: EmployeeActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        employeeActivityBinding = EmployeeActivityBinding.inflate(
            LayoutInflater.from(
                this
            )
        )
        setContentView(employeeActivityBinding.root)
    }
}