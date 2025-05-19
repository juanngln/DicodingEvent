package com.idcamp.dicodingevent.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.idcamp.dicodingevent.data.response.ListEventsItem
import com.idcamp.dicodingevent.databinding.FragmentHomeBinding
import com.idcamp.dicodingevent.ui.EventAdapter
import com.idcamp.dicodingevent.ui.EventHorizontalAdapter

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val homeViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[HomeViewModel::class.java]

        val horizontalLayoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvEventUpcoming.layoutManager = horizontalLayoutManager

        val verticalLayoutManager = LinearLayoutManager(requireActivity())
        binding.rvEventFinished.layoutManager = verticalLayoutManager

        homeViewModel.loadEvents("1")

        homeViewModel.listUpcomingEvent.observe(viewLifecycleOwner) { listEvent ->
            setUpcomingEventsData(listEvent)
        }

        homeViewModel.loadEvents("0")

        homeViewModel.listFinishedEvent.observe(viewLifecycleOwner) { listEvent ->
            setFinishedEventsData(listEvent)
        }

        homeViewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }
    }

    private fun setUpcomingEventsData(listEvents: List<ListEventsItem>) {
        val adapter = EventHorizontalAdapter()
        adapter.submitList(listEvents.take(5))
        binding.rvEventUpcoming.adapter = adapter
    }

    private fun setFinishedEventsData(listEvents: List<ListEventsItem>) {
        val adapter = EventAdapter()
        adapter.submitList(listEvents.take(5))
        binding.rvEventFinished.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
