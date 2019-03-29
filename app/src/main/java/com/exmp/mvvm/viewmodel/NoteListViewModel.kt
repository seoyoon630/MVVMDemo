package com.exmp.mvvm.viewmodel

import com.exmp.mvvm.contract.NoteContract

class NoteListViewModel(private var contract: NoteContract) {

    fun addNote() {
        contract.addNote()
    }
}