package com.santosh.sparknetwork.presentation.category

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.santosh.sparknetwork.R

class CategoryPagerAdapter(private val context: Context, private val categoryList: List<String>,
                           private val imageArray:IntArray, private val imageBackGroundArray:IntArray,
                           private val itemClickListener: (String, Int) -> Unit) : PagerAdapter() {
    private lateinit var layoutInflater: LayoutInflater
    private lateinit var btnNext: Button

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = layoutInflater.inflate(R.layout.item_card_wizard, container, false)
        view.findViewById<TextView>(R.id.title).text = categoryList[position]
        view.findViewById<ImageView>(R.id.image).setImageResource(
            imageArray[position]
        )
        view.findViewById<ImageView>(R.id.image_bg).setImageResource(
            imageBackGroundArray[position]
        )
        btnNext = view.findViewById(R.id.nextButton)
        if (position == categoryList.size - 1) {
            btnNext.text = context.getString(R.string.done)
        } else {
            btnNext.text = context.getString(R.string.answer)
        }
        btnNext.setOnClickListener {
            itemClickListener.invoke(categoryList[position],position)
        }
        container.addView(view)
        return view
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean=view==`object`


    override fun getCount(): Int = categoryList.size

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val view = `object` as View
        container.removeView(view)
    }
}