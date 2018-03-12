package com.enixma.nakarinj.foundations.test

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [TestModule::class])
interface TestComponent {
    fun inject(testFragment: TestFragment)
}