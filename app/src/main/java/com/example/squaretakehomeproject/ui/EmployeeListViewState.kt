package com.example.squaretakehomeproject.ui

import com.example.squaretakehomeproject.models.EmployeeModel

sealed class EmployeeListViewState {
    data object LoadingState : EmployeeListViewState()

    data object EmptyState : EmployeeListViewState()

    data class ErrorState(
        val throwable: Throwable
    ) : EmployeeListViewState()

    data class SuccessState(
        val employeeModels: List<EmployeeModel>
    ) : EmployeeListViewState()
}