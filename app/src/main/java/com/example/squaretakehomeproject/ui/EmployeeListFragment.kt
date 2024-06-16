package com.example.squaretakehomeproject.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.squaretakehomeproject.EmployeeApplication
import com.example.squaretakehomeproject.adapters.EmployeeListAdapter
import com.example.squaretakehomeproject.databinding.EmployeeListFragmentBinding
import kotlinx.coroutines.launch
import javax.inject.Inject

class EmployeeListFragment : Fragment() {

    private lateinit var viewBinding: EmployeeListFragmentBinding

    private val employeeListAdapter = EmployeeListAdapter()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: EmployeeListViewModel by viewModels {
        viewModelFactory
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as EmployeeApplication).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = EmployeeListFragmentBinding.inflate(inflater)
        bindView()
        return viewBinding.root
    }

    private fun bindView() = with(viewBinding) {
        employeeList.adapter = employeeListAdapter
        lifecycleScope.launch {
            viewModel.getEmployees()
            viewModel.viewState.collect { viewState ->
                when (viewState) {
                    EmployeeListViewState.EmptyState -> switchToEmptyState()
                    EmployeeListViewState.LoadingState -> switchToLoadingState()
                    is EmployeeListViewState.ErrorState -> switchToErrorState(viewState)
                    is EmployeeListViewState.SuccessState -> switchToSuccessState(viewState)
                }
            }
        }
    }

    private fun switchToEmptyState() = with(viewBinding) {
        emptyMessage.isVisible = true
        errorMessage.isVisible = false
        employeeList.isVisible = false
        progressBar.isVisible = false
    }

    private fun switchToLoadingState() = with(viewBinding) {
        emptyMessage.isVisible = false
        errorMessage.isVisible = false
        employeeList.isVisible = false
        progressBar.isVisible = true
    }

    private fun switchToErrorState(
        errorState: EmployeeListViewState.ErrorState
    ) = with(viewBinding) {
        emptyMessage.isVisible = false
        errorMessage.isVisible = true
        employeeList.isVisible = false
        progressBar.isVisible = false

        Toast.makeText(
            requireContext(),
            errorState.throwable.message,
            Toast.LENGTH_LONG
        ).show()
    }

    private fun switchToSuccessState(
        successState: EmployeeListViewState.SuccessState
    ) = with(viewBinding) {
        emptyMessage.isVisible = false
        errorMessage.isVisible = false
        employeeList.isVisible = true
        progressBar.isVisible = false

        employeeListAdapter.submitList(
            successState.employeeModels
        )
    }
}