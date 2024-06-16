package com.example.squaretakehomeproject.fixtures

import com.example.squaretakehomeproject.network.EmployeeResponse
import com.google.gson.Gson

object EmployeeListFixtures {
    val employeeList = Gson().fromJson(employeeListFixture, EmployeeResponse::class.java)
    val malformedList = Gson().fromJson(malformedListFixture, EmployeeResponse::class.java)
    val emptyList = Gson().fromJson(emptyListFixture, EmployeeResponse::class.java)
}