package com.androidlapp.viewpagerdemo.viewPager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.androidlapp.viewpagerdemo.R

class OnboardingAdapter(private val onboardingItems:List<onbordingItem>) :
    RecyclerView.Adapter<OnboardingAdapter.OnboardingViewHolder>(){

    inner class OnboardingViewHolder(view:View) : RecyclerView.ViewHolder(view){

        private val imageOnBoarding = view.findViewById<ImageView>(R.id.imgOnBoarding)
        private val  textTitle = view.findViewById<TextView>(R.id.textTitle)
        private val  textDesc = view.findViewById<TextView>(R.id.textDesc)

        fun bind(onbordingItem: onbordingItem){
            imageOnBoarding.setImageResource(onbordingItem.onboardingImage)
            textTitle.text = onbordingItem.title
            textDesc.text = onbordingItem.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingViewHolder {
       return OnboardingViewHolder(
           LayoutInflater.from(parent.context).inflate(
               R.layout.onboarding_item_container, parent,false
           )
       )
    }

    override fun onBindViewHolder(holder: OnboardingViewHolder, position: Int) {
       holder.bind(onboardingItems[position])
    }

    override fun getItemCount(): Int {
       return onboardingItems.size
    }
}