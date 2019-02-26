package fr.sebastienlaunay.MVVMFragmentTan.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fr.sebastienlaunay.MVVMFragmentTan.R
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {

        private const val ARGUMENT_1 = "ARGUMENT_1"
        private const val ARGUMENT_2 = "ARGUMENT_2"

        fun newInstance() = MainFragment()

        fun newInstance(argument1: String? = null, argument2: String? = null) = MainFragment().apply {
            arguments = Bundle().apply {
                putString(MainFragment.ARGUMENT_1, argument1)
                putString(MainFragment.ARGUMENT_2, argument2)
            }
        }
    }

    private lateinit var viewModel: MainViewModel
    private var errorSnackbar: Snackbar? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Log.d("MVVM", "onActivityCreated")
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)


        viewModel.loadingVisibility.observe(this, Observer {
            it?.let {
                progressBar.visibility = it
                Log.d("MVVM", "PROGRESS_BAR = $it")
            }
        })

        viewModel.errorMessage.observe(this, Observer { errorMessage ->
            errorMessage?.let {
                activity?.let {fragmentActivity ->
                    errorSnackbar = Snackbar.make(fragmentActivity.findViewById(R.id.progressBar), it, Snackbar.LENGTH_INDEFINITE)
                    errorSnackbar?.setAction(R.string.retry, viewModel.errorClickListener)
                    errorSnackbar?.show()
                }
            }
        })

        viewModel.listTanArrets.observe(this, Observer {listeDesArrets ->
            listeDesArrets?.forEach {
                Log.d("MVVM","Arret ${it.codeLieu}")
            }
        })

        arguments?.let {
            it.getString(ARGUMENT_1)?.apply {
                message.text = this
            }
        }
    }

}
