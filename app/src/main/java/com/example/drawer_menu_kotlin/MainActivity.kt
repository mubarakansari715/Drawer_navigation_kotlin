package com.example.drawer_menu_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var homeFragment: HomeFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val drawerToggle: ActionBarDrawerToggle = object : ActionBarDrawerToggle(
            this,
            DrawerLayout,
            toolBar,
            R.string.drawer_open,
            R.string.drawer_close
        ) {
            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
                setTitle(R.string.drawer_open)
            }

            override fun onDrawerClosed(drawerView: View) {
                super.onDrawerClosed(drawerView)
                setTitle(R.string.app_name)
            }
        }
        homeFragment = HomeFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fragment, homeFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit()
        drawerToggle.isDrawerIndicatorEnabled = true
        DrawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
        ///nav

        nav_view.setNavigationItemSelectedListener { item: MenuItem ->
            when (item.itemId) {
                R.id.nav_home -> {
                    loadHome(HomeFragment())
                    true
                }
                R.id.nav_setting -> {
                    loadSetting(SettingFragment())
                    true
                }
                R.id.nav_share -> {
                    loadShare(ShareFragment())
                    true
                }
                else -> super.onOptionsItemSelected(item)

            }
            DrawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }

    fun loadHome(fm: HomeFragment) {
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment, fm)
        ft.commit()
    }

    fun loadSetting(fm: SettingFragment) {
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment, fm)
        ft.commit()
    }

    fun loadShare(fm: ShareFragment) {
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment, fm)
        ft.commit()
    }
}