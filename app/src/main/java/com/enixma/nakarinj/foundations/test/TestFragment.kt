package com.enixma.nakarinj.foundations.test

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.enixma.nakarinj.foundations.R
import com.enixma.nakarinj.foundations.databinding.LayoutTestFragmentBinding
import com.enixma.nakarinj.foundations.presentation.BaseFragment
import kotlinx.android.synthetic.main.layout_test_fragment.*
import javax.inject.Inject

class TestFragment : BaseFragment(), TestContract.View {

    private lateinit var binding: LayoutTestFragmentBinding
    private lateinit var viewModel: TestViewModel

    @Inject
    lateinit var presenter: TestContract.Action

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TestViewModel::class.java)

        DaggerTestComponent.builder()
                .testModule(TestModule(this, viewModel))
                .build()
                .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater!!, R.layout.layout_test_fragment, container, false)
        binding.model = this.viewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        lifecycle.addObserver(presenter as LifecycleObserver)

        button_hello.setOnClickListener {
            presenter.updateText()
        }

        button_exit.setOnClickListener {
            activity.finish()
        }
    }
}