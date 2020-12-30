package com.example.gdaxcoroutineexample.screen.main.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gdaxcoroutineexample.R
import com.example.gdaxcoroutineexample.screen.main.MainActivity
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment : Fragment() {

    @Inject
    lateinit var viewModel: HomeViewModel
    private lateinit var homeFragmentSubComponent: HomeFragmentSubComponent

    override fun onAttach(context: Context) {
        super.onAttach(context)
        initSubComponent()
        homeFragmentSubComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnDisconnect.setOnClickListener {
            Log.d("TAG", "disconnect clicked")
            viewModel.disconnect()
        }
        viewModel.btcUsdValue.observe(viewLifecycleOwner) { onBtcUsdUpdate(it) }
    }

    private fun onBtcUsdUpdate(value: String) {
        if(loadingWrapper.visibility == View.VISIBLE) {
            loadingWrapper.visibility = View.GONE
            quotationFiledWrapper.visibility = View.VISIBLE
        }

        val separator = "-------"
        val oldData = tvQuotationFiled.text.toString()
        tvQuotationFiled.text = "$oldData \n$separator \n$value"
    }

    private fun initSubComponent() {
        homeFragmentSubComponent = (activity as? MainActivity)
                ?.mainActivitySubComponent
                ?.homeFragmentSubComponent()
                ?.create(this)
                ?: throw Exception("HomeFragment can only be placed in MainActivity")
    }
}