package com.example.androidlesson15.ui.auth


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.androidlesson15.databinding.FragmentRegisterBinding
import com.example.androidlesson15.ui.auth.AuthViewModel
import com.example.androidlesson15.utils.gone
import com.example.androidlesson15.utils.visible


class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding: FragmentRegisterBinding get() =  _binding!!
    private val viewModel by viewModels<AuthViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeData()

        binding.buttonRegister.setOnClickListener {

            registerUser()

        }
    }

    private fun observeData() {
        viewModel.isLogin.observe(viewLifecycleOwner) {
            if (it) {

                Toast.makeText(context, "Register", Toast.LENGTH_LONG).show()

                findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())

            }
        }

        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }

        viewModel.loading.observe(viewLifecycleOwner) {
            if (it) binding.progressBar3.visible() else binding.progressBar3.gone()
        }
    }

    private fun registerUser() {
        val email = binding.editTextEmailRegister.text.toString().trim()
        val password = binding.editTextPasswordRegister.text.toString().trim()

        if (email.isNotEmpty() && password.isNotEmpty()) {
            viewModel.register(email, password)



        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}