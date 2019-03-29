package com.exmp.mvvm.model

interface NoteService {

    class Data {
        var noteList: MutableList<Note>? = null

        class Note(val noteSeqNo: Int?, val noteTitle: String?) {

            override fun toString(): String {
                return "$noteSeqNo,$noteTitle"
            }
        }
    }
}
