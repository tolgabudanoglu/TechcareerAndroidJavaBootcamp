package com.example.spotifycloneodev6.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spotifycloneodev6.R;
import com.example.spotifycloneodev6.data.entity.Menu;
import com.example.spotifycloneodev6.databinding.ToolbarDesignBinding;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ToolbarMenuHolder> {

    private List<Menu> menuList;
    private Context mContext;

    public MenuAdapter(List<Menu> menuList, Context mContext) {
        this.menuList = menuList;
        this.mContext = mContext;
    }



    public class ToolbarMenuHolder extends RecyclerView.ViewHolder{
        private ToolbarDesignBinding binding;

        public ToolbarMenuHolder( ToolbarDesignBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @NonNull
    @Override
    public ToolbarMenuHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ToolbarDesignBinding binding = ToolbarDesignBinding.inflate(LayoutInflater.from(mContext),parent,false);
        return new ToolbarMenuHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ToolbarMenuHolder holder, int position) {

        Menu menu = menuList.get(position);
        ToolbarDesignBinding binding = holder.binding;

        binding.textViewMenu.setText(menu.getMenuText());

        binding.cardViewLine.setOnClickListener(v -> {
            binding.cardViewLine.setCardBackgroundColor(ContextCompat.getColor(mContext,R.color.green));
            binding.textViewMenu.setTextColor(ContextCompat.getColor(mContext, R.color.black));
        });

    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }
}
