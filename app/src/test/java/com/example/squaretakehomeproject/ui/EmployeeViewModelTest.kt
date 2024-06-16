package com.example.squaretakehomeproject.ui

import com.example.squaretakehomeproject.fixtures.EmployeeListFixtures
import com.example.squaretakehomeproject.network.EmployeeRepository
import com.example.squaretakehomeproject.network.EmployeeService
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class EmployeeViewModelTest {

    private val employeeService: EmployeeService = mockk(relaxed = true)

    private val employeeRepository: EmployeeRepository = EmployeeRepository(
        employeeService = employeeService
    )

    private val employeeViewModel: EmployeeListViewModel = EmployeeListViewModel(
        repository = employeeRepository
    )

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun `assert that ViewModel emits success state for valid data`() = runTest {
        coEvery {
            employeeService.getEmployees()
        } returns EmployeeListFixtures.employeeList
        assertTrue(
            employeeViewModel.viewState.value is EmployeeListViewState.LoadingState
        )
        employeeViewModel.getEmployees()
        advanceUntilIdle()
        assertTrue(
            employeeViewModel.viewState.value is EmployeeListViewState.SuccessState
        )
    }

    @Test
    fun `assert that ViewModel emits empty state for empty data`() = runTest {
        coEvery {
            employeeService.getEmployees()
        } returns EmployeeListFixtures.emptyList
        assertTrue(
            employeeViewModel.viewState.value is EmployeeListViewState.LoadingState
        )
        employeeViewModel.getEmployees()
        advanceUntilIdle()
        assertTrue(
            employeeViewModel.viewState.value is EmployeeListViewState.EmptyState
        )
    }

    @Test
    fun `assert that ViewModel emits error state for malformed data`() = runTest {
        coEvery {
            employeeService.getEmployees()
        } returns EmployeeListFixtures.malformedList
        assertTrue(
            employeeViewModel.viewState.value is EmployeeListViewState.LoadingState
        )
        employeeViewModel.getEmployees()
        advanceUntilIdle()
        assertTrue(
            employeeViewModel.viewState.value is EmployeeListViewState.ErrorState
        )
    }
}