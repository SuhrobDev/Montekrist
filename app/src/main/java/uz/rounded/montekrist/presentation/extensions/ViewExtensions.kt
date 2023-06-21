package uz.rounded.montekrist.presentation.extensions

import android.graphics.PorterDuff
import android.view.View
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun AppCompatActivity.hideActionBar() {
    supportActionBar?.hide()
}

fun animateToolBarTittle(view: View) {
    YoYo.with(Techniques.BounceInRight)
        .duration(1300)
        .repeat(0)
        .playOn(view)
}