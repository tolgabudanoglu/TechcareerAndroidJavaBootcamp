package com.example.spotifycloneodev6.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spotifycloneodev6.data.entity.PlayListContiune;
import com.example.spotifycloneodev6.databinding.PlaylistContiuneDesignBinding;

import java.util.List;

public class PlayListContiuneAdapter extends RecyclerView.Adapter<PlayListContiuneAdapter.PlayListContiuneDesignHolder> {

    private List<PlayListContiune> playListContiuneList;
    private Context mContext;

    public PlayListContiuneAdapter(List<PlayListContiune> playListContiuneList, Context mContext) {
        this.playListContiuneList = playListContiuneList;
        this.mContext = mContext;
    }



    public class PlayListContiuneDesignHolder extends RecyclerView.ViewHolder{

        private PlaylistContiuneDesignBinding binding;

        public PlayListContiuneDesignHolder( PlaylistContiuneDesignBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @NonNull
    @Override
    public PlayListContiuneDesignHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PlaylistContiuneDesignBinding binding = PlaylistContiuneDesignBinding.inflate(LayoutInflater.from(mContext),parent,false);
        return new PlayListContiuneDesignHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayListContiuneDesignHolder holder, int position) {

        PlayListContiune playList = playListContiuneList.get(position);
        PlaylistContiuneDesignBinding binding = holder.binding;

        binding.ivPlayList.setImageResource(mContext.getResources().getIdentifier(playList.getPlayListImage(), "drawable",mContext.getPackageName()));
        binding.tvTittle.setText(playList.getPlayListName());
        binding.textViewExp.setText(playList.getPlayListExplanation());



    }

    @Override
    public int getItemCount() {
        return playListContiuneList.size();
    }
}

