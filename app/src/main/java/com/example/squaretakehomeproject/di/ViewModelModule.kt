package com.example.squaretakehomeproject.di

import androidx.lifecycle.ViewModelProvider
import com.example.squaretakehomeproject.ui.EmployeeListViewModel
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindsViewModelFactory(
        factory: EmployeeListViewModel.Companion.ViewModelFactory
    ): ViewModelProvider.Factory
}