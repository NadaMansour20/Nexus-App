package com.training.graduation.onboarding

import androidx.annotation.DrawableRes
import com.training.graduation.R

sealed class OnboardingModel(
    @DrawableRes val image: Int,
    val title: String,
    val description: String,
) {

    data object FirstPage : OnboardingModel(
        image = R.drawable.onbording1,
        title = "Your Meeting Assist",
        description = "Easy online meeting App"
    )

    data object SecondPage : OnboardingModel(
        image = R.drawable.onboarding2,
        title ="AI-Based Intelligent Assistant",
        description = "AI-Based Intelligent Assistant for Education and Corporate Meeting Engagement"
    )




}