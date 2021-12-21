package com.myprojects.registrationapp

import android.content.Context
import android.icu.number.NumberFormatter.with
import android.icu.number.NumberRangeFormatter.with
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.myprojects.registrationapp.pojo.Albums
import com.squareup.picasso.Picasso

class SearchRecyclerViewAdapter internal constructor(context: Context?, data: Albums) :
    RecyclerView.Adapter<SearchRecyclerViewAdapter.ViewHolder>() {
     val mData: Albums
     val mInflater: LayoutInflater

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

        Picasso.get().load(data.collectionViewUrl).placeholder(R.drawable.placeholder).into(imageView)


    }

    // total number of rows
    override fun getItemCount(): Int {
        return mData.resultCount
    }

    // stores and recycles views as they are scrolled off screen
    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
         var tvAlbumName: TextView
         var tvArtistName: TextView
       var tvSongNumber: TextView
         var logoImageView:ImageView
        init {
            tvAlbumName = itemView.findViewById(R.id.tvAlbumName)
            tvArtistName = itemView.findViewById(R.id.tvArtistName)
            tvSongNumber = itemView.findViewById(R.id.tvSongsNumber)
            logoImageView=itemView.findViewById(R.id.logoImageView)

        }
    }

    // data is passed into the constructor
    init {
        mInflater = LayoutInflater.from(context)
        mData = data
    }
}