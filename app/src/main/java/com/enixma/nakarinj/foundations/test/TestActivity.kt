package com.enixma.nakarinj.foundations.test

import android.os.Bundle
import com.enixma.nakarinj.foundations.R
import com.enixma.nakarinj.foundations.presentation.BaseActivity

class TestActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_test_activity)
        replaceFragment(R.id.test_fragment_host, TestFragment())

    }
}