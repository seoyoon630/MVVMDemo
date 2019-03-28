package com.exmp.mvvm.contract

interface MenuContract {

    fun showToast(message: String)

    fun addMenu()
    fun deleteMenu(seqno: String?)
}