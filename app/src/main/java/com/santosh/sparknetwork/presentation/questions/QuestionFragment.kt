package com.santosh.sparknetwork.presentation.questions

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.viewpager.widget.ViewPager
import com.santosh.sparknetwork.R
import com.santosh.sparknetwork.domain.model.IfPositive
import com.santosh.sparknetwork.domain.model.PersonalityTestData
import com.santosh.sparknetwork.domain.model.Question
import com.santosh.sparknetwork.presentation.base.BaseFragment
import com.santosh.sparknetwork.presentation.category.CURRENT_POSITION
import com.santosh.sparknetwork.presentation.category.QUESTION_LIST
import com.santosh.sparknetwork.presentation.viewmodel.SharedViewModel
import com.santosh.sparknetwork.util.DepthPageTransformer
import org.koin.android.viewmodel.ext.android.viewModel

const val IF_POSITIVE = "ifPositive"
class QuestionFragment : BaseFragment(R.layout.fragment_questions) {

    private val sharedViewModel: SharedViewModel by viewModel()

    private lateinit var viewPager: ViewPager
    private lateinit var questionPagerAdapter: QuestionPagerAdapter
    private lateinit var questionList: List<Question>
    private var currentCategoryPosition=0

    @SuppressLint("ClickableViewAccessibility")
    override fun setUpView() {
        viewPager = rootView.findViewById(R.id.view_pager)
        viewPager.setOnTouchListener { _, _ -> true }
        viewPager.setPageTransformer(true, DepthPageTransformer())
    }

    override fun fetchData() {
        //No operation.
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        questionList=arguments?.getParcelableArrayList(QUESTION_LIST)!!
        arguments?.getInt(CURRENT_POSITION)?.let { currentCategoryPosition=it }
        questionPagerAdapter = QuestionPagerAdapter(
            requireContext(),
            questionList,
            ::itemClickListener,
            ::showBottomSheetListener)
        viewPager.adapter = questionPagerAdapter
    }

    private fun itemClickListener(personalityTestData: PersonalityTestData) {
        sharedViewModel.storeData(personalityTestData)
        val current = viewPager.currentItem + 1
        if (current < questionList.size) {
            viewPager.currentItem = current
        } else {
            val bundle = bundleOf(CURRENT_POSITION to currentCategoryPosition)
            Navigation.findNavController(rootView).navigate(R.id.action_QuestionFragment_to_categoryFragment,bundle)
        }
    }

    private fun showBottomSheetListener(ifPositive: IfPositive) {
        val bundle = bundleOf(IF_POSITIVE to ifPositive)
        Navigation.findNavController(rootView).navigate(R.id.bottomSheet, bundle)
    }

}

