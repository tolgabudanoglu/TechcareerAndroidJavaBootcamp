package com.example.lezzetkapida.ui.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lezzetkapida.data.entity.Food;
import com.example.lezzetkapida.databinding.HomeRowLayoutBinding;
import com.example.lezzetkapida.ui.viewModel.HomeViewModel;
import com.example.lezzetkapida.utils.ImageLoaderHelper;
import com.example.lezzetkapida.utils.Listeners;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.FoodViewHolder> {

    private Context mcontext;
    private HomeViewModel viewModel;

    private List<Food> foodList;

    public HomeAdapter( List<Food> foodList,Context mcontext, HomeViewModel viewModel) {
        this.foodList = foodList;
        this.mcontext = mcontext;
        this.viewModel = viewModel;

    }

    public class FoodViewHolder extends RecyclerView.ViewHolder{
        private HomeRowLayoutBinding binding;

        public FoodViewHolder(HomeRowLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        HomeRowLayoutBinding binding = HomeRowLayoutBinding.inflate(LayoutInflater.from(mcontext),parent,false);
        return new FoodViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {

        Food food = foodList.get(position);
        HomeRowLayoutBinding binding = holder.binding;

        binding.tvFoodName.setText(food.getFoodName());
        binding.tvFoodPrice.setText(food.getFoodPrice() + " ₺");

        ImageLoaderHelper.loadImage(holder.itemView.getContext(),binding.ivFoodImage,food.getImageName());

        binding.cardviewFoodHome.setOnClickListener(v -> {
            Listeners.homeToDetail(food,v);
        });




    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }
}
