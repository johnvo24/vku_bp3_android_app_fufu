package com.example.fufu.ui.main_component.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fufu.R
import com.example.fufu.ui.main_component.viewmodel.FuFuViewModel

class FuFuFragment : Fragment() {

    companion object {
        fun newInstance() = FuFuFragment()
    }

    private lateinit var viewModel: FuFuViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_fu_fu, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FuFuViewModel::class.java)
        // TODO: Use the ViewModel
    }

}