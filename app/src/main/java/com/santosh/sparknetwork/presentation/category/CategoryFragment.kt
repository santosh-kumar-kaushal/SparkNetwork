package com.santosh.sparknetwork.presentation.category

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import com.santosh.sparknetwork.R
import com.santosh.sparknetwork.presentation.base.BaseFragment
import com.santosh.sparknetwork.presentation.viewmodel.SharedViewModel
import com.santosh.sparknetwork.util.*
import org.koin.android.viewmodel.ext.android.viewModel

private const val FILE_NAME="personality_test.json"
class CategoryFragment : BaseFragment(R.layout.fragment_category) {

    private val sharedViewModel: SharedViewModel by viewModel()

    private lateinit var textView:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedViewModel.fetchCategoryList(FILE_NAME)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textView=rootView.findViewById(R.id.text)
        sharedViewModel.uiState.observe(viewLifecycleOwner, stateUI)
    }

    private val stateUI = Observer<UIState> { state ->
        state?.let {
            when (state) {
                is DefaultState -> {
                    textView.text = "DefaultState"
                }
                is LoadingState -> {
                    textView.text = "LoadingState"
                }
                is ErrorState -> {
                    textView.text = "ErrorState"
                }
                is RetrievedPostState -> {
                    textView.text = "RetrievedPostState"
                }

            }
        }
    }

}