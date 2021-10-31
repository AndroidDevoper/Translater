package com.example.translater.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.translater.databinding.FragmentSearchBinding
import com.example.translater.ui.SearchAdapter

class SearchFragment : Fragment() {

    private lateinit var viewModel: SearchViewModel
    private var _binding: FragmentSearchBinding? = null

    private val binding get() = _binding!!

    lateinit var spinnerAdapter: SpinnerAdapter;
    lateinit var searchAdapter: SearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        searchAdapter = SearchAdapter(inflater)
        initViewModel()
        initBinding(inflater, container)
        initSpinners()

        return binding.root
    }

    private fun initSpinners() {
        spinnerAdapter = SpinnerAdapter(requireContext()) {from, adapter ->
            if (from)
                binding.fragmentSearchSpinnerFrom.adapter = adapter
            else
                binding.fragmentSearchSpinnerTo.adapter = adapter
        }
        binding.fragmentSearchSpinnerFrom.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                spinnerAdapter.selectLanguage(p2, true)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }

    private fun initBinding(inflater: LayoutInflater, container: ViewGroup?) {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        binding.fragmentSearchBtnSearch.setOnClickListener {
            search()
        }
        binding.fragmentSearchEtValue.setOnClickListener {
            search()
        }
        binding.fragmentSearchListView.adapter = searchAdapter
    }

    private fun search() {
        val value = binding.fragmentSearchEtValue.text.toString()
        if (value.contentEquals(" ")) {
            binding.fragmentSearchEtValue.error = "one word"
            return
        }
        viewModel.search(value,
            spinnerAdapter.getLanguageFrom(binding.fragmentSearchSpinnerFrom.selectedItemPosition),
            spinnerAdapter.getLanguageTo(binding.fragmentSearchSpinnerTo.selectedItemPosition))
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        viewModel.result.observe(viewLifecycleOwner, {
            searchAdapter.upDate(it)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}