package com.training.graduation.screens.sharedprefrence

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import java.util.Locale

fun UpdateLocale(context: Context, languageCode: String) {
    val locale = Locale(languageCode)
    Locale.setDefault(locale)
    val config = Configuration(context.resources.configuration)
    config.setLocale(locale)
    context.resources.updateConfiguration(config, context.resources.displayMetrics)

//    val activity = context as? Activity
//    activity?.recreate()

}
