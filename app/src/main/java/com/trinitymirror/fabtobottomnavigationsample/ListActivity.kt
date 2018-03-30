package com.trinitymirror.fabtobottomnavigationsample

import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.trinitymirror.fabtobottomnavigationsample.util.BottomNavigationViewBehavior
import kotlinx.android.synthetic.main.activity_list.*


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class ListActivity : AppCompatActivity() {

    private lateinit var fabView: FloatingActionButton
    private lateinit var navigationView: BottomNavigationView

    lateinit var anim: FabToBottomNavigationAnim

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        fabView = list_fab
        fabView.setOnClickListener(onFabClickListener)

        navigationView = list_navigation
        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val layoutParams = navigationView.layoutParams as CoordinatorLayout.LayoutParams
        layoutParams.behavior = BottomNavigationViewBehavior(object : BottomNavigationViewBehavior.Callback {
            override fun onSlideUp() {
                anim.hideNavigationView()
            }

            override fun onSlideDown() {
                anim.hideNavigationView()
            }
        })

        list.layoutManager = LinearLayoutManager(this)
        list.adapter = MyAdapter()

        anim = FabToBottomNavigationAnim(fabView, navigationView)
    }

    private fun animateFabToBottomNav() {
        anim.showNavigationView()
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////

    private val onFabClickListener: View.OnClickListener = View.OnClickListener {
        animateFabToBottomNav()
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                list_message.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                list_message.setText(R.string.title_dashboard)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                list_message.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

}
