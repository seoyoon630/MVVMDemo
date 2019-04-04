package com.exmp.mvvm.view

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.exmp.mvvm.R
import com.exmp.mvvm.databinding.NoteItemBinding
import com.exmp.mvvm.model.Note
import com.exmp.mvvm.viewmodel.NoteItemViewModel

class NoteAdapter(private val mContext: Context) : RecyclerView.Adapter<Holder>() {
    var items = mutableListOf<Note.Data.Note>()

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val bb =
            DataBindingUtil.inflate<NoteItemBinding>(LayoutInflater.from(mContext), R.layout.note_item, parent, false)
        return Holder(bb)
    }

    fun updateItems() {
//        items = Note.getNoteList()
        notifyDataSetChanged()
    }
}

class Holder(private val noteItemBinding: NoteItemBinding) : RecyclerView.ViewHolder(noteItemBinding.root) {

    fun bind(item: Note.Data.Note) {
        if(noteItemBinding.model == null){
            noteItemBinding.model = NoteItemViewModel()
        }
        noteItemBinding.model!!.bindItem(item)
        noteItemBinding.root.setOnClickListener { View.OnClickListener { noteItemBinding.model?.detailNote() } }
    }

}