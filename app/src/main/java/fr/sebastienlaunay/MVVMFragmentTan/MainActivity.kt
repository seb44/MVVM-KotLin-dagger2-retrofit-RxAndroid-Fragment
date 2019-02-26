package fr.sebastienlaunay.MVVMFragmentTan

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import fr.sebastienlaunay.MVVMFragmentTan.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)


        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
            //    .replace(R.id.container, MainFragment.newInstance())
                .replace(R.id.container, MainFragment.newInstance(argument1 = "coucou"))
                .commitNow()
        }
    }
}
