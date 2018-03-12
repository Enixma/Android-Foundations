package com.enixma.nakarinj.foundations.test

import dagger.Module
import dagger.Provides

@Module
class TestModule(var view: TestContract.View,
                 var viewModel: TestViewModel) {

    @Provides
    fun providePresenter(): TestContract.Action {
        return TestPresenter(view, viewModel)
    }

}