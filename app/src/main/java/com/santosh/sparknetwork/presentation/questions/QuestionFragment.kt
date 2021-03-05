package com.santosh.sparknetwork.presentation.questions

import android.os.Bundle
import android.view.View
import com.santosh.sparknetwork.R
import com.santosh.sparknetwork.presentation.base.BaseFragment
import com.santosh.sparknetwork.presentation.viewmodel.SharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class QuestionFragment : BaseFragment(R.layout.fragment_questions) {

    private val sharedViewModel: SharedViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}