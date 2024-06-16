package com.example.squaretakehomeproject.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.squaretakehomeproject.network.EmployeeListDataState
import com.example.squaretakehomeproject.network.EmployeeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class EmployeeListViewModel @Inject constructor(
    private val repository: EmployeeRepository
) : ViewModel() {

    private val mutableViewState =
        MutableStateFlow<EmployeeListViewState>(EmployeeListViewState.LoadingState)

    val viewState: StateFlow<EmployeeListViewState> = mutableViewState

    fun getEmployees() {
        viewModelScope.launch {
            mutableViewState.emit(
                value = EmployeeListViewState.LoadingState
            )
            try {
                val viewState = validateViewState(
                    dataState = repository.getEmployees()
                )
                mutableViewState.emit(
                    value = viewState
                )
            } catch (throwable: Throwable) {
                mutableViewState.emit(
                    value = EmployeeListViewState.ErrorState(
                        throwable = throwable
                    )
                )
            }
        }
    }

    private fun validateViewState(
        dataState: EmployeeListDataState
    ): EmployeeListViewState {
        return when (dataState) {
            is EmployeeListDataState.ErrorState ->
                EmployeeListViewState.ErrorState(
                    throwable = dataState.throwable
                )

            is EmployeeListDataState.SuccessState -> {
                if (dataState.employees.isEmpty()) {
                    EmployeeListViewState.EmptyState
                } else {
                    EmployeeListViewState.SuccessState(
                        employeeModels = dataState.employees
                    )
                }
            }
        }
    }

    companion object {
        @Suppress("UNCHECKED_CAST")
        class ViewModelFactory @Inject constructor(
            private val repository: EmployeeRepository
        ) : ViewModelProvider.Factory {

            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return EmployeeListViewModel(
                    repository
                ) as T
            }
        }
    }
}