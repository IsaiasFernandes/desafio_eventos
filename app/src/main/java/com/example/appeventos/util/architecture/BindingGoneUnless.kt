package com.example.appeventos.util.architecture

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("app:gone_unless")
fun goneUnless(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}
