package com.carvegfer.marvel.core.view.impl

import androidx.fragment.app.Fragment
import com.carvegfer.marvel.core.presenter.Presenter
import com.carvegfer.marvel.core.view.View

class FragmentImpl<PRESENTER : Presenter>(val presenter: PRESENTER) : Fragment(), View {

    override fun onLoaded() {
        TODO("Not yet implemented")
    }

    override fun onLoading() {
        TODO("Not yet implemented")
    }

    override fun isDestroying() {
        TODO("Not yet implemented")
    }
}