package com.exmp.mvvm.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.exmp.mvvm.R
import com.exmp.mvvm.databinding.NoteItemBinding
import com.exmp.mvvm.model.NoteDao

import com.exmp.mvvm.viewmodel.NoteItemViewModel

class NoteAdapter(private val mContext: Context) : RecyclerView.Adapter<NoteAdapter.Holder>() {
    var mItems = mutableListOf<NoteDao.Note>()

    override fun getItemCount(): Int {
        return mItems.size
    }

    fun setItems(items: MutableList<NoteDao.Note>) {
        mItems = items
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = mItems[position]
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val bb =
            DataBindingUtil.inflate<NoteItemBinding>(LayoutInflater.from(mContext), R.layout.note_item, parent, false)
        return Holder(bb, mContext)
    }

    inner class Holder(private val noteItemBinding: NoteItemBinding, private val mContext: Context) :
        RecyclerView.ViewHolder(noteItemBinding.root) {
        var item: NoteDao.Note? = null

        fun bind(item: NoteDao.Note) {
            this.item = item
            if (noteItemBinding.model == null) {
                noteItemBinding.model = NoteItemViewModel()
            }
            noteItemBinding.model?.let { model ->
                model.bindItem(item)
                noteItemBinding.root.setOnClickListener {
                    val i = Intent(mContext, NoteDetailActivity::class.java)
                    i.putExtra(NoteDetailActivity.EXTRA.seqNo, item.seqNo)
                    (mContext as Activity).startActivityForResult(i, NoteMainActivity.UPDATE_NOTE_REQ_CODE)
                }
            }
        }
    }
}
