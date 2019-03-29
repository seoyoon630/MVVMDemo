package com.exmp.mvvm.viewmodel

import com.exmp.mvvm.contract.MenuContract

class MenuListViewModel(private var contract: MenuContract) {

    fun addMenu() {
        contract.showDialog()
    }
}