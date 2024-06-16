package com.example.squaretakehomeproject.network

import com.example.squaretakehomeproject.models.EmployeeModel

sealed class EmployeeListDataState {

    data class ErrorState(
        val throwable: Throwable
    ) : EmployeeListDataState()

    data class SuccessState(
        val employees: List<EmployeeModel>
    ) : EmployeeListDataState()
}