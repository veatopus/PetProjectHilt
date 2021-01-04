package kg.geektech.ruslan.petprojecthilt.core

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun ImageView.loadImage(url: String) {
    Glide
        .with(this.context)
        .load(url)
        .into(this)
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.isVisible(state: Boolean){
    if (state) this.visible()
    else this.gone()
}