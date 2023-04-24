package com.buzz_ht.myportfolio.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.buzz_ht.myportfolio.Models.CustomClass
import com.buzz_ht.myportfolio.R

class AppListRecyclerViewAdapter(listOfItems: List<CustomClass>) :
    RecyclerView.Adapter<AppListRecyclerViewAdapter.ViewHolder>() {

    private var listOfItems: List<CustomClass>

    init {
        this.listOfItems = listOfItems
        var count = 0
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var txtAppTitle: TextView = view.findViewById(R.id.txtAppTitle)
        var txtGithubLink: TextView = view.findViewById(R.id.txtGithubLink)
        var txtAppUseCase: TextView = view.findViewById(R.id.txtAppUseCase)
        var txtComplexity: TextView = view.findViewById(R.id.txtComplexity)
        var txtTechnologiesUsed: TextView = view.findViewById(R.id.txtTechnologiesUsed)
        var txtAppArchitecture: TextView = view.findViewById(R.id.txtAppArchitecture)
        var btnExpansion: Button = view.findViewById(R.id.btnExpansion)
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



        holder.btnExpansion.setOnClickListener {

            holder.expansionLayout.visibility = View.VISIBLE

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
                    "App complexity used: " + listOfItems[position].complexity
            }
            if (listOfItems[position].technologiesUsed != "") {
                holder.txtTechnologiesUsed.text =
                    "Technologies used: " + listOfItems[position].technologiesUsed
            }
        }
    }

    override fun getItemCount(): Int {
        return listOfItems.size
    }


}