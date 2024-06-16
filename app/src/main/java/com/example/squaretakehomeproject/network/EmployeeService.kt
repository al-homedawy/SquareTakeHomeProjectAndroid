package com.example.squaretakehomeproject.network

import retrofit2.http.GET

interface EmployeeService {
    @GET("sq-mobile-interview/employees.json")
    suspend fun getEmployees(): EmployeeResponse

    @GET("sq-mobile-interview/employees_malformed.json")
    suspend fun getMalformedEmployees(): EmployeeResponse

    @GET("sq-mobile-interview/employees_empty.json")
    suspend fun getEmptyEmployees(): EmployeeResponse
}