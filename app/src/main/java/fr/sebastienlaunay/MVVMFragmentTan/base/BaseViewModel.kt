package fr.sebastienlaunay.kotlinfragment.base

import android.arch.lifecycle.ViewModel
import fr.sebastienlaunay.kotlinfragment.injection.component.DaggerViewModelInjector
import fr.sebastienlaunay.kotlinfragment.injection.component.ViewModelInjector
import fr.sebastienlaunay.kotlinfragment.injection.module.NetworkModule
import fr.sebastienlaunay.kotlinfragment.ui.main.MainViewModel

abstract class BaseViewModel : ViewModel() {

    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }


    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is MainViewModel -> injector.inject(this)
        }
    }
}