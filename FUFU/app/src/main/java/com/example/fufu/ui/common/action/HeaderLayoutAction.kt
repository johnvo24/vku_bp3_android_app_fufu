package com.example.fufu.ui.common.action;

import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.example.fufu.R

public class HeaderLayoutAction(activity: AppCompatActivity) {
    private val backButton: ImageButton = activity.findViewById(R.id.btn_back)

    init {
        backButton.setOnClickListener {
            val fragmentManager = activity.supportFragmentManager
            fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            activity.onBackPressed()
        }
    }
}