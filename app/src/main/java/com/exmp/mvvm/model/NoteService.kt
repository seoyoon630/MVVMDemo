package com.exmp.mvvm.model

import android.databinding.ObservableArrayList

interface NoteService {

    class Data {
        var noteList: ObservableArrayList<Note> = ObservableArrayList<Note>()

        class Note(val seqNo: Int?, val title: String?, val content: String?) {

            override fun toString(): String {
                return "$seqNo, $title, $content"
            }
        }
    }
}
