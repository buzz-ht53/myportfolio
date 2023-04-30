package com.buzz_ht.myportfolio.Adapters

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.buzz_ht.myportfolio.Models.CustomClass
import com.buzz_ht.myportfolio.R

class AppListRecyclerViewAdapter(context: Context, listOfItems: List<CustomClass>) :
    RecyclerView.Adapter<AppListRecyclerViewAdapter.ViewHolder>() {

    private var listOfItems: List<CustomClass>
    private var context: Context

    init {
        this.listOfItems = listOfItems
        var count = 0
        this.context = context
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var txtAppTitle: TextView = view.findViewById(R.id.txtAppTitle)
        var txtGithubLink: TextView = view.findViewById(R.id.txtGithubLink)
        var txtAppUseCase: TextView = view.findViewById(R.id.txtAppUseCase)
        var txtComplexity: TextView = view.findViewById(R.id.txtComplexity)
        var txtTechnologiesUsed: TextView = view.findViewById(R.id.txtTechnologiesUsed)
        var txtAppArchitecture: TextView = view.findViewById(R.id.txtAppArchitecture)
        var txtAppLanguage: TextView = view.findViewById(R.id.txtAppLanguage)
        var btnExpansion: Button = view.findViewById(R.id.btnExpansion)
        var btnExpansionReverse: Button = view.findViewById(R.id.btnExpansionReverse)
        var btnGetApp: Button = view.findViewById(R.id.btnGetApp)
        var expansionLayout: CardView = view.findViewById(R.id.expansionLayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.applist_recview, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtAppTitle.text = listOfItems[position].appName

        holder.btnGetApp.setOnClickListener {
            val launchIntent =
                context.packageManager.getLaunchIntentForPackage(listOfItems[position].packageName)

            var packageName = listOfItems[position].packageName
            if (launchIntent != null) {
                context.startActivity(launchIntent)
            } else {
                try {
                    context.startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("market://details?id=$packageName")
                        )
                    )
                } catch (anfe: ActivityNotFoundException) {
                    context.startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://play.google.com/store/apps/details?id=$packageName)")
                        )
                    )
                }
            }
        }


        holder.btnExpansion.setOnClickListener {

            if (!holder.expansionLayout.isVisible) {
                holder.expansionLayout.visibility = View.VISIBLE
                holder.btnExpansionReverse.visibility = View.VISIBLE
                holder.btnExpansion.visibility = View.GONE


                if (listOfItems[position].appLanguage != "") {
                    holder.txtAppLanguage.text =
                        "Language: " + listOfItems[position].appLanguage
                }

                if (listOfItems[position].architecture != "") {
                    holder.txtAppArchitecture.text =
                        "Architecture used: " + listOfItems[position].architecture
                }
                if (listOfItems[position].githubLink != "") {
                    holder.txtGithubLink.text = "Github Link: " + listOfItems[position].githubLink
                }
                if (listOfItems[position].useCase != "") {
                    holder.txtAppUseCase.text = "UseCase: " + listOfItems[position].useCase
                }
                if (listOfItems[position].complexity != "") {
                    holder.txtComplexity.text =
                        "App complexity: " + listOfItems[position].complexity
                }
                if (listOfItems[position].technologiesUsed != "") {
                    holder.txtTechnologiesUsed.text =
                        "Technologies used: " + listOfItems[position].technologiesUsed
                }
            } else {
                holder.expansionLayout.visibility = View.GONE
            }

            holder.btnExpansionReverse.setOnClickListener {
                if (holder.expansionLayout.isVisible) {
                    holder.expansionLayout.visibility = View.GONE
                    holder.btnExpansionReverse.visibility = View.GONE
                    holder.btnExpansion.visibility = View.VISIBLE
                }
            }

        }
    }

    override fun getItemCount(): Int {
        return listOfItems.size
    }

    fun PackageManager.getPackageInfoCompat(packageName: String, flags: Int = 0): PackageInfo =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            getPackageInfo(packageName, PackageManager.PackageInfoFlags.of(flags.toLong()))
        } else {
            @Suppress("DEPRECATION") getPackageInfo(packageName, flags)
        }

}