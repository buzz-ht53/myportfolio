package com.buzz_ht.myportfolio.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.buzz_ht.myportfolio.Models.CustomClass
import com.buzz_ht.myportfolio.R

class AppListRecyclerViewAdapter(listOfItems: List<CustomClass>) :
    RecyclerView.Adapter<AppListRecyclerViewAdapter.ViewHolder>() {

    private var listOfItems: List<CustomClass>

    init {
        this.listOfItems = listOfItems
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var txtAppTitle: TextView = view.findViewById(R.id.txtAppTitle)
        var btnExpansion: Button = view.findViewById(R.id.btnExpansion)
        var btnGetApp: Button = view.findViewById(R.id.btnGetApp)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.applist_recview, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtAppTitle.text = listOfItems[position].appName
    }

    override fun getItemCount(): Int {
        return listOfItems.size
    }


}