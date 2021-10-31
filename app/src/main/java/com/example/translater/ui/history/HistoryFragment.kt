package com.example.translater.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.translater.databinding.FragmentHistoryBinding
import com.example.translater.ui.HistoryAdapter

class HistoryFragment : Fragment() {

    private lateinit var viewModel: HistoryViewModel
    private var _binding: FragmentHistoryBinding? = null

    private val binding get() = _binding!!
    private lateinit var adapter: HistoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        adapter = HistoryAdapter(inflater)
        initViewModel()
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        binding.fragmentHistoryTextView.adapter = adapter
        return binding.root
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(HistoryViewModel::class.java)
        viewModel.history.observe(viewLifecycleOwner, {
            adapter.upDate(ArrayList(it))
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}