package com.exmp.mvvm.contract

interface NoteContract {

    fun addNote()

    fun deleteNote(seqNo: Int?)
    fun detailNote(seqNo: Int?)
}