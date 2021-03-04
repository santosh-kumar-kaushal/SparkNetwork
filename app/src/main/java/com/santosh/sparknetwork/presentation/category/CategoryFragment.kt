package com.santosh.sparknetwork.presentation.category

import android.os.Bundle
import android.view.View
import com.santosh.sparknetwork.R
import com.santosh.sparknetwork.presentation.base.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel

class CategoryFragment : BaseFragment(R.layout.fragment_category) {

    private val categoryViewModel: CategoryViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        categoryViewModel.fetchPersonalityTestQuestionList()
    }

}