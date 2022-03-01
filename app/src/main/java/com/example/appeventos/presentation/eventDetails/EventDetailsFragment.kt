package com.example.appeventos.presentation.eventDetails

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.appeventos.R
import com.example.appeventos.databinding.EventDetailsFragmentBinding
import com.example.appeventos.domain.model.EventDetails
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventDetailsFragment : Fragment() {

    private lateinit var binding: EventDetailsFragmentBinding
    private lateinit var event: EventDetails
    private val args by navArgs<EventDetailsFragmentArgs>()
    private val viewModel: EventDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = EventDetailsFragmentBinding.inflate(inflater, container, false).also {
            binding = it
            binding.lifecycleOwner = viewLifecycleOwner
            binding.viewModel = viewModel
        }.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        Log.d("tag", args.eventId)
        viewModel.addEventId(args.eventId)

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_EventDetailsFragment_to_EventListFragment)
        }

        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.appbar_share -> {
                    shareFromFriends()
                    true
                }
                else -> false
            }
        }
        viewModel.getDetailsEvents()


        viewModel.eventDetails.data.observe(viewLifecycleOwner) {
            inflateLayout(it)
        }
    }

    private fun inflateLayout(eventDetails: EventDetails) {

        event = eventDetails
        binding.details = eventDetails
        val id = eventDetails.id
        binding.actionButton.setOnClickListener {
            /*val directions = EventDetailsFragmentDirections.actionDetailsToCheckIn(id)
            findNavController().navigate(directions)*/
        }
    }

    private fun shareFromFriends() {
       val text = "Venha conhece o Evento \t ${event.title}\n" +
               "Sera realizado no dia ${event.date} \n pelo valor de ${event.price}"
       val send = Intent().apply {
           action = Intent.ACTION_SEND
           putExtra(Intent.EXTRA_TEXT, text)
           type = "text/plain"
       }
        startActivity(Intent.createChooser(send, null))
    }
}
