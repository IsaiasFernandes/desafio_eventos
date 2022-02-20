package com.example.appeventos.presentation.eventslist

import android.R
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
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
        }.root

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
        }
        adapter = EventListAdapter(viewModel)
        binding.reciclerEventList.apply {
            adapter = this@EventListFragment.adapter
        }

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getEvents()

        viewModel.eventsLiveData.observe(viewLifecycleOwner) {
            prepareLayout(it)
        }


        /*viewModel.eventoSelectIdLiveData.observe(viewLifecycleOwner, {
            val direction = EventListFragmentDirections.actionListToDetails(it)
            findNavController().navigate(direction)
        })*/

    }

    private fun prepareLayout(result: ResultOf<List<Events>>) {

        when( result ) {
            is ResultOf.Success -> {
                adapter.submitList(result.value)
            }
            is ResultOf.Failure -> {
                Log.e("Error", result.message.toString())
            }
        }

    }
}