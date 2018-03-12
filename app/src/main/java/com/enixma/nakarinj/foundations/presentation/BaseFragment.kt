package com.enixma.nakarinj.foundations.presentation

import android.content.Context
import android.support.v4.app.Fragment

open class BaseFragment : Fragment() {

    private var mActivity: BaseActivity? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mActivity = context as BaseActivity?
    }

    fun replaceFragment(layoutId: Int, fragment: Fragment) {
        if (mActivity != null)
            mActivity!!.replaceFragment(layoutId, fragment)
    }

    fun replaceFragmentWithBackStack(layoutId: Int, fragment: Fragment) {
        if (mActivity != null)
            mActivity!!.replaceFragmentWithBackStack(layoutId, fragment)
    }

    fun popFragmentFromBackStack() {
        fragmentManager!!.popBackStack()
    }

    fun clearAllFragment() {
        for (i in 0 until fragmentManager!!.backStackEntryCount) {
            fragmentManager!!.popBackStack()
        }
    }

    fun backToParent() {
        if (fragmentManager!!.backStackEntryCount > 0) {
            fragmentManager!!.popBackStack()
        } else {
            activity!!.finish()
        }
    }
}
