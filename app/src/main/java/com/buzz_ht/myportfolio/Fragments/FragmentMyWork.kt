package com.buzz_ht.myportfolio.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.buzz_ht.myportfolio.Adapters.AppListRecyclerViewAdapter
import com.buzz_ht.myportfolio.Models.CustomClass
import com.buzz_ht.myportfolio.R
import com.google.android.material.tabs.TabLayout

class FragmentMyWork : Fragment() {

    private var listOfApps = arrayListOf<CustomClass>()
    private lateinit var appListRecyclerview: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_fragmentmywork, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        appListRecyclerview = view.findViewById(R.id.appListRecyclerview)
        addListItems()
        setUpAdapter()
    }

    private fun setUpAdapter() {

        val adapter = AppListRecyclerViewAdapter(requireContext(), listOfApps)
        appListRecyclerview.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        appListRecyclerview.adapter = adapter
    }

    private fun addListItems() {
        listOfApps.add(
            CustomClass(
                "BMI Calculator",
                "Java",
                "MVC",
                "NA",
                "Firebase Authentication",
                "Calculate your accurate BMI",
                "1/10",
                getString(R.string.bmi_calculator_playstore)
            )
        )
        listOfApps.add(
            CustomClass(
                "PMDB",
                "Kotlin",
                "MVC",
                "NA",
                "Third Party Apis, Retrofit",
                "Find your favourite movies and shows",
                "1.5/10",
                getString(R.string.pmdb_playstore)
            )
        )

        listOfApps.add(
            CustomClass(
                "Quiz",
                "Kotlin",
                "MVC",
                "NA",
                "Shared pref, Recyclerview",
                "Quiz away",
                "2.5/10",
                getString(R.string.quiz_playstore)
            )


        )

        listOfApps.add(
            CustomClass(
                "Ecommerce",
                "Java",
                "MVC",
                "NA",
                "Shared pref, Recyclerview, Bottom Navigation view, Menu",
                "Sample E-commerce application",
                "4/10",
                getString(R.string.ecommerce_playstore)
            )
        )
    }


}