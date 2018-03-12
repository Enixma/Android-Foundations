package com.enixma.nakarinj.foundations.test

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField


class TestViewModel: ViewModel(){
    var titleText: ObservableField<String> = ObservableField("Hello")
}