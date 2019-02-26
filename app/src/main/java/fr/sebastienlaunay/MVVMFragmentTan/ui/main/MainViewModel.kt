package fr.sebastienlaunay.MVVMFragmentTan.ui.main

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import android.view.View
import fr.sebastienlaunay.MVVMFragmentTan.R
import fr.sebastienlaunay.MVVMFragmentTan.base.BaseViewModel
import fr.sebastienlaunay.MVVMFragmentTan.model.TanArret
import fr.sebastienlaunay.MVVMFragmentTan.network.TanApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel : BaseViewModel() {

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    @Inject
    lateinit var tanApi: TanApi


    private lateinit var subscription: Disposable

    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadTanArrets() }
    val listTanArrets: MutableLiveData<List<TanArret>> = MutableLiveData()

    init {
        loadTanArrets()
    }

    private fun loadTanArrets(){
        subscription = tanApi.getTanStops()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveTanArretListStart() }
            .doOnTerminate { onRetrieveTanArretListFinish() }
            .subscribe (
                { listeDesArrets -> onRetrieveTanArretListSuccess(listeDesArrets)},
                { error -> onRetrieveTanArretListError(error)}
            )
    }

    private fun onRetrieveTanArretListStart() {
        loadingVisibility.value = View.VISIBLE
    }

    private fun onRetrieveTanArretListFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveTanArretListSuccess(listeDesArrets: List<TanArret>) {
        listTanArrets.value = listeDesArrets
    }

    private fun onRetrieveTanArretListError(error: Throwable) {
        Log.e("MVVM","Error : $error")
        errorMessage.value = R.string.tanarret_error
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}
