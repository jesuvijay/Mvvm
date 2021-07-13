package com.jesu.dagger_hilt.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.jesu.dagger_hilt.R
import com.jesu.dagger_hilt.models.Blog
import com.jesu.dagger_hilt.viewmodels.MainViewModels

class NoteRecyclerAdapter(val viewModel: MainViewModels, val arrayList: ArrayList<Blog>) : RecyclerView.Adapter<NoteRecyclerAdapter.NotesViewHolder>() {

    inner class NotesViewHolder(private val binding: View) : RecyclerView.ViewHolder(binding) {
        val textView: TextView
        val imageView: ImageView

        init {
            // Define click listener for the ViewHolder's View.
            textView = binding.findViewById(R.id.title)
            imageView = binding.findViewById(R.id.delete)

        }
        fun bind(blog: Blog) {
            textView.text = blog.title
            imageView.setOnClickListener {
                viewModel.remove(blog)
                notifyItemRemoved(arrayList.indexOf(blog))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        var root = LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        return NotesViewHolder(root)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.bind(arrayList[position])
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
}