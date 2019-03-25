package io.github.nikkoes.belajarkotlin.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import io.github.nikkoes.belajarkotlin.R
import io.github.nikkoes.belajarkotlin.model.Penghuni
import kotlinx.android.synthetic.main.item_penghuni.view.*

class PenghuniAdapter(private val context: Context) : RecyclerView.Adapter<PenghuniAdapter.Holder>() {

    private var listItem: ArrayList<Penghuni> = ArrayList()
    var mOnItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(position: Int)
        fun onDelete(position: Int)
    }

    fun setOnItemClickListener(mItemClickListener: OnItemClickListener) {
        this.mOnItemClickListener = mItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PenghuniAdapter.Holder {
        return Holder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_penghuni, parent, false
            )
        )
    }

    override fun getItemCount(): Int = listItem.size

    override fun onBindViewHolder(holder: PenghuniAdapter.Holder, position: Int) {
        val penghuni: Penghuni? = listItem[position]

        holder.view.nama.text = penghuni?.nama
        holder.view.status.text = penghuni?.status
        holder.view.hp.text = penghuni?.hp
        holder.view.gender.text = penghuni?.gender

        holder.view.btn_edit.setOnClickListener {
            mOnItemClickListener?.onItemClick(position)
        }
        holder.view.btn_delete.setOnClickListener {
            mOnItemClickListener?.onDelete(position)
        }
    }

    class Holder(val view: View) : RecyclerView.ViewHolder(view)

    fun add(item: Penghuni) {
        listItem.add(item)
        notifyItemInserted(listItem.size + 1)
    }

    fun addAll(listItem: List<Penghuni>) {
        for (item in listItem) {
            add(item)
        }
    }

    fun removeAll() {
        listItem.clear()
        notifyDataSetChanged()
    }

    fun swap(datas: List<Penghuni>) {
        if (listItem.size >= 0)
            listItem.clear()
        listItem.addAll(datas)
        notifyDataSetChanged()
    }

    fun getItem(pos: Int): Penghuni {
        return listItem[pos]
    }

    fun setFilter(list: List<Penghuni>) {
        listItem = ArrayList<Penghuni>()
        listItem.addAll(list)
        notifyDataSetChanged()
    }

    fun getListItem(): List<Penghuni> {
        return listItem
    }

}