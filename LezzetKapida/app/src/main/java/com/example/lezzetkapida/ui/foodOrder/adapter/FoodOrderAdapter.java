package com.example.lezzetkapida.ui.foodOrder.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lezzetkapida.R;
import com.example.lezzetkapida.data.entity.FoodBasket;
import com.example.lezzetkapida.databinding.FoodOrderRowLayoutBinding;
import com.example.lezzetkapida.ui.foodOrder.FoodOrderFragment;
import com.example.lezzetkapida.ui.foodOrder.viewModel.FoodOrderViewModel;
import com.example.lezzetkapida.utils.FoodBasketUtils;
import com.example.lezzetkapida.utils.ImageLoaderHelper;

import java.util.List;

public class FoodOrderAdapter extends RecyclerView.Adapter<FoodOrderAdapter.FoodBasketViewHolder> {

    private Context mcontext;
    private FoodOrderViewModel viewModel;

    private List<FoodBasket> foodBasketList;

    private FoodOrderFragment fragment;

    public FoodOrderAdapter(Context mcontext, FoodOrderViewModel viewModel, List<FoodBasket> foodBasketList, FoodOrderFragment fragment) {
        this.mcontext = mcontext;
        this.viewModel = viewModel;
        this.foodBasketList = foodBasketList;
        this.fragment = fragment;
    }

    public void updateBasketList(List<FoodBasket> newFoodBasket) {
        this.foodBasketList = newFoodBasket;
        notifyDataSetChanged();
    }

    public class FoodBasketViewHolder extends RecyclerView.ViewHolder {
        private FoodOrderRowLayoutBinding binding;

        public FoodBasketViewHolder(FoodOrderRowLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @NonNull
    @Override
    public FoodBasketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FoodOrderRowLayoutBinding binding = FoodOrderRowLayoutBinding.inflate(LayoutInflater.from(mcontext), parent, false);
        return new FoodBasketViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodBasketViewHolder holder, int position) {

        FoodBasket foodBasket = foodBasketList.get(position);
        FoodOrderRowLayoutBinding binding = holder.binding;

        ImageLoaderHelper.loadImage(mcontext, binding.ivFoodBasketFoodImage, foodBasket.getFoodImageName());

        binding.tvBasketFoodPrice.setText(Integer.toString(foodBasket.getFoodPrice() * foodBasket.getFoodOrderQuantity()) + " ₺");


        int foodCount = FoodBasketUtils.getItem().basketFoodCount(foodBasket.getFoodName());
        Log.e("sayı", String.valueOf(foodCount));
        binding.tvFoodCount.setText(String.valueOf(foodCount));


        binding.tvFoodBasketName.setText(foodBasket.getFoodName());

        binding.ivBasketDelete.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(mcontext);
            LayoutInflater inflater = LayoutInflater.from(mcontext);

            View dialogView = inflater.inflate(R.layout.custom_alert_dialog, null);
            TextView textViewMessage = dialogView.findViewById(R.id.textViewMessage);
            TextView textViewPositive = dialogView.findViewById(R.id.buttonPositive);
            TextView textViewNegative = dialogView.findViewById(R.id.buttonNegative);

            textViewMessage.setTextColor(ContextCompat.getColor(mcontext, R.color.colorTextSecondary));
            textViewPositive.setTextColor(ContextCompat.getColor(mcontext, R.color.colorTextSecondary));
            textViewNegative.setTextColor(ContextCompat.getColor(mcontext, R.color.colorTextSecondary));


            AlertDialog alertDialog = builder.setView(dialogView).show();

            textViewPositive.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewModel.deleteFood(foodBasket.getBasketId(), foodBasket.getUserName());
                    foodBasketList.remove(foodBasket);
                    notifyDataSetChanged();
                    alertDialog.dismiss();
                }
            });
            textViewNegative.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    alertDialog.dismiss();
                }
            });
        });
    }


        @Override
    public int getItemCount() {
        return foodBasketList.size();
    }

    public void setList(List<FoodBasket> list) {
        this.foodBasketList = list;
        notifyDataSetChanged();
    }


}
