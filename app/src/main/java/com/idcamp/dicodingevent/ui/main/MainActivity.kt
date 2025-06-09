package com.idcamp.dicodingevent.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.idcamp.dicodingevent.data.response.ListEventsItem
import com.idcamp.dicodingevent.databinding.ActivityMainBinding
import com.idcamp.dicodingevent.ui.EventAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val mainViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[MainViewModel::class.java]

        mainViewModel.listEvent.observe(this) { listEvent ->
            setEventsData(listEvent)
        }

        val layoutManager = LinearLayoutManager(this)
        binding.rvEvent.layoutManager = layoutManager

        mainViewModel.isLoading.observe(this) {
            showLoading(it)
        }
    }

    private fun setEventsData(listEvents: List<ListEventsItem>) {
        val adapter = EventAdapter()
        adapter.submitList(listEvents)
        binding.rvEvent.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}
