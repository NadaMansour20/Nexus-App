package com.training.graduation

import android.app.Application
import android.util.Log
import com.onesignal.OneSignal
import com.onesignal.user.subscriptions.IPushSubscriptionObserver
import com.onesignal.user.subscriptions.PushSubscriptionChangedState
import com.training.graduation.token.Constent

class App:Application() {
    override fun onCreate() {
        super.onCreate()
        OneSignal.initWithContext(this, Constent.ON_SIGNAL_APP_ID)

        OneSignal.User.pushSubscription.optIn()

        OneSignal.User.pushSubscription.addObserver(object : IPushSubscriptionObserver {

            override fun onPushSubscriptionChange(state: PushSubscriptionChangedState) {
                Log.e("OneSignal", "🔔 Subscribed: ${state.current.optedIn}")
                Log.e("OneSignal", "🎯 Player ID: ${state.current.id}")

            }
        })




    }
}