package com.erwin.subdua.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.erwin.subdua.data.DataPengguna
import com.erwin.subdua.R
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.item_pengguna.view.*

var filterFollowingList = ArrayList<DataPengguna>()

class AdapterFollowing(listingUser: ArrayList<DataPengguna>) : RecyclerView.Adapter<AdapterFollowing.ListViewHolder>() {
    init {
        filterFollowingList = listingUser
    }

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_pengguna, viewGroup, false)
        val sch = ListViewHolder(view)
        mcontext = viewGroup.context
        return sch
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val pdata = filterFollowingList[position]
        Glide.with(holder.itemView.context)
            .load(pdata.avatar)
            .apply(RequestOptions().override(250, 250))
            .into(holder.avatarImg)
        holder.usernameTxt.text = pdata.username
        holder.nameTxt.text = pdata.name
        holder.companyTxt.text = pdata.company
        holder.locationTxt.text = pdata.location
        holder.itemView.setOnClickListener {
            //DO NOTHING
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(UserData: DataPengguna)
    }

    override fun getItemCount(): Int = filterFollowingList.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var avatarImg: CircleImageView = itemView.avatar_img
        var usernameTxt: TextView = itemView.username_txt
        var nameTxt: TextView = itemView.name_txt
        var companyTxt: TextView = itemView.company_txt
        var locationTxt: TextView = itemView.location_txt
    }

}