package com.exmp.mvvm.viewmodel

import android.databinding.ObservableField
import android.view.View
import com.exmp.mvvm.contract.MenuContract
import com.exmp.mvvm.model.MenuService

class MenuItemViewModel(var contract: MenuContract) {
    var menuName = ObservableField<String>()
    var menuSeq = ObservableField<String>()

    fun deleteMenu(itemView : View){
        contract.deleteMenu(menuSeq.get())
        contract.showToast("지우기")
    }

    fun bindItem(item: MenuService.Data.Menu) {
        menuName.set(item.menuTitle)
        menuSeq.set(item.menuSeqNo)
    }
}