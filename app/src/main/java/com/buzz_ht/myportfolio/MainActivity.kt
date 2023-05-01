package com.buzz_ht.myportfolio

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.buzz_ht.myportfolio.Adapters.AppListRecyclerViewAdapter
import com.buzz_ht.myportfolio.Fragments.FragmentAbout
import com.buzz_ht.myportfolio.Fragments.FragmentHome
import com.buzz_ht.myportfolio.Fragments.FragmentMyWork
import com.buzz_ht.myportfolio.Models.CustomClass
import com.google.android.material.tabs.TabLayout
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability


class MainActivity : AppCompatActivity() {


    private val MY_REQUEST_CODE = 99
    private lateinit var tabLayout: TabLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        drawBehindStatusBar()
        hideActionBar()

        setContentView(R.layout.activity_main)

        tabLayout = findViewById(R.id.tabLayout)

        setUpTabLayout()
        setUpFragment(FragmentHome())

    }

    private fun setUpFragment(fragment: Fragment) {
        supportFragmentManager
            // 3
            .beginTransaction()
            // 4
            .replace(R.id.mainFragment, fragment)
            // 5
            .commit()
    }

    private fun setUpTabLayout() {
        tabLayout.addTab(tabLayout.newTab().setText("Home"))
        tabLayout.addTab(tabLayout.newTab().setText("My Work"))
        tabLayout.addTab(tabLayout.newTab().setText("About Me"))


        //TabLayout Selected Listener
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {

                when (tab?.position) {

                    0 -> {
                        setUpFragment(FragmentHome())
                    }
                    1 -> {
                        setUpFragment(FragmentMyWork())
                    }
                    2 -> {
                        setUpFragment(FragmentAbout())
                    }

                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {


            }

        })
    }


    private fun checkInAppUpdate() {
        val appUpdateManager = AppUpdateManagerFactory.create(this)

        // Returns an intent object that you use to check for an update.
        val appUpdateInfoTask = appUpdateManager.appUpdateInfo

        // Checks that the platform will allow the specified type of update.
        appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                // This example applies an immediate update. To apply a flexible update
                // instead, pass in AppUpdateType.FLEXIBLE
                && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)
            ) {

                appUpdateManager.startUpdateFlowForResult(
                    // Pass the intent that is returned by 'getAppUpdateInfo()'.
                    appUpdateInfo,
                    // Or 'AppUpdateType.FLEXIBLE' for flexible updates.
                    AppUpdateType.FLEXIBLE,
                    // The current activity making the update request.
                    this,
                    // Include a request code to later monitor this update request.
                    MY_REQUEST_CODE
                )
            }
        }
    }

    /*override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == MY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                val listener = { state ->
                    if (state.installStatus() == InstallStatus.DOWNLOADED) {
                        // After the update is downloaded, show a notification
                        // and request user confirmation to restart the app.
                        popupSnackbarForCompleteUpdate()
                    }

                }
            }
        }
    }*/

    private fun drawBehindStatusBar() {
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)

        getWindow().setStatusBarColor(Color.TRANSPARENT);

    }

    private fun hideActionBar() {
        supportActionBar?.hide()
    }
}