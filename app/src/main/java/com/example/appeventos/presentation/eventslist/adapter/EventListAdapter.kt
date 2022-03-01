package com.example.appeventos.presentation.eventslist.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.appeventos.R
import com.example.appeventos.databinding.ItemEventBinding
import com.example.appeventos.domain.model.Events
import com.example.appeventos.presentation.eventslist.EventListViewModel
import com.example.appeventos.util.extension.inflate

class EventListAdapter(private val action : EventListViewModel) : ListAdapter<Events, EventListAdapter.EventHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventListAdapter.EventHolder {
        return EventHolder(parent.inflate(R.layout.item_event), action)
    }

    override fun onBindViewHolder(holder: EventListAdapter.EventHolder, position: Int) {
        holder.binding.evento = getItem(position)
    }

    inner class EventHolder(
        val binding: ItemEventBinding,
        action: EventListViewModel
    ) : RecyclerView.ViewHolder(binding.root) {
        init { binding.actions = action }
    }

    private object DiffCallback : DiffUtil.ItemCallback<Events>() {
        override fun areItemsTheSame(oldItem: Events, newItem: Events) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Events, newItem: Events) = oldItem == newItem
    }
}