package com.example.androidlesson15.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidlesson15.databinding.ItemTodoBinding
import com.example.androidlesson15.models.Todo

class TodoAdapter : RecyclerView.Adapter<TodoAdapter.UserViewHolder>() {

    private val List = arrayListOf<Todo>()

    lateinit var onClickItem: (String) -> Unit

    inner class UserViewHolder(val itemTodoBinding: ItemTodoBinding) :
        RecyclerView.ViewHolder(itemTodoBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return List.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        val item = List[position]

        holder.itemTodoBinding.todo =item

        /*  Picasso.get()
              .load(item.image)
              .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
              .into(holder.itemRecipeBinding.imageView)

         */



        /* val slideAnimation = AnimationUtils.loadAnimation(holder.itemRecipeBinding.root.context, com.google.android.material.R.anim.m3_side_sheet_enter_from_left)
         holder.itemRecipeBinding.textView.startAnimation(slideAnimation)


         */

        holder.itemTodoBinding.homeMaterialCardView.setOnClickListener {

            onClickItem.invoke(item.id.toString())
        }



    }

    fun updateList(newList: List<Todo>) {
        List.clear()
        List.addAll(newList)
        notifyDataSetChanged()
    }
}