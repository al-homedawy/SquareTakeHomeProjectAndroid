package com.example.squaretakehomeproject.network

import com.example.squaretakehomeproject.fixtures.EmployeeListFixtures
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Test

class EmployeeRepositoryTest {

    private val employeeService: EmployeeService = mockk(relaxed = true) {
        coEvery { getEmployees() } returns EmployeeListFixtures.employeeList
        coEvery { getMalformedEmployees() } returns EmployeeListFixtures.malformedList
        coEvery { getEmptyEmployees() } returns EmployeeListFixtures.emptyList
    }

    private val employeeRepository: EmployeeRepository = EmployeeRepository(
        employeeService = employeeService
    )

    @Test
    fun `assert that employeeRepository returns success state for valid data`() = runTest {
        val dataState = employeeRepository.getEmployees()
        assertTrue(
            dataState is EmployeeListDataState.SuccessState
        )
    }

    @Test
    fun `assert that employeeRepository returns error state for malformed data`() = runTest {
        val dataState = employeeRepository.getMalformedEmployees()
        assertTrue(
            dataState is EmployeeListDataState.ErrorState
        )
    }

    @Test
    fun `assert that employeeRepository returns success state for empty data`() = runTest {
        val dataState = employeeRepository.getEmptyEmployees()
        assertTrue(
            dataState is EmployeeListDataState.SuccessState
        )
    }
}