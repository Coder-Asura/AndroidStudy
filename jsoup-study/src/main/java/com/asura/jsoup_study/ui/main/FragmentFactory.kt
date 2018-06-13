package com.asura.jsoup_study.ui.main

import android.support.v4.app.Fragment
import android.support.v4.util.SparseArrayCompat
import com.asura.jsoup_study.ui.main.new.NewGirlFragment

/**
 * @author Liuxd
 */
object FragmentFactory {

    val INDEX_FRAGMENT_NEW_GIRL = 0
    val INDEX_FRAGMENT_FIGURE_GIRL = 1
    val INDEX_FRAGMENT_JOKE_GIRL = 2

    val TAG_FRAGMENT_NEW_GIRL = "fragment_new_girl"
    val TAG_FRAGMENT_FIGURE_GIRL = "fragment_figure_girl"
    val TAG_FRAGMENT_JOKE_GIRL = "fragment_joke_girl"

    fun createFragment(id: Int): Fragment? {

        val mCache = SparseArrayCompat<Fragment>()
        var fragment: Fragment? = mCache.get(id)

        if (null != fragment) {
            return fragment
        }
        when (id) {
            INDEX_FRAGMENT_NEW_GIRL -> fragment = NewGirlFragment()
            INDEX_FRAGMENT_FIGURE_GIRL -> {

            }
            INDEX_FRAGMENT_JOKE_GIRL -> {

            }
        }

        mCache.put(id, fragment)

        return fragment
    }
}
