package com.example.usersapp.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.usersapp.R
import com.example.usersapp.data.util.Resource
import com.example.usersapp.databinding.UsersFragmentBinding
import com.example.usersapp.presentation.adapter.UsersAdapter
import com.example.usersapp.presentation.viewmodel.UsersViewModel

class UsersFragment : Fragment() {

    private lateinit var viewModel: UsersViewModel
    private lateinit var usersAdapter: UsersAdapter
    private lateinit var usersFragmentBinding: UsersFragmentBinding
    private var isLoading = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.users_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        usersFragmentBinding = UsersFragmentBinding.bind(view)
        viewModel = (activity as MainActivity).viewModel
        usersAdapter = (activity as MainActivity).usersAdapter
        observeViewModel()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun observeViewModel() {
        lifecycleScope.launchWhenCreated {
            viewModel.uiState.collect { response ->
                when (response) {
                    is Resource.Success -> {
                        hideProgressBar()
                        response.data?.let {
                            Log.i("MYTAG", "came here ${it.users.toList().size}")
                            usersAdapter.differ.submitList(it.users.toList())
                        }
                    }
                    is Resource.Error -> {
                        hideProgressBar()
                        response.message?.let {
                            Toast.makeText(activity, "An error occurred : $it", Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                    is Resource.Loading -> {
                        showProgressBar()
                    }
                }
            }
        }
    }

    private fun showProgressBar() {
        isLoading = true
        usersFragmentBinding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        isLoading = false
        usersFragmentBinding.progressBar.visibility = View.INVISIBLE
    }
}
