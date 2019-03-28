package com.exmp.mvvm.viewmodel

import android.view.View
import com.exmp.mvvm.contract.MenuContract

class MenuListViewModel(var contract: MenuContract) {

    fun addMenu() {
        contract.showToast("메뉴 추가")
        contract.addMenu()
    }

    fun addMenu(itemView: View) {
        contract.showToast("메뉴 추가2")
        contract.addMenu()
    }
}