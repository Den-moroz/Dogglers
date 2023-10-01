package com.example.dogglers.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dogglers.R
import com.example.dogglers.const.Layout
import com.example.dogglers.data.DataSource
import com.example.dogglers.model.Dog

class DogCardAdapter(
    private val context: Context?,
    private val layout: Int
): RecyclerView.Adapter<DogCardAdapter.DogCardViewHolder>() {
    private val dogs: List<Dog> = DataSource.dogs

    class DogCardViewHolder(view: View?): RecyclerView.ViewHolder(view!!) {
        val dogImageView: ImageView = view!!.findViewById(R.id.item_image)
        val nameTextView: TextView = view!!.findViewById(R.id.item_name)
        val ageTextView: TextView = view!!.findViewById(R.id.item_age)
        val hobbiesTextView: TextView = view!!.findViewById(R.id.item_hobbies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogCardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View

        return when (layout) {
            Layout.GRID -> {
                view = inflater.inflate(R.layout.grid_list_item, parent, false)
                DogCardViewHolder(view)
            }
            Layout.VERTICAL, Layout.HORIZONTAL -> {
                view = inflater.inflate(R.layout.vertical_horizontal_list_item, parent, false)
                DogCardViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid layout type")
        }
    }

    override fun getItemCount(): Int {
        return dogs.size
    }

    override fun onBindViewHolder(holder: DogCardViewHolder, position: Int) {
        val item = dogs[position]
        val resources = context?.resources

        holder.dogImageView.setImageResource(item.imageResourceId)
        holder.nameTextView.text = item.name
        holder.ageTextView.text = context?.getString(R.string.dog_age, item.age)
        holder.hobbiesTextView.text = context?.getString(R.string.dog_hobbies, item.hobbies)
    }
}
