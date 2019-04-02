package com.exmp.mvvm.viewmodel

import android.databinding.ObservableInt
import android.view.View
import com.exmp.mvvm.contract.NoteContract

class NoteMainViewModel(private var contract: NoteContract) {

    var showList = ObservableInt(View.INVISIBLE)
    var showInfo = ObservableInt(View.VISIBLE)

    fun addNote() {
        contract.addNote()
    }
}