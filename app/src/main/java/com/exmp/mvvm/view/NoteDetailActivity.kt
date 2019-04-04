package com.exmp.mvvm.view

import android.app.Activity
import android.app.AlertDialog
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import android.view.WindowManager
import com.exmp.mvvm.R
import com.exmp.mvvm.databinding.NoteDetailBinding
import com.exmp.mvvm.util.EE
import com.exmp.mvvm.viewmodel.NoteDetailViewModel
import java.util.*

class NoteDetailActivity : AppCompatActivity(), Observer {

    interface EXTRA {
        companion object {
            const val seqNo = "seqNo"
        }
    }

    private lateinit var binding: NoteDetailBinding
    private lateinit var viewModel: NoteDetailViewModel
    private var seqNo: Int = NoteDetailViewModel.INVALID_SEQ_NO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        initDataBinding()
        init()
    }

    private fun initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.note_detail)
        viewModel = NoteDetailViewModel()
        viewModel.addObserver(this)
        binding.model = viewModel
    }

    // extra로 고유번호가 넘어왔으면 노트 상세보기 화면
    // 안 넘어왔으면 노트 추가하기 화면
    private fun init() {
        seqNo = intent.getIntExtra(EXTRA.seqNo, NoteDetailViewModel.INVALID_SEQ_NO)
        binding.model?.let { model ->
            if (seqNo != NoteDetailViewModel.INVALID_SEQ_NO) {
                val note = model.getNote(seqNo)
                note?.let {
                    model.title.set(it.title)
                    model.content.set(it.content)
                    model.buttonText.set("수정")
                }
            } else {
                model.buttonText.set("추가")
                binding.delete.visibility = View.GONE
            }
        }
    }

    override fun update(o: Observable?, arg: Any?) {
        if (o is NoteDetailViewModel) {
            when {
                EE.CONFIRM_NOTE == (arg as EE) -> {
                    val title = binding.title.text.toString()
                    val content = binding.content.text.toString()
                    when {
                        title.trim().isEmpty() -> Snackbar.make(binding.root, "제목을 써주세요", Snackbar.LENGTH_SHORT).show()
                        content.trim().isEmpty() -> Snackbar.make(binding.root, "내용을 써주세요", Snackbar.LENGTH_SHORT).show()
                        else -> {
                            viewModel.confirmNote(seqNo, title, content)
                            setResult(Activity.RESULT_OK)
                            finish()
                        }
                    }
                }
                EE.DELETE_NOTE == arg -> {
                    AlertDialog.Builder(this)
                        .setView(R.layout.note_dialog)
                        .setPositiveButton("삭제") { _, _ ->
                            run {
                                viewModel.deleteNote(seqNo)
                                setResult(Activity.RESULT_OK)
                                finish()
                            }
                        }.show()
                }
                EE.ON_CANCEL == arg -> {
                    onBackPressed()
                }
            }
        }
    }

}
