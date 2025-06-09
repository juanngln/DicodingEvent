package com.idcamp.dicodingevent.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.idcamp.dicodingevent.data.response.ListEventsItem
import com.idcamp.dicodingevent.databinding.FragmentFavoriteBinding
import com.idcamp.dicodingevent.ui.EventAdapter
import com.idcamp.dicodingevent.ui.ViewModelFactory

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private lateinit var favoriteViewModel: FavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favoriteViewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance(requireActivity().application)
        )[FavoriteViewModel::class.java]

        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvEvent.layoutManager = layoutManager

        val adapter = EventAdapter()
        binding.rvEvent.adapter = adapter

        favoriteViewModel.getAllFavoriteEvents().observe(viewLifecycleOwner) { events ->
            showLoading(events.isEmpty())

            val items = events.map {
                ListEventsItem(
                    id = it.id,
                    name = it.name,
                    imageLogo = it.imageLogo,
                    summary = it.summary,
                    mediaCover = it.mediaCover,
                    description = it.description,
                    link = it.link
                )
            }
            adapter.submitList(items)
        }
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
