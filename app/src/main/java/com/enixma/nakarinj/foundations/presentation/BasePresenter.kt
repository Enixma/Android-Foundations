package com.enixma.nakarinj.foundations.presentation

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent


abstract class BasePresenter : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    abstract fun onPresenterDestroy()
}