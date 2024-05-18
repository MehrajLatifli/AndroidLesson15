package com.example.androidlesson15.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.androidlesson15.databinding.FragmentHomeBinding
import com.example.androidlesson15.utils.gone
import com.example.androidlesson15.utils.visible


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<HomeViewModel>()

    private val todoAdapter =TodoAdapter()



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeData()

        binding.rvHome.adapter=todoAdapter

    }

    private fun observeData(){

        viewModel.todos.observe(viewLifecycleOwner){data->

            todoAdapter.updateList(data)

        }

        viewModel.isLoading.observe(viewLifecycleOwner){isLoading->

            if(isLoading){
                binding.progressBar.visible()
            }
            else{
                binding.progressBar.gone()

            }


        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
