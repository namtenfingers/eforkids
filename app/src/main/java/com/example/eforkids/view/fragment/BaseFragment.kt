package com.example.eforkids.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.eforkids.OnActionCallback
import com.example.eforkids.R

abstract class BaseFragment<VM: ViewBinding> : Fragment(), View.OnClickListener {
    protected lateinit var mContext: Context
    protected lateinit var binding: VM
    lateinit var callback: OnActionCallback
    var mData: Any? = null

    override fun onAttach(context: Context) {
        mContext = context
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = initViewBinding(container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    fun setStatusBarColor(color: Int): Int{
        val window: Window = requireActivity().window
        window.statusBarColor = ContextCompat.getColor(mContext, color)
        return color
    }

    override fun onClick(v: View?) {
        v?.startAnimation(AnimationUtils.loadAnimation(mContext, androidx.appcompat.R.anim.abc_fade_in))
        clickView(v)
    }

    private fun clickView(v: View?) {
        //do something
    }

    fun notify(message: String){
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
    }



    abstract fun initViews()

    abstract fun initViewBinding(container: ViewGroup?): VM
}