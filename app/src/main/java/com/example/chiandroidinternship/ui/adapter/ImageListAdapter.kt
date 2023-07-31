package com.example.chiandroidinternship.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chiandroidinternship.data.model.Shibe
import com.example.chiandroidinternship.databinding.ShibeItemBinding

class ImageListAdapter(
    private var shibesList: List<Shibe>,
    private val favouriteClickListener: (Int) -> Unit
) : RecyclerView.Adapter<ImageListAdapter.ImageViewHolder>() {

    class ImageViewHolder(
        view: ShibeItemBinding
    ) : RecyclerView.ViewHolder(view.root) {
        val imageView: ImageView = view.imageView
        val starButton: ImageButton = view.starButton
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ImageViewHolder {
        val binding =
            ShibeItemBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val shibe = shibesList[position]
        loadImageWithGlide(shibesList[position].url, holder.imageView)

        if (shibe.isFavourite) {
            holder.starButton.setImageResource(android.R.drawable.btn_star_big_on)
        } else {
            holder.starButton.setImageResource(android.R.drawable.btn_star_big_off)
        }

        holder.starButton.setOnClickListener {
            shibe.isFavourite = !shibe.isFavourite
            favouriteClickListener.invoke(position)
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int {
        return shibesList.size
    }

    private fun loadImageWithGlide(imageUrl: String, imageView: ImageView) {
        Glide.with(imageView.context)
            .load(imageUrl)
            .centerCrop()
            .into(imageView)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateShibesList(newShibes: List<Shibe>) {
        shibesList = newShibes
        notifyDataSetChanged()
    }
}