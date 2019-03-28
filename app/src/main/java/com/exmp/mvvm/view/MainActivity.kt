package com.exmp.mvvm.view

import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.exmp.mvvm.R
import com.exmp.mvvm.contract.MenuContract
import com.exmp.mvvm.databinding.ActivityMainBinding
import com.exmp.mvvm.model.MenuService
import com.exmp.mvvm.viewmodel.MenuListViewModel

class MainActivity : AppCompatActivity(), MenuContract {
    private lateinit var adapter: MenuAdapter
    lateinit var bb: ActivityMainBinding

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

    override fun addMenu() {
        val nextIndex = adapter.itemCount + 1
        val seqNo = (10000 + nextIndex).toString()
        val title = "메뉴$nextIndex"
        val newMenu = MenuService.Data.Menu(seqNo, title)
        adapter.addItem(newMenu)
    }

    override fun deleteMenu(menuSeq: String?) {
        if (menuSeq == null)
            return
        adapter.deleteMenu(menuSeq)
    }


    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
