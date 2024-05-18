package com.example.androidlesson15.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.androidlesson15.R
import com.example.androidlesson15.databinding.FragmentLoginBinding
import com.example.androidlesson15.utils.gone
import com.example.androidlesson15.utils.visible


class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<AuthViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()

        binding.progressBar2.gone()

        binding.button.setOnClickListener {
            loginUser()
        }

        binding.button2.setOnClickListener {
          findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }
    }

    private fun loginUser() {
        val email = binding.editTextText1.text.toString().trim()
        val password = binding.editTextText2.text.toString().trim()

        if (email.isNotEmpty() && password.isNotEmpty()) {
            viewModel.login(email, password)
        }

    }

    private fun observeData() {

        viewModel.isLogin.observe(viewLifecycleOwner) {
            if (it) {
                Toast.makeText(context, "${binding.editTextText1.text.toString().trim()}", Toast.LENGTH_LONG).show()
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
            } else {
                Toast.makeText(context, "Login Faild", Toast.LENGTH_LONG).show()
            }
        }

        viewModel.loading.observe(viewLifecycleOwner) {
            if (it) binding.progressBar2.visible() else binding.progressBar2.gone()
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}