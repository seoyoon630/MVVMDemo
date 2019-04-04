package com.exmp.mvvm.model

class NoteData {
    var noteList: MutableList<Note> = mutableListOf()

    class Note(val seqNo: Int?, val title: String?, val content: String?) {

        override fun toString(): String {
            return "$seqNo, $title, $content"
        }
    }
}

