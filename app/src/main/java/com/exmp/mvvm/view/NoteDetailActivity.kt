package com.exmp.mvvm.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.exmp.mvvm.R

class NoteDetailActivity : AppCompatActivity() {
    var seqNo : Int = -1
    interface EXTRA {
        companion object {
            const val seqNo = "seqNo"
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.note_detail)

        initData()
    }

    private fun initData() {
        seqNo = intent.getIntExtra(EXTRA.seqNo, -1)
    }
}
