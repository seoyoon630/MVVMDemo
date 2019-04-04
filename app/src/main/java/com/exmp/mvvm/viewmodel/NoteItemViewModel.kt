package com.exmp.mvvm.viewmodel

import android.databinding.ObservableField
import com.exmp.mvvm.model.NoteData

class NoteItemViewModel : BaseObservable() {
    var noteName = ObservableField<String>()
    var noteSeq = ObservableField<Int>()

    fun bindItem(item: NoteData.Note) {
        noteName.set(item.title)
        noteSeq.set(item.seqNo)
    }


}