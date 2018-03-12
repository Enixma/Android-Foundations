package com.enixma.nakarinj.foundations.data.sharedprefs

import android.content.Context
import dagger.Module
import dagger.Provides

/**
 * Created by nakarinj on 12/3/2018 AD.
 */
@Module
class AppSharedPreferencesModule(context: Context) {

    private val appSharedPreferences: AppSharedPreferences = AppSharedPreferences(context)

    @Provides
    fun provideAppSharedPreferences(): AppSharedPreferences {
        return appSharedPreferences
    }
}



