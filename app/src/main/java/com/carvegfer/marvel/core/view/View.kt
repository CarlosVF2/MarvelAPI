package com.carvegfer.marvel.core.view

interface View {

    fun onLoaded()

    fun onLoading()

    fun isDestroying()
}