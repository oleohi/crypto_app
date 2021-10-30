package com.yoti.android.cryptocurrencychallenge.utils

import android.view.View
import androidx.appcompat.widget.SearchView
import com.google.android.material.snackbar.Snackbar


fun showSnackBar(view: View, message: String, actionTitle: String, action: View.OnClickListener) {
    Snackbar.make(view, message, Snackbar.LENGTH_LONG)
        .setAction(actionTitle, action).show()
}