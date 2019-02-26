package fr.sebastienlaunay.MVVMFragmentTan.base

import android.arch.lifecycle.ViewModel
import fr.sebastienlaunay.MVVMFragmentTan.injection.component.DaggerViewModelInjector
import fr.sebastienlaunay.MVVMFragmentTan.injection.component.ViewModelInjector
import fr.sebastienlaunay.MVVMFragmentTan.injection.module.NetworkModule
import fr.sebastienlaunay.MVVMFragmentTan.ui.main.MainViewModel

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