package com.example.squaretakehomeproject.network

import com.example.squaretakehomeproject.models.EmployeeModel
import com.google.gson.annotations.SerializedName

data class EmployeeResponse(
    @SerializedName("employees")
    val employeeModels: List<EmployeeModel>?
)