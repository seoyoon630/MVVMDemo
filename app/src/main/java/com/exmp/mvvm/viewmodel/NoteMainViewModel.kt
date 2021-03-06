package com.exmp.mvvm.viewmodel

import android.databinding.ObservableInt
import android.view.View
import com.exmp.mvvm.model.NoteData
import com.exmp.mvvm.model.NoteDao

import com.exmp.mvvm.util.EE

class NoteMainViewModel : BaseObservable() {
    var showList = ObservableInt(View.INVISIBLE)
    var showInfo = ObservableInt(View.VISIBLE)

    private var noteModelList: MutableList<NoteData.Note> = mutableListOf()

    fun loadList() {
        val note = NoteDao()
        noteModelList = note.getNoteList()
        setListVisibility()
        notify(EE.SHOW_LIST)
    }

    fun getList(): MutableList<NoteData.Note> {
        return noteModelList
    }

    fun moveToAddNote() {
        notify(EE.MOVE_TO_ADD_NOTE)
    }

    private fun setListVisibility() {
        if (noteModelList.size == 0) {
            showInfo.set(View.VISIBLE)
            showList.set(View.INVISIBLE)
        } else {
            showInfo.set(View.INVISIBLE)
            showList.set(View.VISIBLE)
        }
    }

}