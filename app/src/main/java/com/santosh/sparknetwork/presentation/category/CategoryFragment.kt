package com.santosh.sparknetwork.presentation.category

import android.annotation.SuppressLint
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.viewpager.widget.ViewPager
import com.google.android.material.snackbar.Snackbar
import com.santosh.sparknetwork.R
import com.santosh.sparknetwork.SparkApplication
import com.santosh.sparknetwork.domain.model.Question
import com.santosh.sparknetwork.presentation.base.BaseFragment
import com.santosh.sparknetwork.presentation.gone
import com.santosh.sparknetwork.presentation.viewmodel.SharedViewModel
import com.santosh.sparknetwork.presentation.visible
import com.santosh.sparknetwork.util.*
import org.koin.android.viewmodel.ext.android.viewModel


private const val FILE_NAME = "personality_test.json"
const val QUESTION_LIST = "questionList"
const val CURRENT_POSITION = "currentPosition"

class CategoryFragment : BaseFragment(R.layout.fragment_category) {

    private lateinit var viewPager: ViewPager
    private lateinit var dotsLayout: LinearLayout
    private lateinit var categoryPagerAdapter: CategoryPagerAdapter
    private lateinit var progressBar: ProgressBar
    private var totalSize: Int = 0
    private lateinit var questionList: List<Question>
    private val sharedViewModel: SharedViewModel by viewModel()

    @SuppressLint("ClickableViewAccessibility")
    override fun setUpView() {
        viewPager = rootView.findViewById(R.id.view_pager)
        arguments?.getInt(CURRENT_POSITION)?.let {
            val current = viewPager.currentItem + it + 1
            viewPager.postDelayed({ viewPager.currentItem = current }, 1000)
        }
        viewPager.setPageTransformer(true, DepthPageTransformer())
        viewPager.setOnTouchListener { _, _ -> true }
        dotsLayout = rootView.findViewById(R.id.layoutDots)
        progressBar = rootView.findViewById(R.id.progressBar)
    }

    override fun fetchData() {
        if (SparkApplication.isNetworkAvailable()) {
            sharedViewModel.fetchSparkNetworkPersonalityTestList(FILE_NAME)
        } else {
            Snackbar.make(rootView, getString(R.string.no_internet), Snackbar.LENGTH_LONG).show()
        }
    }

    private var viewPagerPageChangeListener: ViewPager.OnPageChangeListener =
        object : ViewPager.OnPageChangeListener {
            override fun onPageSelected(position: Int) {
                bottomProgressDots(position, totalSize)
            }

            override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) {}
            override fun onPageScrollStateChanged(arg0: Int) {}
        }

    private fun bottomProgressDots(currentIndex: Int, totalSize: Int) {
        val dots = arrayOfNulls<ImageView>(totalSize)
        dotsLayout.removeAllViews()
        for (i in dots.indices) {
            dots[i] = ImageView(activity)
            val size = 15
            val params = LinearLayout.LayoutParams(ViewGroup.LayoutParams(size, size))
            params.setMargins(10, 10, 10, 10)
            dots[i]?.let {
                dots[i]?.layoutParams = params
                dots[i]?.setImageResource(R.drawable.shape_circle)
                dots[i]?.setColorFilter(
                    ContextCompat.getColor(requireContext(), R.color.grey_20),
                    PorterDuff.Mode.SRC_IN
                )
                dotsLayout.addView(dots[i])
            }
        }
        if (dots.isNotEmpty()) {
            dots[currentIndex]?.setImageResource(R.drawable.shape_circle)
            dots[currentIndex]?.setColorFilter(
                ContextCompat.getColor(requireContext(), R.color.light_green_600),
                PorterDuff.Mode.SRC_IN
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel.uiState.observe(viewLifecycleOwner, stateUI)
    }

    private val stateUI = Observer<UIState> { state ->
        state?.let {
            when (state) {
                is DefaultState -> {
                    progressBar.gone()
                }
                is LoadingState -> {
                    progressBar.visible()
                }
                is ErrorState -> {
                    progressBar.gone()
                }
                is RetrievedPostState -> {
                    questionList = state.sparkNetwork.questions
                    val thankYou = listOf(getString(R.string.thanks))
                    val categoryList = state.sparkNetwork.categories + thankYou
                    totalSize = categoryList.size
                    bottomProgressDots(0, totalSize)
                    categoryPagerAdapter = CategoryPagerAdapter(
                        requireContext(),
                        categoryList,
                        categoryImageArray,
                        ::itemClickListener
                    )
                    viewPager.adapter = categoryPagerAdapter
                    viewPager.addOnPageChangeListener(viewPagerPageChangeListener)
                    progressBar.gone()
                }
            }
        }
    }

    private fun itemClickListener(category: String, position: Int) {
        val current = viewPager.currentItem + 1
        if (current > totalSize) {
            sharedViewModel.postPersonalityTestData()
            activity?.finish()
        } else {
            val filteredQuestions = sharedViewModel.filteredQuestion(questionList, category)
            val bundle = bundleOf(QUESTION_LIST to filteredQuestions, CURRENT_POSITION to position)
            Navigation.findNavController(rootView)
                .navigate(R.id.action_categoryFragment_to_QuestionFragment, bundle)
        }

    }

}