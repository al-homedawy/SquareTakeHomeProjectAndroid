package com.example.squaretakehomeproject

import android.app.Application
import com.example.squaretakehomeproject.di.DaggerApplicationComponent

class EmployeeApplication : Application() {

    val appComponent by lazy {
        DaggerApplicationComponent.builder().build()
    }
}