package fr.sebastienlaunay.MVVMFragmentTan.injection.component

import dagger.Component
import fr.sebastienlaunay.MVVMFragmentTan.injection.module.NetworkModule
import fr.sebastienlaunay.MVVMFragmentTan.ui.main.MainViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {
    /**
     * Injects required dependencies into the specified tanArretListViewModel.
     * @param MainViewModel MainViewModel in which to inject the dependencies
     */
    fun inject(mainViewModel: MainViewModel)
    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector
        fun networkModule(networkModule: NetworkModule): Builder
    }
}