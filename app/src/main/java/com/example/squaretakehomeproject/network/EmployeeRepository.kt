package com.example.squaretakehomeproject.network

import com.example.squaretakehomeproject.models.EmployeeModel
import javax.inject.Inject

class EmployeeRepository @Inject constructor(
    private val employeeService: EmployeeService
) {

    suspend fun getEmployees(): EmployeeListDataState {
        return try {
            val employeeResponse = employeeService.getEmployees()
            validateData(
                employeeResponse
            )
        } catch (e: Exception) {
            EmployeeListDataState.ErrorState(Throwable("An unexpected error occurred"))
        }
    }

    suspend fun getMalformedEmployees(): EmployeeListDataState {
        return try {
            val employeeResponse = employeeService.getMalformedEmployees()
            validateData(
                employeeResponse
            )
        } catch (e: Exception) {
            EmployeeListDataState.ErrorState(Throwable("An unexpected error occurred"))
        }
    }

    suspend fun getEmptyEmployees(): EmployeeListDataState {
        return try {
            val employeeResponse = employeeService.getEmptyEmployees()
            validateData(
                employeeResponse
            )
        } catch (e: Exception) {
            EmployeeListDataState.ErrorState(Throwable("An unexpected error occurred"))
        }
    }

    private fun validateData(
        employeeResponse: EmployeeResponse
    ): EmployeeListDataState {
        val employees = employeeResponse.employeeModels
        return when {
            employees == null -> {
                EmployeeListDataState.ErrorState(
                    Throwable("Employee data is null")
                )
            }
            employees.any { !EmployeeModel.isValid(it) } -> {
                EmployeeListDataState.ErrorState(
                    Throwable("Employee data is malformed")
                )
            }
            else -> {
                EmployeeListDataState.SuccessState(employees)
            }
        }
    }
}