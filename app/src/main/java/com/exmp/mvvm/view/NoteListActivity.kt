package com.exmp.mvvm.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.exmp.mvvm.R
import com.exmp.mvvm.contract.NoteContract
import com.exmp.mvvm.databinding.NoteDlgBinding
import com.exmp.mvvm.databinding.NoteListBinding
import com.exmp.mvvm.model.Notes
import com.exmp.mvvm.viewmodel.NoteListViewModel

class NoteListActivity : AppCompatActivity(), NoteContract {

    private lateinit var adapter: NoteAdapter
    private lateinit var bb: NoteListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bb = DataBindingUtil.setContentView(this, R.layout.note_list)
        bb.model = NoteListViewModel(this)

        init()
    }

    private fun init() {
        adapter = NoteAdapter(this as Context, this as NoteContract)
        bb.list.adapter = adapter
        showListOrInfo()
    }

    /**
     * 노트 추가
     */
    override fun addNote() {
        startActivityForResult(Intent(this, NoteDetailActivity::class.java), 1000)
    }

    /**
     * 노트 삭제
     */
    override fun deleteNote(seqNo: Int?) {
        seqNo?.let {
            adapter.deleteItem(it)
            showListOrInfo()
        }
    }

    /**
     * 노트 상세
     */
    override fun detailNote(seqNo: Int?) {
        seqNo?.let {
            val i = Intent(this, NoteDetailActivity::class.java)
            i.putExtra(NoteDetailActivity.EXTRA.seqNo, it)
            startActivityForResult(i, 1001)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 1000 || requestCode == 1001) {
                adapter.updateItems()
                showListOrInfo()
            }
        }
    }

    private fun showListOrInfo() {
        bb.model?.let { model ->
            if (adapter.itemCount == 0) {
                model.showInfo.set(View.VISIBLE)
                model.showList.set(View.INVISIBLE)
            } else {
                model.showInfo.set(View.INVISIBLE)
                model.showList.set(View.VISIBLE)
            }
        }
    }

}
