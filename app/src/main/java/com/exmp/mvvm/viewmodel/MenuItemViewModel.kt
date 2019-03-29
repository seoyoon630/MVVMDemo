package com.exmp.mvvm.viewmodel

import android.databinding.ObservableField
import com.exmp.mvvm.contract.MenuContract
import com.exmp.mvvm.model.MenuService

class MenuItemViewModel(private var contract: MenuContract) {
    private var menuName = ObservableField<String>()
    private var menuSeq = ObservableField<Int>()

    fun deleteMenu(){
        contract.deleteMenu(menuSeq.get())
        contract.showToast("지우기")
    }

    fun bindItem(item: MenuService.Data.Menu) {
        menuName.set(item.menuTitle)
        menuSeq.set(item.menuSeqNo)
    }
}