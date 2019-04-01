package com.exmp.mvvm.view

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.exmp.mvvm.R
import com.exmp.mvvm.contract.NoteDetailContract
import com.exmp.mvvm.databinding.NoteDetailBinding
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
//        bb.model.getData(seqNo)
        }
    }

    override fun onConfirm(title: String?, content: String?) {
        // sql에 저장하는 부분
        Toast.makeText(this, "저장해야합니다", Toast.LENGTH_SHORT).show()
        finish()
    }
}
