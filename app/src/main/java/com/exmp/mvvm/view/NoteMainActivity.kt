package com.exmp.mvvm.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.exmp.mvvm.R
import com.exmp.mvvm.contract.NoteContract
import com.exmp.mvvm.databinding.NoteMainBinding
import com.exmp.mvvm.viewmodel.NoteMainViewModel

class NoteMainActivity : AppCompatActivity(), NoteContract {

    private lateinit var adapter: NoteAdapter
    private lateinit var bb: NoteMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bb = DataBindingUtil.setContentView(this, R.layout.note_main)
        bb.model = NoteMainViewModel(this)

        init()
    }

    private fun init() {
        adapter = NoteAdapter(this as Context)
        bb.list.adapter = adapter
        showListOrInfo()
    }

    // 노트 추가
    override fun addNote() {
        startActivityForResult(Intent(this, NoteDetailActivity::class.java), 1000)
    }

    // 노트 상세화면 보기
    override fun detailNote(seqNo: Int?) {
        seqNo?.let {
            val i = Intent(this, NoteDetailActivity::class.java)
            i.putExtra(NoteDetailActivity.EXTRA.seqNo, it)
            startActivityForResult(i, 1001)
        }
    }

    // 노트 추가 후 리스트 반영
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 1000 || requestCode == 1001) {
                adapter.updateItems()
                showListOrInfo()
            }
        }
    }

    // 노트 개수에 따라 리스트 혹은 안내 문구 출력
    // 노트가 한 개도 없으면 안내 문구 보여줌
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
