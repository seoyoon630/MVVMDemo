package com.exmp.mvvm.view

import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.widget.Toast
import com.exmp.mvvm.R
import com.exmp.mvvm.contract.MenuContract
import com.exmp.mvvm.databinding.ActivityMainBinding
import com.exmp.mvvm.databinding.MenuDlgBinding
import com.exmp.mvvm.viewmodel.MenuListViewModel

class MainActivity : AppCompatActivity(), MenuContract {
    private lateinit var adapter: MenuAdapter
    private lateinit var bb: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bb = DataBindingUtil.setContentView(this, R.layout.activity_main)
        bb.model = MenuListViewModel(this)
        setupViews()
        bb.list.adapter = adapter
    }

    private fun setupViews() {
        // Recycler View
        adapter = MenuAdapter(this as Context, this as MenuContract)
        bb.list.adapter = adapter
        adapter.setDummyData()
    }

    override fun addMenu(title: String) {
        adapter.addItem(title)
    }

    override fun addNextMenu() {
        adapter.addNextItem()
    }

    override fun deleteMenu(seqNo: Int?) {
        if (seqNo == null)
            return
        adapter.deleteMenu(seqNo)
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showDialog() {
        val bb = DataBindingUtil.inflate<MenuDlgBinding>(LayoutInflater.from(this), R.layout.menu_dlg, null, false)
        AlertDialog.Builder(this)
            .setView(bb.root)
            .setPositiveButton("확인") { _, _ ->
                run {
                    if (bb.title.text.isNotEmpty()) {
                        addMenu(bb.title.text.toString())
                    } else {
                        addNextMenu()
                    }
                    showToast("확인")
                }
            }
            .setNegativeButton("취소") { _, _ -> run { showToast("취소") } }
            .show()
    }

}
