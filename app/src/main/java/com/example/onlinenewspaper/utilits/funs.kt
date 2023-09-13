package com.example.onlinenewspaper.utilits

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.Activity
import android.app.Dialog
import android.os.Build
import android.view.Window
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.onlinenewspaper.business.model.NewsModel
import android.R
import android.content.Context
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.example.onlinenewspaper.business.model.FavoriteModel
import com.example.onlinenewspaper.viewModel.NewsViewModel
import com.example.onlinenewspaper.viewModel.SaveViewModel
import kotlinx.coroutines.launch

fun replaceFragmentMain(fagmnt: Fragment, aStack: Boolean = true) {
    if (aStack) {
        APP_ACTIVITY.supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(
                com.example.onlinenewspaper.R.id.main_layout,
                fagmnt
            ).commit()
    } else {
        APP_ACTIVITY.supportFragmentManager.beginTransaction()
            .replace(
                com.example.onlinenewspaper.R.id.main_layout,
                fagmnt
            ).commit()
    }
}

@SuppressLint("ObsoleteSdkInt")
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
fun setStatusBarGradiantMain(activity: Activity) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        val window: Window = activity.window
        val background = ContextCompat.getDrawable(activity, com.example.onlinenewspaper.R.color.background)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

        window.statusBarColor = ContextCompat.getColor(activity,android.R.color.transparent)
        window.setBackgroundDrawable(background)
    }
}

fun getDialogDetails(
    new: NewsModel,
    context: Context,
    viewModelNews: NewsViewModel,
    viewModelFav: SaveViewModel,
    viewLifecycleOwner: LifecycleOwner
){
    val dialog = Dialog(context, R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen)
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog.setContentView(com.example.onlinenewspaper.R.layout.full_screen_deails_new)
    dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    dialog.window?.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
    dialog.window?.statusBarColor = ContextCompat.getColor(context, android.R.color.transparent)

    val icon : ImageView = dialog.findViewById(com.example.onlinenewspaper.R.id.icon_news)
    val titleNews : TextView = dialog.findViewById(com.example.onlinenewspaper.R.id.tv_title_news_detail)
    val textNews : TextView = dialog.findViewById(com.example.onlinenewspaper.R.id.tv_text_news_detail)
    val dateNews : TextView = dialog.findViewById(com.example.onlinenewspaper.R.id.tv_date_detail)
    val btArrow : ImageView = dialog.findViewById(com.example.onlinenewspaper.R.id.ic_arrow)
    val btSave : ConstraintLayout = dialog.findViewById(com.example.onlinenewspaper.R.id.bt_save)

    Glide.with(context).load(new.icon).into(icon)
    titleNews.setText(new.title)
    textNews.setText(new.text)
    dateNews.setText(new.date)

    btArrow.setOnClickListener { dialog.cancel() }

    btSave.setOnClickListener {
        viewModelNews.isNewsFavorite(newsId = new.id).observe(viewLifecycleOwner, Observer {isFavorite ->
            if(isFavorite){
                Toast.makeText(context, "Новость уже добавлена в избранное!!!", Toast.LENGTH_SHORT).show()
            } else{
                viewModelFav.viewModelScope.launch {
                    viewModelFav.insertFavorite(favorite = FavoriteModel(newsId = new.id, icon = new.icon,
                        title = new.title, text = new.text, date = new.date)
                    )
                }
            }
        })
    }

    dialog.show()
}

fun replaceFragmentOnBoarding(frent: Fragment, stack: Boolean = true) {
    if (stack) {
        APP_ACTIVITY_ONBOARDING.supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(
                com.example.onlinenewspaper.R.id.on_boarding_layout,
                frent
            ).commit()
    } else {
        APP_ACTIVITY_ONBOARDING.supportFragmentManager.beginTransaction()
            .replace(
                com.example.onlinenewspaper.R.id.on_boarding_layout,
                frent
            ).commit()
    }
}

@SuppressLint("ObsoleteSdkInt")
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
fun setStatusBarGradiantOnBoarding(activity: Activity) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        val window: Window = activity.window
        val background = ContextCompat.getDrawable(activity, com.example.onlinenewspaper.R.color.background)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

        window.statusBarColor = ContextCompat.getColor(activity,android.R.color.transparent)
        window.setBackgroundDrawable(background)
    }
}
