package com.erwin.subdua.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.erwin.subdua.data.DataPengguna
import com.erwin.subdua.activity.DetailPengguna
import com.erwin.subdua.R
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.item_pengguna.view.*
import java.util.*
import kotlin.collections.ArrayList

var filterUserList = ArrayList<DataPengguna>()
lateinit var mcontext: Context

class AdapterPengguna(private var listingData: ArrayList<DataPengguna>) : RecyclerView.Adapter<AdapterPengguna.ListViewHolder>(), Filterable {
    init {
        filterUserList = listingData
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
        val pdata = filterUserList[position]
        Glide.with(holder.itemView.context)
            .load(pdata.avatar)
            .apply(RequestOptions().override(250, 250))
            .into(holder.avatarImg)
        holder.usernameTxt.text = pdata.username
        holder.nameTxt.text = pdata.name
        holder.companyTxt.text = pdata.company
        holder.locationTxt.text = pdata.location
        holder.itemView.setOnClickListener {
            val dataUser = DataPengguna(
                pdata.username,
                pdata.name,
                pdata.avatar,
                pdata.company,
                pdata.location,
                pdata.repository,
                pdata.followers,
                pdata.following
            )
            val intentDetail = Intent(mcontext, DetailPengguna::class.java)
            intentDetail.putExtra(DetailPengguna.EXTRA_DATA, dataUser)
            mcontext.startActivity(intentDetail)
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(dataUsers: DataPengguna)
    }

    override fun getItemCount(): Int = filterUserList.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var avatarImg: CircleImageView = itemView.avatar_img
        var usernameTxt: TextView = itemView.username_txt
        var nameTxt: TextView = itemView.name_txt
        var companyTxt: TextView = itemView.company_txt
        var locationTxt: TextView = itemView.location_txt
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence): FilterResults {
                val charSearch = constraint.toString()
                filterUserList = if (charSearch.isEmpty()) {
                    listingData
                } else {
                    val resultList = ArrayList<DataPengguna>()
                    for (row in filterUserList) {
                        if ((row.username.toString().toLowerCase(Locale.ROOT)
                                .contains(charSearch.toLowerCase(Locale.ROOT)))
                        ) {
                            resultList.add(
                                DataPengguna(
                                    row.username,
                                    row.name,
                                    row.avatar,
                                    row.company,
                                    row.location,
                                    row.repository,
                                    row.followers,
                                    row.following
                                )
                            )
                        }
                    }
                    resultList
                }
                val resultsFilter = FilterResults()
                resultsFilter.values = filterUserList
                return resultsFilter
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence, results: FilterResults) {
                filterUserList = results.values as ArrayList<DataPengguna>
                notifyDataSetChanged()
            }
        }
    }
}