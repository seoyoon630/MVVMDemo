package com.exmp.mvvm.viewmodel

import android.databinding.ObservableField
import com.exmp.mvvm.contract.NoteDetailContract
import com.exmp.mvvm.model.NoteService
import com.exmp.mvvm.model.Notes

class NoteDetailViewModel(var contract: NoteDetailContract) {
    var title = ObservableField<String>()
    var content = ObservableField<String>()
    var buttonText = ObservableField<String>()

    fun getNote(seqNo: Int): NoteService.Data.Note? {
        return Notes.getNote(seqNo)
    }

    fun onCancel(){
        contract.onCancel()
    }

    fun onConfirm() {
        contract.onConfirm()
    }
}