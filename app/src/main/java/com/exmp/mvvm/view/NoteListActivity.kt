package com.exmp.mvvm.view

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.widget.Toast
import com.exmp.mvvm.R
import com.exmp.mvvm.contract.NoteContract
import com.exmp.mvvm.databinding.NoteDlgBinding
import com.exmp.mvvm.databinding.NoteListBinding
import com.exmp.mvvm.viewmodel.NoteListViewModel

class NoteListActivity : AppCompatActivity(), NoteContract {

    private lateinit var adapter: NoteAdapter
    private lateinit var bb: NoteListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bb = DataBindingUtil.setContentView(this, R.layout.note_list)
        bb.model = NoteListViewModel(this)
        initData()
        initViews()
    }

    /**
     * 데이터 초기 설정
     */
    private fun initData() {
        adapter = NoteAdapter(this as Context, this as NoteContract)
        adapter.setDummyData()
    }

    /**
     * 화면 초기 설정
     */
    private fun initViews() {
        bb.list.adapter = adapter
    }

    /**
     * 메뉴 추가
     */
    override fun addNote() {
        val bb = DataBindingUtil.inflate<NoteDlgBinding>(LayoutInflater.from(this), R.layout.note_dlg, null, false)
        AlertDialog.Builder(this)
            .setView(bb.root)
            .setPositiveButton("확인") { _, _ ->
                run {
                    if (bb.title.text.isNotEmpty()) {
                        adapter.addItem(bb.title.text.toString())
                    } else {
                        adapter.addNextItem()
                    }
                    Toast.makeText(this, "확인", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("취소") { _, _ -> run { Toast.makeText(this, "취소", Toast.LENGTH_SHORT).show() } }
            .show()
    }

    /**
     * 메뉴 삭제
     */
    override fun deleteNote(seqNo: Int?) {
        seqNo?.let {
            adapter.deleteItem(it)
        }
    }

    /**
     *
     */
    override fun detailNote(seqNo: Int?) {
        seqNo?.let {
            val i = Intent(this, NoteDetailActivity::class.java)
            // todo 데이터 다 넘기기
            i.putExtra(NoteDetailActivity.EXTRA.seqNo, it)
            startActivity(i)
        }
    }

}
