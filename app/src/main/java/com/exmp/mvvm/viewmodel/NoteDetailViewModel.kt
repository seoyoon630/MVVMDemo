package com.exmp.mvvm.viewmodel

import android.databinding.ObservableField
import com.exmp.mvvm.contract.NoteDetailContract

class NoteDetailViewModel(var contract : NoteDetailContract) {
    var title = ObservableField<String>()
    var content = ObservableField<String>()

    fun getData(){
        // todo sql 전문 읽어서 title, content 내용 set

    }

    fun onConfirm(){
        contract.onConfirm(title.get(), content.get())
    }
}