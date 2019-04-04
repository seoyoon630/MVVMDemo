package com.exmp.mvvm.model

import android.databinding.ObservableArrayList
import android.util.Log
import com.exmp.mvvm.util.PP
import com.google.gson.Gson

class Note {
    var data: Data? = null

    class Data {
        var noteList: ObservableArrayList<Note> = ObservableArrayList()

        class Note(val seqNo: Int?, val title: String?, val content: String?) {

            override fun toString(): String {
                return "$seqNo, $title, $content"
            }
        }
    }

    fun getNoteList(): ObservableArrayList<Data.Note> {
        if (data == null) {
            val rawData = PP.NOTE.getString("")
            val temp = Gson().fromJson<Data>(rawData, Data::class.java)
            data = temp ?: Data()
        }
        return data!!.noteList
    }

    fun getNote(seqNo: Int): Data.Note? {
        data?.let {
            for (note in it.noteList) {
                if (seqNo == note.seqNo) {
                    return note
                }
            }
        }
        return null
    }

    fun addNote(note: Data.Note) {
        data?.let {
            it.noteList.add(note)
            PP.LAST_SEQNO.set(note.seqNo!!)
            updatePreference()
        }
    }

    fun updateNote(note: Data.Note) {
        data?.let {
            for ((index, orgNote) in it.noteList.withIndex()) {
                if (orgNote.seqNo == note.seqNo) {
                    it.noteList[index] = note
                }
            }
            updatePreference()
        }
    }

    fun deleteNote(seqNo: Int) {
        data?.let {
            for (note in it.noteList) {
                if (note.seqNo == seqNo) {
                    deleteNote(note)
                    break
                }
            }
        }
    }

    private fun deleteNote(note: Data.Note) {
        data?.let {
            it.noteList.remove(note)
            updatePreference()
        }
    }

    fun updatePreference() {
        data?.let {
            val json = Gson().toJson(it)
            Log.i("updatePreference", json)
            PP.NOTE.set(json)
        }
    }
    
    
}