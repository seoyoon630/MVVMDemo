package com.exmp.mvvm.view

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.exmp.mvvm.R
import com.exmp.mvvm.contract.NoteContract
import com.exmp.mvvm.databinding.NoteItemBinding
import com.exmp.mvvm.model.NoteService
import com.exmp.mvvm.util.Util
import com.exmp.mvvm.viewmodel.NoteItemViewModel
import com.google.gson.Gson

class NoteAdapter(private val mContext: Context, private val mContract: NoteContract) : RecyclerView.Adapter<Holder>() {
    var lastSeq = 0
    var items: MutableList<NoteService.Data.Note> = mutableListOf()

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
        bb.model = NoteItemViewModel(mContext as NoteContract)
        return Holder(bb.root, bb.model)
    }

    fun addItems(items: MutableList<NoteService.Data.Note>) {
        lastSeq += items.size
        val beforeIdx = this.items.size - 1
        this.items.addAll(items)
        notifyItemRangeInserted(beforeIdx, itemCount - 1)
    }

    fun addItem(title: String) {
        lastSeq += 1
        val newNote = NoteService.Data.Note(lastSeq, title)
        this.items.add(newNote)
        notifyItemInserted(itemCount - 1)
    }

    fun addNextItem() {
        lastSeq += 1
        val title = "노트$lastSeq"
        val newNote = NoteService.Data.Note(lastSeq, title)
        this.items.add(newNote)
        notifyItemInserted(itemCount - 1)
    }

    fun deleteItem(seqNo: Int) {
        for (item in items) {
            if (seqNo == item.noteSeqNo) {
                val idx = items.indexOf(item)
                items.remove(item)
                notifyItemRemoved(idx)
                break
            }
        }
    }

    fun setDummyData() {
        val rawData = Util.raw2string(mContext, R.raw.dummydata)
        val data = Gson().fromJson<NoteService.Data>(rawData, NoteService.Data::class.java)
        data.noteList?.let {
            addItems(it)
        }
    }
}

class Holder(itemView: View, val model: NoteItemViewModel?) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: NoteService.Data.Note) {
        model?.bindItem(item)
    }

}