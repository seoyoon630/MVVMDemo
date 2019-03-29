package com.exmp.mvvm.viewmodel

import android.databinding.ObservableField
import com.exmp.mvvm.contract.NoteContract
import com.exmp.mvvm.model.NoteService

class NoteItemViewModel(private var contract: NoteContract) {
    var noteName = ObservableField<String>()
    var noteSeq = ObservableField<Int>()

    fun deleteNote(){
        contract.deleteNote(noteSeq.get())
    }

    fun detailNote(){
        contract.detailNote(noteSeq.get())
    }

    fun bindItem(item: NoteService.Data.Note) {
        noteName.set(item.noteTitle)
        noteSeq.set(item.noteSeqNo)
    }
}