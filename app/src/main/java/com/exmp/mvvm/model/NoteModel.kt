package com.exmp.mvvm.model

import android.util.Log
import com.exmp.mvvm.util.PP
import com.google.gson.Gson

class NoteModel {
    private val data by lazy {
        val rawData = PP.NOTE.getString("")
        val temp = Gson().fromJson<NoteDao>(rawData, NoteDao::class.java)
        temp ?: NoteDao()
    }

    fun getNoteList(): MutableList<NoteDao.Note> {
        return data.noteList
    }

    fun getNote(seqNo: Int): NoteDao.Note? {
        for (note in data.noteList) {
            if (seqNo == note.seqNo) {
                return note
            }
        }
        return null
    }

    fun addNote(note: NoteDao.Note) {
        data.noteList.add(note)
        PP.LAST_SEQNO.set(note.seqNo!!)
        updatePreference()
    }

    fun updateNote(note: NoteDao.Note) {
        for ((index, orgNote) in data.noteList.withIndex()) {
            if (orgNote.seqNo == note.seqNo) {
                data.noteList[index] = note
            }
        }
        updatePreference()
    }

    fun deleteNote(seqNo: Int) {
        for (note in data.noteList) {
            if (note.seqNo == seqNo) {
                deleteNote(note)
                break
            }
        }
    }

    private fun deleteNote(note: NoteDao.Note) {
        data.noteList.remove(note)
        updatePreference()
    }

    private fun updatePreference() {
        val json = Gson().toJson(data)
        Log.i("updatePreference", json)
        PP.NOTE.set(json)
    }


}