package com.exmp.mvvm.view

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import android.view.WindowManager
import com.exmp.mvvm.NoteID
import com.exmp.mvvm.R
import com.exmp.mvvm.contract.NoteDetailContract
import com.exmp.mvvm.databinding.NoteDetailBinding
import com.exmp.mvvm.model.NoteService
import com.exmp.mvvm.model.Notes
import com.exmp.mvvm.viewmodel.NoteDetailViewModel

class NoteDetailActivity : AppCompatActivity(), NoteDetailContract {

    private lateinit var bb: NoteDetailBinding
    private val INVALID_SEQNO = -1
    private var seqNo: Int = INVALID_SEQNO

    interface EXTRA {
        companion object {
            const val seqNo = "seqNo"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bb = DataBindingUtil.setContentView(this, R.layout.note_detail)
        bb.model = NoteDetailViewModel(this as NoteDetailContract)

        init()
    }

    // extra로 고유번호가 넘어왔으면 노트 상세보기 화면
    // 안 넘어왔으면 노트 추가하기 화면
    private fun init() {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        seqNo = intent.getIntExtra(EXTRA.seqNo, INVALID_SEQNO)
        bb.model?.let { model ->
            if (seqNo != INVALID_SEQNO) {
                val note = model.getNote(seqNo)
                note?.let {
                    model.title.set(it.title)
                    model.content.set(it.content)
                    model.buttonText.set("수정")
                }
            } else {
                model.buttonText.set("추가")
                bb.delete.visibility = View.GONE
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
                if (seqNo != INVALID_SEQNO) {
                    Notes.updateNote(NoteService.Data.Note(seqNo, title, content))
                } else {
                    Notes.addNote(NoteService.Data.Note(NoteID.getID(), title, content))
                }
                setResult(Activity.RESULT_OK)
                finish()
            }
        }
    }

    override fun back() {
        onBackPressed()
    }

    override fun onDelete() {
        AlertDialog.Builder(this)
            .setView(R.layout.note_dialog)
            .setPositiveButton("삭제") { _, _ ->
                run {
                    Notes.deleteNote(seqNo)
                    setResult(Activity.RESULT_OK)
                    finish()
                }
            }.show()
    }

    override fun onCancel() {
        setResult(Activity.RESULT_CANCELED)
        finish()
    }

}
