package com.myprojects.registrationapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.myprojects.registrationapp.pojo.Albums
import com.squareup.picasso.Picasso


class SearchRecyclerViewAdapter internal constructor(context: Context?, data: Albums) :
    RecyclerView.Adapter<SearchRecyclerViewAdapter.ViewHolder>() {
     private val mData: Albums
     private val mInflater: LayoutInflater
    private var mClickListener: ItemClickListener? = null
    // inflates the row layout from xml when needed
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.recyclerview_row_table, parent, false)
        return ViewHolder(view)
    }

    // binds the data to the TextView in each row
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = mData.results.get(position)
        val imageView = holder.logoImageView
        holder.tvAlbumName.text=data.collectionName
        holder.tvArtistName.text=data.artistName
        holder.tvSongNumber.text=data.trackCount.toString()

        Picasso.get().load(data.collectionViewUrl).into(imageView)


    }

    // total number of rows
    override fun getItemCount(): Int {
        return mData.resultCount
    }

    // stores and recycles views as they are scrolled off screen
    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener {
         var tvAlbumName: TextView
         var tvArtistName: TextView
       var tvSongNumber: TextView
         var logoImageView:ImageView
        init {
            tvAlbumName = itemView.findViewById(R.id.tvAlbumName)
            tvArtistName = itemView.findViewById(R.id.tvArtistName)
            tvSongNumber = itemView.findViewById(R.id.tvSongsNumber)
            logoImageView=itemView.findViewById(R.id.logoImageView)
            itemView.setOnClickListener(this)

        }

        override fun onClick(view: View?) {
            if (mClickListener != null) mClickListener!!.onItemClick(view, adapterPosition)
        }

    }

    // data is passed into the constructor
    init {
        mInflater = LayoutInflater.from(context)
        mData = data
    }

    fun setClickListener(itemClickListener: ItemClickListener?) {
        mClickListener = itemClickListener
    }
    interface ItemClickListener {
        fun onItemClick(view: View?, position: Int)
    }

}