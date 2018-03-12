package com.enixma.nakarinj.foundations.test

import com.enixma.nakarinj.foundations.presentation.BasePresenter

class TestPresenter(private var view: TestContract.View?,
                    private var viewModel: TestViewModel): BasePresenter(), TestContract.Action{

    override fun updateText() {
        viewModel.titleText.set(viewModel.titleText.get() + " Hello")
    }

    override fun onPresenterDestroy() {
        view = null
    }
}