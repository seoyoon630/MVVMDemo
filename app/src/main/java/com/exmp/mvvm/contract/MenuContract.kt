package com.exmp.mvvm.contract

interface MenuContract {

    fun showToast(message: String)
    fun showDialog()

    fun addMenu(title: String)
    fun addNextMenu()
    fun deleteMenu(seqNo: Int?)
}