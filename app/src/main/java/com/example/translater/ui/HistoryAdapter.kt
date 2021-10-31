package com.example.translater.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.translater.databinding.ItemHistoryBinding
import com.example.translater.model.DataHistory
import java.text.SimpleDateFormat

class HistoryAdapter(private val inflater: LayoutInflater): BaseAdapter() {

    private var list = ArrayList<DataHistory>()

    fun upDate(listUp: ArrayList<DataHistory>) {
        list = listUp
        list.sortByDescending { it.date }
        notifyDataSetChanged()
    }

    override fun getCount(): Int = list.size

    override fun getItem(p0: Int): Any = list[p0]

    override fun getItemId(p0: Int): Long = p0.toLong()

    @SuppressLint("ViewHolder")
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val item = list[p0]
        val binding = ItemHistoryBinding.inflate(inflater)
        binding.itemHistoryTvLanguageFrom.text = item.from.toString().substring(0, 3)
        binding.itemHistoryTvLanguageTo.text = item.to.toString().substring(0, 3)
        binding.itemHistoryTvValue.text = item.value
        binding.itemHistoryTvResult.text = item.result
        binding.itemHistoryTvDate.text = parseToStringFormat(item.date)
        return binding.root
    }

    @SuppressLint("SimpleDateFormat")
    private fun parseToStringFormat(date: Long): String {
        val format = SimpleDateFormat("hh:mm:ss  dd/MM/yyyy")
        return format.format(date)
    }
}