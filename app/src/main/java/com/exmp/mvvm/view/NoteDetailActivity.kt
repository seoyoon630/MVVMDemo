package com.exmp.mvvm.view

import android.app.Activity
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.widget.Toast
import com.exmp.mvvm.NoteID
import com.exmp.mvvm.R
import com.exmp.mvvm.contract.NoteDetailContract
import com.exmp.mvvm.databinding.NoteDetailBinding
import com.exmp.mvvm.model.NoteService
import com.exmp.mvvm.model.Notes
import com.exmp.mvvm.viewmodel.NoteDetailViewModel

class NoteDetailActivity : AppCompatActivity(), NoteDetailContract {

    private lateinit var bb: NoteDetailBinding
    var seqNo: Int = -1

    interface EXTRA {
        companion object {
            const val seqNo = "seqNo"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bb = DataBindingUtil.setContentView(this, R.layout.note_detail)
        bb.model = NoteDetailViewModel(this as NoteDetailContract)
        initData()
    }

    private fun initData() {
        seqNo = intent.getIntExtra(EXTRA.seqNo, -1)
        if (seqNo != -1) {
            bb.model?.let { model ->
                val note = model.getNote(seqNo)
                note?.let {
                    model.title.set(it.title)
                    model.content.set(it.content)
                }
            }
        }
    }

    override fun onConfirm() {
        val title = bb.title.text.toString()
        val content = bb.content.text.toString()
        when {
            title.isEmpty() -> Snackbar.make(bb.root, "제목을 써주세요", Snackbar.LENGTH_SHORT).show()
            content.isEmpty() -> Snackbar.make(bb.root, "내용을 써주세요", Snackbar.LENGTH_SHORT).show()
            else -> {
                if (seqNo != -1) {
                    Notes.updateNote(NoteService.Data.Note(seqNo, title, content))
                } else {
                    Notes.addNote(NoteService.Data.Note(NoteID.getID(), title, content))
                }
                setResult(Activity.RESULT_OK)
                finish()
            }
        }
    }
}
