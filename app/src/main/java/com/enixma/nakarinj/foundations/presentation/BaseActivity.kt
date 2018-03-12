package com.enixma.nakarinj.foundations.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.akexorcist.localizationactivity.LocalizationDelegate
import com.akexorcist.localizationactivity.OnLocaleChangedListener
import java.util.*

open class BaseActivity : AppCompatActivity(), OnLocaleChangedListener {

    private val localizationDelegate = LocalizationDelegate(this)

    var language: String
        get() = localizationDelegate.language
        set(language) {
            localizationDelegate.language = language
        }

    val locale: Locale
        get() = localizationDelegate.locale


    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        localizationDelegate.addOnLocaleChangedListener(this)
        localizationDelegate.onCreate(savedInstanceState)
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        localizationDelegate.onResume()
    }

    fun setLanguage(locale: Locale) {
        localizationDelegate.setLanguage(locale)
    }

    fun setDefaultLanguage(language: String) {
        localizationDelegate.setDefaultLanguage(language)
    }

    fun setDefaultLanguage(locale: Locale) {
        localizationDelegate.setDefaultLanguage(locale)
    }

    override fun onBeforeLocaleChanged() {}

    override fun onAfterLocaleChanged() {}

    fun replaceFragment(layoutId: Int, fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(layoutId, fragment)
                .commit()
    }

    fun replaceFragmentWithBackStack(layoutId: Int, fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(layoutId, fragment)
                .addToBackStack(fragment.javaClass.simpleName)
                .commit()
    }

    fun openActivityByAction(actionString: String) {
        val intent = Intent()
        intent.action = actionString
        startActivity(intent)
    }
}
