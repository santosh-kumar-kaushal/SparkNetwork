package com.santosh.sparknetwork.presentation.questions

import android.content.Context
import android.os.Build
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.viewpager.widget.PagerAdapter
import com.santosh.sparknetwork.R
import com.santosh.sparknetwork.domain.model.IfPositive
import com.santosh.sparknetwork.domain.model.PersonalityTestData
import com.santosh.sparknetwork.domain.model.Question
import com.santosh.sparknetwork.domain.model.QuestionType

class QuestionPagerAdapter(
    private val context: Context, private val questionList: List<Question>,
    private val itemClickListener: (PersonalityTestData) -> Unit,
    private val showBottomSheetListener: (IfPositive) -> Unit
) : PagerAdapter() {
    private lateinit var layoutInflater: LayoutInflater
    private lateinit var radioGroup: RadioGroup
    private lateinit var btnNext: Button
    private var personalityTestData: PersonalityTestData? = null

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        personalityTestData = null
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = layoutInflater.inflate(R.layout.item_question_card, container, false)
        view.findViewById<TextView>(R.id.question).text = questionList[position].question
        btnNext = view.findViewById(R.id.nextButton)
        radioGroup = view.findViewById(R.id.radioGroup)
        addRadioButtonOptions(
            view,
            questionList[position].questionType,
            questionList[position].question,
            questionList[position].category
        )
        btnNext.setOnClickListener {
            personalityTestData?.let { data ->
                itemClickListener.invoke(data)
            } ?: run { Toast.makeText(context, context.getString(R.string.please_select), Toast.LENGTH_SHORT).show()}
        }
        container.addView(view)
        return view
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private fun addRadioButtonOptions(
        view: View,
        questionType: QuestionType, question: String, category: String
    ) {
        for (i in questionType.options.indices) {
            val radioButton = RadioButton(context)
            val params = RadioGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(15, 15, 15, 15)
            radioButton.layoutParams = params
            radioButton.setPadding(15, 30, 15, 30)
            radioButton.gravity = Gravity.CENTER
            radioButton.setBackgroundResource(R.drawable.radio_background)
            radioButton.setButtonDrawable(R.drawable.null_selector)
            radioButton.id = View.generateViewId()
            radioButton.text = questionType.options[i]
            radioGroup.addView(radioButton)
        }
        radioGroup.setOnCheckedChangeListener { group, _ ->
            val radioButtonId = group.checkedRadioButtonId
            val selectedOptions = view.findViewById<RadioButton>(radioButtonId)
            personalityTestData = PersonalityTestData(question, category, selectedOptions.text.toString())
            questionType.condition?.let {
                it.predicate?.let { it ->
                    if (it.exactEquals.contains(selectedOptions.text.toString())) {
                        questionType.condition?.ifPositive?.let { ifPositive ->
                            showBottomSheetListener.invoke(ifPositive)
                        }
                    }
                }
            }

        }
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object`


    override fun getCount(): Int = questionList.size

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val view = `object` as View
        container.removeView(view)
    }
}