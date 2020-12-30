package com.example.gdaxcoroutineexample.screen.main.start

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gdaxcoroutineexample.R
import com.example.gdaxcoroutineexample.screen.main.MainActivity
import com.neovisionaries.ws.client.WebSocketState
import kotlinx.android.synthetic.main.fragment_start.*
import javax.inject.Inject

class StartFragment : Fragment() {

    @Inject
    lateinit var viewModel: StartViewModel

    private lateinit var startFragmentSubComponent: StartFragmentSubComponent

    override fun onAttach(context: Context) {
        super.onAttach(context)
        initSubComponent()
        startFragmentSubComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnConnect.setOnClickListener { viewModel.connect() }
        viewModel.connectionState.observe(viewLifecycleOwner) { onConnectionStateChange(it) }
    }


    private fun onConnectionStateChange(webSocketState: WebSocketState) {
        if(webSocketState == WebSocketState.CONNECTING) {
            btnConnect.visibility = View.GONE
            pbLoading.visibility = View.VISIBLE }
        else {
            pbLoading.visibility = View.GONE
            btnConnect.visibility = View.VISIBLE
        }
    }

    private fun initSubComponent() {
        startFragmentSubComponent = (activity as? MainActivity)
            ?.mainActivitySubComponent
            ?.startFragmentSubComponent()
            ?.create(this)
            ?: throw Exception("StartFragment can only be placed in MainActivity")
    }
}