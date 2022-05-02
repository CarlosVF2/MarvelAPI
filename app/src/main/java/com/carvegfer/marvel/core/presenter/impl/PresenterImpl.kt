package com.carvegfer.marvel.core.presenter.impl

import com.carvegfer.marvel.core.presenter.Presenter
import com.carvegfer.marvel.core.view.View

/**
 * Implementación del core del PRESENTER encargado de gestionar lógica
 */
abstract class PresenterImpl<VIEW : View>(private val view: VIEW) : Presenter {

}