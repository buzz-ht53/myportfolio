package com.buzz_ht.myportfolio

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.buzz_ht.myportfolio.Adapters.AppListRecyclerViewAdapter
import com.buzz_ht.myportfolio.Models.CustomClass


class MainActivity : AppCompatActivity() {

    lateinit var btnBMICalculator: Button
    lateinit var appListRecyclerview: RecyclerView
    val appPackageName = "com.buzz_ht.bigbmi"
    private var listOfApps = arrayListOf<CustomClass>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        drawBehindStatusBar()
        hideActionBar()
        setContentView(R.layout.activity_main)

        btnBMICalculator = findViewById(R.id.btnBMICalculator)
        appListRecyclerview = findViewById(R.id.appListRecyclerview)

        btnBMICalculator.setOnClickListener {

//            val launchIntent = packageManager.getLaunchIntentForPackage("com.google.android.youtube")
            val launchIntent = packageManager.getLaunchIntentForPackage(appPackageName)

            if (launchIntent != null) {
                startActivity(launchIntent)
            } else {
                try {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("market://details?id=$appPackageName")
                        )
                    )
                } catch (anfe: ActivityNotFoundException) {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")
                        )
                    )
                }
            }
        }


        listOfApps.add(
            CustomClass(
                "BMI Calculator",
                "Firebase Authentication",
                "MVC",
                "NA",
                "Calculate your accurate BMI"
            )
        )
        listOfApps.add(
            CustomClass(
                "PMDB",
                "Third Party Apis, Retrofit",
                "MVC",
                "NA",
                "Find your favourite movies and shwos"
            )
        )

        val adapter = AppListRecyclerViewAdapter(listOfApps)
        appListRecyclerview.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        appListRecyclerview.adapter = adapter

    }

    private fun drawBehindStatusBar() {
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)

        getWindow().setStatusBarColor(Color.BLACK);

    }

    private fun hideActionBar() {
        supportActionBar?.hide()
    }
}