package com.example.usersapp.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
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
        val args: UsersFragmentArgs by navArgs()
        getUsers(args.url)
        initRecyclerView()
        observeViewModel()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initRecyclerView() {
        usersFragmentBinding.usersRv.apply {
            adapter = usersAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun getUsers(url: String) {
        viewModel.getUsers(url)
    }

    private fun observeViewModel() {
        lifecycleScope.launchWhenCreated {
            viewModel.uiState.collect { response ->
                when (response) {
                    is Resource.Success -> {
                        hideProgressBar()
                        showUsersTitle()
                        response.data.let { users ->
                            val finalList = users.distinctBy { it.id }
                            usersAdapter.differ.submitList(finalList)
                        }
                    }
                    is Resource.Error -> {
                        hideProgressBar()
                        response.message.let {
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


    override fun onPause() {
        super.onPause()
        usersAdapter.differ.submitList(listOf())
    }


    override fun onStop() {
        super.onStop()
        usersAdapter.differ.submitList(listOf())
    }

    private fun showUsersTitle() {
        usersFragmentBinding.usersTitle.visibility = View.VISIBLE
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
