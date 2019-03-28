package com.exmp.mvvm.view

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.exmp.mvvm.R
import com.exmp.mvvm.contract.MenuContract
import com.exmp.mvvm.databinding.MenuItemBinding
import com.exmp.mvvm.model.MenuService
import com.exmp.mvvm.util.Util
import com.exmp.mvvm.viewmodel.MenuItemViewModel
import com.google.gson.Gson

class MenuAdapter(mContext: Context, mContract: MenuContract) : RecyclerView.Adapter<MenuHolder>() {
    val mContext = mContext
    val mContract = mContract
    var items: MutableList<MenuService.Data.Menu> = mutableListOf()

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MenuHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuHolder {
        val bb =
            DataBindingUtil.inflate<MenuItemBinding>(LayoutInflater.from(mContext), R.layout.menu_item, parent, false)
        bb.model = MenuItemViewModel(mContext as MenuContract)
        return MenuHolder(bb.root, bb.model)
    }

    fun addItems(items: MutableList<MenuService.Data.Menu>) {
        val beforeIdx = this.items.size-1
        this.items.addAll(items)
        notifyItemRangeInserted(beforeIdx, itemCount-1)
    }

    fun addItem(item: MenuService.Data.Menu) {
        this.items.add(item)
        notifyItemInserted(itemCount-1)
    }

    fun deleteMenu(seqNo: String) {
        for (item in items) {
            if (seqNo == item.menuSeqNo) {
                val idx = items.indexOf(item)
                items.remove(item)
                notifyItemRemoved(idx)
                break
            }
        }
    }

    fun setDummyData() {
        val rawData = Util.raw2string(mContext, R.raw.dummydata)
        val data = Gson().fromJson<MenuService.Data>(rawData, MenuService.Data::class.java)
        data.menuList?.let {
            addItems(it)
        }
    }
}

class MenuHolder(itemView: View, model: MenuItemViewModel?) : RecyclerView.ViewHolder(itemView) {
    val model = model

    fun bind(item: MenuService.Data.Menu) {
        model?.bindItem(item)
    }

}

