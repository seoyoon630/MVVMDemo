package com.exmp.mvvm.viewmodel

import android.databinding.ObservableField
import com.exmp.mvvm.contract.NoteContract
import com.exmp.mvvm.model.Note
import java.util.*

class NoteItemViewModel : Observable() {
    var noteName = ObservableField<String>()
    var noteSeq = ObservableField<Int>()

    fun detailNote(){
//        contract.detailNote(noteSeq.get())
    }

    fun bindItem(item: Note.Data.Note) {
        noteName.set(item.title)
        noteSeq.set(item.seqNo)
    }


}