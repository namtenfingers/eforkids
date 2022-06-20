package com.example.eforkids


interface OnActionCallback {
    fun showFragment(tag: Class<*>, screenTag: Class<*>, data: Any? = null, isBack: Boolean = false)
    fun backToPrevious()
}