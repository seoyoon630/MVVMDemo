package com.exmp.mvvm.viewmodel

import android.databinding.ObservableField
import com.exmp.mvvm.model.NoteDao

class NoteItemViewModel : BaseObservable() {
    var noteName = ObservableField<String>()
    var noteSeq = ObservableField<Int>()

    fun bindItem(item: NoteDao.Note) {
        noteName.set(item.title)
        noteSeq.set(item.seqNo)
    }


}