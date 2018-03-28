package com.trinitymirror.fabtobottomnavigationsample

import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_sample.*


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class SampleActivity : AppCompatActivity() {

    private lateinit var fabView: FloatingActionButton
    private lateinit var navigationView: BottomNavigationView

    lateinit var anim: FabToBottomNavigationAnim

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample)

        fabView = fab//createFab()
        fabView.setOnClickListener(onFabClickListener)

        navigationView = navigation//createBottomView()
        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        button.setOnClickListener(onButtonClickListener)

        anim = FabToBottomNavigationAnim(fabView, navigationView)
    }

    private fun animateFabToBottomNav() {
        anim.showNavigationView()
    }

    private fun animateBottomNavToFab() {
        anim.hideNavigationView()
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    private val onButtonClickListener: View.OnClickListener = View.OnClickListener {
        animateBottomNavToFab()
    }

    private val onFabClickListener: View.OnClickListener = View.OnClickListener {
        animateFabToBottomNav()
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                message.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                message.setText(R.string.title_dashboard)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                message.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

}
