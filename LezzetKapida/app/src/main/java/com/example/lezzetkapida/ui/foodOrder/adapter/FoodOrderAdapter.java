package com.example.lezzetkapida.ui.foodOrder.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lezzetkapida.R;
import com.example.lezzetkapida.data.entity.Food;
import com.example.lezzetkapida.data.entity.FoodBasket;
import com.example.lezzetkapida.databinding.FoodOrderRowLayoutBinding;
import com.example.lezzetkapida.databinding.HomeRowLayoutBinding;
import com.example.lezzetkapida.ui.home.adapter.HomeAdapter;
import com.example.lezzetkapida.ui.viewModel.FoodOrderViewModel;
import com.example.lezzetkapida.ui.viewModel.HomeViewModel;
import com.example.lezzetkapida.utils.ImageLoaderHelper;

import java.util.List;

public class FoodOrderAdapter extends RecyclerView.Adapter<FoodOrderAdapter.FoodBasketViewHolder> {

    private Context mcontext;
    private FoodOrderViewModel viewModel;

    private List<FoodBasket> foodBasketList;

    public FoodOrderAdapter(Context mcontext, FoodOrderViewModel viewModel, List<FoodBasket> foodBasketList) {
        this.mcontext = mcontext;
        this.viewModel = viewModel;
        this.foodBasketList = foodBasketList;
    }
    public class FoodBasketViewHolder extends RecyclerView.ViewHolder{
        private FoodOrderRowLayoutBinding binding;

        public FoodBasketViewHolder(FoodOrderRowLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @NonNull
    @Override
    public FoodBasketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FoodOrderRowLayoutBinding binding = FoodOrderRowLayoutBinding.inflate(LayoutInflater.from(mcontext),parent,false);
        return new FoodBasketViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodBasketViewHolder holder, int position) {

        FoodBasket foodBasket = foodBasketList.get(position);
        FoodOrderRowLayoutBinding binding = holder.binding;

        ImageLoaderHelper.loadImage(mcontext,binding.ivFoodBasketFoodImage,foodBasket.getFoodImageName());

        binding.tvBasketFoodPrice.setText(Integer.toString(foodBasket.getFoodPrice()));

        binding.tvFoodBasketName.setText(foodBasket.getFoodName());

        binding.ivBasketDelete.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(mcontext);
            builder.setMessage("Emin misiniz?")
                    .setPositiveButton("Sil", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            viewModel.deleteFood(foodBasket.getBasketId(),foodBasket.getUserName());
                        }
                    })
                    .setNegativeButton("Kapat", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
                    .show();
        });


    }

    @Override
    public int getItemCount() {
        return foodBasketList.size();
    }
}
