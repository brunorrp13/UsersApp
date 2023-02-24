package com.example.usersapp.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.usersapp.R
import com.example.usersapp.databinding.HomeFragmentBinding

class HomeFragment : Fragment() {

    private lateinit var homeFragmentBinding: HomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeFragmentBinding = HomeFragmentBinding.bind(view)
        handleButtonClick()
    }

    private fun handleButtonClick() {
        homeFragmentBinding.buttonFirst.setOnClickListener {
            if (homeFragmentBinding.endpointTextInput.text.isNullOrEmpty()) {
                Toast.makeText(activity, resources.getString(R.string.empty_text_field_msg), Toast.LENGTH_LONG)
                    .show()
            } else {
                val endpoint = Bundle().apply {
                    putString(resources.getString(R.string.url), homeFragmentBinding.endpointTextInput.text.toString())
                }
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, endpoint)
            }
        }
    }
}
