package com.androidlapp.viewpagerdemo.viewPager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.androidlapp.viewpagerdemo.HomeActivity
import com.androidlapp.viewpagerdemo.R
import com.androidlapp.viewpagerdemo.viewPager.onbordingItem
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    private lateinit var onboardingAdapter: OnboardingAdapter
    private lateinit var indicatorsContainer: LinearLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var indicatorsContainer = findViewById<LinearLayout>(R.id.indicatorsContainer)

        setOnBordingItems()
        setupindicator()
        setCurrentIndicator(0)
    }

    private fun setOnBordingItems(){
        onboardingAdapter = OnboardingAdapter(
            listOf(
                onbordingItem(
                    onboardingImage = R.drawable.first,
                    title = "This our first Image",
                    description = "How are you guys how do you do in your life everything good"
                ),
                onbordingItem(
                    R.drawable.scond,
                    "This is second Image",
                    "How are you guys how do you do in your life everything good"
                ),
                onbordingItem(
                    R.drawable.third,
                    "This is third image",
                    "How are you guys how do you do in your life everything good"
                )
            )
        )
        val onbordingViewPager = findViewById<ViewPager2>(R.id.onBordingViewPager)
        onbordingViewPager.adapter = onboardingAdapter

        onbordingViewPager.registerOnPageChangeCallback(object :
        ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })

        (onbordingViewPager.getChildAt(0) as RecyclerView).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        findViewById<ImageView>(R.id.imageNext).setOnClickListener{
            if (onbordingViewPager.currentItem + 1 < onboardingAdapter.itemCount){
                onbordingViewPager.currentItem += 1
            }else{
                navigatetoHomeActivity()
            }
        }

        findViewById<TextView>(R.id.textSkip).setOnClickListener{
            navigatetoHomeActivity()
        }

        findViewById<MaterialButton>(R.id.buttonGetStarted).setOnClickListener {
            navigatetoHomeActivity()
        }
    }

    private fun navigatetoHomeActivity(){
        startActivity(Intent(applicationContext, HomeActivity::class.java))
        finish()
    }

    private fun setupindicator(){

        val indicators = arrayOfNulls<ImageView>(onboardingAdapter.itemCount)
        val layoutParams:LinearLayout.LayoutParams =

            LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8,0,8,0)

        for (i in indicators.indices){
            indicators[i] = ImageView(applicationContext)
            indicators[i]?.let {
                it.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
                it.layoutParams = layoutParams
                 indicatorsContainer = findViewById(R.id.indicatorsContainer)
                indicatorsContainer.addView(it)
            }

        }
    }

    private fun setCurrentIndicator(position: Int){

        val childcount = indicatorsContainer.childCount
        for (i in 0 until childcount){
            val imageView = indicatorsContainer.getChildAt(i) as ImageView

            if (i == position){
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext, R.drawable.indicator_active
                    )
                )
            }else{
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext, R.drawable.indicator_inactive
                    )
                )
            }
        }
    }
}