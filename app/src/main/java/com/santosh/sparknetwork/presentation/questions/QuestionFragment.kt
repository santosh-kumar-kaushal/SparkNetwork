package com.santosh.sparknetwork.presentation.questions

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import androidx.viewpager.widget.ViewPager
import com.santosh.sparknetwork.R
import com.santosh.sparknetwork.domain.model.PersonalityTestData
import com.santosh.sparknetwork.domain.model.Question
import com.santosh.sparknetwork.presentation.base.BaseFragment
import com.santosh.sparknetwork.presentation.category.QUESTION_LIST
import com.santosh.sparknetwork.presentation.viewmodel.SharedViewModel
import com.santosh.sparknetwork.util.DepthPageTransformer
import org.koin.android.viewmodel.ext.android.viewModel

class QuestionFragment : BaseFragment(R.layout.fragment_questions) {

    private val sharedViewModel: SharedViewModel by viewModel()

    private lateinit var viewPager: ViewPager
    private lateinit var questionPagerAdapter: QuestionPagerAdapter
    private lateinit var questionList: List<Question>

    override fun setUpView() {
        viewPager = rootView.findViewById(R.id.view_pager)
        viewPager.setPageTransformer(true, DepthPageTransformer())
    }

    override fun fetchData() {
        //No operation.
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        questionList= arguments?.getParcelableArrayList(QUESTION_LIST)!!
        questionPagerAdapter = QuestionPagerAdapter(
            requireContext(),
            questionList,
            ::itemClickListener
        )
        viewPager.adapter = questionPagerAdapter
    }

    private fun itemClickListener(personalityTestData: PersonalityTestData) {
        sharedViewModel.storeData(personalityTestData)
        val current = viewPager.currentItem + 1
        if (current < questionList.size) {
            viewPager.currentItem = current
        } else {
            Navigation.findNavController(rootView).navigate(R.id.action_QuestionFragment_to_categoryFragment)
        }
    }

}