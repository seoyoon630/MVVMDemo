package com.exmp.mvvm.viewmodel

import android.databinding.ObservableInt
import android.view.View
import com.exmp.mvvm.model.Note
import com.exmp.mvvm.util.EE
import java.util.*

class NoteMainViewModel : Observable() {
    var showList = ObservableInt(View.INVISIBLE)
    var showInfo = ObservableInt(View.VISIBLE)

    private var noteList: MutableList<Note.Data.Note> = mutableListOf()

    fun loadList() {
        val note = Note()
        noteList = note.getNoteList()
        setListVisibility()
        notify(EE.SHOW_LIST)
    }

    fun getList(): MutableList<Note.Data.Note> {
        return noteList
    }

    fun moveToAddNote() {
        notify(EE.MOVE_TO_ADD_NOTE)
    }

    private fun notify(e: EE) {
        setChanged()
        notifyObservers(e)
    }

    private fun setListVisibility() {
        if (noteList.size == 0) {
            showInfo.set(View.VISIBLE)
            showList.set(View.INVISIBLE)
        } else {
            showInfo.set(View.INVISIBLE)
            showList.set(View.VISIBLE)
        }
    }

}