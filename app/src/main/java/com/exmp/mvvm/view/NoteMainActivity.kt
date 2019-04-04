package com.exmp.mvvm.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.exmp.mvvm.R
import com.exmp.mvvm.databinding.NoteMainBinding
import com.exmp.mvvm.util.EE
import com.exmp.mvvm.viewmodel.NoteMainViewModel
import java.util.*

class NoteMainActivity : AppCompatActivity(), Observer {

    companion object {
        const val ADD_NOTE_REQ_CODE = 1000
        const val UPDATE_NOTE_REQ_CODE = 1001
    }

    private lateinit var binding: NoteMainBinding
    private lateinit var viewModel: NoteMainViewModel

    private lateinit var adapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDataBinding()
        setupAdapter()
    }

    private fun initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.note_main)
        viewModel = NoteMainViewModel()
        viewModel.addObserver(this)
        binding.model = viewModel
    }

    private fun setupAdapter() {
        adapter = NoteAdapter(this as Context)
        binding.list.adapter = adapter
        viewModel.loadList()
    }

    override fun update(o: Observable?, arg: Any?) {
        if (o is NoteMainViewModel) {
            when {
                EE.SHOW_LIST == (arg as EE) -> {
                    adapter.setItems(viewModel.getList())
                }
                EE.MOVE_TO_ADD_NOTE == arg -> {
                    startActivityForResult(Intent(this, NoteDetailActivity::class.java), ADD_NOTE_REQ_CODE)
                }
            }
        }
    }

    // 노트 추가 후 리스트 반영
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == ADD_NOTE_REQ_CODE || requestCode == UPDATE_NOTE_REQ_CODE) {
                viewModel.loadList()
            }
        }
    }
}
