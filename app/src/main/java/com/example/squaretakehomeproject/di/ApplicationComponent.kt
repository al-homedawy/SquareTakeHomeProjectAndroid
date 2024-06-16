package com.example.squaretakehomeproject.di

import com.example.squaretakehomeproject.ui.EmployeeListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(employeeListFragment: EmployeeListFragment)
}