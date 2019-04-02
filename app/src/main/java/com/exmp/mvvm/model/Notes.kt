package com.exmp.mvvm.model

import android.databinding.ObservableArrayList
import android.util.Log
import com.exmp.mvvm.util.PP
import com.google.gson.Gson

object Notes {
    var data: NoteService.Data? = null

    fun getNoteList(): ObservableArrayList<NoteService.Data.Note> {
        if (data == null) {
            val rawData = PP.NOTE.getString("")
            val temp = Gson().fromJson<NoteService.Data>(rawData, NoteService.Data::class.java)
            data = temp ?: NoteService.Data()
        }
        return data!!.noteList
    }

    fun getNote(seqNo: Int): NoteService.Data.Note? {
        data?.let {
            for (note in it.noteList) {
                if (seqNo == note.seqNo) {
                    return note
                }
            }
        }
        return null
    }

    fun addNote(note: NoteService.Data.Note) {
        data?.let {
            it.noteList.add(note)
            PP.LAST_SEQNO.set(note.seqNo!!)
            updatePreference()
        }
    }

    fun updateNote(note: NoteService.Data.Note) {
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

    private fun deleteNote(note: NoteService.Data.Note) {
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