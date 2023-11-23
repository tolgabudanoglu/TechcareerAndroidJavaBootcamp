package com.example.odev7todoapp.ui.fragment.list.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.odev7todoapp.data.entity.ToDo;
import com.example.odev7todoapp.databinding.RowLayoutBinding;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyHolder> {

    private List<ToDo> toDoList;
    private Context mcontext;

    public ListAdapter(List<ToDo> toDoList, Context mcontext) {
        this.toDoList = toDoList;
        this.mcontext = mcontext;
    }
    public class MyHolder extends RecyclerView.ViewHolder{
        private RowLayoutBinding binding;

        public MyHolder(RowLayoutBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }
    }
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowLayoutBinding binding = RowLayoutBinding.inflate(LayoutInflater.from(mcontext),parent,false);
        return new MyHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        ToDo toDo = toDoList.get(position);
        RowLayoutBinding binding = holder.binding;

        binding.tvToDo.setText(toDo.getName());

    }

    @Override
    public int getItemCount() {
        return toDoList.size();
    }
}
