package com.example.appeventos.presentation.eventslist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.appeventos.databinding.EventListFragmentBinding
import com.example.appeventos.domain.model.Events
import com.example.appeventos.presentation.eventslist.adapter.EventListAdapter
import com.example.appeventos.util.architecture.ResultOf
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class EventListFragment : Fragment() {

    private val viewModel: EventListViewModel by viewModels()
    private lateinit var binding: EventListFragmentBinding
    private lateinit var adapter: EventListAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View {
        val view = EventListFragmentBinding.inflate(inflater, container, false).also {
            binding = it
            binding.lifecycleOwner = viewLifecycleOwner
            binding.viewModel = viewModel
        }.root

        adapter = EventListAdapter(viewModel)

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getEvents()

        viewModel.eventsLiveData.data.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        viewModel.eventoSelectIdLiveData.observe(viewLifecycleOwner) {
            val direction = EventListFragmentDirections.actionListToDetails(it)
            findNavController().navigate(direction)
        }

        binding.reciclerEventList.adapter = adapter

    }
}