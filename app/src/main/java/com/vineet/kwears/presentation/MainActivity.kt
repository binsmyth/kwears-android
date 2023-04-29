package com.vineet.kwears.presentation

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.*
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import androidx.room.withTransaction
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.badge.BadgeUtils
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.vineet.kwears.R
import com.vineet.kwears.data.database.AppDatabase
import com.vineet.kwears.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class   MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_dashboard,
                R.id.navigation_notifications,
                R.id.navigation_product,
                R.id.navigation_productitem,
                R.id.navigation_cart
            )
        )
        //This manually replaces default action bar with material appbar
        setSupportActionBar(binding.topAppBar)
        addMenuProvider(MenuProvider(binding,this, Activity()))
        binding.topAppBar.setOnMenuItemClickListener{menuItem->
            when (menuItem.itemId){
                R.id.cart ->{
                    val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
                    val navController = navHostFragment.navController
                    navController.navigate(R.id.navigation_cart)
                    true
                }
                else -> {
                    false
                }
            }
        }
        //Setting app to be full screen and let the upper bar to be same color as the appbar
        WindowCompat.setDecorFitsSystemWindows(window,false)
        //This is to set navigation for top bar from product item to product
        setupActionBarWithNavController(navController, appBarConfiguration)

        navView.setupWithNavController(navController)
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        val database=AppDatabase.getDatabase(binding.root.context)
        lifecycleScope.launch{
            database.withTransaction {
                database.addCartProductDao().clearCart()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }
}

private class MenuProvider(activityMainBinding: ActivityMainBinding, lifecycleOwner: LifecycleOwner, a:Activity):androidx.core.view.MenuProvider{

    val activityMainBinding  = activityMainBinding
    val lifecycleOwner = lifecycleOwner
    val a = a
    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.top_app_bar, menu)
    }

    @SuppressLint("UnsafeOptInUsageError")
    override fun onPrepareMenu(menu: Menu) {
        super.onPrepareMenu(menu)
        val badgeDrawable = BadgeDrawable.create(activityMainBinding.topAppBar.context)
        val database = AppDatabase.getDatabase(activityMainBinding.root.context).addCartProductDao().getCartProductCount()
        database.observe(lifecycleOwner, Observer {
            badgeDrawable.number = it
        })

        badgeDrawable.horizontalOffset=130
        badgeDrawable.verticalOffset=50
        BadgeUtils.attachBadgeDrawable(badgeDrawable,activityMainBinding.topAppBar.findViewById(R.id.topAppBar))
    }
    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return true
    }
}