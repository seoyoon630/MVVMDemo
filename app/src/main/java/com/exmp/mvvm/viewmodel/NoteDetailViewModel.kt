package com.exmp.mvvm.viewmodel

import android.databinding.ObservableField
import com.exmp.mvvm.NoteID
import com.exmp.mvvm.contract.NoteDetailContract
import com.exmp.mvvm.model.Note

class NoteDetailViewModel(var contract: NoteDetailContract) {

    var title = ObservableField<String>()
    var content = ObservableField<String>()
    var buttonText = ObservableField<String>()

    fun getNote(seqNo: Int): Note.Data.Note? {
//        return Note.getNote(seqNo)
        return null
    }

    fun onCancel(){
        contract.onCancel()
    }

    fun onConfirm() {
        contract.onConfirm()
    }

    fun onDelete(){
        contract.onDelete()
    }

    fun updateNote(seqNo: Int, title: String, content: String) {
//        if (seqNo != INVALID_SEQNO) {
//
//            Note.updateNote(Note.Data.Note(seqNo, title, content))
//        } else {
//            Note.moveToAddNote(Note.Data.Note(NoteID.getID(), title, content))
//        }
    }

    companion object {
        val INVALID_SEQNO = -1
    }

}