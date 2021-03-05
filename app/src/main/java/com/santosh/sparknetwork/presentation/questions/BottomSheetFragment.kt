package com.santosh.sparknetwork.presentation.questions

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.santosh.sparknetwork.R
import com.santosh.sparknetwork.domain.model.IfPositive

private const val STEP=1
class BottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var rootView: View

    private lateinit var ifPositive: IfPositive

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        rootView = inflater.inflate(R.layout.bottom_sheet_dialog_fragment, container, false)
        ifPositive= arguments?.getParcelable(IF_POSITIVE)!!
        return rootView
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.specialQuestion).text = ifPositive.question
        val seekBar = view.findViewById<SeekBar>(R.id.seekbar)
        val tView = view.findViewById<TextView>(R.id.progressHere)
        view.findViewById<Button>(R.id.submit).setOnClickListener { dismiss() }
        val max = ifPositive.questionType.range.to
        val min = ifPositive.questionType.range.from
        tView.text = "$min ${getString(R.string.years)}"
        seekBar.max = (max - min) / STEP
        seekBar.setOnSeekBarChangeListener(
            object : OnSeekBarChangeListener {
                var value = 0
                override fun onStopTrackingTouch(seekBar: SeekBar) {}
                override fun onStartTrackingTouch(seekBar: SeekBar) {}
                override fun onProgressChanged(
                    seekBar: SeekBar, progress: Int,
                    fromUser: Boolean
                ) {
                    value = min + progress * STEP
                    tView.text = "$value ${getString(R.string.years)}"
                }
            }
        )
    }
}
