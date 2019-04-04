package com.exmp.mvvm.viewmodel

import android.databinding.ObservableField
import com.exmp.mvvm.NoteID
import com.exmp.mvvm.model.NoteDao
import com.exmp.mvvm.model.NoteModel

import com.exmp.mvvm.util.EE

class NoteDetailViewModel : BaseObservable() {

    companion object {
        const val INVALID_SEQ_NO = -1
    }

    var title = ObservableField<String>()
    var content = ObservableField<String>()
    var buttonText = ObservableField<String>()

    fun onCancelClick() {
        notify(EE.ON_CANCEL)
    }

    fun onConfirmClick() {
        notify(EE.CONFIRM_NOTE)
    }

    fun onDeleteClick() {
        notify(EE.DELETE_NOTE)
    }

    fun getNote(seqNo: Int): NoteDao.Note? {
        val note = NoteModel()
        return note.getNote(seqNo)
    }

    fun confirmNote(seqNo: Int, title: String, content: String) {
        val note = NoteModel()
        if (seqNo != INVALID_SEQ_NO) {
            note.updateNote(NoteDao.Note(seqNo, title, content))
        } else {
            note.addNote(NoteDao.Note(NoteID.getID(), title, content))
        }
    }

    fun deleteNote(seqNo: Int) {
        val note = NoteModel()
        note.deleteNote(seqNo)
    }

}