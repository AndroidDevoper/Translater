package com.example.translater.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.translater.databinding.ItemResultSearchBinding

class SearchAdapter(private val inflater: LayoutInflater) : BaseAdapter() {

    private var list = ArrayList<String>()

    fun upDate(list: ArrayList<String>) {
        this.list = list
        notifyDataSetChanged()
    }
    override fun getCount(): Int = list.size

    override fun getItem(p0: Int): Any = list[p0]

    override fun getItemId(p0: Int): Long = p0.toLong()

    @SuppressLint("ViewHolder")
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val binding = ItemResultSearchBinding.inflate(inflater)
        val item = list[p0]
        binding.itemResultSearchTvNumber.text = (p0 + 1).toString()
        binding.itemResultSearchTvResult.text = item
        return binding.root
    }
}