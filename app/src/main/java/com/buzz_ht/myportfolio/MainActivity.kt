package com.buzz_ht.myportfolio

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    lateinit var btnBMICalculator : Button
    val appPackageName ="com.buzz_ht.bigbmi"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnBMICalculator = findViewById(R.id.btnBMICalculator)

        btnBMICalculator.setOnClickListener{

//            val launchIntent = packageManager.getLaunchIntentForPackage("com.google.android.youtube")
            val launchIntent=  packageManager.getLaunchIntentForPackage(appPackageName)

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






    }
}