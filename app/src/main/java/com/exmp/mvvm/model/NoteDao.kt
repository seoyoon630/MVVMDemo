package com.exmp.mvvm.model

import android.util.Log
import com.exmp.mvvm.util.FileUtil
import com.exmp.mvvm.util.PP
import com.google.gson.Gson

class NoteDao {
    private val data by lazy {
        val rawData = FileUtil.readFile(FileUtil.getNoteJsonFile())
        val temp = Gson().fromJson<NoteData>(rawData, NoteData::class.java)
        temp ?: NoteData()
    }

    fun getNoteList(): MutableList<NoteData.Note> {
        return data.noteList
    }

    fun getNote(seqNo: Int): NoteData.Note? {
        for (note in data.noteList) {
            if (seqNo == note.seqNo) {
                return note
            }
        }
        return null
    }

    fun addNote(note: NoteData.Note) {
        data.noteList.add(note)
        PP.LAST_SEQNO.set(note.seqNo!!)
        updateFile()
    }

    fun updateNote(note: NoteData.Note) {
        for ((index, orgNote) in data.noteList.withIndex()) {
            if (orgNote.seqNo == note.seqNo) {
                data.noteList[index] = note
            }
        }
        updateFile()
    }

    fun deleteNote(seqNo: Int) {
        for (note in data.noteList) {
            if (note.seqNo == seqNo) {
                deleteNote(note)
                break
            }
        }
    }

    private fun deleteNote(note: NoteData.Note) {
        data.noteList.remove(note)
        updateFile()
    }

    private fun updateFile() {
        val json = Gson().toJson(data)
        Log.i("updateFile", json)
        FileUtil.writeFile(FileUtil.getNoteJsonFile(), json)
    }


}